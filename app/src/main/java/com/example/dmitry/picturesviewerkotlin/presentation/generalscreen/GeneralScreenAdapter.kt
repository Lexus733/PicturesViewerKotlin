package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.dmitry.picturesviewerkotlin.R
import com.example.dmitry.picturesviewerkotlin.domain.Image
import com.squareup.picasso.Picasso
import java.io.File


class GeneralScreenAdapter(private var images: List<Image>, private var listener: OnItemClickListener, private var listenerLong: OnItemLongClickListener) : RecyclerView.Adapter<GeneralScreenAdapter.ViewHolder>() {
    interface OnItemLongClickListener {
        fun onItemLongClick(item: Image): Boolean
    }

    interface OnItemClickListener {
        fun onItemClick(item: Image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image: Image = images[position]
        Picasso.get()
                .load(File(image.path))
                .placeholder(R.drawable.ic_file_download_black_24dp)
                .centerCrop()
                .resize(240, 240)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(holder.imageView)
        holder.bind(image, listener, listenerLong)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.picture_item_image)

        fun bind(image: Image, listener: OnItemClickListener, longListener: OnItemLongClickListener) {
            imageView.setOnClickListener { listener.onItemClick(image) }
            imageView.setOnLongClickListener { longListener.onItemLongClick(image) }
        }
    }
}