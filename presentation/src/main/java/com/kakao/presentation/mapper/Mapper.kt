package com.kakao.presentation

import com.kakao.domain.model.Search
import com.kakao.presentation.model.SearchUiModel

fun Search.Document.toMap(): SearchUiModel {
    return SearchUiModel(
        collection = this.collection,
        thumbnailUrl = this.thumbnailUrl,
        imageUrl = this.imageUrl,
        width = this.width,
        height = this.height,
        displaySiteName = this.displaySiteName,
        docUrl = this.docUrl,
        dateTime = this.dateTime,
    )
}