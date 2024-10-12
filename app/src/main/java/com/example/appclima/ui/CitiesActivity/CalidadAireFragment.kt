package com.example.appclima.ui.CitiesActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appclima.R

class CalidadAireFragment: Fragment() {
    private var currentView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        if (currentView != null){
            return currentView
        } else{
            currentView = inflater.inflate(R.layout.fragment_aire,container,false)
            return currentView
        }

    }
}