package com.example.albertheijnsampleapp.data.repository.detail

import com.example.albertheijnsampleapp.data.model.Result
import com.example.albertheijnsampleapp.domain.model.detail.BreedDetailResult

/**
 * A repository interface for retrieving breed detail information.
 */
interface BreedDetailRepository {
   /**
    * Retrieves the breed detail information for a specific breed ID.
    *
    * @param id The unique identifier of the breed.
    * @return A [Result] object representing the outcome of the operation. It can be a [Result.Success] with the [BreedDetailResult] data or a [Result.Error] with an exception.
    */
   suspend fun getBreedDetail(id: String): Result<BreedDetailResult>
}
