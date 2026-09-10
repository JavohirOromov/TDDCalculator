package com.javohir.tddcalculator

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal
import java.math.MathContext

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

    override fun inputDot() {
        val current = if (addToLeft) left else right
        if (current.contains(char = DOT)) return
        inputDigit(digit = if (current.isEmpty()) "$ZERO$DOT" else DOT.toString())
    }

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

    /**
     * A number is still being typed while it ends with a dot, so "1." means 1.
     */
    private fun numberOf(operand: String): BigDecimal =
        BigDecimal(operand.removeSuffix(suffix = DOT.toString()))

    override fun calculate() {
        if (left.isEmpty() || right.isEmpty()) return

        val leftNumber = numberOf(operand = left)
        val rightNumber = numberOf(operand = right)

        if (operation == DIVIDE && rightNumber.signum() == 0) {
            resultMutableFlow.value = ERROR
            return
        }

        val result = when(operation){
            PLUS -> leftNumber.plus(rightNumber)
            MINUS -> leftNumber.minus(rightNumber)
            MULTIPLY -> leftNumber.multiply(rightNumber)
            DIVIDE -> leftNumber.divide(rightNumber, PRECISION)
            else -> return
        }
        resultMutableFlow.value = result.stripTrailingZeros().toPlainString()
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
        private const val ZERO = "0"
        private const val DOT = '.'

        /** Endless quotients such as 1/3 are rounded to 16 significant digits. */
        private val PRECISION = MathContext.DECIMAL64
    }
}
