package ru.syndicate.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponseRemote(
    val token: String
)

data class RegisterErrorResponseRemote(
    val error: String
)