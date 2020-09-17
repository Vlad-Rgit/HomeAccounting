package cf.feuerkrieg.homeaccounting.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cf.feuerkrieg.homeaccounting.HomeApplication
import cf.feuerkrieg.homeaccounting.R
import cf.feuerkrieg.homeaccounting.repositories.FlatRepo
import kotlinx.coroutines.launch
import java.io.IOException

class FlatListViewModel(private val homeId: Int): ViewModel() {

    private val flatRepo = FlatRepo()
    private val appContext = HomeApplication.getAppContext()

    val flats = flatRepo.flats

    val syncingErrorText = MutableLiveData<String>()

    init {
        refreshFlats()
    }


    fun refreshFlats() {
        viewModelScope.launch {
            try {
                flatRepo.refreshFlats(homeId)
            }
            catch (ex: IOException) {
                 syncingErrorText.postValue(
                     appContext.getString(R.string.syncing_error)
                 )
            }
        }
    }




}