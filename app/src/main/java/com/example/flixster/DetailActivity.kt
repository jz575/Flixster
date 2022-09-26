package com.example.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView
import org.w3c.dom.Text

private const val TAG = "DetailActivity"
class DetailActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvOverview: TextView
    private lateinit var rbVoteAvg: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvTitle = findViewById(R.id.tvTitle)
        tvOverview = findViewById(R.id.tvOverview)
        rbVoteAvg = findViewById(R.id.rbVoteAvg)

        val movie = intent.getParcelableExtra<Movie>(MOVIE_EXTRA) as Movie
        //Log.i(TAG, "Movie is $Movie")
        tvTitle.text = movie.title
        tvOverview.text = movie.overview
        rbVoteAvg.rating = movie.voteAverage.toFloat()
    }
}