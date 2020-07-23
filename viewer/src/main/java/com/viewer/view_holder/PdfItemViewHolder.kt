package com.viewer.view_holder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.viewer.R

class PdfItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val imageView = itemView.findViewById(R.id.pdf_item_image_view) as ImageView
}