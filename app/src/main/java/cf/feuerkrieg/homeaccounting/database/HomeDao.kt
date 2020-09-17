package cf.feuerkrieg.homeaccounting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cf.feuerkrieg.homeaccounting.models.Home

@Dao
interface HomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(homes: Iterable<Home>)

    @Query("Select * from home")
    fun getAll(): LiveData<List<Home>>
}