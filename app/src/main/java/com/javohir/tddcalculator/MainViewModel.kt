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

    private fun showInput() {
        inputMutableFlow.value = "$left$operation$right"
    }

    private fun inputDigit(digit: String) {
        if (addToLeft) left += digit else right += digit
        showInput()
    }

    private fun chooseOperation(symbol: String){
        operation = symbol
        addToLeft = false
        showInput()
    }

    override fun input(number: String) {
        inputDigit(digit = number)
    }

    override fun inputZero() {
        inputDigit(digit = "0")
    }

    override fun inputDot() = Unit

    override fun plus() {
       return chooseOperation(symbol = PLUS)
    }

    override fun minus()  {
       return chooseOperation(symbol = MINUS)
    }

    override fun multiply() {
        return chooseOperation(symbol = MULTIPLY)
    }

    override fun divide()  {
        return chooseOperation(symbol = DIVIDE)
    }
    override fun calculate() {
        val leftNumber = BigInteger(left)
        val rightNumber = BigInteger(right)

        if (operation == DIVIDE && rightNumber == BigInteger.ZERO) {
            resultMutableFlow.value = ERROR
            return
        }

        val result = when(operation){
            PLUS -> leftNumber.plus(rightNumber)
            MINUS -> leftNumber.minus(rightNumber)
            MULTIPLY -> leftNumber.multiply(rightNumber)
            DIVIDE -> leftNumber.divide(rightNumber)
            else -> return
        }
        resultMutableFlow.value = result.toString()
    }

    override fun backspace() {
        when {
            addToLeft -> left = left.dropLast(n = 1)
            right.isNotEmpty() -> right = right.dropLast(n = 1)
            else -> {
                operation = ""
                addToLeft = true
            }
        }
        showInput()
    }

    override fun clearAll() {
        left = ""
        right = ""
        operation = ""
        addToLeft = true
        inputMutableFlow.value = ""
        resultMutableFlow.value = ""
    }

    companion object{
        private const val PLUS = "+"
        private const val MINUS = "-"
        private const val MULTIPLY = "*"
        private const val DIVIDE = "/"
        private const val ERROR = "Error"
    }
}
