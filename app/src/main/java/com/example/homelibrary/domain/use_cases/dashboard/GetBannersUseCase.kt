package com.example.homelibrary.domain.use_cases.dashboard

import com.example.homelibrary.domain.model.Banner
import com.example.homelibrary.domain.repository.BannersRepository
import com.example.homelibrary.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBannersUseCase @Inject constructor(
    private val bannersRepository: BannersRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Banner>>> {
        return bannersRepository.getBanners()
    }
}