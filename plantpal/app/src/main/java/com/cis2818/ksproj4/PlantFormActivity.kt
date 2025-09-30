package com.cis2818.ksproj4

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

//adding plant
class PlantFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_form)
        title = "Add Plant"

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val lightSeek = findViewById<SeekBar>(R.id.lightSeek)
        val difficultyRate = findViewById<RatingBar>(R.id.difficultyRate)
        val notesInput = findViewById<EditText>(R.id.notesInput)
        val saveBtn = findViewById<Button>(R.id.saveBtn)

        saveBtn.setOnClickListener {
            val newPlant = Plant(
                name = nameInput.text.toString(),
                light = lightSeek.progress,
                difficulty = difficultyRate.rating.toInt(),
                notes = notesInput.text.toString()
            )

            setResult(RESULT_OK, intent.putExtra("plant", newPlant))
            finish()
        }
    }
}
