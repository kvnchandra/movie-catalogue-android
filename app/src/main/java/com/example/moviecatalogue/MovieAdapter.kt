package com.example.moviecatalogue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColorInt

class MovieAdapter internal constructor(private val context: Context) : BaseAdapter() {

    internal var movies = arrayListOf<Movie>()

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = movies.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        }
        val viewHolder = ViewHolder(itemView as View)
        val hero = getItem(position) as Movie
        viewHolder.bind(hero)
        return itemView!!
    }

    private inner class ViewHolder internal constructor(view: View) {
        private val txtName: TextView = view.findViewById(R.id.txt_name)
        private val txtScore: TextView = view.findViewById(R.id.txt_score)
        private val txtDate: TextView = view.findViewById(R.id.txt_date)
        private val txtDescription: TextView = view.findViewById(R.id.txt_desc)
        private val imgPhoto: ImageView = view.findViewById(R.id.img_photo)
        internal fun bind(movie: Movie) {
            txtName.text = movie.name
            txtScore.text = movie.score
            txtDate.text = movie.date
            txtDescription.text = movie.desc
            imgPhoto.setImageResource(movie.photo)

            if (movie.score.toDouble() >= 8.0)
                txtScore.setTextColor("#007000".toColorInt())
            else
                txtScore.setTextColor("#ffbf00".toColorInt())
        }
    }
}