package com.smendon.android.newshub.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smendon.android.newshub.data.local.entities.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllKeys(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM remote_keys WHERE newsId = :id")
    suspend fun remoteKeysNewsId(id: String): RemoteKey?

    @Query("DELETE FROM remote_keys")
    suspend fun deleteAllKeys()
}

