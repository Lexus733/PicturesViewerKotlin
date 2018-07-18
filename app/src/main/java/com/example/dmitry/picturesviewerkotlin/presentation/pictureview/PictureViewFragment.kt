package com.example.dmitry.picturesviewerkotlin.presentation.pictureview


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dmitry.picturesviewerkotlin.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_picture_view.*
import java.io.File


class PictureViewFragment : Fragment(), IPictureView.View {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_picture_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PictureViewPresenter(this, activity?.intent!!)
    }

    override fun showPicture(path: String) {
        Picasso.get()
                .load(File(path))
                .error(R.drawable.ic_error_outline_black_24dp)
                .placeholder(R.drawable.ic_file_download_black_24dp)
                .into(pictureView_picture_image_view)
    }

}
