package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.radioAmazingTip.isChecked = true
        setOnClickListeners()
        updateTipAmount(0.0)
    }

    private fun updateTipAmount(newValue: Double) {
        val numberFormat = NumberFormat.getCurrencyInstance()
        if (binding.checkRoundTip.isChecked) {
            binding.tipAmount.text =
                getString(R.string.tip_amount, numberFormat.format(ceil(newValue)))
        } else {
            binding.tipAmount.text =
                getString(R.string.tip_amount, numberFormat.format(newValue))
        }
    }

    private fun getCheckedPercentage(): Double {
        return when (binding.tipOptions.checkedRadioButtonId) {
            R.id.radioAmazingTip -> 20.0
            R.id.radioGoodTip -> 18.0
            R.id.radioOkayTip -> 15.0
            else -> 0.0
        }
    }

    private fun setOnClickListeners() {
        var costOfService: Double
        binding.btnCalculate.setOnClickListener {
            costOfService = if (binding.edtServiceCost.text.toString() == "") 0.0
            else binding.edtServiceCost.text.toString().toDouble()

            updateTipAmount(Calculations.addPercent(costOfService, getCheckedPercentage()))
        }
    }

}