package com.geded.ubayamedicalcenter160420008.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.geded.ubayamedicalcenter160420008.model.Consultation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConsultationListViewModel(application: Application): AndroidViewModel(application) {
    val consultationLD = MutableLiveData<ArrayList<Consultation>>()
    val consultationLoadErrorLD = MutableLiveData<Boolean>()
    val consultationLoadingLD = MutableLiveData<Boolean>()

    val TAG= "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(){
        consultationLoadingLD.value = true
        consultationLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.geded.ip4amin.site/anmp-midexam/consultations.json"

        val stringRequest = StringRequest(Request.Method.GET, url, {
            val dType = object: TypeToken<List<Consultation>>(){}.type
            val result = Gson().fromJson<List<Consultation>>(it, dType)
            consultationLD.value = result as ArrayList<Consultation>
            consultationLoadingLD.value = false

            Log.d("showVolleyConsultationList", it)
        },{
            Log.d("showVolleyConsultationList", it.toString())
            consultationLoadErrorLD.value = true
            consultationLoadingLD.value = false
        })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}