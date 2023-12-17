package com.dicoding.parentpal.ui

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.parentpal.R
import com.dicoding.parentpal.databinding.ActivityFormBinding
import com.dicoding.parentpal.ml.Pp
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupActivityFactorDialog()

        binding.btnCalculate.setOnClickListener {
            setupButton()
        }
    }

    private fun setupButton() {
        val height = binding.edtHeight.text.toString().toFloat()
        val weight = binding.edtWeight.text.toString().toFloat()
        val gender = binding.spnGender.selectedItem.toString()
        val age = binding.edtAge.text.toString().toFloat()

        val sex = if (gender == "Perempuan") {
            0
        } else {
            1
        }

        Log.e("INPUT", height.toString())
        Log.e("INPUT", weight.toString())
        Log.e("INPUT", sex.toString())
        Log.e("INPUT", age.toString())

        val floatArray1 = floatArrayOf(sex.toFloat(), age, height, weight)

        setupCalculate(floatArray1)
    }

    private fun setupCalculate(floatArray1: FloatArray) {
        val byteBuffer = ByteBuffer.allocateDirect(floatArray1.size * 4) // 4 bytes per float
        byteBuffer.order(ByteOrder.nativeOrder())
        val floatBuffer = byteBuffer.asFloatBuffer()
        floatBuffer.put(floatArray1)

        // Reset the position of the ByteBuffer
        byteBuffer.position(0)

        // Load the ByteBuffer into a TensorBuffer
        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, floatArray1.size), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        // Assuming you have your TensorFlow Lite model
        val model = Pp.newInstance(this)

        // Run model inference
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

        Log.e("GIZIBAIK", outputFeature0[0].toString())
        Log.e("OVERWEIGHT", outputFeature0[1].toString())
        Log.e("UNDERWEIGHT", outputFeature0[2].toString())
        Log.e("STUNTING", outputFeature0[3].toString())

        // Releases model resources if no longer used.
        model.close()

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