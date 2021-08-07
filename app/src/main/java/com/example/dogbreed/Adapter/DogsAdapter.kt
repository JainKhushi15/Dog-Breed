package com.example.dogbreed.Adapter

import Model.DogAPI
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogbreed.R
import com.squareup.picasso.Picasso
import java.security.AccessControlContext
import com.example.dogbreed.Adapter.DogsAdapter.ViewHolder as DogsAdapterViewHolder

class DogsAdapter (private val dogsImages: ArrayList<DogAPI>):RecyclerView.Adapter<RecyclerView.ViewHolder> () {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dogs_layout, parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Picasso.get().load(dogsImages[position].message).into(holder.itemView.dogImage)
    }

    override fun getItemCount(): Int {
        return dogsImages.size
    }

    class ViewHolder(v:View?) : RecyclerView(v!!),View.OnClickListener, Parcelable {
        override fun onClick(v: View?) {
        }

        init {
            itemView.setOnClickListener(this)
        }

        val dogImage = itemView.dogImage!!

        constructor(parcel: Parcel) : this(TODO("v")) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {

        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ViewHolder> {
            override fun createFromParcel(parcel: Parcel): ViewHolder {
                return ViewHolder(parcel)
            }

            override fun newArray(size: Int): Array<ViewHolder?> {
                return arrayOfNulls(size)
            }
        }
    }
}

