package com.example.appclima.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appclima.R
import com.example.appclima.data.model.Forecast
import com.example.appclima.data.model.ForecastMain
import com.example.appclima.ui.CitiesActivity.CitiesActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import okio.IOException
import java.util.Locale

class MainActivity : AppCompatActivity(), MainContract.View {

    private var presenter: MainPresenter? = null
    val LOCATION_PERMISSION_REQUEST_CODE = 1000
    lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        presenter = MainPresenter(this,this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    val btnIr = findViewById<Button>(R.id.cities).setOnClickListener{
        val intent = Intent(this, CitiesActivity::class.java)
        startActivity(intent)
    }
        checklocationPermission()
    }

    override fun getInitialData(lat:Double,lon:Double) {
        presenter?.getWeather(lat,lon)
    }

    override fun showWeather(climate: String) {
        findViewById<TextView>(R.id.climaActual).text = climate
    }

    override fun showItemsData(items: ArrayList<Forecast>) {
        val timeList: RecyclerView? = findViewById(R.id.recyclerview_forecast)

        val recyclerAdapter = RecyclerAdapter(items)
        if(timeList != null){
            timeList.layoutManager=LinearLayoutManager(this)
            timeList.adapter=recyclerAdapter
        }
        recyclerAdapter.setOnClickListener(object : RecyclerAdapter.OnContractClickListener{
            override fun onClick(position: Int, forecast: Forecast) {
                presenter?.triggerDialogInfo(forecast)
            }
        })
    }

    override fun showDialogInfo(forecast: ForecastMain) {
        // Inflate the custom dialog layout
        val dialogView = layoutInflater.inflate(R.layout.mydialog, null)

        // Find the views in the custom layout
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialog_title)
        val dialogButton = dialogView.findViewById<Button>(R.id.dialog_button)

        // Set the data for the dialog
        dialogTitle.text = "Humidity: ${forecast.humidity}, Pressure: ${forecast.pressure}"

        // Create the AlertDialog using the custom layout
        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        // Set the click listener for the button
        dialogButton.setOnClickListener {
            dialogBuilder.dismiss()
        }

        // Show the dialog
        dialogBuilder.show()
    }

    private fun checklocationPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }else{
            //Accedido
            getLastLocation()
            presenter?.generateItemsData()
        }
    }

    private fun getLastLocation(){
        if(ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )!=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )!= PackageManager.PERMISSION_GRANTED
            ){
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener {location: Location?->
                location?.let{
                    val latitude = it.latitude
                    val longitude = it.longitude
                    val cityName = getCityName(latitude, longitude)
                    findViewById<TextView>(R.id.ubicacionActual).text = cityName

                    getInitialData(latitude,longitude)
                } ?: run {
                    Log.e("location","no se pudo encontrar")
                }
            }
            .addOnFailureListener(){
                Log.e("location","Error al obtener la ubicacion")
            }
    }

    private fun getCityName(latitude: Double, longitude: Double): String{
        var cityName = "Ciudad no encontrada"
        val geocoder = Geocoder (this, Locale.getDefault())
        try{
            val address: List<Address>? = geocoder.getFromLocation(latitude, longitude,1)
            if(!address.isNullOrEmpty()){
                val address: Address = address[0]
                cityName = address.locality?: "Ciudad no encontrada"
            }
        } catch (e:IOException){
            Log.e("Geocoder","Error al obtener la ciudad",e)
        }
        return cityName
    }

   // private fun onRequestPermissionResult



}