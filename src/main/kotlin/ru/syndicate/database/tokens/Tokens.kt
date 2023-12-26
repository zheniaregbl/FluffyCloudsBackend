package ru.syndicate.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens: Table() {

    private val id = Tokens.varchar("id", 50)
    private val email = Tokens.varchar("email", 30)
    private val token = Tokens.varchar("token", 50)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            insert {
                it[id] = tokenDTO.id
                it[email] = tokenDTO.email
                it[token] = tokenDTO.token
            }
        }
    }
}