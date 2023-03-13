package com.kakao.data.model

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("meta")
    val meta: MetaData = MetaData(),
    @SerializedName("documents")
    val documentList: List<DocumentData> = emptyList()
) {
    data class MetaData(
        @SerializedName("total_count")
        val totalCount: Int = 0,
        @SerializedName("pageable_count")
        val pageableCount: Int = 0,
        @SerializedName("is_end")
        val isEnd: Boolean = false,
    )
    data class DocumentData(
        @SerializedName("collection")
        val collection:String = "",
        @SerializedName("thumbnail_url")
        val thumbnailUrl:String = "",
        @SerializedName("image_url")
        val imageUrl:String = "",
        @SerializedName("width")
        val width:Int = 0,
        @SerializedName("height")
        val height:Int = 0,
        @SerializedName("display_sitename")
        val displaySiteName:String = "",
        @SerializedName("doc_url")
        val docUrl:String = "",
        @SerializedName("datetime")
        val dateTime:String = "",
    )
}