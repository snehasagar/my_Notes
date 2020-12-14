package com.mynotes.ui.main

import android.os.Parcel
import android.os.Parcelable


data class Notes(
        var Title: String? = "",
        var description : String?=""
): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(Title)
                parcel.writeString(description)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Notes> {
                override fun createFromParcel(parcel: Parcel): Notes {
                        return Notes(parcel)
                }

                override fun newArray(size: Int): Array<Notes?> {
                        return arrayOfNulls(size)
                }
        }
}

