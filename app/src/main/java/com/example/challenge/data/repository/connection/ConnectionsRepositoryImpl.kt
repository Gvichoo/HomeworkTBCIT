package com.example.challenge.data.repository.connection

import com.example.challenge.data.common.Resource
import com.example.challenge.data.common.handleHttpRequest
import com.example.challenge.data.mapper.connection.toDomain
import com.example.challenge.data.service.connection.ConnectionsService
import com.example.challenge.domain.model.connection.GetConnection
import com.example.challenge.domain.repository.connection.ConnectionsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConnectionsRepositoryImpl @Inject constructor(
    private val connectionsService: ConnectionsService,
) : ConnectionsRepository {

    override suspend fun getConnections(): Flow<Resource<GetConnection>> {

        return handleHttpRequest(
            apiCall = {connectionsService.getConnections()},
            mapToDomain = {it.toDomain()}
        )


    }
}