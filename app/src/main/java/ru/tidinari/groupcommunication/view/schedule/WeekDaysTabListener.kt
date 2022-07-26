package ru.tidinari.groupcommunication.view.schedule

import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import ru.tidinari.groupcommunication.R
import ru.tidinari.groupcommunication.app.GroupCommunicationApplication
import ru.tidinari.groupcommunication.models.repo.schedule.Lesson
import ru.tidinari.groupcommunication.viewmodels.schedule.ScheduleViewModel

class WeekDaysTabListener(
    private val schedule: LiveData<Map<Int, List<Lesson>>>,
    private val viewModel: ScheduleViewModel,
    private val weekDayTab: TabLayout,
    private val weekDaysList: RecyclerView,
    lifecycle: LifecycleOwner
) : TabLayout.OnTabSelectedListener {
    private val refreshPosition = 6
    private var previousTab: TabLayout.Tab? = null

    init {
        val dayWeekTab = weekDayTab.getTabAt(
            GroupCommunicationApplication.sharedPreferences.getInt(
                "dayWeekNum",
                0
            )
        )
        weekDayTab.selectTab(dayWeekTab)

        schedule.observe(lifecycle) {
            val index = weekDayTab.selectedTabPosition
            val tab = weekDayTab.getTabAt(index)
            weekDayTab.selectTab(tab, false)
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab == null) return
        val pos = tab.position
        if (pos == refreshPosition) {
            viewModel.getRemoteSchedule()
            tab.parent?.selectTab(previousTab)
            Toast.makeText(
                GroupCommunicationApplication.instance.applicationContext,
                R.string.wait_for_refreshing,
                Toast.LENGTH_SHORT
            )
        } else {
            weekDaysList.adapter = LessonsAdapter(schedule.value?.get(pos) ?: return)
            GroupCommunicationApplication.sharedPreferences.edit().putInt(
                "dayWeekNum",
                pos
            ).commit()
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        if (tab?.position != refreshPosition)
            previousTab = tab
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        onTabSelected(tab)
    }

}