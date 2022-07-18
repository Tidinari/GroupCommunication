package ru.tidinari.groupcommunication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.tidinari.groupcommunication.R
import ru.tidinari.groupcommunication.databinding.ActivityGroupInteractionBinding

class GroupInteractionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupInteractionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val group = (intent.extras?.getString("group") ?: "ТЕСТ-00-00")
        val password = (intent.extras?.getString("password") ?: "testgroup")

        binding = ActivityGroupInteractionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_ingroup_interaction)
        navView.setupWithNavController(navController)
    }
}