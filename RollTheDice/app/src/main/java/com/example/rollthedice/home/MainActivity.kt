package com.example.rollthedice.home

import android.graphics.drawable.shapes.Shape
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.rollthedice.R
import com.example.rollthedice.model.Dice
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {
    val dice = Dice()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtDiceFace = findViewById<MaterialTextView>(R.id.txt_current_face)
        val btnRoll = findViewById<MaterialButton>(R.id.btn_roll_dice)
        val imgVdice = findViewById<ShapeableImageView>(R.id.img_dice_current_face)
        rollTheDice(txtDiceFace,imgVdice)
        btnRoll.setOnClickListener { rollTheDice(txtDiceFace, imgVdice) }
    }

    fun rollTheDice(tvOutput: MaterialTextView, imgvOutput: ShapeableImageView) {
        dice.roll()
        val drawableId = when (dice.current_face) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        tvOutput.setText(getString(R.string.str_current_face) + " ${dice.current_face}" )
        imgvOutput.setImageResource(drawableId)
    }
}
