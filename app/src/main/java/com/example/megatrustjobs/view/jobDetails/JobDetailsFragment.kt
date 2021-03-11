package com.example.megatrustjobs.view.jobDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.megatrustjobs.R
import com.example.megatrustjobs.data.local.room.database.JobsDatabase
import com.example.megatrustjobs.data.local.room.entities.JobsEntityes
import com.example.megatrustjobs.helpers.C
import com.example.megatrustjobs.helpers.CustomDialog
import com.example.megatrustjobs.view.viewModel.appviewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_job_details.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class JobDetailsFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var jobsEntityes: JobsEntityes
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<JobsEntityes>(C.ITEM_JOBS)?.let {
            jobsEntityes = it
        }

        icon_back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        setInfoJob()
        addToFav(jobsEntityes)
    }


    @SuppressLint("SetTextI18n")
    private fun setInfoJob() {
        jobsEntityes.let {
            job_details_fragment_text.setText(it.title)
            Glide.with(company_logo_fragment_job_details)
                .load(it.company_logo)
                .placeholder(R.drawable.ic_buildings)
                .error(R.drawable.ic_buildings)
                .into(company_logo_fragment_job_details)

            company_name_fragment_job_details.text = it.company
            company_location_fragment_job_details.text = it.location
            company_link_fragment_job_details.text = it.company_url
            Job_name_fragment_job_details.text = it.title
            create_at_fragment_job_details.text =
                "${getString(R.string.create_at)} ${it.created_at}"

            val formattedDescriptionString = getString(R.string.job_description, it.description)
            job_des_fragment_job_details.text =
                HtmlCompat.fromHtml(formattedDescriptionString, HtmlCompat.FROM_HTML_MODE_LEGACY)
            value_job_Type_fragment_job_details.text = it.type
            btn_apply_fragment_job_details.setOnClickListener { view ->
                val formattedHowToApplyString = getString(R.string.job_apply, it.how_to_apply)

                CustomDialog.showErrorDialog(
                    requireContext(),
                    layoutInflater,
                    HtmlCompat.fromHtml(formattedHowToApplyString, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .toString()
                )
            }
            if (it.ic_add_fav == true){
                btn_add_to_fav_fragment_job_details.visibility = View.GONE
            }else{
                btn_add_to_fav_fragment_job_details.visibility = View.VISIBLE
            }
        }
    }

    private fun addToFav(jobsEntityes: JobsEntityes) {

        btn_add_to_fav_fragment_job_details.setOnClickListener {
            jobsEntityes.let {
                it.ic_add_fav = it.ic_add_fav == false
               parentFragmentManager.popBackStack()
            }
        }

    }


}