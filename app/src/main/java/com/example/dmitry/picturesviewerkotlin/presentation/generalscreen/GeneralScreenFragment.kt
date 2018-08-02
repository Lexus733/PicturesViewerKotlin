package com.example.dmitry.picturesviewerkotlin.presentation.generalscreen


import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.example.dmitry.picturesviewerkotlin.R
import com.example.dmitry.picturesviewerkotlin.domain.Image
import kotlinx.android.synthetic.main.fragment_general_screen.*


class GeneralScreenFragment : MvpAppCompatFragment(), IGeneralScreen {
    @InjectPresenter(type = PresenterType.GLOBAL)
    lateinit var presenter: GeneralScreenPresenter

    private var deleteDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_general_screen, container, false)
    }

    override fun initView(adapter: GeneralScreenAdapter, listener: View.OnClickListener) {
        imagesList.layoutManager = GridLayoutManager(context, 3)
        imagesList.adapter = adapter
        fab_button_create_photo.setOnClickListener(listener)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.general_screen_menu_items, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when (it.itemId) {
                R.id.sortedByDateNewer -> {
                    presenter.onSortByDateNewer()
                }
                R.id.sortedByDateOlder -> {
                    presenter.onSortByDateOlder()
                }
                R.id.sortedBySizeBigger -> {
                    presenter.onSortBySizeBigger()
                }
                R.id.sortedBySizeSmaller -> {
                    presenter.onSortBySizeSmaller()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        presenter.onRefreshView()
    }

    override fun showMessage(id: Int) {
        Toast.makeText(requireNotNull(activity,{"Parameter 'activity' is missing!"}).applicationContext,
                resources.getString(id), Toast.LENGTH_SHORT).show()
    }

    override fun showDialog(image: Image) {
        deleteDialog?.let { requireNotNull(deleteDialog,{"Parameter 'deleteDialog' is missing!"}).dismiss() }

        deleteDialog = createDeleteDialog(image)
        requireNotNull(deleteDialog,{"Parameter 'deleteDialog' is missing!"}).show()
    }

    private fun createDeleteDialog(item: Image): AlertDialog {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setPositiveButton(R.string.dialog_yes) { _, _ -> presenter.onDeleteItem(item) }
                .setNegativeButton(R.string.dialog_cancel) { dialog, _ -> dialog.dismiss() }
        return alertDialogBuilder.create()
    }
}
