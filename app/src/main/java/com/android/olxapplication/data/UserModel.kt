package com.android.olxapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserModel (
    @PrimaryKey
    val id: Int = 0,
)