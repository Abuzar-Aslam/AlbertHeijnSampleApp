package com.example.albertheijnsampleapp.data.model.detail

import com.google.gson.annotations.SerializedName

/**
 * Represents the response data for breed details from the API.
 *
 * @property id The unique identifier of the response.
 * @property url The URL of the image associated with the breed details.
 * @property breedDetailItemResponse List of [BreedDetailItemResponse] representing the details of the breed.
 */
data class BreedDetailResponse(
    val id: String?,
    val url: String?,
    @SerializedName("breeds")
    val breedDetailItemResponse: List<BreedDetailItemResponse>
)
