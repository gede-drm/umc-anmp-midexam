package com.geded.ubayamedicalcenter.view

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geded.ubayamedicalcenter.R
import com.geded.ubayamedicalcenter.model.MedicineTransaction

class MedicineBoughtListAdapter(val medsBoughtList:ArrayList<MedicineTransaction>): RecyclerView.Adapter<MedicineBoughtListAdapter.MedicineBoughtListViewHolder>() {
    class MedicineBoughtListViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MedicineBoughtListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.medicine_bought_list, parent, false)

        return MedicineBoughtListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineBoughtListViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtDateMBList).text = medsBoughtList[position].date
        holder.view.findViewById<TextView>(R.id.txtIDMBList).text = medsBoughtList[position].id
        holder.view.findViewById<TextView>(R.id.txtTotalPriceMBList).text = "IDR " + NumberFormat.getInstance().format(medsBoughtList[position].totalPrice)
    }

    override fun getItemCount(): Int {
        return medsBoughtList.size
    }

    fun updateMedsBoughtList(newMedsBoughtList:ArrayList<MedicineTransaction>){
        medsBoughtList.clear()
        medsBoughtList.addAll(newMedsBoughtList)
        notifyDataSetChanged()
    }
}