package com.example.moviecatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColorInt

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        val ivPosterFilm: ImageView = findViewById(R.id.img_photo)
        val tvNamaFilm: TextView = findViewById(R.id.txt_name)
        val tvTanggalFilm: TextView = findViewById(R.id.txt_date)
        val tvCastFilm: TextView = findViewById(R.id.txt_cast)
        val tvScoreFilm: TextView = findViewById(R.id.txt_score)
        val tvDetailFilm: TextView = findViewById(R.id.txt_desc)

        ivPosterFilm.setImageResource(movie.photo)
        tvNamaFilm.text = movie.name
        tvScoreFilm.text = movie.score
        tvTanggalFilm.text = movie.date
        tvCastFilm.text = movie.cast
        tvDetailFilm.text = movie.desc

        if (movie.score.toDouble() >= 8.0)
            tvScoreFilm.setTextColor("#007000".toColorInt())
        else
            tvScoreFilm.setTextColor("#ffbf00".toColorInt())
    }

    companion object{
        const val EXTRA_MOVIE = "extra_name"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        this.finish()
        return super.onOptionsItemSelected(item)
    }

}
