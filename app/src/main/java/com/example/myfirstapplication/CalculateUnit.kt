package com.example.myfirstapplication

import java.lang.Exception

// 計算のユニット
public class CalculateUnit {
    // 左辺
    private var leftVal: Double = 0.0
    // 演算子
    private var operator: DoubleOperator = DoubleOperator.Nothing
    // 右辺
    private var rightVal: Double = 0.0

    // どの入力をしているか
    private var calcState: CalcState = CalcState.Left
    enum class CalcState{
        Left, Operator, Right
    }

    fun singleOperator(operator: SingleOperator, option: Int = 0) {
        var tmpVal = when(calcState) {
            CalcState.Left -> leftVal
            CalcState.Operator -> leftVal
            CalcState.Right -> rightVal
        }
        tmpVal = when(operator) {
            SingleOperator.Insert -> insert(tmpVal, option)
            SingleOperator.Frac -> frac(tmpVal)
        }
        when(calcState) {
            CalcState.Left -> leftVal = tmpVal
            CalcState.Operator -> leftVal = tmpVal
            CalcState.Right -> rightVal = tmpVal
        }
    }

    private fun insert(value: Double, num: Int): Double {
        return value * 10 + num
    }

    private fun frac(value: Double): Double {
        var returnValue = 0.0
        try {
            returnValue = 1/value
        } catch(e:Exception) {
            allClear()
        }
        return returnValue
    }

    fun calculate() {
        when(calcState) {
            CalcState.Left -> {}
            CalcState.Operator -> {
                rightVal = leftVal
                operate()
                calcState = CalcState.Left
                operator = DoubleOperator.Nothing
                rightVal = 0.0
            }
            CalcState.Right -> {
                operate()
                calcState = CalcState.Left
                operator = DoubleOperator.Nothing
                rightVal = 0.0
            }
        }
    }

    private fun operate() {
        leftVal = when(operator) {
            DoubleOperator.Nothing -> 0.0
            DoubleOperator.Plus -> plus()
            DoubleOperator.Minus -> minus()
            DoubleOperator.Multiply -> multiply()
            DoubleOperator.Divide -> divide()
        }
    }

    private fun plus(): Double {
        return  leftVal + rightVal
    }

    private fun minus(): Double {
        return leftVal - rightVal
    }

    private fun multiply(): Double {
        return leftVal * rightVal
    }

    private fun divide(): Double {
        var returnValue = 0.0
        try {
            returnValue = leftVal/rightVal
        } catch(e:Exception) {
            allClear()
        }
        return returnValue
    }

    fun setDoubleOperator(operator: DoubleOperator) {
        this.operator = operator
        calcState = CalcState.Operator
    }

    fun clear() {
        when(calcState) {
            CalcState.Left -> {
                leftVal = 0.0
            }
            CalcState.Operator -> {
                leftVal = 0.0
            }
            CalcState.Right -> {
                rightVal = 0.0
            }
        }
    }

    fun allClear() {
        leftVal = 0.0
        operator = DoubleOperator.Nothing
        rightVal = 0.0
        calcState = CalcState.Left
    }

    fun getCalcState(): CalcState {
        return calcState
    }

    fun getDisplayValue(): Double {
        when(calcState) {
            CalcState.Left -> {
                return leftVal
            }
            CalcState.Operator -> {
                return leftVal
            }
            CalcState.Right -> {
                return rightVal
            }
        }
    }
}
