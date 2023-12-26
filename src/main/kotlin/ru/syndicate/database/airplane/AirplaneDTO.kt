package ru.syndicate.database.airplane

import kotlinx.serialization.Serializable

@Serializable
class AirplaneDTO(
    val airplaneId: Int,
    val model: String,
    val amout_seat: Int
)