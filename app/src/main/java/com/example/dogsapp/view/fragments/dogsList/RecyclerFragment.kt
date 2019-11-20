package com.example.dogsapp.view.fragments.dogsList

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsapp.R
import com.example.dogsapp.di.di
import com.example.dogsapp.entity.Dogs
import com.example.dogsapp.view.fragments.dogsDetail.DogsDetailFragment
import com.example.dogsapp.vm.BaseViewModelFactory
import com.example.dogsapp.vm.DogsListViewModel
import kotlinx.android.synthetic.main.fragment_recycler.*
import kotlinx.android.synthetic.main.number_item.*
import org.kodein.di.generic.instance
import androidx.transition.TransitionInflater
import androidx.transition.Visibility


class RecyclerFragment : Fragment() {

    private val viewModelFactory: BaseViewModelFactory by di.instance()
    private var recyclerAdapter = RecyclerAdapter { x, z, y -> onItemClick(x, z, y) }
    private lateinit var viewModel: DogsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DogsListViewModel::class.java)
        observeDogsList()
        viewModel.getBreedList()

    }

    private fun observeDogsList() =
        viewModel.breedList.observe(this, Observer { dogList ->
            updateList(dogList)
        })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_recycler, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = LinearLayoutManager(context)
        iv_hidden.isVisible = false
        iv_hidden.alpha = 0f
        iv_hidden.setOnClickListener { Toast.makeText(context, "you click me", Toast.LENGTH_SHORT).show() }

        recycler_view.adapter = recyclerAdapter
        recycler_view.layoutManager = manager
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0) {
                    iv_hidden.animate().setDuration(500).alpha(0f).withEndAction { iv_hidden.isVisible = false }
                }
                if (dy > 0) {
                    iv_hidden.animate().setDuration(500).alpha(1f).withStartAction { iv_hidden.isVisible = true }
                }
            }
        })
    }

    private fun updateList(dogList: ArrayList<Dogs>) {
        recyclerAdapter.list = dogList
    }

    private fun onItemClick(dog: Dogs, tv: TextView, iv: ImageView) {
        val trId = ViewCompat.getTransitionName(tv).toString()
        val trId1 = ViewCompat.getTransitionName(iv).toString()
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.addSharedElement(tv, trId)
            ?.addSharedElement(iv, trId1)
            ?.addToBackStack("JoJo")
            ?.replace(R.id.container, DogsDetailFragment.newInstance(dog, trId, trId1))
            ?.commit()

    }

    companion object {
        fun newInstance(): RecyclerFragment {
            val args = Bundle()
            val fragment = RecyclerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
