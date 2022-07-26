package ru.tidinari.groupcommunication.view.schedule

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import ru.tidinari.groupcommunication.app.GroupCommunicationApplication
import ru.tidinari.groupcommunication.models.repo.schedule.Lesson
import ru.tidinari.groupcommunication.viewmodels.schedule.ScheduleViewModel

class WeekTabListener(
    private val viewModel: ScheduleViewModel,
    weekDaysList: RecyclerView,
    weekDayTab: TabLayout,
    weekTab: TabLayout,
    lifecycle: LifecycleOwner
) : TabLayout.OnTabSelectedListener {

    private val _weekSchedule: MutableLiveData<Map<Int, List<Lesson>>> = MutableLiveData()
    private val weekSchedule: LiveData<Map<Int, List<Lesson>>> = _weekSchedule

    init {
        val weekNumTab =
            weekTab.getTabAt(GroupCommunicationApplication.sharedPreferences.getInt("weekNum", 0))
        weekTab.selectTab(weekNumTab)

        viewModel.schedule.observe(lifecycle) {
            val selectedTab = weekTab.selectedTabPosition
            val tab = weekTab.getTabAt(selectedTab)
            weekTab.selectTab(tab, false)
        }
        weekDayTab.addOnTabSelectedListener(
            WeekDaysTabListener(
                weekSchedule,
                viewModel,
                weekDayTab,
                weekDaysList,
                lifecycle
            )
        )
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab == null) return
        val week = tab.position + 1
        viewModel.schedule.value?.let { schedule ->
            if (schedule.containsKey(week)) {
                val weekScheduleList = schedule[week] ?: return@let
                _weekSchedule.postValue(weekScheduleList.groupBy { it.day })
            }
        }
        GroupCommunicationApplication.sharedPreferences.edit().putInt(
            "weekNum",
            week - 1
        ).commit()
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        onTabSelected(tab)
    }
}