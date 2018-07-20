package com.example.dmitry.picturesviewerkotlin.presentation.pictureview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dmitry.picturesviewerkotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_picture_view.*
import java.io.File


class PictureViewFragment : MvpAppCompatFragment(), IPictureView {
    @InjectPresenter
    lateinit var presenter: PictureViewPresenter

    @ProvidePresenter
    fun providedPictureViewPresenter(): PictureViewPresenter {
        return PictureViewPresenter(this.arguments!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_picture_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun showPicture(path: String) {
        Picasso.get()
                .load(File(path))
                .error(R.drawable.ic_error_outline_black_24dp)
                .placeholder(R.drawable.ic_file_download_black_24dp)
                .into(pictureView_picture_image_view)
    }
}
