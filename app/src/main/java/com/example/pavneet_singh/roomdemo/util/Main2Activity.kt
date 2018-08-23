package com.example.pavneet_singh.roomdemo.util


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.artproficiencyapp.extension.CALL_API
import com.artproficiencyapp.extension.LoggE
import com.artproficiencyapp.extension.isNetWork
import com.artproficiencyapp.restapi.ApiInitialize
import com.artproficiencyapp.restapi.ApiRequest
import com.artproficiencyapp.restapi.ApiResponseInterface
import com.artproficiencyapp.restapi.ApiResponseManager
import com.example.pavneet_singh.roomdemo.R
import com.example.pavneet_singh.roomdemo.adapter.DataAdapterAdapter
import com.example.pavneet_singh.roomdemo.model.Response
import com.example.pavneet_singh.roomdemo.model.VenuesItem
import com.example.pavneet_singh.roomdemo.new_db.VenueDatabase
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class Main2Activity : AppCompatActivity(), ApiResponseInterface {

    private var list: MutableList<VenuesItem>? = null
    private var dataAdapterAdapter: DataAdapterAdapter? = null
    private var venueDatabase: VenueDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        venueDatabase = VenueDatabase.getInstance(this@Main2Activity)



        fab.setOnClickListener { view ->
            if (isNetWork(this)) {
                setAdapter(true)
                ApiRequest(this@Main2Activity, ApiInitialize.initialize(ApiInitialize.MAIN_URl_API)
                        .getData(), CALL_API, false, this@Main2Activity)
            } else {
                setAdapter(false)
                /*list = ArrayList()
                list?.addAll(venueDatabase?.venueDao?.venue?.get(0)?.venues!!)
                dataAdapterAdapter = DataAdapterAdapter(list,this@Main2Activity)
                val mLayoutManager = LinearLayoutManager(this@Main2Activity, LinearLayoutManager.VERTICAL, false)
                rvMainActivity.adapter = dataAdapterAdapter
                rvMainActivity!!.layoutManager = mLayoutManager
*/
            }
        }
    }

    fun setAdapter(isOn: Boolean) {
        list = ArrayList()
        if (isOn) {
            dataAdapterAdapter = DataAdapterAdapter(list, this@Main2Activity)
        } else {

            list?.addAll(venueDatabase?.venueDao?.venue?.get(0)?.venues!!)
            dataAdapterAdapter = DataAdapterAdapter(list, this@Main2Activity)
        }
        val mLayoutManager = LinearLayoutManager(this@Main2Activity, LinearLayoutManager.VERTICAL, false)
        rvMainActivity.adapter = dataAdapterAdapter
        rvMainActivity!!.layoutManager = mLayoutManager

    }

    override fun getApiResponse(apiResponseManager: ApiResponseManager<*>) {
        when (apiResponseManager.type) {
            CALL_API -> {
                val response = apiResponseManager.response as Response
                LoggE("TAG", "venues Size-->" + response.response.venues.size)
                list?.addAll(response.response.venues)
                venueDatabase?.venueDao?.insertNote(response.response)
                //venueDatabase.
                dataAdapterAdapter?.notifyDataSetChanged()
                Log.d("TAG", "Database--->" + venueDatabase?.venueDao?.venue?.get(0)?.venues?.size)
            }
        }
    }
}
