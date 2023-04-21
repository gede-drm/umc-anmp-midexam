package com.geded.ubayamedicalcenter.model

data class Doctor(
    val id:Int,
    val name:String,
    val specialty:String,
    val description:String,
    val schedule:String,
    val fee:Int,
    val photo_url:String
    )
