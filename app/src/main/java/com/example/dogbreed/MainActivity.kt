package com.example.dogbreed

import Model.DogAPI
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.example.dogbreed.Adapter.DogsAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val imageList = ArrayList<DogAPI>()
    private lateinit var dogsRV:RecyclerView
    private lateinit var dogNameText: EditText
    private lateinit var searchBTN:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogsRV = findViewById(R.id.recyclerViewDog)
        dogNameText = findViewById(R.id.nameDog)
        searchBTN = findViewById(R.id.searchBTN)

        dogsRV.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        searchBTN.setOnClickListener {
            var name =  dogNameText.text.toString()
            searchDogs(name)
        }
    }

    private fun searchDogs(name: String) {
        imageList.clear()
        AndroidNetworking.initialize(this)
        AndroidNetworking.get("https://dog.ceo/api/breed/$name/images")
            .setPriority(Priority.HIGH).build().getAsString(object : StringRequestListener {
                override fun onResponse(response: String?) {
                    val result = JSONObject(response)
                    val image = result.getJSONArray("message")

                    for(i in 0 until image.length()) {
                        val list = image.get(i)
                        imageList.add(
                            DogAPI(list.toString())
                        )
                    }
                    dogsRV.adapter = DogsAdapter(this@MainActivity, imageList)

                }

                override fun onError(anError: ANError?) {
                    TODO("Not yet implemented")
                }
            })

    }
}