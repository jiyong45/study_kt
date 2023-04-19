package com.example.recyclerview_basic


import com.google.gson.annotations.SerializedName

data class Test (
    @SerializedName("member") var member: MutableList<Item>
)

data class Item(
            @SerializedName("follower_yn")
            var followerYn: Boolean = false,
            @SerializedName("following_yn")
            var followingYn: Boolean = false,
            @SerializedName("prof_img")
            var profImg: String? = null,
            @SerializedName("user_name")
            var userName: String? = null,
            @SerializedName("user_number")
            var userNumber: String? = null,
            @SerializedName("last_status")
            var lastStatus: Int = 0

)