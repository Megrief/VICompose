package com.vicompose.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vicompose.data.room.dao.ImageDao
import com.vicompose.data.room.dao.RemoteKeyDao
import com.vicompose.data.room.dto.ImageDto
import com.vicompose.data.room.dto.RemoteKey

@Database(
    entities = [
        ImageDto::class,
        RemoteKey::class
    ],
    version = 1,
    exportSchema = false
)
abstract class VeryInterestingDb : RoomDatabase() {
    abstract val imageDao: ImageDao
    abstract val remoteKeyDao: RemoteKeyDao
}