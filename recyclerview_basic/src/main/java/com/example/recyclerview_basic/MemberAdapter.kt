package com.example.recyclerview_basic

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class MemberAdapter(private var data2List: List<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setData(data: List<Item>) {
        notifyItemRangeRemoved(0,data2List.size)
        data2List = data
        notifyItemRangeInserted(0,data2List.size)
    }

    inner class memberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImg = itemView.findViewById<CircleImageView>(R.id.img)
        val profileStatus = itemView.findViewById<ImageView>(R.id.follow_status)
        val memberName = itemView.findViewById<TextView>(R.id.name)
        val memberNumber = itemView.findViewById<TextView>(R.id.number)
        val followBtn = itemView.findViewById<Button>(R.id.follow_btn)

        fun bind(memberList: Item?){
            Glide.with(profileImg.context)
                .load(memberList?.profImg)
                .into(profileImg)
            //profileStatus.isVisible =
            memberName.setText(memberList?.userName)
            memberNumber.setText(memberList?.userNumber)
            if(memberList?.followingYn == true){
                followBtn.setBackgroundResource(R.drawable.following_btn)
                profileStatus.isVisible=true
            }else {
                followBtn.setBackgroundResource(R.drawable.follow_btn)
                profileStatus.isVisible=false
            }
            if((memberList?.followerYn == false) && (memberList?.followingYn == true)) {
                followBtn.setText("팔로잉 중")
            }else if(memberList?.followerYn == true && memberList?.followingYn == true) {
                followBtn.setText("맞팔로잉 중")
            }else if(memberList?.followerYn == true && memberList?.followingYn == false) {
                followBtn.setText("맞팔로우")
            }else {
                followBtn.setText("팔로우")
            }
            followBtn.setOnClickListener{
                if((memberList?.followerYn == false) && (memberList?.followingYn == true)) {
                    followBtn.setText("팔로우")
                    followBtn.setBackgroundResource(R.drawable.follow_btn)
                    profileStatus.isVisible=false
                    memberList?.followingYn = false
                }else if(memberList?.followerYn == true && memberList?.followingYn == true) {
                    followBtn.setText("맞팔로우")
                    followBtn.setBackgroundResource(R.drawable.follow_btn)
                    profileStatus.isVisible=false
                    memberList?.followingYn = false
                }else if(memberList?.followerYn == true && memberList?.followingYn == false) {
                    followBtn.setText("맞팔로잉 중")
                    followBtn.setBackgroundResource(R.drawable.following_btn)
                    profileStatus.isVisible=true
                    memberList?.followingYn = true
                }else if(memberList?.followerYn == false && memberList?.followingYn == false){
                    followBtn.setText("팔로잉 중")
                    followBtn.setBackgroundResource(R.drawable.following_btn)
                    profileStatus.isVisible= true
                    memberList?.followingYn = true
                }
            }
        }
    }

    inner class lastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val invite_btn = itemView.findViewById<Button>(R.id.invite_btn)

        fun bind(memberList: Item){
            invite_btn.setOnClickListener {
                /*
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                */
            }
        }
    }

    inner class emptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(memberList: Item){

        }
    }

    override fun getItemCount(): Int {
        return data2List.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return data2List.get(position).lastStatus
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("gdgdgd viewType 1", viewType.toString())
        if(viewType == 0 ){
            return memberViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false))
        }else if(viewType == 1){
            Log.d("gdgdgd viewType 1", "in")
            return lastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.last_item, parent, false))
        }else {
            return emptyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.empty_item, parent, false))
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is memberViewHolder -> holder.bind(data2List[position])
            is lastViewHolder -> holder.bind(data2List[position])
            is emptyViewHolder -> holder.bind(data2List[position])
        }
    }
}



/*

class TestListADapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            1 -> 3
            2 -> 2
            3 -> 1
            else -> 0
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val view = LayoutInflater.from(context).inflate(R.layout.view_item, parent, false)
            return memberViewHolder(view,parent)
        }
        else if (viewType = 1) {
            val view = LayoutInflater.from(context).inflate(R.layout.view_item, parent, false)
            return memberViewHolder(view,parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MemberAdapter.memberViewHolder)?.bind(data.get(position))
        (holder as? MemberAdapter.Test2ViewHolder)?.bind(data.get(position))
    }

*/
