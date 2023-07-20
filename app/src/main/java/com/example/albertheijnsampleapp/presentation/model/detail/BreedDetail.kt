package com.example.albertheijnsampleapp.presentation.model.detail

/**
 * Data class representing the details of a breed.
 *
 * @property id The unique identifier of the breed.
 * @property url The URL of the image associated with the breed.
 * @property breedDetailItem The details of the breed item, such as temperament, origin, etc.
 */
data class BreedDetail(
    val id: String,
    val url: String,
    val breedDetailItem: BreedDetailItem
)

