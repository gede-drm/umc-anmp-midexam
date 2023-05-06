package com.geded.ubayamedicalcenter160420008.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.geded.ubayamedicalcenter160420008.model.MedTransactionDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLEncoder

class MedicineTransactionDetailViewModel(application: Application): AndroidViewModel(application) {
    val medsTransactionDetailLD = MutableLiveData <ArrayList<MedTransactionDetail>>()
    val medsTransactionDetailErrorLD = MutableLiveData<Boolean>()
    val medsTransactionDetailLoadingLD = MutableLiveData<Boolean>()

    val TAG= "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(medTransactionId:String){
        medsTransactionDetailLoadingLD.value = true
        medsTransactionDetailErrorLD.value = false

        val medTransactionIdEncode = URLEncoder.encode(medTransactionId, "UTF-8")
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.geded.ip4amin.site/anmp-midexam/medicines_bought.php?detail_id=$medTransactionIdEncode"

        val stringRequest = StringRequest(Request.Method.GET, url, {
            val mtdType = object: TypeToken<List<MedTransactionDetail>>(){}.type
            val result = Gson().fromJson<List<MedTransactionDetail>>(it, mtdType)
            medsTransactionDetailLD.value = result as ArrayList<MedTransactionDetail>
            medsTransactionDetailLoadingLD.value = false

            Log.d("showVolleyMedTDList", it)
        },{
            Log.d("showVolleyMedTDList", it.toString())
            medsTransactionDetailErrorLD.value = true
            medsTransactionDetailLoadingLD.value = false
        })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}