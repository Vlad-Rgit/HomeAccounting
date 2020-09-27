package cf.feuerkrieg.homeaccounting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cf.feuerkrieg.homeaccounting.database.models.WindowTypeDatabase

@Dao
interface WindowTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(windowTypes: Iterable<WindowTypeDatabase>)

    @Query("Select * from window_type")
    fun getAll(): LiveData<List<WindowTypeDatabase>>
}