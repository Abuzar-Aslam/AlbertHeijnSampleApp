package com.example.albertheijnsampleapp.presentation.model.detail

/**
 * Data class representing the details of a breed item.
 *
 * @property id The unique identifier of the breed item.
 * @property name The name of the breed item.
 * @property temperament The temperament of the breed item.
 * @property origin The origin of the breed item.
 * @property description The description of the breed item.
 * @property lifeSpan The life span of the breed item.
 */
data class BreedDetailItem(
    val id: String,
    val name: String,
    val temperament: String,
    val origin: String,
    val description: String,
    val lifeSpan: String
)
