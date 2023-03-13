package com.kakao.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kakao.presentation.model.SearchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {

    val searchUiModel = savedStateHandle.getStateFlow(SearchUiModel::class.java.simpleName, SearchUiModel())

}