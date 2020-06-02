package pl.app.model.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "PERSON")
class PersonEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id :Long = 0,

    @Column(name = "PUBLIC_ID")
    var publicId :String,

    @Column(name = "NAME")
    var name :String,

    @OneToMany(mappedBy = "person", cascade = [CascadeType.ALL])
    var tickets :MutableSet<TicketEntity>?
) : Serializable