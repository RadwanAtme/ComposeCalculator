package com.example.calculator

sealed class CalculatorAction{
    data class Number(val number: Int): CalculatorAction()
    object Clear: CalculatorAction()
    object Delete: CalculatorAction()
    object Decimal: CalculatorAction()
    object Calculate: CalculatorAction()
    sealed class CalculatorOperation(val symbol: String): CalculatorAction(){
        object Add:      CalculatorOperation("+")
        object Subtract: CalculatorOperation("-")
        object Multiply: CalculatorOperation("x")
        object Divide:   CalculatorOperation("/")
    }
}