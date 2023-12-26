package ru.syndicate.database.airplane_model

import org.jetbrains.exposed.sql.Table

object AirplaneModel: Table("airplanemodel") {

    val id = AirplaneModel.integer("id")
    val modelName = AirplaneModel.varchar("modelname", 40)
}