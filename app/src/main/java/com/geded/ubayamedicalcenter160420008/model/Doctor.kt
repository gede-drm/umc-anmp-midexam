package com.geded.ubayamedicalcenter160420008.model

data class Doctor(
    val id:Int,
    val name:String,
    val specialty:String,
    val description:String,
    val schedule:String,
    val fee:Int,
    val photo_url:String
    )
