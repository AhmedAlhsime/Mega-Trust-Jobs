package com.example.megatrustjobs.data.local.room.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class JobsEntityes(
    @PrimaryKey
    var id: String,

    var type: String? = null,

    var url: String? = null,

    var created_at: String? = null,

    var company: String? = null,

    var company_url: String? = null,

    var location: String? = null,

    var title: String? = null,

    var description: String? = null,

    var how_to_apply: String? = null,

    var company_logo: String? = null,

    var ic_add_fav: Boolean = false
):Parcelable
