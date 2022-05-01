package com.example.jokeapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.jokeapi.api.data.JokeResponse
import com.example.jokeapi.databinding.JokeItemViewBinding

class JokePagingAdapter: PagingDataAdapter<JokeResponse,RecyclerView.ViewHolder>(diffCallback = JokeDiffer()) {
    class JokeDiffer: DiffUtil.ItemCallback<JokeResponse>(){
        override fun areItemsTheSame(oldItem: JokeResponse, newItem: JokeResponse): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: JokeResponse, newItem: JokeResponse): Boolean {
            return oldItem.id==newItem.id
        }

    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { val binding = JokeItemViewBinding.bind(holder.itemView)
        binding.textView.text = getItem(position)?.value
        binding.imageView.load(getItem(position)?.iconUrl) {
            crossfade(true)
//            placeholder(R.drawable.image)
            transformations(CircleCropTransformation())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.joke_item_view, parent, false)
        return JokePagingViewHolder(view)

    }
    inner class JokePagingViewHolder(view:View) : RecyclerView.ViewHolder(view)
}