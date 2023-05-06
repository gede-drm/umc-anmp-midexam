package com.geded.ubayamedicalcenter160420008.view

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
import com.geded.ubayamedicalcenter160420008.R
import com.geded.ubayamedicalcenter160420008.viewmodel.MedicineTransactionsListViewModel

class MedicineBoughtListFragment : Fragment() {
    private lateinit var viewModel:MedicineTransactionsListViewModel
    private var medsTransactionListAdapter = MedicineBoughtListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine_bought_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(MedicineTransactionsListViewModel::class.java)
        viewModel.fetch()

        val recViewMBList = view.findViewById<RecyclerView>(R.id.recMBList)
        recViewMBList.layoutManager = LinearLayoutManager(context)
        recViewMBList.adapter = medsTransactionListAdapter

        observeViewModel(view)
    }

    fun observeViewModel(view:View){
        viewModel.medsTransactionsLD.observe(viewLifecycleOwner, Observer{
            medsTransactionListAdapter.updateMedsBoughtList(it)
        })
        viewModel.medsTransactionsErrorLD.observe(viewLifecycleOwner, Observer{
            val txtErrorMBList = view.findViewById<TextView>(R.id.txtErrorMBList)

            if(it==true){
                txtErrorMBList.visibility = View.VISIBLE
            }
            else {
                txtErrorMBList.visibility = View.GONE
            }
        })
        viewModel.medsTransactionsLoadingLD.observe(viewLifecycleOwner, Observer{
            val recViewMBList = view.findViewById<RecyclerView>(R.id.recMBList)
            val progressLoadMBList = view.findViewById<ProgressBar>(R.id.progressLoadMBList)

            if(it==true){
                recViewMBList.visibility = View.GONE
                progressLoadMBList.visibility = View.VISIBLE
            }
            else{
                recViewMBList.visibility = View.VISIBLE
                progressLoadMBList.visibility = View.GONE
            }
        })
    }
}