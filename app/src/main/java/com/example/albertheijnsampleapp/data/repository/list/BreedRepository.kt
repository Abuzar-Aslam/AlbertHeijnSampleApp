package com.example.albertheijnsampleapp.data.repository.list

import androidx.paging.PagingData
import com.example.albertheijnsampleapp.domain.model.list.BreedResult
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for retrieving breeds.
 */
interface BreedRepository {
    fun getBreeds(): Flow<PagingData<BreedResult>>
}
