package cf.feuerkrieg.homeaccounting

import android.app.Application
import android.content.Context

class HomeApplication: Application() {

    companion object {
        lateinit var instance: HomeApplication

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }


    override fun onCreate() {
        instance = this
        super.onCreate()
    }

}