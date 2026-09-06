package com.javohir.tddcalculator
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by: Javohir Oromov macos
 * Project: TDDCalculator
 * Package: com.javohir.tddcalculator
 * Description: Ui Test
 */

@RunWith(value = AndroidJUnit4::class)
class ScenarioUiTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val mainPage = MainPage(composeTestRule)

    @Test
    fun sum_of_two_numbers(){
        mainPage.clickNumberOneButton()
        mainPage.assertInputField(expected = "1")

        mainPage.plus()
        mainPage.assertInputField(expected = "1+")

        mainPage.clickNumberTwoButton()
        mainPage.assertInputField(expected = "1+2")

        mainPage.calculate()
        mainPage.assertInputField(expected = "1+2")
        mainPage.assertResult(expected = "3")
    }

    @Test
    fun sum_of_two_numbers_corner_case(){
        mainPage.clickNumberOneButton()
        mainPage.assertInputField(expected = "1")

        var expected = "1"

        repeat(times = 9){
            mainPage.inputZero()
            expected += "0"
            mainPage.assertInputField(expected = expected)
        }

        mainPage.plus()
        mainPage.assertInputField(expected = "1000000000+")

        mainPage.clickNumberTwoButton()
        mainPage.assertInputField(expected = "1000000000+2")

        expected = "1000000000+2"
        repeat(times = 9){
            mainPage.inputZero()
            expected += "0"
            mainPage.assertInputField(expected = expected)
        }

        mainPage.calculate()
        mainPage.assertInputField(expected = "1000000000+2000000000")
        mainPage.assertResult(expected = "3000000000")
    }
}

