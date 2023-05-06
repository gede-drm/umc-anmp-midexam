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
import com.geded.ubayamedicalcenter160420008.viewmodel.ConsultationListViewModel

class ConsultationListFragment : Fragment() {
    private lateinit var viewModel:ConsultationListViewModel
    private var consultationListAdapter = ConsultationListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consultation_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ConsultationListViewModel::class.java)
        viewModel.fetch()

        val recViewConsult = view.findViewById<RecyclerView>(R.id.recViewConsultList)
        recViewConsult.layoutManager = LinearLayoutManager(context)
        recViewConsult.adapter = consultationListAdapter

        observeViewModel(view)
    }

    fun observeViewModel(view:View){
        viewModel.consultationLD.observe(viewLifecycleOwner, Observer{
            consultationListAdapter.updateConsultationList(it)
        })
        viewModel.consultationLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtErrorConsult = view.findViewById<TextView>(R.id.txtErrorConsult)

            if(it==true){
                txtErrorConsult.visibility = View.VISIBLE
            }
            else {
                txtErrorConsult.visibility = View.GONE
            }
        })
        viewModel.consultationLoadingLD.observe(viewLifecycleOwner, Observer{
            val recViewConsult = view.findViewById<RecyclerView>(R.id.recViewConsultList)
            val progressLoadConsult = view.findViewById<ProgressBar>(R.id.progressLoadConsult)

            if(it==true){
                recViewConsult.visibility = View.GONE
                progressLoadConsult.visibility = View.VISIBLE
            }
            else{
                recViewConsult.visibility = View.VISIBLE
                progressLoadConsult.visibility = View.GONE
            }
        })
    }
}