package org.acme

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import jakarta.persistence.Entity
import java.time.LocalDate

enum class Status {
    Alive,
    Deceased
}

@Entity
class Person : PanacheEntity() {
    lateinit var name: String
    lateinit var birth: LocalDate
    lateinit var status: Status

    companion object : PanacheCompanion<Person> {
        fun findByName(name: String) = find("name", name).firstResult()
        fun findAlive() = list("status", Status.Alive)
        fun deleteStefs() = delete("name", "Stef")
    }
}
