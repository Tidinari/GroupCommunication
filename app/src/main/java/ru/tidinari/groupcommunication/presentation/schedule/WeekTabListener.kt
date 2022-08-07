package ru.tidinari.groupcommunication.presentation.schedule

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import ru.tidinari.groupcommunication.app.GroupCommApplication

class WeekTabListener(
    private val viewModel: ScheduleViewModel,
    weekDaysList: RecyclerView,
    weekDayTab: TabLayout,
    weekTab: TabLayout,
    lifecycle: LifecycleOwner
) : TabLayout.OnTabSelectedListener {
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