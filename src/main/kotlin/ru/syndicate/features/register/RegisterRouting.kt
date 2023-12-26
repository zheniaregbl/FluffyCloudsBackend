package ru.syndicate.features.register

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRegisterRouting() {

    routing {
        post("/api/register") {

            val registerController = RegisterController(call)
            registerController.registerNewUser()
        }
    }
}