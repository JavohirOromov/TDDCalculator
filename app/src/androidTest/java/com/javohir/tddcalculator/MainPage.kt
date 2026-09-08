package com.javohir.tddcalculator
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasNoClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick

/**
 * Created by: Javohir Oromov macOS
 * Project: TDDCalculator
 * Package: com.javohir.tddcalculator
 * Description: MainPage Unit test
 */
class MainPage(private val composeTestRule: ComposeContentTestRule) {


    private val numberZeroButton = composeTestRule.onNode(
        hasTestTag(testTag = "number zero button") and
                hasText(text = "0") and
                hasClickAction())

    private val plusButton = composeTestRule.onNode(
        hasTestTag(testTag = "plus button") and
                hasText(text = "+") and
                hasClickAction())


    private val equalsButton = composeTestRule.onNode(
        hasTestTag(testTag = "equals button") and
        hasText(text = "=") and
        hasClickAction()
    )

    private val minusButton =
        composeTestRule.onNode(
            hasTestTag("minus button") and
            hasText("-") and
            hasClickAction()
        )

    private val multiplyButton =
        composeTestRule.onNode(
            hasTestTag("multiply button") and
            hasText("*") and
            hasClickAction()
        )

    private val divideButton =
        composeTestRule.onNode(
            hasTestTag("divide button") and
            hasText("/") and
            hasClickAction()
        )

    private val clearAllButton =
        composeTestRule.onNode(
            hasTestTag("clear button") and
            hasText("C") and
            hasClickAction()
        )
    private val backspaceButton =
        composeTestRule.onNode(
            hasTestTag("backspace button") and
            hasText("x") and
            hasClickAction()
        )
    private val dotButton =
        composeTestRule.onNode(
            hasTestTag("dot button") and
            hasText(".") and
            hasClickAction()
        )
    private val inputText = composeTestRule.onNode(
        hasTestTag(testTag = "input text") and
                hasNoClickAction()
    )

    private val resultText = composeTestRule.onNode(
        hasTestTag(testTag = "result text") and
                hasNoClickAction()
    )

    fun assertInputField(expected: String) {
         inputText.assertTextEquals( expected )
    }

    fun plus() {
        plusButton.performClick()
    }

    fun minus(){
        minusButton.performClick()
    }

    fun multiply(){
        multiplyButton.performClick()
    }

    fun divide() {
        divideButton.performClick()
    }

        fun input(number: String) {
            composeTestRule.onNode(
                hasTestTag("number $number button") and
                        hasText(number) and
                        hasClickAction()
            ).performClick()
        }

        fun calculate() {
            equalsButton.performClick()
        }

        fun assertResult(expected: String) {
            resultText.assertTextEquals(expected)
        }

        fun inputZero() {
            numberZeroButton.performClick()
        }

        fun clearAll() {
            clearAllButton.performClick()
        }

        fun backspace() {
            backspaceButton.performClick()
        }

        fun inputDot() {
            dotButton.performClick()
        }
}