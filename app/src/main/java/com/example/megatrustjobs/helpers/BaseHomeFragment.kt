package com.example.megatrustjobs.helpers

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment

abstract class BaseHomeFragment : Fragment() {
    private val TAG = "AppDebug"
    lateinit var loadingStateListener: LoadingStateListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            loadingStateListener = context as LoadingStateListener
        }catch (e:ClassCastException){
            Log.e(TAG, "$context must implement LoadingStateListener Interface")
        }
    }
}