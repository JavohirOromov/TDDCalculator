package com.javohir.tddcalculator
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasNoClickAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule

/**
 * Created by: Javohir Oromov macos
 * Project: TDDCalculator
 * Package: com.javohir.tddcalculator
 * Description: 
 */
class MainPage(
     composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
 ) {

    private val numberOneButton = composeTestRule.onNode(
        hasTestTag(testTag = "number one button") and
                hasText(text = "1") and
                hasClickAction())

    private val plusButton = composeTestRule.onNode(
        hasTestTag(testTag = "plus button") and
                hasText(text = "+") and
                hasClickAction())

    private val numberTwoButton = composeTestRule.onNode(
        hasTestTag(testTag = "number two button") and
                hasText(text = "2") and
                hasClickAction())

    private val equalsButon = composeTestRule.onNode(
        hasTestTag(testTag = "equals button") and
        hasText(text = "=") and
        hasClickAction()
    )

    private val inputText = composeTestRule.onNode(
        hasTestTag(testTag = "input text  ") and
                hasNoClickAction()
    )

    private val resultText = composeTestRule.onNode(
        hasTestTag(testTag = "result text") and
                hasNoClickAction()
    )
    fun clickNumberOneButton() {
        numberOneButton.performClick()
    }

    fun assertInputField(expected: String) {
         inputText.assertTextEquals( expected )
    }

    fun clickOperationPlusButton() {
        plusButton.performClick()
    }

    fun clickNumberTwoButton() {
        numberTwoButton.performClick()
    }

    fun clickEqualsButton() {
        equalsButon.performClick()
    }

    fun assertResult(expected: String) {
        resultText.assertTextEquals(expected)
    }

}