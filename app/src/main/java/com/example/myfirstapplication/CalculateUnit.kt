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

    // 1/xや三角関数などの一つのみを引数とするような演算(その場で実行)
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

    // x^yのような2つのものを引数として取るような演算(イコールキーで実行)
    fun setDoubleOperator(operator: DoubleOperator) {
        this.operator = operator
        calcState = CalcState.Operator
    }

    /*
     * イコールが押されたときの処理
     * 左入力してるときは何もしない
     * 演算子入力中は左の値を右の値として扱う
     */
    fun calculate() {
        when(calcState) {
            CalcState.Left -> {return}
            CalcState.Operator -> {rightVal = leftVal}
            CalcState.Right -> {}
        }
        leftVal = when(operator) {
            DoubleOperator.Nothing -> leftVal
            DoubleOperator.Plus -> plus()
            DoubleOperator.Minus -> minus()
            DoubleOperator.Multiply -> multiply()
            DoubleOperator.Divide -> divide()
        }
        calcState = CalcState.Left
        operator = DoubleOperator.Nothing
        rightVal = 0.0
    }

    /* Single Operator */
    // 数字キーによる入力
    private fun insert(value: Double, num: Int): Double {
        return value * 10 + num
    }

    // 1/x
    private fun frac(value: Double): Double {
        var returnValue = 0.0
        try {
            returnValue = 1/value
        } catch(e:Exception) {
            allClear()
        }
        return returnValue
    }

    /* Double Operator*/
    // 加算
    private fun plus(): Double {
        return  leftVal + rightVal
    }

    // 減算
    private fun minus(): Double {
        return leftVal - rightVal
    }

    // 乗算
    private fun multiply(): Double {
        return leftVal * rightVal
    }

    // 除算 例外が発生したらACして0を返す
    private fun divide(): Double {
        var returnValue = 0.0
        try {
            returnValue = leftVal/rightVal
        } catch(e:Exception) {
            allClear()
        }
        return returnValue
    }

    // Cキー
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

    // ACキー
    fun allClear() {
        leftVal = 0.0
        operator = DoubleOperator.Nothing
        rightVal = 0.0
        calcState = CalcState.Left
    }

    // どの入力をしているか取得
    fun getCalcState(): CalcState {
        return calcState
    }

    // 表示する値を取得
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
