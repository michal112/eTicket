package pl.app.model.entity

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "TICKET")
class TicketEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id :Long = 0,

    @Column(name = "PUBLIC_ID")
    var publicId :String,

    @Column(name = "PRICE")
    var price :Int,

    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.DATE)
    var timestamp :Date,

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    var type :TicketType
) : Serializable {

    @ManyToOne
    @JoinColumn(name = "PERSON_PUBLIC_ID", referencedColumnName = "PUBLIC_ID")
    lateinit var person :PersonEntity
}