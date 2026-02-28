package com.example.bmi_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvGender = findViewById<TextView>(R.id.tv_gender)
        val tvBmiValue = findViewById<TextView>(R.id.tv_bmi_value)
        val tvBmiCategory = findViewById<TextView>(R.id.tv_bmi_category)
        val tvBmiMessage = findViewById<TextView>(R.id.tv_bmi_message)
        val tvBmiRange = findViewById<TextView>(R.id.tv_bmi_range)
        val btnRecalculate = findViewById<Button>(R.id.btn_recalculate)

        // Get data from intent
        val bmi = intent.getFloatExtra("BMI_VALUE", 0f)
        val gender = intent.getStringExtra("GENDER") ?: "Male"
        val age = intent.getIntExtra("AGE", 0)

        // Display values
        tvGender.text = "Gender: $gender  |  Age: $age"
        tvBmiValue.text = String.format("%.1f", bmi)

        // Determine category, color, and message based on BMI
        when {
            bmi < 18.5 -> {
                tvBmiCategory.text = "Underweight"
                tvBmiCategory.setTextColor(ContextCompat.getColor(this, R.color.red))
                tvBmiMessage.text = "You have a lower than normal body weight. Try to eat more nutritious food."
                tvBmiRange.text = "Normal BMI range: 18.5 - 24.9"
            }
            bmi < 25.0 -> {
                tvBmiCategory.text = "Normal"
                tvBmiCategory.setTextColor(0xFF4CAF50.toInt()) // Green
                tvBmiMessage.text = "You have a normal body weight. Good job!"
                tvBmiRange.text = "Normal BMI range: 18.5 - 24.9"
            }
            bmi < 30.0 -> {
                tvBmiCategory.text = "Overweight"
                tvBmiCategory.setTextColor(0xFFFF9800.toInt()) // Orange
                tvBmiMessage.text = "You have a higher than normal body weight. Try to exercise more."
                tvBmiRange.text = "Normal BMI range: 18.5 - 24.9"
            }
            else -> {
                tvBmiCategory.text = "Obese"
                tvBmiCategory.setTextColor(0xFFF44336.toInt()) // Red
                tvBmiMessage.text = "You have a very high body weight. Please consult a doctor."
                tvBmiRange.text = "Normal BMI range: 18.5 - 24.9"
            }
        }

        btnRecalculate.setOnClickListener {
            finish() // Goes back to MainActivity
        }
    }
}