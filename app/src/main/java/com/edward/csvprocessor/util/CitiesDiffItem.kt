package com.edward.csvprocessor.util

import androidx.recyclerview.widget.DiffUtil
import com.edward.csvprocessor.model.Cities

/**
 * Created by Edward Muturi on 20/09/2019.
 */
class CitiesDiffItem : DiffUtil.ItemCallback<Cities>() {
    override fun areItemsTheSame(oldItem: Cities, newItem: Cities): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Cities, newItem: Cities): Boolean {
        return oldItem.state == newItem.state
    }
}