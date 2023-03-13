package com.kakao.data

import com.kakao.data.model.SearchData
import com.kakao.domain.model.Search

fun SearchData.toMap() = Search(
    meta = this.meta.toMap(),
    documentList = this.documentList.map { it.toMap() },
)

fun SearchData.MetaData.toMap() = Search.Meta(
    total_count = this.totalCount,
    pageable_count = this.pageableCount,
    is_end = this.isEnd,
)

fun SearchData.DocumentData.toMap() = Search.Document(
    collection = this.collection,
    thumbnailUrl = this.thumbnailUrl,
    imageUrl = this.imageUrl,
    width = this.width,
    height = this.height,
    displaySiteName = this.displaySiteName,
    docUrl = this.docUrl,
    dateTime = this.dateTime,
)