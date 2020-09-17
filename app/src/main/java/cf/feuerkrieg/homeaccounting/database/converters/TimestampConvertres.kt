package cf.feuerkrieg.homeaccounting.database.converters

import androidx.room.TypeConverter
import java.sql.Timestamp

class TimestampConvertres {
    @TypeConverter
    fun fromMillis(value: Long?): Timestamp? {
        return value?.let { Timestamp(value) }
    }

    @TypeConverter
    fun fromTimestamp(timestamp: Timestamp?): Long? {
        return timestamp?.let { timestamp.time }
    }

}