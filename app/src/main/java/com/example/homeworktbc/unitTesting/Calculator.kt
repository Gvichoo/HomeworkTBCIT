package com.example.homeworktbc.unitTesting

class Calculator {
    fun add(a: Int, b: Int): Int {
        return a + b
    }
}

class StringHelper {
    fun isPositiveNumber(number : Int) : Boolean{
        return number > 0
    }
}

class StringUtils {
    fun reverseString(input: String): String {
        return input.reversed()
    }
}