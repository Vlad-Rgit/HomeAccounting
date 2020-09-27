package cf.feuerkrieg.homeaccounting.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cf.feuerkrieg.homeaccounting.models.DraftVentilation
import cf.feuerkrieg.homeaccounting.network.draftVentilationApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DraftVentilationRepo: BaseRepo() {

    val draftVentilations: LiveData<List<DraftVentilation>>
        = Transformations.map(database.draftVentilationDao.getAll()) {
        it.map { it.asModel() }
    }

    override suspend fun refreshItems() {
        withContext(Dispatchers.IO) {
            val draftVentilations = draftVentilationApiService.getAll()
            database.draftVentilationDao.insertAll(
                draftVentilations.map { it.asDatabase() }
            )
        }
    }
}