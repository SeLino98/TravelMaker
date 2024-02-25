package com.gumibom.travelmaker.ui.common

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class CustomGlide(private val context : Context) {

    /**
     *  Bitmap을 이용하여 Image Rendering
     */
    fun uploadBitmapImage(bitmap : Bitmap, view : ImageView) {
        Glide.with(context)
            .load(bitmap)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
            .transform(CenterCrop()) // Apply center crop to maintain aspect ratio
            .into(view)
    }

    /**
     *  uri를 이용하여 Image Rendering
     */
    fun uploadUriImage(uri : Uri, view : ImageView) {
        Glide.with(context)
            .load(uri)
            .transform(CenterCrop()) // Apply center crop to maintain aspect ratio
            .into(view)
    }

    /**
     *  url(String)을 이용하여 Image Rendering
     */
    fun uploadUriImage(uri : String, view : ImageView) {
        Glide.with(context)
            .load(uri)
            .transform(CenterCrop()) // Apply center crop to maintain aspect ratio
            .into(view)
    }

    /**
     * DrawableID를 이용하여 Image Rendering
     */
    fun uploadDrawableImage(drawableId : Int?, view : ImageView) {
        drawableId?.let {
            Glide.with(context)
                .load(drawableId)
                .into(view)
        }
    }
}