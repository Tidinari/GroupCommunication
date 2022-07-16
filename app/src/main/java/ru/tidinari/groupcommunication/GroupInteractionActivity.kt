package ru.tidinari.groupcommunication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.tidinari.groupcommunication.databinding.ActivityGroupInteractionBinding

class GroupInteractionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupInteractionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val group = (intent.extras?.getString("group") ?: "АААА-01-20")
        val password = (intent.extras?.getString("password") ?: "123456789asd")

        binding = ActivityGroupInteractionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_ingroup_interaction)
        navView.setupWithNavController(navController)
    }
}