package com.example.myfirstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_display_message.*

class MainActivity : AppCompatActivity() {
    val EXTRA_MESSAGE: String = "com.example.myfirstapplication.MESSAGE"

    private var calculateUnit: CalculateUnit = CalculateUnit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView2) as TextView
    }

    private fun updateDisplay() {
        val textView: TextView = findViewById(R.id.textView2) as TextView
        textView.setText(calculateUnit.getDisplayValue().toString())
    }

    // 数を挿入
    fun insert(view: View) {
        val num = when (view.id) {
            //R.id.button0 -> 0
            R.id.button1 -> 1
            R.id.button2 -> 2
            R.id.button3 -> 3
            R.id.button4 -> 4
            R.id.button5 -> 5
            R.id.button6 -> 6
            R.id.button7 -> 7
            R.id.button8 -> 8
            R.id.button9 -> 9
            else -> 0
        }


        // resultValue = resultValue * 10 + num
        updateDisplay()
    }

    fun allClear(view: View) {
        calculateUnit.allClear()
        updateDisplay()
    }

    fun changeSign(view: View) {


    }

    fun calcPercentage(view: View) {
        // resultValue /= 100
        updateDisplay()
    }

}