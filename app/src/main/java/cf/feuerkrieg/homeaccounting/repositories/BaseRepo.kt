package cf.feuerkrieg.homeaccounting.repositories

import cf.feuerkrieg.homeaccounting.HomeApplication
import cf.feuerkrieg.homeaccounting.database.HomeDatabase

abstract class BaseRepo {
    protected val context = HomeApplication.getAppContext()
    protected val database = HomeDatabase.getInstance(context)


    abstract suspend fun refreshItems()
}