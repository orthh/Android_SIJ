package com.orthh.exgps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = getSystemService(LOCATION_SERVICE) as LocationManager

        var result = "All Providers : "
        val providers = manager.allProviders

        for(provider in providers){
            result += "$provider,"
        }
        Log.d("kkang", result)



        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) === PackageManager.PERMISSION_GRANTED
        ) {
            val location: Location? = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            location?.let {
                val latitude = location.latitude
                val longitude = location.longitude
                val accuracy = location.accuracy
                val time = location.time
                Toast.makeText(this@MainActivity, longitude.toString(), Toast.LENGTH_SHORT).show()
                Log.d("orthh", "$latitude, $longitude, $accuracy, $time")
            }
        }


    }
}