package com.example.albertheijnsampleapp.data.model.detail

import com.google.gson.annotations.SerializedName

/**
 * Represents the response data for a breed detail item from the API.
 *
 * @property id The unique identifier of the breed.
 * @property name The name of the breed.
 * @property temperament The temperament of the breed.
 * @property origin The origin of the breed.
 * @property description The description of the breed.
 * @property lifeSpan The life span of the breed.
 */
data class BreedDetailItemResponse(
    val id: String?,
    val name: String?,
    val temperament: String?,
    val origin: String?,
    val description: String?,
    @SerializedName("life_span")
    val lifeSpan: String?
)
