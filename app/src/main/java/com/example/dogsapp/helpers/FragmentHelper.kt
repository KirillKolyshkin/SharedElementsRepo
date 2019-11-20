package com.example.dogsapp.helpers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager


object FragmentHelper {
    fun getFragmentManager(context: Context): FragmentManager {
        return (context as AppCompatActivity).supportFragmentManager
    }

    fun openFragment(context: FragmentActivity?, frameId: Int, fragment: Fragment) {
        if (context != null) {
            getFragmentManager(context).beginTransaction()
                    .replace(frameId, fragment,fragment.javaClass.name)
                    .addToBackStack(null).commit()
        }
    }
}
