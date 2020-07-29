package com.starscience.cuisinietmoi.cuisineetmoi.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.starscience.cuisinietmoi.cuisineetmoi.cache.utils.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class GhibliCache(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val director: String,
    val producer: String,
    val release_date: String,
    val rt_score: String
)