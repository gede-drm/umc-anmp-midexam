package com.geded.ubayamedicalcenter160420008.view

import android.icu.text.NumberFormat
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
import com.geded.ubayamedicalcenter160420008.model.Medicine
import com.geded.ubayamedicalcenter160420008.util.loadImage

class MedicineListAdapter(val medicineList:ArrayList<Medicine>): RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>() {
    class MedicineViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.medicine_list_layout, parent, false)

        return MedicineListAdapter.MedicineViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.txtMedNameList).text = medicineList[position].name
        holder.view.findViewById<TextView>(R.id.txtMedCatList).text = medicineList[position].category
        holder.view.findViewById<TextView>(R.id.txtMedPriceList).text = "IDR " + NumberFormat.getInstance().format(medicineList[position].price)

        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBarMedList)
        holder.view.findViewById<ImageView>(R.id.imgMedList).loadImage(medicineList[position].image_url, progressBar)

        val btnMedDetail = holder.view.findViewById<Button>(R.id.btnMedDetail)
        btnMedDetail.setOnClickListener {
            val action = PharmacyFragmentDirections.actionMedicineDetail(medicineList[position].id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return medicineList.size
    }

    fun updateMedicineList(newMedicineList:ArrayList<Medicine>){
        medicineList.clear()
        medicineList.addAll(newMedicineList)
        notifyDataSetChanged()
    }
}
