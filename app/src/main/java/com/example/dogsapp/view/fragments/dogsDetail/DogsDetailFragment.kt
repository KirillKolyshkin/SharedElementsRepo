package com.example.dogsapp.view.fragments.dogsDetail

import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.example.dogsapp.R
import com.example.dogsapp.di.di
import com.example.dogsapp.entity.Dogs
import com.example.dogsapp.vm.BaseViewModelFactory

import com.example.dogsapp.vm.DogsDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dogs_detail_fragment.*
import org.kodein.di.generic.instance


class DogsDetailFragment : Fragment() {

    private lateinit var viewModel: DogsDetailViewModel
    private val viewModelFactory: BaseViewModelFactory by di.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.activity?.let { ActivityCompat.postponeEnterTransition(it) }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context)
                .inflateTransition(android.R.transition.move)
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DogsDetailViewModel::class.java)
        viewModel.showProgress.observe(this, Observer { aBoolean ->
            if (aBoolean) {
                showProgress()
            } else {
                hideProgress()
            }
        }
        )
        var dogBreed = arguments?.getString("dog_breed").toString()
        var dogImg = arguments?.getInt("dog_image")
        viewModel.setDogBreed(dogBreed)
        dogImg?.let { viewModel.setDogImg(it) }

        viewModel.dogBreed.observe(this, Observer { setText(dogBreed) })
        viewModel.dogImg.observe(this, Observer { setImg(it) })
    }

    private fun setImg(img: Int) {
        iv_dog_img.setImageResource(img)
    }

    private fun setText(text: String) {
        tv_breed_det.text = text
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dogs_detail_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tv_breed_det.transitionName = arguments?.getString("trId")
            iv_dog_img.transitionName = arguments?.getString("trId1")
        }

    }

    fun showProgress() {
        pb.visibility = View.VISIBLE
    }

    fun hideProgress() {
        pb.visibility = View.GONE
    }

    companion object {
        fun newInstance(dog: Dogs, transitionId : String, transitionId1 : String): DogsDetailFragment {
            val args = Bundle()
            args.putString("dog_breed", dog.status)
            args.putInt("dog_image", dog.uri)
            args.putString("trId", transitionId)
            args.putString("trId1", transitionId1)
            val fragment = DogsDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
