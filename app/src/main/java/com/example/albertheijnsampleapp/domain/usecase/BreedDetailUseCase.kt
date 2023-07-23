package com.example.albertheijnsampleapp.domain.usecase

import com.example.albertheijnsampleapp.data.model.Result
import com.example.albertheijnsampleapp.data.repository.detail.BreedDetailRepository
import com.example.albertheijnsampleapp.domain.model.detail.BreedDetailItemResult
import com.example.albertheijnsampleapp.domain.model.detail.BreedDetailResult
import com.example.albertheijnsampleapp.presentation.model.detail.BreedDetail
import com.example.albertheijnsampleapp.presentation.model.detail.BreedDetailItem
import javax.inject.Inject

/**
 * Use case class responsible for fetching breed detail information from the repository.
 *
 * @property breedDetailRepository The repository to fetch breed detail information.
 */
class BreedDetailUseCase @Inject constructor(
    private val breedDetailRepository: BreedDetailRepository
) {

    /**
     * Retrieves the breed detail information for a specific breed from the repository.
     *
     * @param id The unique identifier of the breed to fetch the detail for.
     * @return A [Result] representing the result of the operation.
     * If the result is [Result.Success], it contains a [BreedDetail] object with the breed detail information.
     * If the result is [Result.Error], it contains an [Exception] with the error message.
     */
    suspend fun getBreedDetail(id: String): Result<BreedDetail> {
        return when (val result = breedDetailRepository.getBreedDetail(id)) {
            is Result.Success -> {
                Result.Success(result.data.toBreedItem())
            }
            is Result.Error -> Result.Error(result.exception)
        }
    }

    /**
     * Maps the [BreedDetailResult] to [BreedDetail].
     *
     * @return The [BreedDetail] mapped from the [BreedDetailResult].
     */
    private fun BreedDetailResult.toBreedItem(): BreedDetail {
        return BreedDetail(
            id = id,
            url = url,
            breedDetailItem = breedDetailItemResult.toBreedDetailItem()
        )
    }

    /**
     * Maps the [BreedDetailItemResult] to [BreedDetailItem].
     *
     * @return The [BreedDetailItem] mapped from the [BreedDetailItemResult].
     */
    private fun BreedDetailItemResult.toBreedDetailItem(): BreedDetailItem {
        return BreedDetailItem(
            id = id,
            name = name,
            temperament = temperament,
            origin = origin,
            description = description,
            lifeSpan = lifeSpan
        )
    }
}
