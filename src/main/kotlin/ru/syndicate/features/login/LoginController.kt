package ru.syndicate.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import ru.syndicate.database.account.Account
import ru.syndicate.database.tokens.TokenDTO
import ru.syndicate.database.tokens.Tokens
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {

        val email = call.request.queryParameters["email"]
        val password = call.request.queryParameters["password"]

        val userDTO = Account.fetchAccount(email!!)

        if (userDTO == null)
            call.respond(HttpStatusCode.BadRequest, "User not found")
        else {
            if (userDTO.password == password) {
                val token = UUID.randomUUID().toString()

                Tokens.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        email = email,
                        token = token
                    )
                )

                call.respond(
                    LoginResponseRemote(
                        token = token
                    )
                )
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }
}