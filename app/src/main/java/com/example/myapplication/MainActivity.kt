package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val surveyTypeRadioGroup: RadioGroup = findViewById(R.id.surveyTypeRadioGroup)
        val startSurveyButton: Button = findViewById(R.id.startSurveyButton)

        startSurveyButton.setOnClickListener {
            val selectedRadioButtonId = surveyTypeRadioGroup.checkedRadioButtonId

            if (selectedRadioButtonId != -1) {
                val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
                val surveyType = selectedRadioButton.text.toString()

                startSurvey(surveyType)
            }
        }
    }

    private fun startSurvey(surveyType: String) {
        val intent = Intent(this, SurveyActivity::class.java)
        intent.putExtra("SurveyType", surveyType)
        startActivity(intent)
    }

}