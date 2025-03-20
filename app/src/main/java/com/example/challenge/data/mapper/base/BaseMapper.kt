package com.example.challenge.data.mapper.base

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
