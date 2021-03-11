package com.example.megatrustjobs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.example.megatrustjobs.helpers.LoadingStateListener
import com.example.megatrustjobs.view.StartFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),LoadingStateListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container_main,StartFragment())
            .commit()
    }

    override fun showLoadingState(isLoading: Boolean) {
        if (isLoading) {
//            activity_main_buyer_home_parent_relative_layout.visibility = View.INVISIBLE
            progressbar_activity_home.visibility = View.VISIBLE
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else {
//            activity_main_buyer_home_parent_relative_layout.visibility = View.VISIBLE
            progressbar_activity_home.visibility = View.GONE
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }
}