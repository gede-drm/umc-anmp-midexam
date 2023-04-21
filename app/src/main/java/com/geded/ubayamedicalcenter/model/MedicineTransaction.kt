package com.geded.ubayamedicalcenter.model

data class MedicineTransaction(
    val id:String,
    val date:String,
    @SerializedName('total_price')
    val totalPrice: Int
)
