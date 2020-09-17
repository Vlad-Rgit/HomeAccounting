package cf.feuerkrieg.homeaccounting.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.sql.Timestamp
import java.text.SimpleDateFormat

class TimestampSerializer : KSerializer<Timestamp> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Timestamp", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Timestamp {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val date = formatter.parse(decoder.decodeString())
        return Timestamp(date.time)
    }

    override fun serialize(encoder: Encoder, value: Timestamp) {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val isoDate = formatter.format(value)
        encoder.encodeString(isoDate)
    }

}