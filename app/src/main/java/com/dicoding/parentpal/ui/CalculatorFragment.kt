package com.dicoding.parentpal.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.dicoding.parentpal.R
import com.dicoding.parentpal.databinding.FragmentCalculatorBinding
import com.dicoding.parentpal.ml.HealthStatPredict
import com.dicoding.parentpal.ui.result.ResultActivity
import com.dicoding.parentpal.util.showSnackbarShort
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding

    private lateinit var hobbiesList: List<HobbyModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSpinner()
        setupActivityFactorDialog()
        binding.btnCalculate.setOnClickListener {
            setupButtonCalculate()
        }
        binding.btnClear.setOnClickListener {
            setupButtonClear()
        }
    }

    private fun setupButtonClear() {
        binding.edtHeight.setText("")
        binding.edtWeight.setText("")
        binding.edtAge.setText("")
        binding.etActivity.setText("")
        binding.edtNote.setText("")
        hobbiesList.forEach {
            it.isChecked = false
        }
    }

    private fun setupButtonCalculate() {
        val height = binding.edtHeight.text.toString()
        val weight = binding.edtWeight.text.toString()
        val gender = binding.spnGender.selectedItem.toString()
        val age = binding.edtAge.text.toString()

        val sex = if (gender == "Perempuan") {
            0
        } else {
            1
        }

        if (height.isEmpty() || weight.isEmpty() || age.isEmpty()) {
            showSnackbarShort(getString(R.string.empty_message), binding.root)
        } else {
            val floatArray1 =
                floatArrayOf(sex.toFloat(), age.toFloat(), height.toFloat(), weight.toFloat())
            setupCalculate(floatArray1)
        }

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
        val model = HealthStatPredict.newInstance(requireContext())

        // Run model inference
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray
        val resultIntent = Intent(requireContext(), ResultActivity::class.java)

        when (maxOf(outputFeature0[0], outputFeature0[1], outputFeature0[2], outputFeature0[3])) {
            outputFeature0[0] -> {
                resultIntent.putExtra(ResultActivity.EXTRA_RESULT, 0)
            }

            outputFeature0[1] -> {
                resultIntent.putExtra(ResultActivity.EXTRA_RESULT, 1)
            }

            outputFeature0[2] -> {
                resultIntent.putExtra(ResultActivity.EXTRA_RESULT, 2)
            }

            else -> {
                resultIntent.putExtra(ResultActivity.EXTRA_RESULT, 3)
            }
        }

        startActivity(resultIntent)

        // Releases model resources if no longer used.
        model.close()

    }

    private fun setupActivityFactorDialog() {

        hobbiesList = resources.getStringArray(R.array.activities_array).map { activity ->
            HobbyModel(activity, false)
        }

        binding.btnActivityFactor.setOnClickListener {
            val checkBoxBuilder = AlertDialog.Builder(requireContext())
            checkBoxBuilder.setTitle("Choose Hobbies")

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
        val spinner: Spinner = binding.root.findViewById(R.id.spn_gender)
        ArrayAdapter.createFromResource(
            requireContext(),
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