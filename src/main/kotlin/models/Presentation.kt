package models

data class Presentation(
    val message: String = "Hi! This is an Activej REST Api built with Kotlin & Gradle.",
    val availableEndpoints: Array<String> = arrayOf("/addContact", "/getAllContacts"),
    val author: String = "Raul Armas"
)