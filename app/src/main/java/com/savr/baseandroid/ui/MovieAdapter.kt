package com.savr.baseandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.savr.baseandroid.BuildConfig.URL_POSTER
import com.savr.baseandroid.R
import com.savr.baseandroid.data.movie.remote.response.MovieItem
import kotlinx.android.synthetic.main.items_movie.view.*

class MovieAdapter(val listener: ((movie: MovieItem) -> Unit)?)
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<MovieItem>()

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: MovieItem) {
            with(itemView) {
                tv_title.text = data.title
                tv_vote.text = data.vote_average.toString()
                itemView.setOnClickListener {
                    listener?.invoke(data)
                }

                Glide.with(itemView.context)
                    .load(URL_POSTER+ data.poster_path)
                    .into(img_poster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.items_movie, parent, false)
        return MovieViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    fun setMovies(tvShows: List<MovieItem>?) {
        if (tvShows == null) return
        this.listMovies.clear()
        this.listMovies.addAll(tvShows)
        notifyDataSetChanged()
    }
}