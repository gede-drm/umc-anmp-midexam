package com.geded.ubayamedicalcenter160420008.model

import com.google.gson.annotations.SerializedName

data class MedicineTransaction(
    val id:String,
    val date:String,
    @SerializedName("total_price")
    val totalPrice: Int
)
