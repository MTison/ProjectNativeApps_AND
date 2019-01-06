package com.example.matthiastison.emotionsapplication

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.matthiastison.emotionsapplication.TestUtil.getValue
import com.example.matthiastison.emotionsapplication.Database.DAO.ThemeDao
import com.example.matthiastison.emotionsapplication.Database.EmotionsAppDb
import com.example.matthiastison.emotionsapplication.Database.Entities.ThemeEntity
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var themeDao: ThemeDao
    private lateinit var db: EmotionsAppDb

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, EmotionsAppDb::class.java).build()
        themeDao = db.themeDao()
    }

    @Test
    fun insertWords_areInserted() {
        themeDao.insert(ThemeEntity("WONEN",R.color.themeColor1, "1"))
        themeDao.insert(ThemeEntity("SOCIAAL",R.color.themeColor1, "2"))

        assertEquals(2, getValue(themeDao.getAllThemes()).size)
        assert(
                getValue(themeDao.getAllThemes())
                        .map(ThemeEntity::title)
                        .containsAll(listOf("WONEN", "SOCIAAL"))
        )
    }

    @Test
    fun deleteAll_empty() {
        val theme = ThemeEntity("WONEN",R.color.themeColor1, "1")
        themeDao.insert(theme)

        themeDao.delete(theme)

        assertEquals(0, getValue(themeDao.getAllThemes()).size)
    }
}