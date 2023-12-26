package ru.syndicate.database.account

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Account: Table() {

    private val email = Account.varchar("email", 30)
    private val password = Account.varchar("password", 30)

    fun insert(accountDTO: AccountDTO) {
        transaction {
            Account.insert {
                it[email] = accountDTO.email
                it[password] = accountDTO.password
            }
        }
    }

    fun fetchAccount(email: String): AccountDTO? {
        return try {
            transaction {
                val account = Account.select { Account.email.eq(email) }.single()

                AccountDTO(
                    email = account[Account.email],
                    password = account[password]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}