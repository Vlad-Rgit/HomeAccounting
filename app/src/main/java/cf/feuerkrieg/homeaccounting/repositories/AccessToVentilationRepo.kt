package cf.feuerkrieg.homeaccounting.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cf.feuerkrieg.homeaccounting.models.AccessToVentilation
import cf.feuerkrieg.homeaccounting.network.accessToVentilationApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccessToVentilationRepo: BaseRepo() {

    val accessToVentilations: LiveData<List<AccessToVentilation>>
        = Transformations.map(database.accessToVentilationDao.getAll()) {
        it.map { it.asModel() }
    }

    override suspend fun refreshItems() {
        withContext(Dispatchers.IO) {

            val accessToVentilations
                    = accessToVentilationApiService.getAll()

            database.accessToVentilationDao
                .insertAll(accessToVentilations.map {it.asDatabase()})
        }
    }
}