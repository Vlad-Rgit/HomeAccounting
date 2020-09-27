package cf.feuerkrieg.homeaccounting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cf.feuerkrieg.homeaccounting.database.models.DraftVentilationDatabase

@Dao
interface DraftVentilationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: Iterable<DraftVentilationDatabase>)

    @Query("Select * from draft_ventilation")
    fun getAll(): LiveData<List<DraftVentilationDatabase>>

}