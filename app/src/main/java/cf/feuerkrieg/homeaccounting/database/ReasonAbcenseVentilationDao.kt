package cf.feuerkrieg.homeaccounting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cf.feuerkrieg.homeaccounting.database.models.ReasonAbcenseVentilationDatabase

@Dao
interface ReasonAbcenseVentilationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: Iterable<ReasonAbcenseVentilationDatabase>)

    @Query("Select * from reason_abcense_ventilation")
    fun getAll(): LiveData<List<ReasonAbcenseVentilationDatabase>>
}