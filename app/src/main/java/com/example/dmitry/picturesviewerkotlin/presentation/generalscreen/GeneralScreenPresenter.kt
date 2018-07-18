package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen

import android.view.View
import com.example.dmitry.picturesviewerkotlin.R
import com.example.dmitry.picturesviewerkotlin.data.ReposInternal
import com.example.dmitry.picturesviewerkotlin.domain.Image
import java.util.*
import java.util.Collections.sort


class GeneralScreenPresenter(var view: IGeneralScreen.View) {
    private var repos: ReposInternal = ReposInternal()
    private var images = ArrayList<Image>()

    private var adapter: GeneralScreenAdapter

    private var sortBySize: Boolean = false
    private var sortByDate: Boolean = false

    init {
        this.images = repos.getData()
        this.adapter = GeneralScreenAdapter(images, getOnItemListener(), getLongListener())
    }

    fun menuSortBySize() {
        if (sortBySize) {
            view.showMessage(R.string.sortBySizeBigger)

            sort(images) { o1, o2 ->
                if (o1.size > o2.size) {
                    return@sort -1
                } else if (o1.size < o2.size) {
                    return@sort 1
                }
                0
            }

            sortBySize = !sortBySize
            adapter.notifyDataSetChanged()
        } else {
            view.showMessage(R.string.sortBySizeSmaller)

            sort(images) { o1, o2 ->
                if (o1.size > o2.size) {
                    return@sort 1
                } else if (o1.size < o2.size) {
                    return@sort -1
                }
                0
            }

            sortBySize = !sortBySize
            adapter.notifyDataSetChanged()
        }
    }

    fun menuSortByDate() {
        if (sortByDate) {
            view.showMessage(R.string.sortByDateNewer)

            sort(images) { o1, o2 ->
                if (o1.date.time > o2.date.time) {
                    return@sort -1
                } else if (o1.date.time < o2.date.time) {
                    return@sort 1
                }
                0
            }

            sortByDate = !sortByDate
            adapter.notifyDataSetChanged()
        } else {
            view.showMessage(R.string.sortByDateOlder)

            sort(images) { o1, o2 ->
                if (o1.date.time > o2.date.time) {
                    return@sort 1
                } else if (o1.date.time < o2.date.time) {
                    return@sort -1
                }
                0
            }

            sortByDate = !sortByDate
            adapter.notifyDataSetChanged()
        }
    }


    private fun getLongListener(): GeneralScreenAdapter.OnItemLongClickListener {
        return object : GeneralScreenAdapter.OnItemLongClickListener {
            override fun onItemLongClick(item: Image): Boolean {
                view.showDialog(item)
                return true
            }
        }
    }

    private fun getOnItemListener(): GeneralScreenAdapter.OnItemClickListener {
        return object : GeneralScreenAdapter.OnItemClickListener {
            override fun onItemClick(item: Image) {
                //listener to another fragment
                //need add menu
            }
        }
    }

    private fun createPhotoListener(): View.OnClickListener {
        return View.OnClickListener { v -> v.context.startActivity(repos.createPhoto()) }
    }

    fun setPhotoListener() {
        view.setOnClickCreatePhoto(createPhotoListener())
    }

    fun deleteItem(item: Image) {
        images.remove(item)
        repos.deleteFile(item.path!!)
        adapter.notifyDataSetChanged()
    }

    fun getAdapter(): GeneralScreenAdapter {
        return adapter
    }

}