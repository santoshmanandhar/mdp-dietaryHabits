package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.widget.*

class SurveyActivity : AppCompatActivity() {
    private var surveyType: String? = null
    private lateinit var radioGroupContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        surveyType = intent.getStringExtra("SurveyType")

        val surveyTitleTextView: TextView = findViewById(R.id.surveyTitleTextView)
        surveyTitleTextView.text = "Survey: $surveyType"

        radioGroupContainer = findViewById(R.id.radioGroupContainer)

        // Set up survey questions and options with radio buttons
        setupSurveyQuestions()

        val startSurveyButton: Button = findViewById(R.id.submitSurveyButton)
        startSurveyButton.setOnClickListener {
            submitSurvey()
        }

    }

    private fun setupSurveyQuestions() {
        val surveyQuestions = getSurveyQuestions() // Replace with your data source

        for (question in surveyQuestions) {
            val questionTextView = TextView(this)
            questionTextView.text = question.question
            radioGroupContainer.addView(questionTextView)

            val radioGroup = RadioGroup(this)
            for (option in question.options) {
                val radioButton = RadioButton(this)
                radioButton.text = option
                radioGroup.addView(radioButton)
            }
            radioGroupContainer.addView(radioGroup)
        }
    }

    private fun getSurveyQuestions(): ArrayList<SurveyQuestion> {
        val questions = ArrayList<SurveyQuestion>()

        when (surveyType) {
            "Food Preferences" -> {
                val question1Options = arrayListOf("Chinese", "French", "Italian", "Indian", "Japanese", "Thai", "Turkish")
                questions.add(SurveyQuestion("What is your favorite cuisine?", question1Options))

                val question2Options = arrayListOf("Never", "Rarely", "Sometimes", "Frequently")
                questions.add(SurveyQuestion("How often do you eat out?", question2Options))

                val question3Options = arrayListOf("Yes", "No")
                questions.add(SurveyQuestion("Do you prefer spicy food?", question3Options))

                val question4Options = arrayListOf("Yes", "No")
                questions.add(SurveyQuestion("Do you prefer vegetarian food?", question4Options))

                val question5Options = arrayListOf("Yes", "No")
                questions.add(SurveyQuestion("Do you like seafood?", question5Options))
            }
            "Dietary Habits" -> {
                val question1Options = arrayListOf("Yes", "No")
                questions.add(SurveyQuestion("Are you vegetarian?", question1Options))

                val question2Options = arrayListOf("Yes", "No")
                questions.add(SurveyQuestion("Do you prefer organic food?", question2Options))

                val question3Options = arrayListOf("Yes", "No")
                questions.add(SurveyQuestion("Do you consume dairy products?", question3Options))

                val question4Options = arrayListOf("Yes", "No")
                questions.add(SurveyQuestion("Do you eat fast food?", question4Options))

                val question5Options = arrayListOf("Yes", "No")
                questions.add(SurveyQuestion("Do you have any food allergies?", question5Options))
            }
            // Add more cases if needed
            else -> {
                // Default or unknown survey type
                // You can handle this case as needed
            }
        }

        return questions
    }

    private fun submitSurvey() {
        val selectedOptions = ArrayList<String>()

        // Collect selected options from RadioGroups
        for (i in 0 until radioGroupContainer.childCount step 2) {
            val questionTextView = radioGroupContainer.getChildAt(i) as TextView
            val radioGroup = radioGroupContainer.getChildAt(i + 1) as RadioGroup

            val selectedRadioButtonId = radioGroup.checkedRadioButtonId

            if (selectedRadioButtonId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedOption = selectedRadioButton.text.toString()
                selectedOptions.add("${questionTextView.text}: $selectedOption")
            }
        }

        // Navigate to SurveySummaryActivity
        val intent = Intent(this, SurveySummaryActivity::class.java)
        intent.putStringArrayListExtra("SurveySummaryData", selectedOptions)
        startActivity(intent)
    }
}
