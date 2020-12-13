package com.nindikiranaf.indieband

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [IndieBand::class], version = 1)
abstract class IndieBandDatabase: RoomDatabase(){
    abstract  fun indieBandDao() : IndieBandDao

    companion object{
        private var instance: IndieBandDatabase? = null

        private val callback = object: Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }

        private fun populate(db: IndieBandDatabase) {
            val indieBandDao = db.indieBandDao()
        }
    }
}