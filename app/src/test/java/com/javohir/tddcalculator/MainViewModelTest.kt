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


    @Before
    fun setup(){
        viewModel = MainViewModel()
    }

    @Test
    fun scenario_number_one(){
        val inputFlow: StateFlow<String> = viewModel.inputFlow
        val resultFlow: StateFlow<String> = viewModel.resultFlow

        assertEquals("", inputFlow.value)
        assertEquals("", resultFlow.value)


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
        val inputFlow: StateFlow<String> = viewModel.inputFlow
        val resultFlow: StateFlow<String> = viewModel.resultFlow

        assertEquals("", inputFlow.value)
        assertEquals("", resultFlow.value)


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
}