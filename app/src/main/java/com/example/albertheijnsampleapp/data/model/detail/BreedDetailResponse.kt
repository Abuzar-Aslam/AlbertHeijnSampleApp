package com.example.albertheijnsampleapp.data.model.detail

import com.google.gson.annotations.SerializedName

/**
 * Model class encapsulating detailed attributes of a cat breed and its associated image from the API response.
 *
 * The class structures the breed detail data along with its unique identifier and associated image URL for convenient usage within the app.
 *
 * @param id Unique identifier of the breed detail response.
 * @param url URL of the image associated with the breed details.
 * @param breedDetailItemResponse A list of [BreedDetailItemResponse] instances representing the detailed attributes of the breed.
 */
data class BreedDetailResponse(
    val id: String?,
    val url: String?,
    @SerializedName("breeds")
    val breedDetailItemResponse: List<BreedDetailItemResponse>
)
