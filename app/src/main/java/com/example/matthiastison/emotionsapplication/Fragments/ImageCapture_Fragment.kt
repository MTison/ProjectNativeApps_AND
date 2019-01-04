package com.example.matthiastison.emotionsapplication.Fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.matthiastison.emotionsapplication.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_imagecapture.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class ImageCapture_Fragment : Fragment() {

    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // '?.' is safe asserted call on nullable receiver
    // '!!.' is non-null asserted call on nullable receiver
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_imagecapture,container,false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_CaptureImage.setOnClickListener {
            // intent for taking an image with the provided camera
            val captureImageIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            checkForCompatibility(captureImageIntent, CAP_IMAGE)
            hideLayouts(true)
        }

        btn_UploadImage.setOnClickListener {
            // intent for taking an image with the provided camera
            val uploadImageIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            uploadImageIntent.type = "image/*"
            startActivityForResult(uploadImageIntent, UP_IMAGE)
            hideLayouts(true)
        }

        btn_SaveSubject.setOnClickListener {

        }

        btn_ClearAll.setOnClickListener {
            newSubjectImage.setImageResource(android.R.color.transparent)
            edtView_SubjectTitle.setText("")
            edtView_SubjectDate.setText("")
            edtView_SubjectDescription.setText("")
            hideLayouts(false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            CAP_IMAGE -> {
                when(resultCode) {
                    Activity.RESULT_OK -> Picasso.with(activity!!).load(imageUri).fit().into(newSubjectImage)
                    Activity.RESULT_CANCELED -> hideLayouts(false)
                }
            }
            UP_IMAGE -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        imageUri = data!!.data
                        Picasso.with(activity!!).load(imageUri).fit().into(newSubjectImage)
                    }
                    Activity.RESULT_CANCELED -> hideLayouts(false)
                }
            }
        }
    }

    private fun checkForCompatibility (intent: Intent, requestCode: Int? = null) {
        // Check whether an activity exists to start with the provided intent
        val manager = activity!!.packageManager
        val name = intent.resolveActivity(manager)

        if (name == null) {
            Toast.makeText(activity, " Could not find the add image activity ", Toast.LENGTH_LONG ).show()
        } else {
            if (requestCode == null)
                startActivity(intent)
            else {
                openCamera(intent)
            }
        }
    }

    private fun openCamera(intent: Intent) {
        var imageFile: File? = null

        try {
            imageFile = createImageFile()
        } catch (ex: IOException) {
            Log.i("Temp file err", ex.localizedMessage)
        }

        if (imageFile != null) {
            // getting the uri to the self made File for the picture that was taken, this through a File provider
            imageUri = FileProvider.getUriForFile(activity!!,
                    "com.example.android.fileprovider", imageFile)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

            // checking for permission granting on different build versions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            } else {
                val resInfoList = activity!!.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
                for (resolveInfo in resInfoList) {
                    val packageName = resolveInfo.activityInfo.packageName
                    activity!!.grantUriPermission(packageName, imageUri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
            }

            startActivityForResult(intent, CAP_IMAGE)
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        //Making a unique filename with current date and time, creating collision-resistant file
        val time = SimpleDateFormat("ddMMyyyy_HHmmss").format(Date())
        val fileName = "JPEG_" + time + "_"

        val fileDir = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        //Creating the file for the picture in the fileDir, with fitting prefix and suffix
        val imageFile = File.createTempFile(fileName, ".jpg", fileDir)

        // photoPath = imageFile.absolutePath
        return imageFile
    }

    private fun hideLayouts(hide: Boolean) {
        when(hide) {
            true -> newSubject_layout.visibility = View.INVISIBLE
            false -> newSubject_layout.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val CAP_IMAGE = 1
        private const val UP_IMAGE = 2
    }
}