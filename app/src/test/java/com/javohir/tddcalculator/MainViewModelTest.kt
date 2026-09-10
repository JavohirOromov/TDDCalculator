package com.javohir.tddcalculator

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.StateFlow
import org.junit.Before
import org.junit.Test

/**
 * Created by: Javohir Oromov macOS
 * Project: TDDCalculator
 * Package: com.javohir.tddcalculator
 * Description: Unit Test
 */
class MainViewModelTest  {

    private lateinit var viewModel: MainViewModel

    private lateinit var inputFlow: StateFlow<String>

    private lateinit var resultFlow: StateFlow<String>

    @Before
    fun setup(){
        viewModel = MainViewModel()
        inputFlow = viewModel.inputFlow
        resultFlow = viewModel.resultFlow
        assertEquals("", inputFlow.value)
        assertEquals("", resultFlow.value)
    }

    @Test
    fun scenario_number_one(){

        viewModel.input("1")
         assertEquals("1", inputFlow.value)

        viewModel.plus()
        assertEquals("1+", inputFlow.value)

        viewModel.input("2")
        assertEquals("1+2", inputFlow.value)

        viewModel.calculate()
        assertEquals("1+2", inputFlow.value)
        assertEquals("3", resultFlow.value)
    }

    @Test
    fun sum_of_two_numbers_corner_case(){

        viewModel.input("1")
        assertEquals("1", inputFlow.value)

        var expected = "1"
        repeat(times = 9){
            viewModel.inputZero()
            expected += "0"
            assertEquals(expected, inputFlow.value)
        }
        viewModel.plus()
        assertEquals("1000000000+",inputFlow.value)

        viewModel.input("2")
        assertEquals("1000000000+2",inputFlow.value)

        expected = "1000000000+2"

        repeat(times = 9){
            viewModel.inputZero()
            expected += "0"
            assertEquals(expected, inputFlow.value)
        }

        viewModel.calculate()
        assertEquals( "1000000000+2000000000", inputFlow.value)
        assertEquals("3000000000", resultFlow.value)
    }

    @Test
    fun subtraction_of_two_numbers(){
        viewModel.input("9")
        assertEquals("9",inputFlow.value)

        viewModel.minus()
        assertEquals("9-",inputFlow.value)

        viewModel.input("4")
        assertEquals("9-4", inputFlow.value)

        viewModel.calculate()
        assertEquals("9-4",inputFlow.value)
        assertEquals("5",resultFlow.value)
    }

    @Test
    fun subtraction_with_negative_result(){
        viewModel.input(number = "4")
        assertEquals("4",inputFlow.value)

        viewModel.minus()
        assertEquals("4-",inputFlow.value)

        viewModel.input(number = "9")
        assertEquals("4-9",inputFlow.value)

        viewModel.calculate()
        assertEquals("4-9",inputFlow.value)
        assertEquals("-5",resultFlow.value)
    }

    @Test
    fun multiplication_of_two_numbers(){
        viewModel.input("7")
        assertEquals("7",inputFlow.value)

        viewModel.multiply()
        assertEquals("7*",inputFlow.value)

        viewModel.input("8")
        assertEquals("7*8",inputFlow.value)

        viewModel.calculate()
        assertEquals("7*8",inputFlow.value)
        assertEquals("56",resultFlow.value)
    }

    @Test
    fun division_of_two_numbers(){
        viewModel.input("8")
        assertEquals("8",inputFlow.value)

        viewModel.divide()
        assertEquals("8/",inputFlow.value)

        viewModel.input("2")
        assertEquals("8/2",inputFlow.value)

        viewModel.calculate()
        assertEquals("8/2",inputFlow.value)
        assertEquals("4",resultFlow.value)
    }
    @Test
    fun division_with_remainder_is_truncated(){
        viewModel.input("9")
        viewModel.divide()
        viewModel.input("4")
        assertEquals("9/4",inputFlow.value)

        viewModel.calculate()
        assertEquals("9/4",inputFlow.value)
        assertEquals("2",resultFlow.value)
    }


    @Test
    fun division_by_zero(){
        viewModel.input("5")
        assertEquals("5",inputFlow.value)

        viewModel.divide()
        assertEquals("5/",inputFlow.value)

        viewModel.inputZero()
        assertEquals("5/0",inputFlow.value)

        viewModel.calculate()
        assertEquals("5/0",inputFlow.value)
        assertEquals("Error",resultFlow.value)
    }
}