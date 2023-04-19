package com.example.recyclerviewtest3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val aData = listOf<AItem>(
            AItem("ㄴㅏ는"),
            AItem("안드로이드"),
            AItem("개발자다"),
        )

        val bData = listOf<BItem>(
            BItem(5),
            BItem(2),
            BItem(3),
            BItem(7),
        )

        val cData = listOf<CItem>(
            CItem(false),
            CItem(false),
            CItem(true)
        )

        val listData = mutableListOf<BaseItem>()

        aData.forEach {
            listData.add(
                it.apply { type = TYPE.A }
            )
        }

        /*bData.forEach {
            listData.add(
                RecyclerViewModel(type = TYPE.B, number = it.number)
            )
        }

        cData.forEach {
            listData.add(
                RecyclerViewModel(type = TYPE.C, isPick = it.isPick)
            )
        }

        listData.add(
            RecyclerViewModel(type = TYPE.A, name = "sssss")
        )
        listData.add(
            RecyclerViewModel(type = TYPE.B, number = 777)
        )*/

        findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = ListAdapter(listData)
        }

    }

    private inner class ListAdapter(private val items: List<BaseItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
            Log.d("gdgdgd onAttachedToRecyclerView", recyclerView.javaClass.simpleName)
        }

        override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
            super.onDetachedFromRecyclerView(recyclerView)
            Log.d("gdgdgd onDetachedFromRecyclerView", recyclerView.javaClass.simpleName)
        }

        override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
            super.onViewAttachedToWindow(holder)
            Log.d("gdgdgd onViewAttachedToWindow", holder.javaClass.simpleName)
        }

        override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
            super.onViewDetachedFromWindow(holder)
            Log.d("gdgdgd onViewDetachedFromWindow", holder.javaClass.simpleName)
        }


        override fun getItemCount(): Int = items.size
        override fun getItemViewType(position: Int): Int = items[position].type.ordinal
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (TYPE.values()[viewType]) {
                TYPE.A -> AItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_list_a_item, parent, false))
                TYPE.B -> BItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_list_b_item, parent, false))
                else -> CItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_list_c_item, parent, false))
            }
        }
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            when (holder) {
                is AItemViewHolder -> holder.onBind(items[position])
                /*is BItemViewHolder -> holder.onBind(items[position])
                is CItemViewHolder -> holder.onBind(items[position])*/
            }
        }
    }

    private inner class AItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun onBind(data: BaseItem?) {
            itemView.findViewById<TextView>(R.id.txt).apply {
                text = (data as? AItem)?.name ?: ""
            }
        }
    }

    private inner class BItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun onBind(recyclerViewModel: RecyclerViewModel) {
            itemView.findViewById<TextView>(R.id.txt).apply {
                text = recyclerViewModel.number.toString()
            }
        }
    }

    private inner class CItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun onBind(recyclerViewModel: RecyclerViewModel) {
            itemView.findViewById<TextView>(R.id.txt).apply {
                text = recyclerViewModel.isPick.toString()
            }
        }
    }







    open class BaseItem(open var type: TYPE = TYPE.A)

    data class AItem (val name: String): BaseItem(TYPE.A) {
        override var type: TYPE
            get() = super.type
            set(value) {type = value}
    }

    data class BItem (val number: Int): BaseItem(TYPE.B)

    data class CItem (val isPick: Boolean): BaseItem(TYPE.C)

    data class RecyclerViewModel(
        var type: TYPE = TYPE.A,
        var name: String? = null,
        var number: Int = 0,
        var isPick: Boolean = false
    )

    enum class TYPE {
        A,
        B,
        C
    }
}