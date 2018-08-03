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
import com.example.dmitry.picturesviewerkotlin.other.IntentKeys
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_picture_view.*
import java.io.File

class PictureViewFragment : MvpAppCompatFragment(), IPictureView {
    companion object {
        fun newInstance(bundle: Bundle): PictureViewFragment {
            val instance = PictureViewFragment()
            instance.arguments = bundle
            return instance
        }
    }

    @InjectPresenter(type = PresenterType.LOCAL)
    lateinit var presenter: PictureViewPresenter

    @ProvidePresenter(type = PresenterType.LOCAL)
    fun providedPictureViewPresenter(): PictureViewPresenter {
        return PictureViewPresenter(
                requireNotNull(arguments, { "Parameter 'arguments' is missing!" }
                )[IntentKeys.PATH_TO_PHOTO] as String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_picture_view, container, false)
    }

    override fun showPicture(path: String) {
        File(path).let {
            Picasso.get()
                    .load(File(path))
                    .error(R.drawable.ic_error_outline_black_24dp)
                    .placeholder(R.drawable.ic_file_download_black_24dp)
                    .into(pictureView_picture_image_view)
        }
    }
}
