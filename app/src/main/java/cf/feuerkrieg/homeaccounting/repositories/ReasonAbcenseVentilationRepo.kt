package cf.feuerkrieg.homeaccounting.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cf.feuerkrieg.homeaccounting.models.ReasonAbcenseVentilation
import cf.feuerkrieg.homeaccounting.network.reasonAbcenseVentilationApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReasonAbcenseVentilationRepo: BaseRepo() {

    val reasonAbcenseVentilations: LiveData<List<ReasonAbcenseVentilation>>
        = Transformations.map(database.reasonAbcenseVentilationDao.getAll()) {
        it.map { it.asModel() }
    }

    override suspend fun refreshItems() {

        withContext(Dispatchers.IO) {

            val reasons = reasonAbcenseVentilationApiService.getAll()

            database.reasonAbcenseVentilationDao
                .insertAll(reasons.map { it.asDatabase() })
        }
    }
}