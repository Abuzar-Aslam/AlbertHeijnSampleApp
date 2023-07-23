package com.example.albertheijnsampleapp.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Entry point of the Albert Heijn Sample app.
 *
 * This class initiates the application and sets up Hilt for dependency injection.
 * By annotating with @HiltAndroidApp, Hilt's code generation is triggered.
 * This generates a parent class for the application acting as the application-level dependency container.
 * An associated Hilt component is also generated.
 * The "keys" library is loaded upon creation to handle API key retrieval stored securely using NDK.
 *
 * @see Application
 * @see HiltAndroidApp
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        System.loadLibrary("keys")
    }
}
