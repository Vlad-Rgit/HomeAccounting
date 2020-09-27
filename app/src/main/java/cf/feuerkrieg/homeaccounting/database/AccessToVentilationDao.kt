package cf.feuerkrieg.homeaccounting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cf.feuerkrieg.homeaccounting.database.models.AccessToVentilationDatabase

@Dao
interface AccessToVentilationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: Iterable<AccessToVentilationDatabase>)

    @Query("Select * from access_to_ventilation")
    fun getAll(): LiveData<List<AccessToVentilationDatabase>>

}