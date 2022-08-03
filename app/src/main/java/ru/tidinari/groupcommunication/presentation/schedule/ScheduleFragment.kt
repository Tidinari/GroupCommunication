package ru.tidinari.groupcommunication.presentation.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ru.tidinari.groupcommunication.R
import ru.tidinari.groupcommunication.app.GroupCommApplication
import ru.tidinari.groupcommunication.data.models.DaySchedule
import ru.tidinari.groupcommunication.databinding.FragmentScheduleBinding

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
                initializer { ScheduleViewModel(GroupCommApplication.group!!) }
            }
        }).value

        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.weekLessons.adapter = LessonsAdapter(DaySchedule(mutableListOf()))

        // Construct week tabs
        for (i in 1..16) {
            binding.weeks.addTab(binding.weeks.newTab().setText("$i"))
        }
        binding.weeks.addOnTabSelectedListener(
            WeekTabListener(
                scheduleViewModel, binding.weekLessons, binding.weekDays, binding.weeks, viewLifecycleOwner
            )
        )

        scheduleViewModel.schedule.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.successful_refreshed, Toast.LENGTH_LONG)
            if (scheduleViewModel.usedRemoteStorage) {
                scheduleViewModel.writeScheduleToLocalStorage()
            }
        }

        val isSuccessful = scheduleViewModel.getLocalSchedule()
        if (!isSuccessful) {
            Toast.makeText(requireContext(), getString(R.string.problem_with_getting_local_schedule), Toast.LENGTH_SHORT)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}