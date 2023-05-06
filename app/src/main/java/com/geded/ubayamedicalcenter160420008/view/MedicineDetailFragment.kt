package com.geded.ubayamedicalcenter160420008.view

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.geded.ubayamedicalcenter160420008.R
import com.geded.ubayamedicalcenter160420008.util.loadImage
import com.geded.ubayamedicalcenter160420008.viewmodel.MedicineDetailViewModel

class MedicineDetailFragment : Fragment() {
    private lateinit var viewModel:MedicineDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medicine_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MedicineDetailViewModel::class.java)
        if(arguments != null) {
            val doctorId = MedicineDetailFragmentArgs.fromBundle(requireArguments()).medId
            viewModel.fetch(doctorId.toInt())

            observeViewModel(view)
        }
    }

    fun observeViewModel(view:View){
        viewModel.medicineLD.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtMedNameDetail).text = it.name
            view.findViewById<TextView>(R.id.txtMedCatDetail).text = it.category
            view.findViewById<TextView>(R.id.txtMedPriceDetail).text = "IDR " + NumberFormat.getInstance().format(it.price)
            view.findViewById<TextView>(R.id.txtMedDescDetail).text = it.description

            val progressLoadMedDetail = view.findViewById<ProgressBar>(R.id.progressBarMedDetail)
            view.findViewById<ImageView>(R.id.imgMedDetail).loadImage(it.image_url, progressLoadMedDetail)

            val medicine = it
            val btnAddToCart = view.findViewById<Button>(R.id.btnAddToCart)
            btnAddToCart.setOnClickListener {
                Toast.makeText(activity, medicine.name + " added to cart", Toast.LENGTH_SHORT).show()
            }
        })
    }
}