package ir.programmerplus.movies

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
open class Application : Application() {

    /**
     * Initializes the application and sets up exception handling and logging.
     */
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

}