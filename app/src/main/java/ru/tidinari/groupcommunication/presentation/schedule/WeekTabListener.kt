package ru.tidinari.groupcommunication.presentation.schedule

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import ru.tidinari.groupcommunication.app.GroupCommApplication
import ru.tidinari.groupcommunication.data.models.WeekSchedule

class WeekTabListener(
    private val viewModel: ScheduleViewModel,
    weekDaysList: RecyclerView,
    weekDayTab: TabLayout,
    weekTab: TabLayout,
    lifecycle: LifecycleOwner
) : TabLayout.OnTabSelectedListener {

    private val _weekSchedule: MutableLiveData<WeekSchedule> = MutableLiveData()
    private val weekSchedule: LiveData<WeekSchedule> = _weekSchedule

    companion object {
        private const val WEEK_NUM = "weekNum"
    }

    init {
        val weekNumTab =
            weekTab.getTabAt(GroupCommApplication.sharedPreferences.getInt(WEEK_NUM, 0))
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
            val weekSchedule = schedule.onWeek(week)
            _weekSchedule.postValue(weekSchedule)
        }
        GroupCommApplication.sharedPreferences.edit().putInt(
            WEEK_NUM,
            tab.position
        ).commit()
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        onTabSelected(tab)
    }
}