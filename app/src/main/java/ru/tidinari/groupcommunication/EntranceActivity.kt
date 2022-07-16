package ru.tidinari.groupcommunication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import ru.tidinari.groupcommunication.databinding.ActivityEntranceBinding


class EntranceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntranceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntranceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Buttons validation
        val groupInput = binding.groupInput
        val passwordInput = binding.passwordInput
        val button = binding.choose

        var loginCheck = false
        var passwordCheck = false

        // Case - user selected item, but then changed it.
        // This should disable the button
        groupInput.addTextChangedListener(beforeTextChanged = { text, _, _, _ ->
            if (text?.length == 10) {
                loginCheck = false
                button.isEnabled = loginCheck && passwordCheck
            }
        })
        groupInput.setOnItemClickListener { _, _, _, _ ->
            loginCheck = true
            button.isEnabled = loginCheck && passwordCheck
        }

        // Case - password is empty or it's not safe enough for checking
        // This should disable the button
        passwordInput.addTextChangedListener {
            passwordCheck =
                !it.isNullOrBlank() && it.length > 3
            button.isEnabled = loginCheck && passwordCheck
        }

        // Autocomplete adapter
        // TODO: get groups from somewhere
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            listOf(
                "АААА-01-22", "ББББ-22-22"
            )
        )
        groupInput.setAdapter(adapter)
        // Button action
        button.setOnClickListener {
            // TODO: validate group and password properly
            transferToGroupInteractionActivity(groupInput.text.toString(), passwordInput.text.toString())
        }
    }

    private fun transferToGroupInteractionActivity(group: String, password: String) {
        val intent = Intent(this, GroupInteractionActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        intent.putExtra("group", group)
        intent.putExtra("password", password)
        startActivity(intent)
        finish()
    }
}