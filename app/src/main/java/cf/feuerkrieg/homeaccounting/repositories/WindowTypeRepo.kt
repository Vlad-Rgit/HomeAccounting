package cf.feuerkrieg.homeaccounting.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cf.feuerkrieg.homeaccounting.HomeApplication
import cf.feuerkrieg.homeaccounting.database.HomeDatabase
import cf.feuerkrieg.homeaccounting.models.WindowType
import cf.feuerkrieg.homeaccounting.models.asDatabase
import cf.feuerkrieg.homeaccounting.network.windowTypeApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WindowTypeRepo {

    private val context = HomeApplication.getAppContext()
    private val database = HomeDatabase.getInstance(context)

    val windowTypes: LiveData<List<WindowType>> =
        Transformations.map(database.windowTypeDao.getAll()) {
        it.map {
            WindowType(
                windowTypeId = it.windowTypeId,
                windowType1 = it.windowType)}
    }

    suspend fun refreshWindowTypes() {
        withContext(Dispatchers.IO) {
            val windowTypes = windowTypeApiService.getWindowTypes()
            database.windowTypeDao.insertAll(windowTypes.asDatabase())
        }
    }

}