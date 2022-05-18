package com.example.jokeapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.jokeapi.R
import com.example.jokeapi.api.data.JokeResponse
import com.example.jokeapi.databinding.JokeItemViewBinding

class JokeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class JokeViewHolder(view: View) : RecyclerView.ViewHolder(view)

    var jokeList = emptyList<JokeResponse>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.joke_item_view, parent, false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = JokeItemViewBinding.bind(holder.itemView)
        binding.textView.text = jokeList[position].value
        binding.imageView.load(jokeList[position].iconUrl) {
            crossfade(true)
//            placeholder(R.drawable.image)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int {
        return jokeList.size
    }
}