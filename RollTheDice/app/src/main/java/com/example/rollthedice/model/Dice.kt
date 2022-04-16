package com.example.rollthedice.model

class Dice {
    val faces = 1..6
    var current_face = faces.random()
    fun roll() {
        current_face = faces.random()
    }
}