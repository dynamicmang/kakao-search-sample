package com.kakao.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchUiModel(
    val collection: String = "",
    val thumbnailUrl: String = "",
    val imageUrl: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val displaySiteName: String = "",
    val docUrl: String = "",
    val dateTime: String = "",
) : Parcelable

