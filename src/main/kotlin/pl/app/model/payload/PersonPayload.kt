package pl.app.model.payload

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PersonPayload(

    @JsonProperty("publicId")
    val publicId :String?,

    @JsonProperty("name")
    val name :String,

    @JsonProperty("tickets")
    val tickets :Set<TicketPayload>?
)