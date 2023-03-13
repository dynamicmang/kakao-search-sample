package com.kakao.domain.repository

import androidx.paging.PagingData
import com.kakao.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun search(query: String): Flow<PagingData<Search.Document>>

}