package com.example.animecharacters.model

import android.database.Observable
import android.os.Parcel
import android.os.Parcelable
import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class Anime(
    val title: String,
    val synopsis: String,
    val image_url: String,
    val url: String,
    val type: String,
    val episodes: Int,
    val score: Double,
    val startDate: String,
    val endDate: String,
    val members: Double,
    val rated: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(synopsis)
        parcel.writeString(image_url)
        parcel.writeString(url)
        parcel.writeString(type)
        parcel.writeInt(episodes)
        parcel.writeDouble(score)
        parcel.writeString(startDate)
        parcel.writeString(endDate)
        parcel.writeDouble(members)
        parcel.writeString(rated)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Anime> {
        override fun createFromParcel(parcel: Parcel): Anime {
            return Anime(parcel)
        }

        override fun newArray(size: Int): Array<Anime?> {
            return arrayOfNulls(size)
        }
    }
}

data class Animes(
    val results: List<Anime>
)

interface AnimeService {
    @GET("/v3/search/anime")
    fun getAnimes(@Query("q") characterName: String):retrofit2.Call<Animes>
}