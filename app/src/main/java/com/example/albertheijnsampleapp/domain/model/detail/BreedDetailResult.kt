package com.example.albertheijnsampleapp.domain.model.detail

/**
 * Represents the result of breed detail information.
 *
 * @property id The unique identifier of the breed.
 * @property url The URL of the breed image.
 * @property breedDetailItemResult The [BreedDetailItemResult] containing detailed information about the breed.
 */
data class BreedDetailResult(
    val id: String,
    val url: String,
    val breedDetailItemResult: BreedDetailItemResult
)

