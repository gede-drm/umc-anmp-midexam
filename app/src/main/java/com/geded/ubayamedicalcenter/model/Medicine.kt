package com.geded.ubayamedicalcenter.model

data class Medicine(
    val id:Int,
    val name:String,
    val price:Int,
    val description:String,
    val category:String,
    val image_url:String
    )