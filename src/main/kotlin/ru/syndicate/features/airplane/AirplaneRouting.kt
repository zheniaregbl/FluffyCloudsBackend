package ru.syndicate.features.airplane

import io.ktor.server.application.*
import io.ktor.server.routing.*
import ru.syndicate.features.login.LoginController

fun Application.configureAirplaneRouting() {

    routing {
        get("/api/airplane") {
            val airplaneController = AirplaneController(call)
            airplaneController.performAirplane()
        }

        get("/api/searchAirplane") {
            val airplaneController = AirplaneController(call)
            airplaneController.searchAirplaneByModel()
        }
    }
}
