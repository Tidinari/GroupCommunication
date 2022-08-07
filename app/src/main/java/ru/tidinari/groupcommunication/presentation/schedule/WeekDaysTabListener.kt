package ru.tidinari.groupcommunication.presentation.schedule

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import ru.tidinari.groupcommunication.R
import ru.tidinari.groupcommunication.app.GroupCommApplication
import ru.tidinari.groupcommunication.data.models.DayOfWeek

class WeekDaysTabListener(
    private val viewModel: ScheduleViewModel,
    private val weekDayTab: TabLayout,
    private val weekDaysList: RecyclerView,
    lifecycle: LifecycleOwner
) : TabLayout.OnTabSelectedListener {
    private val refreshPosition = 6
    private var previousTab: TabLayout.Tab? = null

    companion object {
        private const val DAY_WEEK_NUM = "dayWeekNum"
    }

    init {
        val dayWeekTab = weekDayTab.getTabAt(
            GroupCommApplication.sharedPreferences.getInt(
                DAY_WEEK_NUM,
                0
            )
        )
        weekDayTab.selectTab(dayWeekTab)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab == null) return
        val pos = tab.position
        if (pos == refreshPosition) {
            viewModel.getRemoteSchedule()
            tab.parent?.selectTab(previousTab)
            GroupCommApplication.toast(R.string.wait_for_refreshing)
        } else {
            GroupCommApplication.sharedPreferences.edit().putInt(
                DAY_WEEK_NUM,
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