package com.geded.ubayamedicalcenter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.geded.ubayamedicalcenter.R

class ProfileFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAppointmentHis = view.findViewById<Button>(R.id.btnAppointmentHis)
        val btnMedTransactionHis = view.findViewById<Button>(R.id.btnMedTransactionHis)

        btnAppointmentHis.setOnClickListener {
            val action = ProfileFragmentDirections.actionConsultList()
            Navigation.findNavController(it).navigate(action)
        }

        btnMedTransactionHis.setOnClickListener {
            val action = ProfileFragmentDirections.actionMedBoughtList()
            Navigation.findNavController(it).navigate(action)
        }
    }
}