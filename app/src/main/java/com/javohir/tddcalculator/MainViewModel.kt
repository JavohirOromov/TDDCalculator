package com.javohir.tddcalculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigInteger

/**
 * Created by: Javohir Oromov macos
 * Project: TDDCalculator
 * Package: com.javohir.tddcalculator
 * Description: ViewModel
 */
class MainViewModel: ViewModel(), MainActions {

    private val inputMutableFlow: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val resultMutableFlow: MutableStateFlow<String> = MutableStateFlow(value = "")
    val inputFlow: StateFlow<String>
        get() = inputMutableFlow
    val resultFlow: StateFlow<String>
        get() = resultMutableFlow

    private var left: String = ""
    private var right: String = ""
    private var operation: String = ""
    private var addToLeft: Boolean = true

    private fun inputDigit(digit: String) {
        if (addToLeft) {
            left += digit
            inputMutableFlow.value = left
        } else {
            right += digit
            inputMutableFlow.value = "$left$operation$right"
        }
    }

    private fun chooseOperation(symbol: String){
        operation = symbol
        addToLeft = false
        inputMutableFlow.value = "$left$operation"
    }

    override fun input(number: String) {
        inputDigit(digit = number)
    }

    override fun inputZero() {
        inputDigit(digit = "0")
    }

    override fun inputDot() = Unit

    override fun plus() {
       return chooseOperation(symbol = "+")
    }

    override fun minus()  {
       return chooseOperation(symbol = "-")
    }

    override fun multiply() = Unit

    override fun divide() = Unit
    override fun calculate() {
        val leftNumber = BigInteger(left)
        val rightNumber = BigInteger(right)
        val result = when(operation){
            "+" -> leftNumber.plus(rightNumber)
            "-" -> leftNumber.minus(rightNumber)
            else -> return
        }
        resultMutableFlow.value = result.toString()
    }

    override fun backspace() = Unit

    override fun clearAll() = Unit

}
