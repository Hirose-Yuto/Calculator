package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val EXTRA_MESSAGE: String = "com.example.myfirstapplication.MESSAGE"

    private var calculateUnit: CalculateUnit = CalculateUnit()
    private var dotPressed:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun updateDisplay() {
        val textView: TextView = findViewById(R.id.result) as TextView
        var displayValue = calculateUnit.getDisplayValue()
        if(displayValue % 1 == 0.0) {
            if(dotPressed) {
                textView.setText(displayValue.toInt().toString() + ".")
            } else {
                textView.setText(displayValue.toInt().toString())
            }
        } else {
            textView.setText(displayValue.toString())
        }
    }

    // 数を挿入
    fun insert(view: View) {
        val num = when (view.id) {
            R.id.button0 -> 0
            R.id.button1 -> 1
            R.id.button2 -> 2
            R.id.button3 -> 3
            R.id.button4 -> 4
            R.id.button5 -> 5
            R.id.button6 -> 6
            R.id.button7 -> 7
            R.id.button8 -> 8
            R.id.button9 -> 9
            R.id.dot -> -1
            else -> 0
        }
        dotPressed = view.id == R.id.dot
        calculateUnit.singleOperator(SingleOperator.Insert, num)
        updateDisplay()
    }

    fun doubleOperator(view: View) {
        calculateUnit.setDoubleOperator(when(view.id) {
            R.id.plus -> DoubleOperator.Plus
            R.id.minus -> DoubleOperator.Minus
            R.id.multiply -> DoubleOperator.Multiply
            R.id.divide -> DoubleOperator.Divide
            else -> DoubleOperator.Nothing
        })
        updateDisplay()
    }

    fun equal(view: View) {
        calculateUnit.calculate()
        updateDisplay()
    }

    fun allClear(view: View) {
        dotPressed = false
        calculateUnit.allClear()
        updateDisplay()
    }

    fun changeSign(view: View) {
        calculateUnit.singleOperator(SingleOperator.ChangeSign)
        updateDisplay()
    }

    fun percentage(view: View) {
        calculateUnit.singleOperator(SingleOperator.Percentage)
        updateDisplay()
    }

}