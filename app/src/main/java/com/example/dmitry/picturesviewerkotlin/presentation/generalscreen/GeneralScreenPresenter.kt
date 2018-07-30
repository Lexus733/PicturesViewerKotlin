package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.dmitry.picturesviewerkotlin.presentation.mainactivity.MainApplication
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
        viewState.initView(adapter, View.OnClickListener { v -> v.context.startActivity(repos.createPhoto()) })
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
        repos.deleteFile(item.path!!)
        adapter.notifyDataSetChanged()
    }

    fun onRefreshView() {
        adapter.setData(repos.getData())
    }

    fun onCancelDelete(arg0: DialogInterface) {
        arg0.dismiss()
    }

    fun onSortByDateNewer() {
        adapter.sortByDateNewer()
        adapter.notifyDataSetChanged()
    }

    fun onSortByDateOlder() {
        adapter.sortByDateOlder()
        adapter.notifyDataSetChanged()
    }

    fun onSortBySizeBigger() {
        adapter.sortBySizeBigger()
        adapter.notifyDataSetChanged()
    }

    fun onSortBySizeSmaller() {
        adapter.sortBySizeSmaller()
        adapter.notifyDataSetChanged()
    }
}