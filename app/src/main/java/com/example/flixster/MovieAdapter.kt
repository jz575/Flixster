package com.example.flixster

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val MOVIE_EXTRA = "MOVIE_EXTRA"
class MovieAdapter(private val context: Context, private val movies: List<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        private val ivBackdrop = itemView.findViewById<ImageView>(R.id.ivBackdrop)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview

            val orientation = context.resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Glide
                    .with(context)
                    .load(movie.posterImageUrl)
                    .placeholder(R.drawable.camera)
                    .into(ivPoster)
            } else {
                Glide
                    .with(context)
                    .load(movie.backdropImageUrl)
                    .placeholder(R.drawable.camera)
                    .into(ivBackdrop)
            }
        }

        override fun onClick(v: View?) {
            //1. know what movie is clicked on
            val movie = movies[adapterPosition]
            //Toast.makeText(context,movie.title, Toast.LENGTH_SHORT).show()
            //2. use that information with an intent to get to the next activity/screen
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            val activity: Activity = context as Activity
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, tvTitle, "title")
            context.startActivity(intent, options.toBundle()
            )
        }
    }
}
