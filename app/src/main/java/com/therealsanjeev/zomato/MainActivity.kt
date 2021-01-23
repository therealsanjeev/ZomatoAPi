package com.therealsanjeev.zomato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.simplepass.loadingbutton.customViews.CircularProgressButton
import com.therealsanjeev.zomato.repo.Repository
import com.therealsanjeev.zomato.views.AppViewModel
import com.therealsanjeev.zomato.views.AppViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: AppViewModel
    private lateinit var searchBtn: CircularProgressButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        searchBtn=btn_id
        searchBtn.setOnClickListener {
            var searchtext=et_search.text;
            if(searchtext.isNotEmpty()){
                search(searchtext.toString())
            }else{
                et_search.requestFocus()
                Toast.makeText(applicationContext, "Text must be provided", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


        }




    }

    private fun search(searchText: String) {

        searchBtn.startAnimation()
        Log.d("EDITTEXT","$searchText")
        val repository = Repository()
        val viewModelFactory = AppViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AppViewModel::class.java)

        viewModel.getSearchResponse(searchText.toString())
        viewModel.newApiResponse.observe(this, Observer {
            response ->
            if (response.isSuccessful){
                searchBtn.revertAnimation()
//                Log.d("RESPONSE", "Getting the response code: ${response.code()}")
//                Log.d("RESPONSE", "==============================================")
//                Log.d("RESPONSE", "Getting the response body: ${response.body()}")
//                Log.d("RESPONSE", "==============================================")
//                Log.d("RESPONSE", "Getting the response name: ${response.body()!!.restaurants.get(1).restaurant.name}")


                val sizeOfRest = response.body()!!.restaurants.size
                Log.d("RESPONSE", "Restaurant Size: $sizeOfRest")

                for (element in response.body()!!.restaurants){
                    Log.d("RESPONSE", "Restaurant Name: ${element.restaurant.name}")
//                    Log.d("RESPONSE", "==============================================")
                    Log.d("RESPONSE", "Average Price For Two: ${element.restaurant.average_cost_for_two}")
                    Log.d("RESPONSE", "Cuisines: ${element.restaurant.cuisines}")
                }
            }else{
                searchBtn.revertAnimation()
                Log.d("RESPONSE", "Getting the response error: ${response.errorBody()}")
            }
        })

    }
}