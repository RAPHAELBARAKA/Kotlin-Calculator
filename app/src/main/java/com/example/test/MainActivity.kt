package com.example.test

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputField: EditText
    private lateinit var resultField: EditText
    private var currentNumber = ""
    private var firstOperand = 0.0
    private var secondOperand = 0.0
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputField = findViewById(R.id.inputs)
        resultField = findViewById(R.id.results)

        setupButtons()

        // Handle equals button click
        findViewById<Button>(R.id.buttonequal).setOnClickListener {
            if (operator.isNotEmpty()) {
                evaluateExpression()
            }
        }

        // Clear screen button
        findViewById<Button>(R.id.buttonAC).setOnClickListener {
            clearScreen()
        }
    }

    private fun setupButtons() {
        val buttons = arrayOf(
            R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6,
            R.id.button7, R.id.button8, R.id.button9,
            R.id.button0, R.id.buttondot
        )

        for (buttonId in buttons) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                appendNumber(button.text.toString())
            }
        }

        // Setup operators
        val operators = mapOf(
            R.id.buttonplus to "+",
            R.id.buttonminus to "-",
            R.id.buttonslash to "/",
            R.id.buttonstar to "*"
        )

        for ((buttonId, operatorSymbol) in operators) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {
                selectOperator(operatorSymbol)
            }
        }
    }

    private fun appendNumber(number: String) {
        currentNumber += number
        inputField.append(number) // Append the number to the input field
    }

    private fun selectOperator(operatorSymbol: String) {
        if (currentNumber.isNotEmpty()) {
            firstOperand = currentNumber.toDoubleOrNull() ?: 0.0
            currentNumber = ""
            operator = operatorSymbol
            inputField.append(operator) // Append the operator to the input field
        }
    }

    private fun evaluateExpression() {
        secondOperand = currentNumber.toDoubleOrNull() ?: 0.0
        val result = when (operator) {
            "+" -> firstOperand + secondOperand
            "-" -> firstOperand - secondOperand
            "*" -> firstOperand * secondOperand
            "/" -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN
            else -> 0.0
        }
        resultField.setText(result.toString()) // Display the result in the result field
        clearCurrentState()
    }

    private fun clearScreen() {
        inputField.text.clear()
        resultField.text.clear()
        clearCurrentState()
    }

    private fun clearCurrentState() {
        currentNumber = ""
        firstOperand = 0.0
        secondOperand = 0.0
        operator = ""
    }
}