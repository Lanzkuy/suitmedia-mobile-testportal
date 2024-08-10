package com.lans.do_test_question.repository

import com.lans.do_test_question.model.User
import com.lans.do_test_question.network.ApiService
import com.lans.do_test_question.network.Resource
import com.lans.do_test_question.network.SafeApiCall
import com.lans.do_test_question.network.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository(
    private val api: ApiService
) : SafeApiCall {
    suspend fun getUsers(page: Int): Flow<Resource<List<User>>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getUsers(page)
                if (response.data.isNotEmpty()) {
                    response.data.map { it.toModel() }
                } else {
                    throw Exception("Something went wrong")
                }
            })
        }.flowOn(Dispatchers.IO)
    }
}