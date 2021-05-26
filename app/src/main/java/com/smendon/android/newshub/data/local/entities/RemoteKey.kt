package com.smendon.android.newshub.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey val newsId: String,
    val prevKey: Int?,
    val nextKey: Int?
)
