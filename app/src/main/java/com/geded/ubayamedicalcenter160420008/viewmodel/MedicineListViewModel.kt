package com.geded.ubayamedicalcenter160420008.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.geded.ubayamedicalcenter160420008.model.Medicine
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MedicineListViewModel(application: Application): AndroidViewModel(application) {
    val medicinesLD = MutableLiveData<ArrayList<Medicine>>()
    val medicineLoadErrorLD = MutableLiveData<Boolean>()
    val medicineLoadingLD = MutableLiveData<Boolean>()

    val TAG= "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(){
        medicineLoadingLD.value = true
        medicineLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.geded.ip4amin.site/anmp-midexam/medicines.php"

        val stringRequest = StringRequest(Request.Method.GET, url, {
            val dType = object: TypeToken<List<Medicine>>(){}.type
            val result = Gson().fromJson<List<Medicine>>(it, dType)
            medicinesLD.value = result as ArrayList<Medicine>
            medicineLoadingLD.value = false

            Log.d("showVolleyMedList", it)
        },{
            Log.d("showVolleyMedList", it.toString())
            medicineLoadErrorLD.value = true
            medicineLoadingLD.value = false
        })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}