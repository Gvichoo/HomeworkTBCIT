package com.example.homeworktbc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val shesayvani = findViewById<EditText>(R.id.sheiyvane)
        val switchLanguage = findViewById<Switch>(R.id.switch1)
        val clickme = findViewById<Button>(R.id.button1)
        val shedegi = findViewById<TextView>(R.id.shedegi)

        clickme.setOnClickListener {
            val input = shesayvani.text.toString()

            if (input.isEmpty()) {
                Toast.makeText(this, "გთხოვთ შეიყვანოთ რიცხვი.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val number = input.toInt()
            if (number < 1 || number > 1000) {
                Toast.makeText(this, "ჩაწერე რიცხვი 1-დან 1000მდე მხოლოდ.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val isEnglish = switchLanguage.isChecked
            val result = if (isEnglish) {
                inglisurzeGadacvla(number)
            } else {
                qartulzeGadacvla(number)
            }

            shedegi.text = result
        }
    }

    fun inglisurzeGadacvla(number: Int): String {
        val oneToNine = arrayOf(
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
        )
        val tenToNineteen = arrayOf(
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen"
        )
        val tens = arrayOf(
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
        )

        var result = ""
        var num = number

        if (num >= 1000) {
            result += "one thousand "
            num %= 1000
        }
        if (num >= 100) {
            result += oneToNine[num / 100] + " hundred "
            num %= 100
        }
        if (num >= 20) {
            result += tens[num / 10] + " "
            num %= 10
        } else if (num >= 10) {
            result += tenToNineteen[num - 10] + " "
            num = 0
        }
        if (num > 0) {
            result += oneToNine[num]
        }

        return result.trim()
    }

    fun qartulzeGadacvla(number: Int): String {
        val erteulebi = arrayOf(
            "","ერთი", "ორი", "სამი", "ოთხი", "ხუთი", "ექვსი", "შვიდი", "რვა", "ცხრა"
        )
        val atidanCxrametamde = arrayOf(
            "ათი", "თერთმეტი", "თორმეტი", "ცამეტი", "თოთხმეტი", "თხუთმეტი",
            "თექვსმეტი", "ჩვიდმეტი", "თვრამეტი", "ცხრამეტი"
        )
        val ateulebi = arrayOf(
            "", "", "ოცდა", "ოცდაათი", "ორმოცი", "ორმოცდაათი", "სამოცი",
            "სამოცდაათი", "ოთხმოცი", "ოთხმოცდაათი"
        )

        var result = ""
        var num = number

        if (num >= 1000) {
            result += "ათასი "
            num %= 1000
        }
        if (num >= 100) {
            result += erteulebi[num / 100] + "ასი "
            num %= 100
        }
        if (num >= 20) {
            result += ateulebi[num / 10] + " "
            num %= 10
        } else if (num >= 10) {
            result += atidanCxrametamde[num - 10] + " "
            num = 0
        }
        if (num > 0) {
            result += erteulebi[num]
        }

        return result.trim()
    }
}