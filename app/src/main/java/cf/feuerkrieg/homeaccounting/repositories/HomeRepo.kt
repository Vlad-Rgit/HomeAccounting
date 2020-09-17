package cf.feuerkrieg.homeaccounting.repositories

import cf.feuerkrieg.homeaccounting.HomeApplication
import cf.feuerkrieg.homeaccounting.database.HomeDatabase
import cf.feuerkrieg.homeaccounting.network.homeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepo {

    private val localDatabase =
        HomeDatabase.getInstance(HomeApplication.getAppContext())

    val homes = localDatabase.homeDao.getAll()

    suspend fun refreshHomes() {
        withContext(Dispatchers.IO) {
            val result = homeApiService.getHomes()
            localDatabase.homeDao.insertAll(result)
        }
    }

}