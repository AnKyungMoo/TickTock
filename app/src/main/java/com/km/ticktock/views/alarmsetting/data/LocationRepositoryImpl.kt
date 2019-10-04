package com.km.ticktock.views.alarmsetting.data

import android.content.Context
import com.km.ticktock.views.alarmsetting.domain.LocationRepository
import com.km.ticktock.views.alarmsetting.entity.SearchPubTransPath
import io.reactivex.Observable
import java.util.ArrayList

class LocationRepositoryImpl: LocationRepository {

    override fun showToast(context: Context) {

    }

    override fun getTransPath() = Observable.fromCallable {
        val transPaths = ArrayList<SearchPubTransPath>()

        val subPath1 = ArrayList<SearchPubTransPath.SubPath>()
        subPath1.add(SearchPubTransPath.SubPath(
            3,5,
            SearchPubTransPath.Lane("", "", 0, 0),
            "", ""
        ))
        subPath1.add(SearchPubTransPath.SubPath(
            1,9,
            SearchPubTransPath.Lane("", "", 0, 2),
            "합정역", "시청역"
        ))
        subPath1.add(SearchPubTransPath.SubPath(
            3,5,
            SearchPubTransPath.Lane("", "", 0, 0),
            "", ""
        ))
        subPath1.add(SearchPubTransPath.SubPath(
            1,10,
            SearchPubTransPath.Lane("", "", 0, 1),
            "시청역", "종각역"
        ))
        subPath1.add(SearchPubTransPath.SubPath(
            3,5,
            SearchPubTransPath.Lane("", "", 0, 0),
            "", ""
        ))

        transPaths.add(SearchPubTransPath(1, SearchPubTransPath.Path(
            10, 34, 6250,
            0, 0, 0),
            subPath1)
        )

        val subPath2 = ArrayList<SearchPubTransPath.SubPath>()
        subPath2.add(SearchPubTransPath.SubPath(
            3,5,
            SearchPubTransPath.Lane("", "", 0, 0),
            "", ""
        ))
        subPath2.add(SearchPubTransPath.SubPath(
            2,21,
            SearchPubTransPath.Lane("", "740", 3, 0),
            "전쟁기념관", "종각역 3번 출구"
        ))
        subPath2.add(SearchPubTransPath.SubPath(
            3,5,
            SearchPubTransPath.Lane("", "", 0, 0),
            "", ""
        ))

        transPaths.add(SearchPubTransPath(1, SearchPubTransPath.Path(
            15, 31, 6250,
            0, 0, 0),
            subPath2)
        )

        transPaths
    }
}