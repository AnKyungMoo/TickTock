package com.km.ticktock.services

import com.km.ticktock.utils.APIKey
import com.km.ticktock.views.alarmsetting.model.SearchObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.*

interface SearchInterface {
    // 키워드로 장소 검색
    @Headers("Authorization: KakaoAK " + APIKey.KAKAO)
    @GET("v2/local/search/keyword.json")
    fun keywordSearch(@Query("query") keyword: String) : Observable<SearchObject>
}