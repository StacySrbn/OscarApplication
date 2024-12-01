package com.example.homelibrary.domain.use_cases.details

import com.example.homelibrary.domain.repository.DetailsRepository
import javax.inject.Inject

class GetTrailerById @Inject constructor(
    private val detailsRepository: DetailsRepository
) {
    suspend operator fun invoke(movieId: Int) : String? {
        return detailsRepository.getMovieTrailerId(movieId)
    }
}