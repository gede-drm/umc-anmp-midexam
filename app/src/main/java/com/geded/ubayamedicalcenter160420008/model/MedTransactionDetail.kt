package com.geded.ubayamedicalcenter160420008.model

import com.google.gson.annotations.SerializedName

data class MedTransactionDetail(
    val name:String,
    val quantity:Int,
    val price:Int,
    @SerializedName("sub_total")
    val subTotal:Int
)
