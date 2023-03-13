package com.kakao.domain.model

data class Search(
    val meta: Meta = Meta(),
    val documentList: List<Document> = emptyList()
) {
    data class Meta(
        val total_count: Int = 0,
        val pageable_count: Int = 0,
        val is_end: Boolean = false,
    )
    data class Document(
        val collection:String = "",
        val thumbnailUrl:String = "",
        val imageUrl:String = "",
        val width:Int = 0,
        val height:Int = 0,
        val displaySiteName:String = "",
        val docUrl:String = "",
        val dateTime:String = "",
    )
}