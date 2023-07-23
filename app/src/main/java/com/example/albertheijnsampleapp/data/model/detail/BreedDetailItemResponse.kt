package com.example.albertheijnsampleapp.data.model.detail

import com.google.gson.annotations.SerializedName

/**
 * Model class encapsulating detailed attributes of a cat breed from the API response.
 *
 * The class carries detailed information about a breed, including its ID, name, temperament, origin, description, and lifespan.
 * It serves to structure the raw data from the API response for convenient use in the app.
 *
 * @param id Unique identifier of the breed.
 * @param name Breed's name.
 * @param temperament Breed's temperament characteristics.
 * @param origin Geographic origin of the breed.
 * @param description Detailed description of the breed.
 * @param lifeSpan Typical lifespan of the breed.
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
