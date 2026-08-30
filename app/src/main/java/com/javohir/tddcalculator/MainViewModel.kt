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
    private var addToLeft: Boolean = true

    private fun inputDigit(digit: String) {
        if (addToLeft) {
            left += digit
            inputMutableFlow.value = left
        } else {
            right += digit
            inputMutableFlow.value = "$left+$right"
        }
    }

    override fun inputOne() {
        inputDigit(digit = "1")
    }

    override fun inputTwo() {
        inputDigit(digit = "2")
    }

    override fun inputZero() {
        inputDigit(digit = "0")
    }

    override fun plus() {
        addToLeft = false
        inputMutableFlow.value = "$left+"
    }

    override fun calculate() {
        val result = BigInteger(left).plus(BigInteger(right))
        resultMutableFlow.value = result.toString()
    }

}
