package com.example.learneveryday.Logic.Model.Response


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("code")
    val code: String?, // 200
    @SerializedName("msg")
    val msg: String?, // success
    @SerializedName("result")
    val result: NewsResult?
)
    data class NewsResult(
        @SerializedName("allnum")
        val allnum: Int?, // 500
        @SerializedName("curpage")
        val curpage: Int?, // 1
        @SerializedName("newslist")
        val list: List<News>?
    )
    data class News(
        @SerializedName("ctime")
        val time: String?, // 2020-12-18 00:00
        @SerializedName("description")
        val description: String?, // 深圳正发力新基建，超前规划、统筹布局以5G基站、数据中心为核心的信息通信基础设施建设，让城市运转更聪明、更智慧。该规划提出，到2025年，深圳5G宏基站站址达3万余座；到2035年，新增政务数据中心20座；未来全市新建、改扩建道路统一配建多功能智能杆。
        @SerializedName("id")
        val id: String?, // 98e542967f52c69c940b4339f5b36af6
        @SerializedName("picUrl")
        val picUrl: String?, // http://www.xinhuanet.com/tech/titlepic/112687/1126875609_1608252073487_title0h.jpg
        @SerializedName("source")
        val source: String?, // 新华科技
        @SerializedName("title")
        val title: String?, // 深圳：2025年5G宏基站站址将达3万余座
        @SerializedName("url")
        val url: String? // http://www.xinhuanet.com/tech/2020-12/18/c_1126875609.htm
    )