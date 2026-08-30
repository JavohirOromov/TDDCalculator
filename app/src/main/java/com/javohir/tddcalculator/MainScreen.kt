package com.javohir.tddcalculator
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by: Javohir Oromov macos
 * Project: TDDCalculator
 * Package: com.javohir.tddcalculator
 * Description: MainScreen
 */
@Composable
fun MainScreen(input: String, result: String, actions: MainActions){

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = input,
            modifier = Modifier
                .testTag("input text")
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = result,
            modifier = Modifier
                .testTag("result text")
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
        ) {
            CalculatorButton(testTag = "number one button", text = "1", onClick = actions::inputOne)
            CalculatorButton(testTag = "number two button", text = "2", onClick = actions::inputTwo)
            CalculatorButton(testTag = "number zero button", text = "0", onClick = actions::inputZero)

        }
        Row(
        ) {
            CalculatorButton(testTag = "plus button", text = "+", onClick = actions::plus)
            CalculatorButton(testTag = "equals button", text = "=", onClick = actions::calculate)
        }
    }
}

@Preview
@Composable
fun MainPreview(){
    MainScreen(
        input = "1+2", result = "3", actions = object : MainActions {
            override fun inputOne() = Unit

            override fun inputTwo() = Unit

            override fun inputZero() = Unit

            override fun plus() = Unit

            override fun calculate() = Unit
        }
    )
}

@Composable
fun CalculatorButton(
    testTag: String,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(4.dp)
            .testTag(testTag)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}