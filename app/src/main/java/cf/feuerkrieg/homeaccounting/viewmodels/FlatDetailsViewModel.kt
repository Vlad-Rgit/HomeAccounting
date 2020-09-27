package cf.feuerkrieg.homeaccounting.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cf.feuerkrieg.homeaccounting.models.Flat
import cf.feuerkrieg.homeaccounting.models.Home
import cf.feuerkrieg.homeaccounting.models.Kitchen
import cf.feuerkrieg.homeaccounting.models.Ventilation
import cf.feuerkrieg.homeaccounting.repositories.*
import kotlinx.coroutines.launch

class FlatDetailsViewModel : ViewModel() {

    private val windowTypeRepo = WindowTypeRepo()

    private val redevelopmentRepo = RedevelopmentRepo()

    private val accessToVentilationRepo = AccessToVentilationRepo()

    private val reasonAbcenseVentilationRepo = ReasonAbcenseVentilationRepo()

    private val draftVentilationRepo = DraftVentilationRepo()

    var selectedIndexAccessToVentilation: Int = 0
        set(value) {
            field = value
            showReasonVentilationAbcense.value = field > 0
        }

    val showReasonVentilationAbcense = MutableLiveData<Boolean>()
    lateinit var flat: Flat

    val hasFlatAccess = MutableLiveData<Byte>()

    val windowTypes = windowTypeRepo.windowTypes

    val redevelopments = redevelopmentRepo.redevelopments

    val accessToVentilations = accessToVentilationRepo.accessToVentilations

    val reasonAbcenseVentilations = reasonAbcenseVentilationRepo
        .reasonAbcenseVentilations

    val draftVentilations = draftVentilationRepo.draftVentilations

    init {
        viewModelScope.launch {
            try {
                windowTypeRepo.refreshWindowTypes()
                redevelopmentRepo.refreshItems()
                accessToVentilationRepo.refreshItems()
                reasonAbcenseVentilationRepo.refreshItems()
                draftVentilationRepo.refreshItems()
            }
            catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun setExistingFlat(flat: Flat) {
        this.flat = flat
        hasFlatAccess.value = flat.hasAccess
    }

    fun createFlat(home: Home) {
        flat = Flat(home = home)
        hasFlatAccess.value = 0
    }

    fun initKitchen() {
        if(flat.kitchen == null) {
            flat.kitchen = Kitchen()
            flat.kitchen!!.ventilation = Ventilation()
        }
    }

}