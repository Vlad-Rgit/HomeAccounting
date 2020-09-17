package cf.feuerkrieg.homeaccounting.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cf.feuerkrieg.homeaccounting.database.converters.TimestampConvertres
import cf.feuerkrieg.homeaccounting.database.models.FlatDatabase
import cf.feuerkrieg.homeaccounting.models.Home

@Database(entities = [
    Home::class,
    FlatDatabase::class],
    version = 3,
    exportSchema = false)
@TypeConverters(TimestampConvertres::class)
abstract class HomeDatabase: RoomDatabase() {

    abstract val homeDao: HomeDao
    abstract val flatDao: FlatDao

    companion object {

        private lateinit var INSTANCE: HomeDatabase

        fun getInstance(context: Context): HomeDatabase {

            synchronized(HomeDatabase::class.java) {

                if (!::INSTANCE.isInitialized) {

                    INSTANCE = Room.databaseBuilder(
                        context,
                        HomeDatabase::class.java,
                        "homeDB"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }

                return INSTANCE
            }
        }
    }
}