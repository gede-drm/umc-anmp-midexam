package com.geded.ubayamedicalcenter160420008.view

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geded.ubayamedicalcenter160420008.R
import com.geded.ubayamedicalcenter160420008.model.Consultation

class ConsultationListAdapter(val consultationList:ArrayList<Consultation>):RecyclerView.Adapter<ConsultationListAdapter.ConsultationViewHolder>() {
    class ConsultationViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.consultation_list_layout, parent, false)

        return ConsultationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConsultationViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtDateConsultList).text = consultationList[position].date
        holder.view.findViewById<TextView>(R.id.txtDocNameConsultList).text = consultationList[position].doctor
        holder.view.findViewById<TextView>(R.id.txtFeeConsultationList).text = "IDR " + NumberFormat.getInstance().format(consultationList[position].fee)
    }

    override fun getItemCount(): Int {
        return consultationList.size
    }

    fun updateConsultationList(newConsultationList:ArrayList<Consultation>){
        consultationList.clear()
        consultationList.addAll(newConsultationList)
        notifyDataSetChanged()
    }
}