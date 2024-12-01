package com.example.homelibrary.domain.use_cases.details

import com.example.homelibrary.domain.repository.DetailsRepository
import javax.inject.Inject

class GetGenres @Inject constructor(
    private val detailsRepository: DetailsRepository
) {
    suspend operator fun invoke() : Map<Int, String> {
        return detailsRepository.getGenreMap()
    }
}