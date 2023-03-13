package com.kakao.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kakao.data.ApiService
import com.kakao.data.BuildConfig
import com.kakao.data.toMap
import com.kakao.domain.model.Search

class SearchPagingSource(
    private val api: ApiService,
    private val query: String,
) : PagingSource<Int, Search.Document>() {

    override fun getRefreshKey(state: PagingState<Int, Search.Document>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search.Document> {
        return try {
            val nextPage = params.key ?: 1
            val response = api.search(
                key = "KakaoAK ${BuildConfig.kakao_key}",
                query = query,
                page = nextPage,
                size = 30
            )
            val documentList = response.body()?.documentList ?: emptyList()
            LoadResult.Page(
                data = documentList.map { it.toMap() },
                prevKey = null,
                nextKey = if (documentList.size == 30) nextPage + 1 else null,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}