package com.example.alcoholTracker.presentation.home.components.home_milestones_card

import com.example.alcoholTracker.R
import com.example.alcoholTracker.data.Milestone

object HomeMilestonesCardHelper {
    fun getIconForMilestone(milestone: Milestone?): Int {
        when(milestone?.icon) {
            "money_24px" -> {
                return R.drawable.money_24px
            }
            "liquor_24px" -> {
                return R.drawable.liquor_24px
            }
            "mail_24px" -> {
                return R.drawable.mail_24px
            }
        }
        return R.drawable.numbers_24px
    }
}