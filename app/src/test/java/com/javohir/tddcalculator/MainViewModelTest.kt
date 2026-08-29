package com.javohir.tddcalculator

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.StateFlow
import org.junit.Before
import org.junit.Test

/**
 * Created by: Javohir Oromov macos
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


        viewModel.inputOne()
         assertEquals("1", inputFlow.value)

        viewModel.plus()
        assertEquals("1+", inputFlow.value)

        viewModel.inputTwo()
        assertEquals("1+2", inputFlow.value)

        viewModel.calculate()
        assertEquals("1+2", inputFlow.value)
        assertEquals("3", resultFlow.value)
    }
}