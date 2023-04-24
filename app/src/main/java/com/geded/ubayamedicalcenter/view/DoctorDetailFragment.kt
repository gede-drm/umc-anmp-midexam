package com.geded.ubayamedicalcenter.view

import android.icu.text.NumberFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.geded.ubayamedicalcenter.R
import com.geded.ubayamedicalcenter.util.loadImage
import com.geded.ubayamedicalcenter.viewmodel.DoctorDetailViewModel

class DoctorDetailFragment : Fragment() {
    private lateinit var viewModel:DoctorDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DoctorDetailViewModel::class.java)
        if(arguments != null) {
            val doctorId = DoctorDetailFragmentArgs.fromBundle(requireArguments()).doctorId
            viewModel.fetch(doctorId.toInt())

            observeViewModel(view)
        }
    }

    fun observeViewModel(view:View){
        viewModel.doctorLD.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.txtDocNameDetail).text = it.name
            view.findViewById<TextView>(R.id.txtDocSpecialtyDetail).text = it.specialty
            view.findViewById<TextView>(R.id.txtDocSchedules).text = it.schedule
            view.findViewById<TextView>(R.id.txtDocQualifications).text = it.description
            view.findViewById<TextView>(R.id.txtConsultFee).text = "IDR " + NumberFormat.getInstance().format(it.fee)

            val progressLoadDocDetail = view.findViewById<ProgressBar>(R.id.progressBarDocDetail)
            view.findViewById<ImageView>(R.id.imgProfileDocDetail).loadImage(it.photo_url, progressLoadDocDetail)

            val doctor = it
            val btnMakeAppointment = view.findViewById<Button>(R.id.btnMakeAppointment)
            btnMakeAppointment.setOnClickListener {
                Toast.makeText(activity, "Appointment with doctor " + doctor.name + " successfully created", Toast.LENGTH_SHORT).show()
            }
        })
    }
}