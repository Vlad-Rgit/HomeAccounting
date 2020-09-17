package cf.feuerkrieg.homeaccounting.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cf.feuerkrieg.homeaccounting.HomeApplication
import cf.feuerkrieg.homeaccounting.R
import cf.feuerkrieg.homeaccounting.repositories.HomeRepo
import kotlinx.coroutines.launch
import java.io.IOException

class HomeListViewModel: ViewModel() {

    private val homeRepo = HomeRepo()
    private val appContext = HomeApplication.getAppContext()

    val homes = homeRepo.homes

    val isSyncing = MutableLiveData<Boolean>(false)
    val syncingText = MutableLiveData<String>(appContext
        .getString(R.string.syncing))
    val hasSyncingError = MutableLiveData<Boolean>(false)

    init {
        refreshHomes()
    }

    fun refreshHomes() {
        viewModelScope.launch {

            isSyncing.postValue(true)
            syncingText.postValue(appContext
                .getString(R.string.syncing))
            hasSyncingError.postValue(false)

            try {
                homeRepo.refreshHomes()
                isSyncing.postValue(false)
            }
            catch (ex: IOException) {
                syncingText.postValue(appContext
                    .getString(R.string.syncing_error))
                hasSyncingError.postValue(true)
            }
        }
    }
}