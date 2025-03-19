package com.example.challenge.data.mapper.base

import com.example.challenge.data.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//suspend fun <Dto : Any, Domain : Any> Flow<Resource<Dto>>.asResource(
//    onSuccess: suspend (Dto) -> Domain,
//): Flow<Resource<Domain>> {
//    return this.map {
//        when (it) {
//            is Resource.Success -> Resource.Success(data = onSuccess.invoke(it.data))
//            is Resource.Failed -> Resource.Failed(message = it.message, data = it.data)
//            is Resource.Loading -> Resource.Loading(data = it.data)
//        }
//    }
//}
