package com.savr.baseandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.savr.baseandroid.entity.Movie
import com.savr.baseandroid.network.UtilsApi
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movieList: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            itemView.apply {
                tv_title.text = movie.title
                tv_overview.text = movie.overview

                Glide.with(context)
                    .load(UtilsApi.BASE_POSTER_URL + movie.poster_path)
                    .into(iv_poster)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }
}