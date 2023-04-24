package com.geded.ubayamedicalcenter.view

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geded.ubayamedicalcenter.R
import com.geded.ubayamedicalcenter.viewmodel.MedicineTransactionDetailViewModel

class MedicineBoughtDetailFragment : Fragment() {
    private lateinit var viewModel:MedicineTransactionDetailViewModel
    private var medsTransactionDetailAdapter = MedicineBoughtDetailAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine_bought_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MedicineTransactionDetailViewModel::class.java)
        if(arguments != null) {
            val medTransactionId = MedicineBoughtDetailFragmentArgs.fromBundle(requireArguments()).medTransactionId
            val medTransactionDate = MedicineBoughtDetailFragmentArgs.fromBundle(requireArguments()).medTransactionDate
            val medTransactionTotalPrice = MedicineBoughtDetailFragmentArgs.fromBundle(requireArguments()).medTransactionTotalPrice

            view.findViewById<TextView>(R.id.txtMBDetailID).text = medTransactionId
            view.findViewById<TextView>(R.id.txtMBDetailDate).text = medTransactionDate
            view.findViewById<TextView>(R.id.txtMBDetailTotalPrice).text = "IDR " + NumberFormat.getInstance().format(medTransactionTotalPrice.toInt())

            viewModel.fetch(medTransactionId)

            val recViewMBDetailList = view.findViewById<RecyclerView>(R.id.recViewMBDetail)
            recViewMBDetailList.layoutManager = LinearLayoutManager(context)
            recViewMBDetailList.adapter = medsTransactionDetailAdapter

            observeViewModel(view)
        }
    }

    fun observeViewModel(view:View){
        viewModel.medsTransactionDetailLD.observe(viewLifecycleOwner, Observer{
            medsTransactionDetailAdapter.updateMedsBoughtDetail(it)
        })
        viewModel.medsTransactionDetailErrorLD.observe(viewLifecycleOwner, Observer{
            val txtErrorMBDetailList = view.findViewById<TextView>(R.id.txtErrorMBDetail)

            if(it==true){
                txtErrorMBDetailList.visibility = View.VISIBLE
            }
            else {
                txtErrorMBDetailList.visibility = View.GONE
            }
        })
        viewModel.medsTransactionDetailLoadingLD.observe(viewLifecycleOwner, Observer{
            val recViewMBDetailList = view.findViewById<RecyclerView>(R.id.recViewMBDetail)
            val progressLoadMBDetailList = view.findViewById<ProgressBar>(R.id.progressLoadMBDetail)

            if(it==true){
                recViewMBDetailList.visibility = View.GONE
                progressLoadMBDetailList.visibility = View.VISIBLE
            }
            else{
                recViewMBDetailList.visibility = View.VISIBLE
                progressLoadMBDetailList.visibility = View.GONE
            }
        })
    }
}