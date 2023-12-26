package ru.syndicate.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import ru.syndicate.database.account.Account
import ru.syndicate.database.account.AccountDTO
import ru.syndicate.database.tokens.TokenDTO
import ru.syndicate.database.tokens.Tokens
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {

        val email = call.request.queryParameters["email"]
        val password = call.request.queryParameters["password"]

        val accountDTO = Account.fetchAccount(email!!)

        if (accountDTO != null) {
            call.respond(HttpStatusCode.Conflict, "user already exists")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Account.insert(
                    AccountDTO(
                        email = email,
                        password = password!!
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "user already exists")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Can't create user ${e.message}")
            }

            Tokens.insert(
                TokenDTO(
                    id = UUID.randomUUID().toString(),
                    email = email,
                    token = token
                )
            )

            call.respond(
                RegisterResponseRemote(
                token = token
            )
            )
        }
    }
}