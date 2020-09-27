package cf.feuerkrieg.homeaccounting.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cf.feuerkrieg.homeaccounting.models.Home
import cf.feuerkrieg.homeaccounting.viewmodels.FlatListViewModel

class FlatListViewModelFactory(private val home: Home)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(FlatListViewModel::class.java.isAssignableFrom(modelClass)) {
            return FlatListViewModel(home) as T
        }
        else {
            throw IllegalArgumentException("FlatListViewModel is " +
                    "not assignable from ${modelClass.name}")
        }
    }

}