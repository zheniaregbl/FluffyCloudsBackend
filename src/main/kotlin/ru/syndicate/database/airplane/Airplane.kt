package ru.syndicate.database.airplane

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.syndicate.database.airplane_model.AirplaneModel
import ru.syndicate.database.airplane_seat.AirplaneSeat

object Airplane : Table() {

    private val airplaneId = Airplane.integer("id")
    private val modelId = Airplane.integer("model")

    fun fetchAll(): List<AirplaneDTO> {
        return try {
            transaction {
                Airplane
                    .innerJoin(AirplaneModel, { modelId }, { id })
                    .innerJoin(AirplaneSeat, { airplaneId }, { idAirplane })
                    .slice(airplaneId, AirplaneModel.modelName, AirplaneSeat.amount.sum())
                    .selectAll()
                    .groupBy(airplaneId, AirplaneModel.modelName)
                    .map {
                        AirplaneDTO(
                            airplaneId = it[airplaneId],
                            model = it[AirplaneModel.modelName],
                            amout_seat = it[AirplaneSeat.amount.sum()] ?: 0
                        )
                    }
            }
        } catch (e: Exception) {
            println("--------------------------- ${e.message}")
            emptyList()
        }
    }

    fun fetchWithFilter(model: String): List<AirplaneDTO> {
        return try {
            transaction {
                Airplane
                    .innerJoin(AirplaneModel, { modelId }, { id })
                    .innerJoin(AirplaneSeat, { airplaneId }, { idAirplane })
                    .slice(airplaneId, AirplaneModel.modelName, AirplaneSeat.amount.sum())
                    .select( where = { AirplaneModel.modelName eq model } )
                    .groupBy(airplaneId, AirplaneModel.modelName)
                    .map {
                        AirplaneDTO(
                            airplaneId = it[airplaneId],
                            model = it[AirplaneModel.modelName],
                            amout_seat = it[AirplaneSeat.amount.sum()] ?: 0
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun fetchWithSearch(searchText: String): List<AirplaneDTO> {
        return try {
            transaction {
                Airplane
                    .innerJoin(AirplaneModel, { modelId }, { id })
                    .innerJoin(AirplaneSeat, { airplaneId }, { idAirplane })
                    .slice(airplaneId, AirplaneModel.modelName, AirplaneSeat.amount.sum())
                    .select( where = { AirplaneModel.modelName like "$searchText%" } )
                    .groupBy(airplaneId, AirplaneModel.modelName)
                    .map {
                        AirplaneDTO(
                            airplaneId = it[airplaneId],
                            model = it[AirplaneModel.modelName],
                            amout_seat = it[AirplaneSeat.amount.sum()] ?: 0
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}