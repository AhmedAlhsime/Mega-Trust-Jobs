package com.example.megatrustjobs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.megatrustjobs.R
import com.example.megatrustjobs.view.Home.HomeFragment
import kotlinx.android.synthetic.main.fragment_start.*


class StartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_get_start.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container_main, HomeFragment())
                ?.commit()
        }

    }
}