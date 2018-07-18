package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dmitry.picturesviewerkotlin.R
import com.example.dmitry.picturesviewerkotlin.domain.Image
import kotlinx.android.synthetic.main.fragment_general_screen.*


class GeneralScreenFragment : Fragment(), IGeneralScreen.View {
    private lateinit var generalScreenPresenter: GeneralScreenPresenter

    private var deleteDialog: AlertDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_general_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagesList.layoutManager = GridLayoutManager(context,3)
        generalScreenPresenter = GeneralScreenPresenter(this)
        imagesList.adapter = generalScreenPresenter.getAdapter()
        generalScreenPresenter.setPhotoListener()

    }

    override fun setOnClickCreatePhoto(clickCreatePhoto: View.OnClickListener) {
        fab_button_create_photo.setOnClickListener(clickCreatePhoto)
    }

    override fun showMessage(id: Int) {
        Toast.makeText(activity!!.applicationContext, resources.getString(id), Toast.LENGTH_SHORT).show()
    }

    override fun showDialog(image: Image) {
        if (deleteDialog != null) {
            deleteDialog!!.dismiss()
            deleteDialog = null
            deleteDialog = createDeleteDialog(image)
        } else {
            deleteDialog = createDeleteDialog(image)
            deleteDialog!!.show()
        }
    }

    private fun createDeleteDialog(item: Image): AlertDialog {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setPositiveButton(R.string.dialog_yes) { _, _ -> generalScreenPresenter.deleteItem(item) }
                .setNegativeButton(R.string.dialog_cancel) { arg0, _ -> arg0.dismiss() }
        return alertDialogBuilder.create()
    }
}
