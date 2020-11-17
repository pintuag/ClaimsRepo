package com.example.gitproject.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitproject.R
import com.example.gitproject.models.dataModel.Claim
import kotlinx.android.synthetic.main.repo_recyclerview_item.view.*

class ClaimsListAdapter : RecyclerView.Adapter<ClaimsListAdapter.RecyclerViewHolder>() {

    lateinit var context: Context
    lateinit var itemClickListener: ItemClickListener
    var claimList = emptyList<Claim>()

    fun setTrendingListData(
        context: Context,
        claimList: List<Claim>,
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

            /*username.text = claimListModel.username
            repoName.text = claimListModel.name

            imageLoader.DisplayImage(claimListModel.avatar, R.drawable.placeholder, userProfile)*/

            itemView.setOnClickListener {
                itemClickListener.itemClick(claimListModel)
            }
        }

    }


    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val username = view.username
        val repoName = view.repoName
        val userProfile = view.userProfile

    }


    /*
    * Item click listener
    * */
    interface ItemClickListener {

        fun itemClick(claimListModel: Claim)
    }


}