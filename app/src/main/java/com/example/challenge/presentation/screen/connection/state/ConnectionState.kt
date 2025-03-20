package com.example.challenge.presentation.screen.connection.state

import com.example.challenge.presentation.model.connection.Connection

data class ConnectionState (
    val isLoading: Boolean = false,
    val connections: List<Connection> = emptyList(),
    val errorMessage: String? = null
)