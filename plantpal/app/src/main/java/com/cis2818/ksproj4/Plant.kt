package com.cis2818.ksproj4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//parcelize plant objects between activities
@Parcelize
data class Plant(
    val id: Long = System.currentTimeMillis(),
    var name: String = "",
    var light: Int = 50,
    var difficulty: Int = 3,
    var notes: String = ""
) : Parcelable
