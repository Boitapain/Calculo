package com.example.calculo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private lateinit var tvDisplay: TextView
    private var currentInput = ""
    private var lastOperator = ""
    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        tvDisplay = findViewById(R.id.tvDisplay)

        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in numberButtons){
            findViewById<Button>(id).setOnClickListener{appendToInput((it as Button).text.toString())}
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener{performOperation("+")}
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { performOperation("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { performOperation("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { performOperation("/") }

        findViewById<Button>(R.id.btnClear).setOnClickListener { clear() }
        findViewById<Button>(R.id.btnDot).setOnClickListener{ if(!currentInput.contains(".") && currentInput.isNotEmpty()){appendToInput(".")}}
        findViewById<Button>(R.id.btnPercent).setOnClickListener{ percentage() }
        findViewById<Button>(R.id.btnChangeSign).setOnClickListener{ changeSign() }
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }
    }

    fun appendToInput(value: String){
        currentInput+=value
        tvDisplay.text=currentInput
    }

    fun performOperation(operator: String){
        if (currentInput.isNotEmpty()) {
            calculateResult()
            lastOperator = operator
            currentInput = ""
        }
    }

    fun calculateResult(){
        if (currentInput.isNotEmpty()) {
            val number = currentInput.toDouble()
            result = when (lastOperator) {
                "+" -> result + number
                "-" -> result - number
                "*" -> result * number
                "/" -> result / number
                else -> number
            }
            tvDisplay.text = result.toString()
            currentInput = ""
            lastOperator = ""
        }
    }

    fun clear(){
        currentInput = ""
        lastOperator = ""
        result = 0.0
        tvDisplay.text = "0"
    }

    fun percentage(){
        if(currentInput.isNotEmpty()){
            val value = currentInput.toDouble()
            val percentageValue = value / 100

            tvDisplay.text = percentageValue.toString()
            currentInput = percentageValue.toString()
        }
    }

    fun changeSign(){
        var value = currentInput.toDouble()
        value = -value
        tvDisplay.text = value.toString()
        currentInput = value.toString()
    }
}

