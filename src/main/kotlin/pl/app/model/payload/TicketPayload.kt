package pl.app.model.payload

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class TicketPayload(

    @JsonProperty("publicId")
    val publicId :String?,

    @JsonProperty("price")
    val price :Int,

    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val timestamp :Date,

    @JsonProperty("type")
    val type :TicketTypePayload
)