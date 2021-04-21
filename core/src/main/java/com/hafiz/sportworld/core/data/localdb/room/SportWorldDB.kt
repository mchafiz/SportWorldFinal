package com.hafiz.sportworld.core.data.localdb.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hafiz.sportworld.core.data.localdb.entities.SportWorldEntities


@Database(entities = [SportWorldEntities::class], version = 1, exportSchema = false)
abstract class SportWorldDB : RoomDatabase() {

    abstract fun sportWorldDao(): SportWorldDao

}