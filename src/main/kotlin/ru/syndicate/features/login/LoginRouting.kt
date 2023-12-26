package ru.syndicate.features.login

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {

    routing {
        post("/api/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}
