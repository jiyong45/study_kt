package com.example.recyclerview_basic

class MemberData2 : ArrayList<MemberData2.MemberData2Item>(){
    data class MemberData2Item(
        val member: List<Member>
    ) {
        data class Member(
            val follower_yn: Boolean,
            val following_yn: Boolean,
            val prof_img: String,
            val user_name: String,
            val user_number: String
        )
    }
}