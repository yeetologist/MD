package com.dicoding.parentpal.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.parentpal.R
import com.dicoding.parentpal.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupActivityFactorDialog()
    }

    private fun setupActivityFactorDialog() {
        binding.btnActivityFactor.setOnClickListener {
            val checkBoxBuilder = AlertDialog.Builder(this@FormActivity)
            checkBoxBuilder.setTitle("Choose Hobbies")
            val hobbiesList = arrayListOf(
                HobbyModel("Playing tag", false),
                HobbyModel("Soccer", false),
                HobbyModel("Basketball", false),
                HobbyModel("Cycling", false),
                HobbyModel("Swimming", false),
                HobbyModel("Jumping rope", false),
                HobbyModel("Dancing", false),
                HobbyModel("Gymnastics", false),
                HobbyModel("Hiking", false),
                HobbyModel("Skateboarding", false),
                HobbyModel("Rollerblading", false),
                HobbyModel("Yoga for kids", false),
                HobbyModel("Obstacle course", false),
                HobbyModel("Hide and seek", false),
                HobbyModel("Tennis", false),
                HobbyModel("Bowling", false),
                HobbyModel("Ice skating", false),
                HobbyModel("Mini golf", false),
                HobbyModel("Trampoline fun", false),
                HobbyModel("Tree climbing", false),
                HobbyModel("Hopscotch", false),
                HobbyModel("Frisbee", false),
                HobbyModel("Karate", false),
                HobbyModel("Judo", false),
                HobbyModel("Biking", false),
                HobbyModel("Rock climbing", false),
                HobbyModel("Ziplining", false),
                HobbyModel("Playground activities", false),
                HobbyModel("Scooter riding", false)
            )

            val onlyHobbiesNameList = hobbiesList.map { it.name }.toTypedArray()

            // here return ischecked is true list
            val onlyHobbiesIsCheckedList = hobbiesList.map { it.isChecked }.toBooleanArray()

            checkBoxBuilder.setMultiChoiceItems(
                onlyHobbiesNameList,
                onlyHobbiesIsCheckedList
            ) { _, position, isChecked ->
                hobbiesList[position].isChecked = isChecked
            }

            checkBoxBuilder.setPositiveButton("Ok") { dialog, which ->
//                val unCheckedHobbiesList = hobbiesList.filter { !it.isChecked }.map { it.name }
                val checkedHobbiesList = hobbiesList.filter { it.isChecked }.map { it.name }

                binding.etActivity.setText(getString(R.string.hobby_checked, "$checkedHobbiesList"))
            }

            checkBoxBuilder.setNegativeButton("Cancel", null)

            val dialog = checkBoxBuilder.create()
            dialog.show()
        }

    }

    private fun setupSpinner() {
        val spinner: Spinner = findViewById(R.id.spn_gender)
        ArrayAdapter.createFromResource(
            this,
            R.array.gender,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    data class HobbyModel(
        val name: String,
        var isChecked: Boolean = false,
    )

}