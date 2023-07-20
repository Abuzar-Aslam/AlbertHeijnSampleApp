package com.example.albertheijnsampleapp.data.repository.list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.example.albertheijnsampleapp.data.model.list.BreedResponse
import com.example.albertheijnsampleapp.data.model.list.ImageResponse
import com.example.albertheijnsampleapp.data.source.BreedPagingSource
import com.example.albertheijnsampleapp.domain.model.list.BreedResult
import com.example.albertheijnsampleapp.domain.model.list.ImageResult
import com.example.albertheijnsampleapp.utils.Constants

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementation of the BreedRepository interface.
 * Provides methods to retrieve a list of breeds from a data source.
 *
 * @param breedPagingSource The data source for retrieving the breeds.
 */
class BreedRepositoryImpl(
    private val breedPagingSource: BreedPagingSource
) : BreedRepository {

    /**
     * Retrieves a list of breeds from the data source.
     *
     * @return A Flow of PagingData containing BreedResult objects representing the breeds.
     */
    override fun getBreeds(): Flow<PagingData<BreedResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { breedPagingSource }
        ).flow.map { pagingData ->
            pagingData.filter { breedResponse -> breedResponse.image != null }
                .map { breedResponse ->
                    breedResponse.toBreedResult()
                }
        }
    }

    /**
     * Maps the BreedResponse object to BreedResult.
     *
     * @return The BreedResult object mapped from the BreedResponse.
     */
    private fun BreedResponse.toBreedResult(): BreedResult {
        return BreedResult(
            id = id ?: "",
            name = name ?: "",
            temperament = temperament ?: "",
            image = image?.toImageResult() ?: ImageResult("", 0, 0, "")
        )
    }

    /**
     * Maps the ImageResponse object to ImageResult.
     *
     * @return The ImageResult object mapped from the ImageResponse.
     */
    private fun ImageResponse.toImageResult(): ImageResult {
        return ImageResult(
            id = id ?: "",
            width = width ?: 0,
            height = height ?: 0,
            url = url ?: ""
        )
    }
}
