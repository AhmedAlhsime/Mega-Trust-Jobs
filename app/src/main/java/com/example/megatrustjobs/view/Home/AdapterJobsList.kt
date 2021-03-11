package com.example.megatrustjobs.view.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.megatrustjobs.R
import com.example.megatrustjobs.data.local.room.entities.JobsEntityes
import com.example.megatrustjobs.model.Jobs
import kotlinx.android.synthetic.main.item_rv_jobs.view.*
import java.util.*

class AdapterJobsList(val callback: Callback) : RecyclerView.Adapter<AdapterJobsList.JobsHolder>() {

    private var data: List<JobsEntityes> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobsHolder {
        return JobsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rv_jobs, parent, false)
        ,callback)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: JobsHolder, position: Int) = holder.bind(data[position])

    fun addOrRemoveProductFromFavourite(position: Int){
        data[position].ic_add_fav = data[position].ic_add_fav
        notifyItemChanged(position)
    }
    fun swapData(data: List<JobsEntityes>) {
        this.data = data
        notifyDataSetChanged()
    }

    class JobsHolder(
        itemView: View, private val callback: Callback
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: JobsEntityes) = with(itemView) {
            itemView.company_name.setText(item.company)

            Glide.with(company_logo)
                .load(item.company_logo)
                .placeholder(R.drawable.ic_buildings)
                .error(R.drawable.ic_buildings)
                .into(company_logo)
            favorite_job.isClickable = true
            itemView.Job.setText(item.title)
            item.ic_add_fav.let {
                if (it) {
                    favorite_job.setImageResource(R.drawable.ic_star_job)
                } else {
                    favorite_job.setImageResource(R.drawable.ic_favorite)
                }
            }
           itemView.setOnClickListener {
                callback.onItemClickedItem(item)
            }
            itemView.favorite_job.setOnClickListener {
                item.ic_add_fav = item.ic_add_fav == false
                callback.onItemFavItem(item,adapterPosition)
            }
        }

    }

    interface Callback {
        fun onItemClickedItem(items: JobsEntityes)
        fun onItemFavItem(items: JobsEntityes,position: Int)

    }

}
