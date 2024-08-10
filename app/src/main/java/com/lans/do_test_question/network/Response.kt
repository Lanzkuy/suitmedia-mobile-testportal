package com.lans.do_test_question.network

import com.lans.do_test_question.model.User
import com.squareup.moshi.Json

data class Response(
    @field:Json(name = "page") var page: Int,
    @field:Json(name = "per_page") var perPage: Int,
    @field:Json(name = "total") var total: Int,
    @field:Json(name = "total_pages") var totalPages: Int,
    @field:Json(name = "data") var data: List<UserResponse> = emptyList(),
    @field:Json(name = "support") var support: SupportResponse
)

data class UserResponse(
    @field:Json(name = "id") var id: Int,
    @field:Json(name = "email") var email: String,
    @field:Json(name = "first_name") var firstName: String,
    @field:Json(name = "last_name") var lastName: String,
    @field:Json(name = "avatar") var avatar: String? = null
)

data class SupportResponse(
    @field:Json(name = "url") var url: String,
    @field:Json(name = "text") var text: String
)

fun UserResponse.toModel() = User(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar ?: ""
)
