package com.viewer.model

import android.graphics.drawable.Drawable

class File(val type : Type, var title : String, var path : String?, val appIcon : Drawable?){
    enum class Type{
        Pdf, Audio, Video, Photo, Other, App
    }
}