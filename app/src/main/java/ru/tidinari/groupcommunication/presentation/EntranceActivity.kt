package ru.tidinari.groupcommunication.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import ru.tidinari.groupcommunication.databinding.ActivityEntranceBinding


class EntranceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntranceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val entranceViewModel = viewModels<EntranceViewModel>().value
        // Bind activity
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val groupInput = binding.groupInput
        val button = binding.choose
        // Allow only to autocomplete
        validateInput(groupInput, button)
        // Autocomplete adapter
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            entranceViewModel.groupList
        )
        groupInput.setAdapter(adapter)
        // Button action
        button.setOnClickListener {
            entranceViewModel.saveGroupLocally(groupInput.text.toString())
            transferToGroupInteraction()
        }
    }

    private fun transferToGroupInteraction() {
        val intent = Intent(this, GroupInteractionActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        finish()
    }

    private fun validateInput(
        groupInput: AutoCompleteTextView, button: Button
    ) {
        var loginCheck: Boolean
        // Case - user selected item, but then changed it.
        // This should disable the button
        groupInput.addTextChangedListener(beforeTextChanged = { text, _, _, _ ->
            if (text?.length == 10) {
                loginCheck = false
                button.isEnabled = loginCheck
            }
        })
        groupInput.setOnItemClickListener { _, _, _, _ ->
            loginCheck = true
            button.isEnabled = loginCheck
        }
    }
}