package com.mmaquera.odoo.shopping.data.serialization

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = StringOrBooleanSerializer::class)
data class StringOrBoolean(val value: String)

object StringOrBooleanSerializer : KSerializer<StringOrBoolean> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("StringOrBoolean", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: StringOrBoolean) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder): StringOrBoolean {
        return try {
            // Intentar deserializar como String
            StringOrBoolean(decoder.decodeString())
        } catch (e: Exception) {
            try {
                // Intentar deserializar como Boolean
                val booleanValue = decoder.decodeBoolean()
                StringOrBoolean(if (booleanValue) "true" else "false")
            } catch (e: Exception) {
                // Si falla, devolver una cadena vacía
                StringOrBoolean("")
            }
        }
    }
}