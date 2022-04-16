package com.example.tipcalculator

class Calculations {
    companion object {
        fun addPercent(value: Double, percentage: Double): Double {
            return value * (1 + (percentage / 100));
        }
    }
}