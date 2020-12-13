package com.nindikiranaf.indieband

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class IndieBand (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var nama_band: String = "",
    var genre: String = "",
    var asal: String = "",
    var th_aktif: String = "",
    var label: String = "",
    var anggota: String = "",
    var lagu_fav: String = "",
    var url: String = "",
    var gambar: String = ""
) : Parcelable