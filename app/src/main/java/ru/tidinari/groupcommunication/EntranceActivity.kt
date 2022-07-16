package ru.tidinari.groupcommunication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener


class EntranceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        // Buttons validation
        val groupInput = findViewById<AutoCompleteTextView>(R.id.group_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val button = findViewById<Button>(R.id.choose)
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

        // Case - password is empty or it's not safe enough for check
        // This should disable the button
        passwordInput.addTextChangedListener {
            passwordCheck =
                !it.isNullOrBlank() && it.length > 3
            button.isEnabled = loginCheck && passwordCheck
        }

        // Autocomplete adapter
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            listOf(
                "Belgium", "France", "Italy", "Germany111", "Spain67891"
            )
        )
        groupInput.setAdapter(adapter)
    }
}