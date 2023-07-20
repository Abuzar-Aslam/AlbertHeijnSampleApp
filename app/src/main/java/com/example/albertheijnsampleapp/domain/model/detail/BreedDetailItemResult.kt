package com.example.albertheijnsampleapp.domain.model.detail


/**
 * Represents the result of breed detail information for a specific breed.
 *
 * @property id The unique identifier of the breed.
 * @property name The name of the breed.
 * @property temperament The temperament of the breed.
 * @property origin The origin of the breed.
 * @property description The description of the breed.
 * @property lifeSpan The life span of the breed.
 */
data class BreedDetailItemResult(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val lifeSpan: String
)
