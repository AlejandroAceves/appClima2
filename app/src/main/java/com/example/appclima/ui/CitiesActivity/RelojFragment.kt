package com.example.appclima.ui.CitiesActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import com.example.appclima.R

class RelojFragment: Fragment() {
    private var currentView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?):View? {
        super.onCreate(savedInstanceState)
        if (currentView != null){
            return currentView
        } else{
            currentView = inflater.inflate(R.layout.fragment_reloj,container,false)
            return currentView
        }

    }
}