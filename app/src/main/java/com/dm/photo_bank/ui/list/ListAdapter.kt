package com.dm.photo_bank.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dm.photo_bank.R
import com.dm.photo_bank.data.UnsplashPhoto

class ListAdapter: RecyclerView.Adapter<ListAdapter.PhotoListHolder>() {

    private var photoList = listOf<UnsplashPhoto>()

    fun setData(data: List<UnsplashPhoto>) {
        photoList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.photo_item,parent,false)
        return PhotoListHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoListHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount() = photoList.size

    inner class PhotoListHolder (view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: UnsplashPhoto) {
           itemView.findViewById<ImageView>(R.id.imageView).load(item.urls.regular)
           itemView.setOnClickListener {
               val action = ListFragmentDirections.actionListFragmentToDetailFragment(item.urls)
               itemView.findNavController().navigate(action)
           }
        }
    }
}