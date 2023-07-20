package com.example.albertheijnsampleapp.data.source

import com.example.albertheijnsampleapp.data.model.detail.BreedDetailResponse
import com.example.albertheijnsampleapp.data.model.list.BreedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Data source interface for retrieving items.
 */
interface DataSource {

    /**
     * Retrieves a list of breeds from server.
     *
     * @return A list of [BreedResponse] representing the items.
     */
    @GET("breeds")
    suspend fun getBreeds(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): List<BreedResponse>



    @GET("images/{id}")
    suspend fun getBreedDetail(@Path("id") id: String): BreedDetailResponse

}