package com.eslirodrigues.serviceappcompose.management.memberTab

import com.eslirodrigues.serviceappcompose.management.memberTab.model.MemberModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType

class MemberRepository(private val client: HttpClient) {
    private val membersUrl = "http://localhost:8090/api/v1/members"

    suspend fun getMembers(): List<MemberModel> = client.get(membersUrl).body()

    suspend fun createMember(member: MemberModel): MemberModel {
        return client.post(membersUrl) {
            contentType(ContentType.Application.Json)
            setBody(member)
        }.body()
    }

    suspend fun updateMember(id: Long, member: MemberModel): MemberModel {
        return client.put("$membersUrl/$id") {
            contentType(ContentType.Application.Json)
            setBody(member)
        }.body()
    }

    suspend fun deleteMember(id: Long) {
        client.delete("$membersUrl/$id")
    }
}