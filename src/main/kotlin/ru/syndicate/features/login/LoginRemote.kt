package ru.syndicate.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseRemote(
    val token: String
)

data class LoginErrorResponseRemote(
    val error: String
)