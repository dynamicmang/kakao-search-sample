package com.kakao.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kakao.data.ApiService
import com.kakao.data.datasource.SearchPagingSource
import com.kakao.domain.model.Search
import com.kakao.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val api: ApiService) : SearchRepository {

    override fun search(query: String): Flow<PagingData<Search.Document>> {
        return Pager(config = PagingConfig(pageSize = 30)) {
            SearchPagingSource(api, query)
        }.flow
    }

}