package com.example.dmitry.picturesviewerkotlin.presentation.pictureview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.PresenterType
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dmitry.picturesviewerkotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_picture_view.*
import java.io.File

class PictureViewFragment : MvpAppCompatFragment(), IPictureView {
    companion object {
        private lateinit var bundle: Bundle
        fun newInstance(bundle: Bundle): PictureViewFragment {
            this.bundle = bundle
            return PictureViewFragment()
        }
    }

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: PictureViewPresenter

    @ProvidePresenter(type = PresenterType.LOCAL)
    fun providedPictureViewPresenter(): PictureViewPresenter {
        return PictureViewPresenter(bundle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_picture_view, container, false)
    }

    override fun showPicture(path: String) {
        Picasso.get()
                .load(File(path))
                .error(R.drawable.ic_error_outline_black_24dp)
                .placeholder(R.drawable.ic_file_download_black_24dp)
                .into(pictureView_picture_image_view)
    }
}
