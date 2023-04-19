package com.example.recyclerview_basic


import com.google.gson.annotations.SerializedName

data class MemberData(
    val member: List<Member>
) {
    data class Member(
        @SerializedName("follower_yn")
        val followerYn: Boolean,
        @SerializedName("following_yn")
        val followingYn: Boolean,
        val img: String,
        val name: String,
        val number: String
    )
}