package com.example.matthiastison.emotionsapplication.Database

import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()


// Utility method to run blocks on a dedicated background thread, used for io/database work

fun ioThread(task : () -> Unit) {
    IO_EXECUTOR.execute(task)
}