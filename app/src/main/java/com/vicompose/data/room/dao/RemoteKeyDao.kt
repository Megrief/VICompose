package com.vicompose.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vicompose.data.room.dto.RemoteKey

@Dao
interface RemoteKeyDao {

    @Insert(entity = RemoteKey::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM remote_keys where id = :imageUrl")
    suspend fun getRemoteKey(imageUrl: String): RemoteKey?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()

    @Query("SELECT created FROM remote_keys ORDER BY created DESC LIMIT 1")
    suspend fun getLastRemoteKeyCreated(): Long?
}