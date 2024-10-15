package com.example.myapp  // Replace with your app's package name

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometer: Sensor
    private lateinit var gyroscope: Sensor
    private lateinit var lightSensor: Sensor
    private lateinit var proximitySensor: Sensor
    private lateinit var magnetometer: Sensor
    private lateinit var pressureSensor: Sensor

    private lateinit var accelerometerText: TextView
    private lateinit var gyroscopeText: TextView
    private lateinit var lightText: TextView
    private lateinit var proximityText: TextView
    private lateinit var magnetometerText: TextView
    private lateinit var pressureText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Sensor Manager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Initialize Sensors
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) ?: return
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) ?: return
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) ?: return
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) ?: return
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) ?: return
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) ?: return

        // Initialize TextViews
        accelerometerText = findViewById(R.id.accelerometer_text)
        gyroscopeText = findViewById(R.id.gyroscope_text)
        lightText = findViewById(R.id.light_text)
        proximityText = findViewById(R.id.proximity_text)
        magnetometerText = findViewById(R.id.magnetometer_text)
        pressureText = findViewById(R.id.pressure_text)

        // Button to navigate to References Activity
        val referencesButton: Button = findViewById(R.id.references_button)
        referencesButton.setOnClickListener {
            // Start References Activity
            val intent = Intent(this, ReferencesActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                accelerometerText.text = "Accelerometer: x=${event.values[0]}, y=${event.values[1]}, z=${event.values[2]}"
            }
            Sensor.TYPE_GYROSCOPE -> {
                gyroscopeText.text = "Gyroscope: x=${event.values[0]}, y=${event.values[1]}, z=${event.values[2]}"
            }
            Sensor.TYPE_LIGHT -> {
                lightText.text = "Light: ${event.values[0]} lux"
            }
            Sensor.TYPE_PROXIMITY -> {
                proximityText.text = "Proximity: ${event.values[0]} cm"
            }
            Sensor.TYPE_MAGNETIC_FIELD -> {
                magnetometerText.text = "Magnetometer: x=${event.values[0]}, y=${event.values[1]}, z=${event.values[2]}"
       
