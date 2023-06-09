package com.geded.ubayamedicalcenter160420008.view

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.geded.ubayamedicalcenter160420008.R
import com.geded.ubayamedicalcenter160420008.model.MedicineTransaction

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

        val btnMBDetail = holder.view.findViewById<Button>(R.id.btnDetailMBList)
        btnMBDetail.setOnClickListener {
            val action = MedicineBoughtListFragmentDirections.actionMedicineBoughtDetail(medsBoughtList[position].id.toString(), medsBoughtList[position].date, medsBoughtList[position].totalPrice.toString())
            Navigation.findNavController(it).navigate(action)
        }
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