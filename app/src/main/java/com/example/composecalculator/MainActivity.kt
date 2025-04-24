package com.example.composecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.Calculator
import com.example.calculator.CalculatorViewModel
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.MeduimGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    // get reference to our state
                    val viewModel = viewModel<CalculatorViewModel>()
                    val state = viewModel.state
                    val buttonSpacing = 8.dp
                    Calculator(
                        state = state,
                        onAction = viewModel::onAction,
                        buttonSpacing = buttonSpacing,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MeduimGray)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}
