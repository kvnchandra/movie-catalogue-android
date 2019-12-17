package com.example.moviecatalogue

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataScore: Array<String>
    private lateinit var dataDate: Array<String>
    private lateinit var dataCast: Array<String>
    private lateinit var dataDescription: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var movies = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.lv_list)
        adapter = MovieAdapter(this)
        listView.adapter = adapter

        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            showMovieDetails(position)
        }
    }

    private fun showMovieDetails(position : Int){
        val movieClicked = Movie(
            movies[position].photo,
            movies[position].name,
            movies[position].score,
            movies[position].date,
            movies[position].cast,
            movies[position].desc
        )
        val moveToDetail = Intent(this@MainActivity, DetailActivity::class.java)
        moveToDetail.putExtra(DetailActivity.EXTRA_MOVIE, movieClicked)
        startActivity(moveToDetail)
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.data_name)
        dataScore = resources.getStringArray(R.array.data_score)
        dataDate = resources.getStringArray(R.array.data_date)
        dataCast = resources.getStringArray(R.array.data_cast)
        dataDescription = resources.getStringArray(R.array.data_description)
        dataPhoto = resources.obtainTypedArray(R.array.data_photo)
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val movie = Movie(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataScore[position],
                dataDate[position],
                dataCast[position],
                dataDescription[position]
            )
            movies.add(movie)
        }
        adapter.movies = movies
    }
}
