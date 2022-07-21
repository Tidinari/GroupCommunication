package ru.tidinari.groupcommunication.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.tidinari.groupcommunication.R
import ru.tidinari.groupcommunication.app.GroupCommunicationApplication
import ru.tidinari.groupcommunication.databinding.ActivityGroupInteractionBinding
import ru.tidinari.groupcommunication.models.groups.Group
import ru.tidinari.groupcommunication.view.entrance.EntranceActivity

class GroupInteractionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupInteractionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val group = GroupCommunicationApplication.localRepo.getString("group", null)
        if (group.isNullOrEmpty()) {
            transferToGroupInteractionActivity()
        }

        binding = ActivityGroupInteractionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_ingroup_interaction)
        navView.setupWithNavController(navController)
    }

    private fun transferToGroupInteractionActivity() {
        val intent = Intent(this, EntranceActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME
        startActivity(intent)
        finish()
    }
}