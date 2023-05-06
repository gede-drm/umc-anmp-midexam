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
import com.geded.ubayamedicalcenter160420008.viewmodel.DoctorListViewModel

class DoctorsListFragment : Fragment() {
    private lateinit var viewModel: DoctorListViewModel
    private var doctorListAdapter = DoctorListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctors_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DoctorListViewModel::class.java)
        viewModel.fetch()

        val recViewOoctors = view.findViewById<RecyclerView>(R.id.recViewDoctors)
        recViewOoctors.layoutManager = LinearLayoutManager(context)
        recViewOoctors.adapter = doctorListAdapter

        observeViewModel(view)
    }

    fun observeViewModel(view:View){
        viewModel.doctorsLD.observe(viewLifecycleOwner, Observer{
            doctorListAdapter.updateDoctorList(it)
        })
        viewModel.doctorLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtErrorDoc = view.findViewById<TextView>(R.id.txtErrorDoc)

            if(it==true){
                txtErrorDoc.visibility = View.VISIBLE
            }
            else {
                txtErrorDoc.visibility = View.GONE
            }
        })
        viewModel.doctorLoadingLD.observe(viewLifecycleOwner, Observer{
            val recViewDoctors = view.findViewById<RecyclerView>(R.id.recViewDoctors)
            val progressLoadDoc = view.findViewById<ProgressBar>(R.id.progressLoadDoc)

            if(it==true){
                recViewDoctors.visibility = View.GONE
                progressLoadDoc.visibility = View.VISIBLE
            }
            else{
                recViewDoctors.visibility = View.VISIBLE
                progressLoadDoc.visibility = View.GONE
            }
        })
    }
}