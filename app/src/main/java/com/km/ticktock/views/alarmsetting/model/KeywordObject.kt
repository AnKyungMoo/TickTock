package com.km.ticktock.views.alarmsetting.model


data class SameNameModel (
    val region: ArrayList<String>,
    val keyword: String,
    val selected_region: String
)

object KeywordObject {
    data class meta (
        val same_name: SameNameModel,
        val pageable_count: Int,
        val total_count: Int,
        val is_end: Boolean
    )

    data class documents (
        val place_name: String,
        val place_url: String,
        val category_name: String,
        val address_name: String,
        val road_address_name: String,
        val id: String,
        val phone: String,
        val category_group_code: String,
        val category_group_name: String,
        val x: String,
        val y: String
    )
}