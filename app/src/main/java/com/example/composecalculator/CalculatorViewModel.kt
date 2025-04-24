package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {
    // private set means we can't change the state from outside
    // but we can still access and read it
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction){
        when(action){
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.CalculatorOperation -> enterOperation(action)
        }
    }

    private fun performDeletion() {
        when{
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun performCalculation(){
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if(number1 != null && number2 != null){
            val result = when(state.operation){
                is CalculatorAction.CalculatorOperation.Add -> number1 + number2
                is CalculatorAction.CalculatorOperation.Subtract -> number1 - number2
                is CalculatorAction.CalculatorOperation.Multiply -> number1 * number2
                is CalculatorAction.CalculatorOperation.Divide -> number1 / number2
                null -> return
            }
            state = state.copy(
                number1 = result.toString().take(9),
                number2 = "",
                operation = null)

        }
    }

    private fun enterNumber(number: Int) {
        if(state.operation == null){
            if(state.number1.length >= MAX_NUM_LENGTH){
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if(state.number2.length < MAX_NUM_LENGTH){
            state = state.copy(
                number2 = state.number2 + number
            )
        }
    }

    companion object{
        private const val MAX_NUM_LENGTH = 8
    }

    private fun enterOperation(operation: CalculatorAction.CalculatorOperation){
        if(state.number1.isNotBlank()){
            // state.copy makes a new copy of state and changes the given parameters
            // this allows us to keep state fields as immutable and trigger recompisition
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal(){
        if(state.operation == null && !state.number1.contains(".")
            && state.number1.isNotBlank()){
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }
        if(state.number2.isNotBlank() && !state.number2.contains(".")){
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }
}

