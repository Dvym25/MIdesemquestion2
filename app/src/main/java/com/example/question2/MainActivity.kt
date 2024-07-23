package com.example.question2

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.question2.R

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerCompanies: Spinner
    private lateinit var spinnerModels: Spinner
    private lateinit var btnFindPrice: Button
    private lateinit var tvPrice: TextView

    private val carData = mapOf(
        "Audi" to listOf("A3" to 33000, "A4" to 39000, "A6" to 54000),
        "Mercedes" to listOf("C-Class" to 41000, "E-Class" to 54000, "S-Class" to 94000),
        "BMW" to listOf("3 Series" to 41000, "5 Series" to 54000, "7 Series" to 86000)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerCompanies = findViewById(R.id.spinnerCompanies)
        spinnerModels = findViewById(R.id.spinnerModels)
        btnFindPrice = findViewById(R.id.btnFindPrice)
        tvPrice = findViewById(R.id.tvPrice)

        val companies = carData.keys.toList()
        val adapterCompanies = ArrayAdapter(this, android.R.layout.simple_spinner_item, companies)
        adapterCompanies.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCompanies.adapter = adapterCompanies

        spinnerCompanies.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedCompany = companies[position]
                val models = carData[selectedCompany]?.map { it.first } ?: emptyList()
                val adapterModels = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, models)
                adapterModels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerModels.adapter = adapterModels
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        btnFindPrice.setOnClickListener {
            val selectedCompany = spinnerCompanies.selectedItem.toString()
            val selectedModel = spinnerModels.selectedItem.toString()
            val price = carData[selectedCompany]?.find { it.first == selectedModel }?.second ?: "N/A"
            tvPrice.text = "Price: $${price}"
        }
    }
}
