package com.example.pokemonapi

import com.example.pokemonapi.models.AbilityDetailResponse
import com.example.pokemonapi.models.AbilityListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("ability/")
    suspend fun getAbilities(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): AbilityListResponse

    @GET("ability/{id}/")
    suspend fun getAbilityDetail(
        @Path("id") id: Int
    ): AbilityDetailResponse
}
