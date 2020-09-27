package cf.feuerkrieg.homeaccounting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cf.feuerkrieg.homeaccounting.database.models.RedevelopmentDatabase

@Dao
interface RedevelopmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: Iterable<RedevelopmentDatabase>)

    @Query("Select * from redevelopment")
    fun getAll(): LiveData<List<RedevelopmentDatabase>>
}