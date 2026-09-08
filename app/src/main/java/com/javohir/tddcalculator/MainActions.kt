package com.javohir.tddcalculator

/**
 * Created by: Javohir Oromov macos
 * Project: TDDCalculator
 * Package: com.javohir.tddcalculator
 * Description: 
 */
interface MainActions {
    fun input(number: String)
    fun inputZero()
    fun inputDot()


    fun plus()
    fun minus()
    fun multiply()
    fun divide()
    fun calculate()

    fun backspace()
    fun clearAll()
}