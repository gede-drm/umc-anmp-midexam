package com.geded.ubayamedicalcenter160420008.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.geded.ubayamedicalcenter160420008.R
import com.geded.ubayamedicalcenter160420008.model.Doctor
import com.geded.ubayamedicalcenter160420008.util.loadImage

class DoctorListAdapter(val doctorList:ArrayList<Doctor>):RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>() {
    class DoctorViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.doctor_list_layout, parent, false)

        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtDocNameList).text = doctorList[position].name
        holder.view.findViewById<TextView>(R.id.txtDocSpecialtyList).text = doctorList[position].specialty

        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarDocList)
        holder.view.findViewById<ImageView>(R.id.imgDocList).loadImage(doctorList[position].photo_url, progressBar)

        val btnDocDetail = holder.view.findViewById<Button>(R.id.btnDocDetail)
        btnDocDetail.setOnClickListener {
            val action = DoctorsListFragmentDirections.actionDoctorDetail(doctorList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }


    fun updateDoctorList(newDoctorList:ArrayList<Doctor>){
        doctorList.clear()
        doctorList.addAll(newDoctorList)
        notifyDataSetChanged()
    }
}