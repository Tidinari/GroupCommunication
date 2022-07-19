package ru.tidinari.groupcommunication.view.entrance

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import ru.tidinari.groupcommunication.view.GroupInteractionActivity
import ru.tidinari.groupcommunication.databinding.ActivityEntranceBinding
import ru.tidinari.groupcommunication.models.groups.Group
import ru.tidinari.groupcommunication.models.groups.repo.entrance.User
import ru.tidinari.groupcommunication.viewmodels.entrance.EntranceViewModel
import ru.tidinari.groupcommunication.viewmodels.settings.SettingsViewModel


class EntranceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntranceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val entranceViewModel = viewModels<EntranceViewModel>().value

        entranceViewModel.group.observe(this) {
            if (it != null) {
                transferToGroupInteractionActivity(it)
            } else {
                Toast.makeText(applicationContext, "Неправильный пароль!", Toast.LENGTH_LONG).show()
            }
        }

        val isUserStoredLocally = entranceViewModel.isUserStoredLocally()
        if (isUserStoredLocally) {
            entranceViewModel.signIn(entranceViewModel.getUser())
        }

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
                "АААА-01-22", "ТЕСТ-00-00"
            )
        )
        groupInput.setAdapter(adapter)
        // Button action
        button.setOnClickListener {
            entranceViewModel.signUp(groupInput.text.toString(), passwordInput.text.toString())
        }
    }

    private fun transferToGroupInteractionActivity(group: Group) {
        val intent = Intent(this, GroupInteractionActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        intent.putExtra("group", group.group)
        intent.putExtra("user", group.userSecret.userHash)
        intent.putExtra("password", group.groupSecret)
        startActivity(intent)
        finish()
    }
}