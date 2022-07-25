package ru.tidinari.groupcommunication.view.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.tidinari.groupcommunication.R
import ru.tidinari.groupcommunication.app.GroupCommunicationApplication
import ru.tidinari.groupcommunication.databinding.FragmentScheduleBinding
import ru.tidinari.groupcommunication.viewmodels.EntranceViewModel
import ru.tidinari.groupcommunication.viewmodels.schedule.ScheduleViewModel

class ScheduleFragment : Fragment() {

    private var _binding: FragmentScheduleBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val scheduleViewModel = viewModels<ScheduleViewModel>(factoryProducer = {
            viewModelFactory {
                initializer { ScheduleViewModel(getGroup()) }
            }
        }).value

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        scheduleViewModel.schedule.observe(viewLifecycleOwner) {
            binding.schedule.text = Json.encodeToString(it)
            if (scheduleViewModel.usedRemoteStorage) {
                scheduleViewModel.writeScheduleToLocalStorage()
            }
        }

        if (scheduleViewModel.isLocalSchedulePresented()) {
            val isSuccessful = scheduleViewModel.getLocalSchedule()
            if (!isSuccessful) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.problem_with_getting_local_schedule),
                    Toast.LENGTH_SHORT
                )
            }
        }

        binding.refreshButton.setOnClickListener {
            scheduleViewModel.getRemoteSchedule()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getGroup(): String {
        return GroupCommunicationApplication.sharedPreferences.getString("group", "ТЕСТ-00-00")!!
    }
}