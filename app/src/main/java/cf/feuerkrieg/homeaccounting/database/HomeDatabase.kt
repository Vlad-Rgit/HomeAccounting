package cf.feuerkrieg.homeaccounting.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cf.feuerkrieg.homeaccounting.database.converters.TimestampConvertres
import cf.feuerkrieg.homeaccounting.database.models.*
import cf.feuerkrieg.homeaccounting.models.Home

@Database(entities = [
    Home::class,
    FlatDatabase::class,
    WindowTypeDatabase::class,
    RedevelopmentDatabase::class,
    AccessToVentilationDatabase::class,
    ReasonAbcenseVentilationDatabase::class,
    DraftVentilationDatabase::class],
    version = 8,
    exportSchema = false)
@TypeConverters(TimestampConvertres::class)
abstract class HomeDatabase: RoomDatabase() {

    abstract val homeDao: HomeDao
    abstract val flatDao: FlatDao
    abstract val windowTypeDao: WindowTypeDao
    abstract val redevelopmentDao: RedevelopmentDao
    abstract val accessToVentilationDao: AccessToVentilationDao
    abstract val reasonAbcenseVentilationDao: ReasonAbcenseVentilationDao
    abstract val draftVentilationDao: DraftVentilationDao

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