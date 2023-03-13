package com.kakao.domain.usecase

import com.kakao.domain.repository.SearchRepository
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {

    operator fun invoke(query: String) = repository.search(query)

}