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
import com.geded.ubayamedicalcenter160420008.viewmodel.MedicineListViewModel

class PharmacyFragment : Fragment() {
    private lateinit var viewModel:MedicineListViewModel
    private var medicineListAdapter = MedicineListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pharmacy, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MedicineListViewModel::class.java)
        viewModel.fetch()

        val recViewMedicines = view.findViewById<RecyclerView>(R.id.recViewMedicines)
        recViewMedicines.layoutManager = LinearLayoutManager(context)
        recViewMedicines.adapter = medicineListAdapter

        observeViewModel(view)
    }

    fun observeViewModel(view:View){
        viewModel.medicinesLD.observe(viewLifecycleOwner, Observer{
            medicineListAdapter.updateMedicineList(it)
        })
        viewModel.medicineLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtErrorMed = view.findViewById<TextView>(R.id.txtErrorMedList)

            if(it==true){
                txtErrorMed.visibility = View.VISIBLE
            }
            else {
                txtErrorMed.visibility = View.GONE
            }
        })
        viewModel.medicineLoadingLD.observe(viewLifecycleOwner, Observer{
            val recViewMedicines = view.findViewById<RecyclerView>(R.id.recViewMedicines)
            val progressLoadMed = view.findViewById<ProgressBar>(R.id.progressLoadMedList)

            if(it==true){
                recViewMedicines.visibility = View.GONE
                progressLoadMed.visibility = View.VISIBLE
            }
            else{
                recViewMedicines.visibility = View.VISIBLE
                progressLoadMed.visibility = View.GONE
            }
        })
    }
}