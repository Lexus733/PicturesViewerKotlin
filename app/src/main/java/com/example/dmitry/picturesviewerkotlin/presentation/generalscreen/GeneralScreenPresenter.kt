package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.picturesviewerkotlin.MainApplication
import com.example.dmitry.picturesviewerkotlin.data.ReposInternal
import com.example.dmitry.picturesviewerkotlin.domain.Image
import com.example.dmitry.picturesviewerkotlin.other.IntentKeys
import com.example.dmitry.picturesviewerkotlin.other.ScreenKeys

@InjectViewState
class GeneralScreenPresenter : MvpPresenter<IGeneralScreen>() {
    private var repos: ReposInternal = ReposInternal()
    private var adapter: GeneralScreenAdapter

    init {
        this.adapter = GeneralScreenAdapter(getOnItemListener(), getLongListener())
        adapter.setData(repos.getData())
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initView(adapter, View.OnClickListener
        { v -> v.context.startActivity(repos.createPhoto()) })
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
                val bundle = Bundle()
                bundle.putString(IntentKeys.PATH_TO_PHOTO, item.path)
                MainApplication.getRouter().navigateTo(ScreenKeys.PICTURE_VIEW, bundle)
            }
        }
    }

    fun onDeleteItem(item: Image) {
        adapter.removeItem(item)
        repos.deleteFile(requireNotNull(item.path, { "Parameter 'item.path' is missing!" }))
    }

    fun onRefreshView() = adapter.setData(repos.getData())

    fun onSortByDateNewer() = adapter.sortByDateNewer()

    fun onSortByDateOlder() = adapter.sortByDateOlder()

    fun onSortBySizeBigger() = adapter.sortBySizeBigger()

    fun onSortBySizeSmaller() = adapter.sortBySizeSmaller()
}