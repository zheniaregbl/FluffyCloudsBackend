package ru.syndicate.database.airplane_seat

import org.jetbrains.exposed.sql.Table

object AirplaneSeat: Table("airplaneseat") {

    val id = AirplaneSeat.integer("id")
    val idAirplane = AirplaneSeat.integer("idairplane")
    val idClassType = AirplaneSeat.integer("idclasstype")
    val amount = AirplaneSeat.integer("amountseat")
}