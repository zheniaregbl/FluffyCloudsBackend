package ru.syndicate.features.airplane

import io.ktor.server.application.*
import io.ktor.server.response.*
import ru.syndicate.database.airplane.Airplane

class AirplaneController(private val call: ApplicationCall) {

    suspend fun performAirplane() {
        val filterModel = call.request.queryParameters["model"]

        if (filterModel.isNullOrBlank())
            call.respond(Airplane.fetchAll())
        else
            call.respond(Airplane.fetchWithFilter(filterModel))
    }

    suspend fun searchAirplaneByModel() {
        val searchText = call.request.queryParameters["model"]

        if (searchText.isNullOrBlank())
            call.respond(Airplane.fetchAll())
        else
            call.respond(Airplane.fetchWithSearch(searchText))
    }
}