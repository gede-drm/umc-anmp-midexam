package com.geded.ubayamedicalcenter160420008.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.geded.ubayamedicalcenter160420008.model.MedicineTransaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MedicineTransactionsListViewModel(application: Application): AndroidViewModel(application) {
    val medsTransactionsLD = MutableLiveData<ArrayList<MedicineTransaction>>()
    val medsTransactionsErrorLD = MutableLiveData<Boolean>()
    val medsTransactionsLoadingLD = MutableLiveData<Boolean>()

    val TAG= "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(){
        medsTransactionsLoadingLD.value = true
        medsTransactionsErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.geded.ip4amin.site/anmp-midexam/medicines_bought.php"

        val stringRequest = StringRequest(Request.Method.GET, url, {
            val mtType = object: TypeToken<List<MedicineTransaction>>(){}.type
            val result = Gson().fromJson<List<MedicineTransaction>>(it, mtType)
            medsTransactionsLD.value = result as ArrayList<MedicineTransaction>
            medsTransactionsLoadingLD.value = false

            Log.d("showVolleyMedTList", it)
        },{
            Log.d("showVolleyMedTList", it.toString())
            medsTransactionsErrorLD.value = true
            medsTransactionsLoadingLD.value = false
        })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}