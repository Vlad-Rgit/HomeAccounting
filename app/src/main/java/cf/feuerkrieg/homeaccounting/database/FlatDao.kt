package cf.feuerkrieg.homeaccounting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cf.feuerkrieg.homeaccounting.database.models.FlatDatabase

@Dao
interface FlatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(flats: Iterable<FlatDatabase>)

    @Query("Select * from flat")
    fun getAll(): LiveData<List<FlatDatabase>>
}