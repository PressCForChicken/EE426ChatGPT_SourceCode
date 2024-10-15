package com.example.yourappname

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var compassNeedle: ImageView

    private var accelerometerData = FloatArray(3)
    private var magneticData = FloatArray(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the ImageView for the compass needle
        compassNeedle = findViewById(R.id.compassNeedle)

        // Get an instance of the sensor manager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Register the accelerometer and magnetometer listeners
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI)
        
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
            SensorManager.SENSOR_DELAY_UI)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            // Copy new accelerometer data
            accelerometerData = event.values.clone()
        }
        if (event?.sensor?.type == Sensor.TYPE_MAGNETIC_FIELD) {
            // Copy new magnetometer data
            magneticData = event.values.clone()
        }

        val rotationMatrix = FloatArray(9)
        val orientationAngles = FloatArray(3)

        // Calculate the rotation matrix using the accelerometer and magnetometer data
        if (SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerData, magneticData)) {
            // Get the orientation angles from the rotation matrix
            SensorManager.getOrientation(rotationMatrix, orientationAngles)

            // Calculate the azimuth (rotation around Z-axis) in degrees
            val azimuthInRadians = orientationAngles[0]
            val azimuthInDegrees = Math.toDegrees(azimuthInRadians.toDouble()).toFloat()

            // Rotate the compass needle based on the azimuth
            compassNeedle.rotation = -azimuthInDegrees // Negative to rotate in the correct direction
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // You can leave this empty
    }

    override fun onPause() {
        super.onPause()
        // Unregister the sensor listeners to save battery when the app is paused
        sensorManager.unregisterListener(this)
    }

    override fun onResume() {
        super.onResume()
        // Register the sensor listeners again when the app is resumed
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI)
        
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
            SensorManager.SENSOR_DELAY_UI)
    }
}
