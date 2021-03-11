package com.example.megatrustjobs.view.Home

//import com.example.megatrustjobs.view.viewModel.appviewmodel.AppViewModelFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.megatrustjobs.R
import com.example.megatrustjobs.data.local.room.database.JobsDatabase
import com.example.megatrustjobs.data.local.room.entities.JobsEntityes
import com.example.megatrustjobs.data.network.repo.MainRepository
import com.example.megatrustjobs.helpers.BaseHomeFragment
import com.example.megatrustjobs.utils.Status
import com.example.megatrustjobs.view.jobDetails.JobDetailsFragment
import com.example.megatrustjobs.view.viewModel.appviewmodel.HomeViewModel
import com.example.megatrustjobs.helpers.C
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseHomeFragment(), AdapterJobsList.Callback {
    private lateinit var adapterJobsList: AdapterJobsList
    private lateinit var MainRepository: MainRepository
    private var jobPosition: Int? = null
    private var jobsEntityes = ArrayList<JobsEntityes>()

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListProduct()
        setupObservers()

    }


    private fun initListProduct() {
        rv_list_jobs.run {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapterJobsList = AdapterJobsList(this@HomeFragment)
            adapter = adapterJobsList
            isNestedScrollingEnabled = false
        }
    }

    private fun setupObservers() {
        viewModel.data.observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loadingStateListener.showLoadingState(false)
                        resource.data?.let { jobs ->
                            Log.d("homeFragment", "setupObservers: $jobs ")
//                            jobsEntityes.clear()
//                            jobsEntityes = jobs.toCollection(jobsEntityes)
                            retrieveList(jobs)
                        }
                    }
                    Status.ERROR -> {
                        loadingStateListener.showLoadingState(false)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        loadingStateListener.showLoadingState(true)

                    }
                }
            }
        })

    }

    private fun retrieveList(jobs: List<JobsEntityes>) {
        adapterJobsList.apply {
            swapData(jobs)
            notifyDataSetChanged()
        }
    }


    private fun addFav(jobs: List<JobsEntityes>) {
        GlobalScope.launch {
            JobsDatabase.getInstance(requireContext()).jobDeo().insertJobs(jobs)
        }

        adapterJobsList.addOrRemoveProductFromFavourite(jobPosition!!)

    }

    override fun onResume() {
        super.onResume()
        setupObservers()
    }

    override fun onItemClickedItem(items: JobsEntityes) {
        val bundle = Bundle()
        bundle.putParcelable(C.ITEM_JOBS,items)
        val jobDetailsFragment = JobDetailsFragment()
        jobDetailsFragment.arguments = bundle
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.container_main,jobDetailsFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onItemFavItem(items: JobsEntityes, position: Int) {
        jobPosition = position
        addFav(listOf(items))
    }
}