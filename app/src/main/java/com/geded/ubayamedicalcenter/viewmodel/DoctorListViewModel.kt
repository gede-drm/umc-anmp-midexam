package com.geded.ubayamedicalcenter.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.geded.ubayamedicalcenter.model.Doctor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoctorListViewModel(application: Application):AndroidViewModel(application) {
    val doctorsLD = MutableLiveData<ArrayList<Doctor>>()
    val doctorLoadErrorLD = MutableLiveData<Boolean>()
    val doctorLoadingLD = MutableLiveData<Boolean>()

    val TAG= "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(){
        doctorLoadingLD.value = true
        doctorLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.geded.ip4amin.site/anmp-midexam/doctors.json"

        val stringRequest = StringRequest(Request.Method.GET, url, {
            val dType = object:TypeToken<List<Doctor>>(){}.type
            val result = Gson().fromJson<List<Doctor>>(it, dType)
            doctorsLD.value = result as ArrayList<Doctor>
            doctorLoadingLD.value = false

            Log.d("showVolleyDocList", it)
        },{
            Log.d("showVolleyDocList", it.toString())
            doctorLoadErrorLD.value = true
            doctorLoadingLD.value = false
        })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}