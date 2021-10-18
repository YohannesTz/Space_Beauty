package com.yohannes.dev.app.spacebeauty.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.yohannes.dev.app.spacebeauty.R
import com.yohannes.dev.app.spacebeauty.model.Apod

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    val apods: ArrayList<Apod> = ArrayList()
    var apodList = mutableListOf<Apod>()

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView
        val itemDiscription: TextView
        val itemImage: ImageView

        init {
            itemView.apply {
                itemTitle = findViewById(R.id.item_title)
                itemDiscription = findViewById(R.id.item_discription)
                itemImage = findViewById(R.id.item_image)
            }
        }

        fun bind(apod: Apod) {
            itemView.apply {
                Log.e("MainAdapter", apod.toString())
                itemTitle.text = apod.title
                itemDiscription.text = apod.explanation
                Glide.with(itemImage.context)
                    .load(apod.url)
                    .transform(RoundedCorners(16))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemImage)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(apodList[position])
    }

    override fun getItemCount() = this.apodList.size

    fun setApods(newApods: List<Apod>) {
        this.apodList.clear()
        this.apodList = newApods.toMutableList()
        notifyDataSetChanged()
    }

    fun addApods(newApods: List<Apod>) {
        //this.apods = apods.toMutableList()
        Log.e("MainAdapter", "addApods: ${newApods.size}", )

        apods.addAll(newApods)
        notifyDataSetChanged()
    }

}