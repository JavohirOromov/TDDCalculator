package com.javohir.tddcalculator
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
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
    fun sum_of_two_numbers() = with(mainPage) {
        input("2")
        assertInputField(expected = "2")

        plus()
        assertInputField(expected = "2+")

        input("1")
        assertInputField(expected = "2+1")

        calculate()
        assertInputField(expected = "2+1")
        assertResult(expected = "3")
    }

    @Test
    fun sum_of_two_numbers_more_complex() = with(mainPage){
        input(number = "2")
        assertInputField(expected = "2")

        input("1")
        assertInputField("21")

        inputZero()
        assertInputField("210")

        inputZero()
        assertInputField("2100")

        plus()
        assertInputField(expected = "2100+")

        input("1")
        assertInputField("2100+1")

        inputZero()
        assertInputField("2100+10")

        input("2")
        assertInputField(expected = "2100+102")

        calculate()
        assertInputField(expected = "2100+102")
        assertResult(expected = "2202")
    }

    @Test
    fun sum_of_two_numbers_corner_case() = with(mainPage) {
        input("1")
        assertInputField(expected = "1")

        var expected = "1"
        repeat(9) {
            inputZero()
            expected += "0"
            assertInputField(expected = expected)
        }

        plus()
        assertInputField(expected = "1000000000+")

        input("2")
        assertInputField(expected = "1000000000+2")

        expected = "1000000000+2"
        repeat(9) {
            inputZero()
            expected += "0"
            assertInputField(expected = expected)
        }

        calculate()
        assertInputField(expected = "1000000000+2000000000")
        assertResult(expected = "3000000000")
    }

    @Test
    fun subtraction_of_two_numbers() = with(mainPage){
        input(number = "9")
        assertInputField(expected = "9")

        minus()
        assertInputField(expected = "9-")

        input(number = "4")
        assertInputField(expected = "9-4")

        calculate()
        assertInputField(expected = "9-4")
        assertResult(expected = "5")
    }

    @Test
    fun multiplication_of_two_numbers() = with(mainPage){
        input("7")
        assertInputField(expected = "7")

        multiply()
        assertInputField(expected = "7*")

        input("8")
        assertInputField(expected = "7*8")

        calculate()
        assertInputField(expected = "7*8")
        assertResult(expected = "56")
    }

    @Test
    fun division_by_zero() = with(mainPage){
        input("5")
        assertInputField(expected = "5")

        divide()
        assertInputField(expected = "5/")

        inputZero()
        assertInputField(expected = "5/0")

        calculate()
        assertInputField(expected = "5/0")
        assertResult(expected = "Error")
    }
}

