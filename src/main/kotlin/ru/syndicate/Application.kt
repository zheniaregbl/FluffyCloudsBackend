package ru.syndicate

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database
import ru.syndicate.features.airplane.configureAirplaneRouting
import ru.syndicate.features.login.configureLoginRouting
import ru.syndicate.features.register.configureRegisterRouting
import ru.syndicate.plugins.*

fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/fluffyclouds",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "234711"
    )

    embeddedServer(
        CIO, port = 8080,
        host = "0.0.0.0",
        module = Application::module
    )
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureRegisterRouting()
    configureLoginRouting()
    configureAirplaneRouting()
}
