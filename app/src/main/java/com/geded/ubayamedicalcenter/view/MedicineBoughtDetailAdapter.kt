package com.geded.ubayamedicalcenter.view

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geded.ubayamedicalcenter.R
import com.geded.ubayamedicalcenter.model.MedTransactionDetail

class MedicineBoughtDetailAdapter(val medsBoughtDetailList:ArrayList<MedTransactionDetail>): RecyclerView.Adapter<MedicineBoughtDetailAdapter.MedicineBoughtDetailViewHolder>() {
    class MedicineBoughtDetailViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineBoughtDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.medicine_bought_detail_layout, parent, false)

        return MedicineBoughtDetailAdapter.MedicineBoughtDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineBoughtDetailViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtMBDCardMedName).text = medsBoughtDetailList[position].name
        holder.view.findViewById<TextView>(R.id.txtMBDCardQuantity).text = medsBoughtDetailList[position].quantity.toString()
        holder.view.findViewById<TextView>(R.id.txtMBDCardPrice).text = "IDR " + NumberFormat.getInstance().format(medsBoughtDetailList[position].price)
        holder.view.findViewById<TextView>(R.id.txtMBDCardSubTotal).text = "IDR " + NumberFormat.getInstance().format(medsBoughtDetailList[position].subTotal)
    }

    override fun getItemCount(): Int {
        return medsBoughtDetailList.size
    }

    fun updateMedsBoughtDetail(newMedsBoughtDetailList:ArrayList<MedTransactionDetail>){
        medsBoughtDetailList.clear()
        medsBoughtDetailList.addAll(newMedsBoughtDetailList)
        notifyDataSetChanged()
    }
}