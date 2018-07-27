package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.picturesviewerkotlin.R
import com.example.dmitry.picturesviewerkotlin.data.ReposInternal
import com.example.dmitry.picturesviewerkotlin.domain.Image
import com.example.dmitry.picturesviewerkotlin.other.IntentKeys
import java.util.*
import java.util.Collections.sort

@InjectViewState
class GeneralScreenPresenter : MvpPresenter<IGeneralScreen>() {
    private var bundle = Bundle()

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
            viewState.showMessage(R.string.sortBySizeBigger)

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
            viewState.showMessage(R.string.sortBySizeSmaller)

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
            viewState.showMessage(R.string.sortByDateNewer)

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
            viewState.showMessage(R.string.sortByDateOlder)

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
                viewState.showDialog(item)
                return true
            }
        }
    }

    private fun getOnItemListener(): GeneralScreenAdapter.OnItemClickListener {
        return object : GeneralScreenAdapter.OnItemClickListener {
            override fun onItemClick(item: Image) {
                bundle.putString(IntentKeys.PATH_TO_PHOTO, item.path)
                viewState.goToFragment(bundle)
            }
        }
    }

    private fun createPhotoListener(): View.OnClickListener {
        return View.OnClickListener { v -> v.context.startActivity(repos.createPhoto()) }
    }

    fun setPhotoListener() {
        viewState.setOnClickCreatePhoto(createPhotoListener())
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