package com.nindikiranaf.indieband

import androidx.room.*

@Dao
interface IndieBandDao {
    @Insert
    fun insert(indieBand: IndieBand)

    @Update
    fun update(indieBand: IndieBand)

    @Delete
    fun delete(indieBand: IndieBand)

    @Query("SELECT * FROM IndieBand")
    fun selectAll() : List<IndieBand>

    @Query("SELECT * FROM IndieBand WHERE id=:id")
    fun select(id: Int) : IndieBand
}