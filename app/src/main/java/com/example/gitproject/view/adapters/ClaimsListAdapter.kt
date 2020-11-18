package com.example.gitproject.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitproject.R
import com.example.gitproject.models.dataModel.ClaimDummyModel
import kotlinx.android.synthetic.main.repo_recyclerview_item.view.*

class ClaimsListAdapter : RecyclerView.Adapter<ClaimsListAdapter.RecyclerViewHolder>() {

    lateinit var context: Context
    lateinit var itemClickListener: ItemClickListener
    var claimList = emptyList<ClaimDummyModel>()

    fun setClaimDateList(
        context: Context,
        claimList: List<ClaimDummyModel>,
        itemClickListener: ItemClickListener
    ) {
        this.context = context
        this.itemClickListener = itemClickListener
        this.claimList = claimList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.repo_recyclerview_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return claimList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        val claimListModel = claimList[position]
        holder.apply {
            claimType.text = claimListModel.name
            claimDate.text = claimListModel.date
            claimExpenses.text = claimListModel.expensesAmount
            viewMore.setOnClickListener {
                itemClickListener.itemClick(claimListModel)
            }
        }

    }


    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val claimType = view.claimType
        val claimDate = view.claimDate
        val claimExpenses = view.expensesAmount
        val viewMore = view.viewMoreText

    }


    /*
    * Item click listener
    * */
    interface ItemClickListener {
        fun itemClick(claimListModel: ClaimDummyModel)
    }
}