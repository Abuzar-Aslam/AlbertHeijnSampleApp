package com.example.albertheijnsampleapp.data.model.list

/**
 * Model class to represent a cat breed from the API response.
 *
 * It carries detailed properties describing a breed, such as its ID, name, temperament, and an associated image.
 * This class aids in mapping the raw data from the API response into a more structured model for use in the domain layer.
 *
 * @param id Unique identifier of the breed.
 * @param name Breed's name.
 * @param temperament Breed's temperament characteristics.
 * @param image Associated image details of the breed.
 */
data class BreedResponse(
    val id: String?,
    val name: String?,
    val temperament: String?,
    val image: ImageResponse?
)
