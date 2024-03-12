package com.example.myapplication

// SurveySummaryActivity.kt
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SurveySummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_summary)

        val summaryContainer: LinearLayout = findViewById(R.id.summaryContainer)
        val summaryTitleTextView: TextView = findViewById(R.id.summaryTitleTextView)

        // Retrieve survey summary data from intent
        val surveySummaryData = intent.getStringArrayListExtra("SurveySummaryData")

        if (surveySummaryData != null) {
            summaryTitleTextView.text = "Survey Summary"

            for (item in surveySummaryData) {
                val summaryItemTextView = TextView(this)
                summaryItemTextView.text = item
                summaryContainer.addView(summaryItemTextView)
            }
        } else {
            // Handle the case where no survey summary data is provided
            summaryTitleTextView.text = "Error: No survey summary data"
        }
    }
}
