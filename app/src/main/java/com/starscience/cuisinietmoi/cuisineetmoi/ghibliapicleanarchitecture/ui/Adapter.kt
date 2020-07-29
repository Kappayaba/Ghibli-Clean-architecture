package com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.RecyclerView
import com.starscience.cuisinietmoi.cuisineetmoi.data.model.Ghibli
import com.starscience.cuisinietmoi.cuisineetmoi.ghibliapicleanarchitecture.R

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    var ghiblis: List<Ghibli> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_ghibli, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return ghiblis.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ghibli = ghiblis[position]

        holder.titleText.text = ghibli.title
        holder.releaseDateText.text = ghibli.releaseDate
        holder.descriptionText.text = ghibli.description
        holder.directorText.text = ghibli.director
        holder.producerText.text = ghibli.producer
        holder.ratingText.rating = ghibli.rt_score.toFloat()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val titleText: TextView = v.findViewById(R.id.title)
        val releaseDateText: TextView = v.findViewById(R.id.release_date)
        val descriptionText: TextView = v.findViewById(R.id.description)
        val directorText: TextView = v.findViewById(R.id.director)
        val producerText: TextView = v.findViewById(R.id.producer)
        val ratingText: AppCompatRatingBar = v.findViewById(R.id.rt_score)
    }

}