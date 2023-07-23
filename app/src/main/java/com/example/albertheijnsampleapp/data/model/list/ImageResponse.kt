package com.example.albertheijnsampleapp.data.model.list

/**
 * Model class representing the details of a cat breed image.
 *
 * It encapsulates properties such as ID, dimensions (width and height), and URL of an image.
 * This class assists in mapping the unstructured data from the API response to a well-defined model for further usage.
 *
 * @param id Unique identifier of the image.
 * @param width Width dimension of the image.
 * @param height Height dimension of the image.
 * @param url Direct URL link of the image.
 */
data class ImageResponse(
    val id: String?,
    val width: Int?,
    val height: Int?,
    val url: String?
)

