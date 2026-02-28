package com.example.bmi_calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.slider.Slider
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // age
        val ageText = findViewById<TextView>(R.id.age_text)
        val add_age_btn = findViewById<Button>(R.id.age_add_btn)
        val sub_age_btn = findViewById<Button>(R.id.age_remove_btn)
        // weight
        val weightText = findViewById<TextView>(R.id.weight_text)
        val add_weight_btn = findViewById<Button>(R.id.weight_add_btn)
        val sub_weight_btn = findViewById<Button>(R.id.weight_remove_btn)
        //height
        val heightText = findViewById<TextView>(R.id.height_text)
        val height_Slider = findViewById<Slider>(R.id.height_slider)
        //CalculateButton
        val cal_button = findViewById<Button>(R.id.Calculate_btn)
        val Gender_radio = findViewById<RadioGroup>(R.id.radioGroupGender)
        add_age_btn.setOnClickListener {
            val current_age = ageText.text.toString().toIntOrNull() ?: 0
            val new_age = current_age + 1
            ageText.text = new_age.toString()

        }
        sub_age_btn.setOnClickListener {
            val current_age = ageText.text.toString().toIntOrNull() ?: 0
            val newAge = if (current_age > 0) current_age - 1 else 0
            ageText.text = newAge.toString()
        }


        add_weight_btn.setOnClickListener {
            val current_weight = weightText.text.toString().toIntOrNull() ?: 0
            val new_weight = current_weight + 1
            weightText.text = new_weight.toString()

        }
        sub_weight_btn.setOnClickListener {
            val current_weight = weightText.text.toString().toIntOrNull() ?: 0
            val new_weight = if (current_weight > 0) current_weight - 1 else 0
            weightText.text = new_weight.toString()
        }

        height_Slider.addOnChangeListener { _, value, _ ->
            heightText.text = value.toInt().toString()
        }

        cal_button.setOnClickListener {
            val age = ageText.text.toString().toIntOrNull() ?:0
            val height = heightText.text.toString().toIntOrNull() ?: 0
            val weight = weightText.text.toString().toIntOrNull() ?: 0
            //Gender
            val selectedGenderId = Gender_radio.checkedRadioButtonId
            var gender = ""
            if(selectedGenderId != -1)
            {
                val radioButton = findViewById<RadioButton>(selectedGenderId)
                gender = radioButton.text.toString()
            }
            else{
                gender = "Not defined"
            }
            if (weight == 0 || height == 0 || age == 0) {
                Toast.makeText(this, "Please fill all values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }  
            val bmi = CalculateBMI(age,height,weight)

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("BMI_VALUE", bmi.toFloat())
            intent.putExtra("GENDER", gender)
            intent.putExtra("AGE", age)
            startActivity(intent)

        }

    }
    fun CalculateBMI(age: Int, height : Int, weight: Int) : Int
    {
        val heightInMeters = height / 100.0
        val bmi = weight / (heightInMeters * heightInMeters)
        return bmi.toInt()
    }
}