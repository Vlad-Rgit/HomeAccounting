package cf.feuerkrieg.homeaccounting.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cf.feuerkrieg.homeaccounting.models.Redevelopment
import cf.feuerkrieg.homeaccounting.models.asDatabase
import cf.feuerkrieg.homeaccounting.network.redevelopmentApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RedevelopmentRepo: BaseRepo() {

    val redevelopments: LiveData<List<Redevelopment>> = Transformations
        .map(database.redevelopmentDao.getAll()) {
        it.map {
            Redevelopment(
                redevelopmentId = it.redevelopmentId,
                redevelopment1 = it.redevelopment
            )
        }
    }

    override suspend fun refreshItems() {
        withContext(Dispatchers.IO) {
            val redevelopments = redevelopmentApiService.getAll()
            database.redevelopmentDao.insertAll(redevelopments.asDatabase())
        }
    }

}