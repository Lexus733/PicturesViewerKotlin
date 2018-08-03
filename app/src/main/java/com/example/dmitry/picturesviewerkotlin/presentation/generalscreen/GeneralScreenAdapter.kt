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
import java.util.*


class GeneralScreenAdapter(private val listener: OnItemClickListener, private val listenerLong: OnItemLongClickListener) : RecyclerView.Adapter<GeneralScreenAdapter.ViewHolder>() {
    private lateinit var images: ArrayList<Image>

    interface OnItemLongClickListener {
        fun onItemLongClick(item: Image): Boolean
    }

    interface OnItemClickListener {
        fun onItemClick(item: Image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.picture_item, parent, false))
    }

    fun setData(images: ArrayList<Image>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun getItemCount() = images.size


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

    fun removeItem(item: Image) {
        images.remove(item)
        notifyDataSetChanged()
    }

    fun sortByDateNewer() {
        images.sortWith(Comparator { o1, o2 ->
            if (o1.date.time > o2.date.time) {
                return@Comparator -1
            } else if (o1.date.time < o2.date.time) {
                return@Comparator 1
            }
            0
        })
        notifyDataSetChanged()
    }

    fun sortByDateOlder() {
        images.sortWith(Comparator { o1, o2 ->
            if (o1.date.time > o2.date.time) {
                return@Comparator 1
            } else if (o1.date.time < o2.date.time) {
                return@Comparator -1
            }
            0
        })
        notifyDataSetChanged()
    }

    fun sortBySizeBigger() {
        images.sortWith(Comparator { o1, o2 ->
            if (o1.size > o2.size) {
                return@Comparator -1
            } else if (o1.size < o2.size) {
                return@Comparator 1
            }
            0
        })
        notifyDataSetChanged()
    }

    fun sortBySizeSmaller() {
        images.sortWith(Comparator { o1, o2 ->
            if (o1.size > o2.size) {
                return@Comparator 1
            } else if (o1.size < o2.size) {
                return@Comparator -1
            }
            0
        })
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.picture_item_image)

        fun bind(image: Image, listener: OnItemClickListener, longListener: OnItemLongClickListener) {
            imageView.setOnClickListener { listener.onItemClick(image) }
            imageView.setOnLongClickListener { longListener.onItemLongClick(image) }
        }
    }
}