package com.kakao.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.kakao.domain.usecase.SearchUseCase
import com.kakao.presentation.model.SearchUiModel
import com.kakao.presentation.model.UiState
import com.kakao.presentation.toMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<PagingData<SearchUiModel>>>(UiState.None)
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    fun search(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            _uiState.value = UiState.Loading
            searchUseCase(query)
                .map { item ->
                    item.map { it.toMap() }
                }
                .cachedIn(this)
                .catch {
                    Timber.e(it)
                    _uiState.value = UiState.Error(it)
                }
                .collectLatest {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}