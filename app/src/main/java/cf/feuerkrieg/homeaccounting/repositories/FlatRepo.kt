package cf.feuerkrieg.homeaccounting.repositories

import androidx.lifecycle.MutableLiveData
import cf.feuerkrieg.homeaccounting.HomeApplication
import cf.feuerkrieg.homeaccounting.models.Flat
import cf.feuerkrieg.homeaccounting.network.flatApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlatRepo {

    private val appContext = HomeApplication.getAppContext()

    val flats = MutableLiveData<List<Flat>>()

    suspend fun refreshFlats(homeId: Int) {

        return withContext(Dispatchers.IO) {
            flats.postValue(flatApiService.getFlatsByHome(homeId))
        }
    }

}