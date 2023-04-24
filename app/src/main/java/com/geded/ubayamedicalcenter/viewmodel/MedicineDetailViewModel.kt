package com.geded.ubayamedicalcenter.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.geded.ubayamedicalcenter.model.Medicine
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MedicineDetailViewModel(application: Application): AndroidViewModel(application)  {
    val medicineLD = MutableLiveData <Medicine>()
    val TAG= "volleyTag"
    private var queue: RequestQueue? = null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun fetch(medicineId:Int){
        queue = Volley.newRequestQueue(getApplication())

        var medicineId = medicineId
        Log.d("medId", medicineId.toString())
        val url="https://www.geded.ip4amin.site/anmp-midexam/medicines.php?id=$medicineId"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val mType = object: TypeToken<Medicine>(){}.type
                val result = Gson().fromJson<Medicine>(it, mType)
                medicineLD.value = result

                Log.d("showVolleyMedDetail", it)
            }, {
                Log.d("showVolleyMedDetail", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}