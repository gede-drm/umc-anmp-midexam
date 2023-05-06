package com.geded.ubayamedicalcenter160420008.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.geded.ubayamedicalcenter160420008.model.Doctor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoctorDetailViewModel(application: Application): AndroidViewModel(application) {
    val doctorLD = MutableLiveData<Doctor>()
    val TAG= "volleyTag"
    private var queue: RequestQueue? = null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun fetch(doctorId:Int){
        queue = Volley.newRequestQueue(getApplication())

        var doctorId = doctorId
        val url="https://www.geded.ip4amin.site/anmp-midexam/doctors.php?id=$doctorId"

        val stringRequest = StringRequest(Request.Method.GET, url,
            {
                val dType = object:TypeToken<Doctor>(){}.type
                val result = Gson().fromJson<Doctor>(it, dType)
                doctorLD.value = result

                Log.d("showVolleyDocDetail", it)
            }, {
                Log.d("showVolleyDocDetail", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}