package com.example.albertheijnsampleapp.data.repository.detail

import com.example.albertheijnsampleapp.data.model.Result
import com.example.albertheijnsampleapp.data.model.detail.BreedDetailItemResponse
import com.example.albertheijnsampleapp.data.model.detail.BreedDetailResponse
import com.example.albertheijnsampleapp.data.source.DataSource
import com.example.albertheijnsampleapp.domain.model.detail.BreedDetailItemResult
import com.example.albertheijnsampleapp.domain.model.detail.BreedDetailResult
import java.lang.Exception
/**
 * Implementation of the [BreedDetailRepository] interface.
 * Provides methods to retrieve breed detail information from a data source.
 *
 * @param dataSource The data source to fetch breed detail information.
 */
class BreedDetailRepositoryImpl(private val dataSource: DataSource) : BreedDetailRepository {

    /**
     * Retrieves the breed detail information for a specific breed from the data source.
     *
     * @param id The unique identifier of the breed to fetch the detail for.
     * @return A [Result] representing the result of the operation.
     * If the result is [Result.Success], it contains a [BreedDetailResult] object with the breed detail information.
     * If the result is [Result.Error], it contains an [Exception] with the error message.
     */
    override suspend fun getBreedDetail(id: String): Result<BreedDetailResult> {
        return try {
            val breedDetail = dataSource.getBreedDetail(id).toBreedDetailResult()
            Result.Success(breedDetail)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

    /**
     * Maps the [BreedDetailResponse] to [BreedDetailResult].
     *
     * @return The [BreedDetailResult] mapped from the [BreedDetailResponse].
     */
    private fun BreedDetailResponse.toBreedDetailResult(): BreedDetailResult {
        return BreedDetailResult(
            id = id ?: "",
            url = url ?: "",
            breedDetailItemResult = breedDetailItemResponse[0].toBreedDetailItemResult()
        )
    }

    /**
     * Maps the [BreedDetailItemResponse] to [BreedDetailItemResult].
     *
     * @return The [BreedDetailItemResult] mapped from the [BreedDetailItemResponse].
     */
    private fun BreedDetailItemResponse.toBreedDetailItemResult(): BreedDetailItemResult {
        return BreedDetailItemResult(
            id = id ?: "",
            name = name ?: "",
            temperament = temperament ?: "",
            origin = origin ?: "",
            description = description ?: "",
            lifeSpan = lifeSpan ?: ""
        )
    }
}
