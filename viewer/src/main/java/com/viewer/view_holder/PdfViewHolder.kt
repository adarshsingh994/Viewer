package com.viewer.view_holder

import android.content.Context
import android.graphics.pdf.PdfRenderer
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.viewer.R
import com.viewer.adapter.PdfAdapter


class PdfViewHolder(private val context : Context, itemView : View, private val pageWidth : Int) : RecyclerView.ViewHolder(itemView){

    val pdfRecyclerView = itemView.findViewById(R.id.pdf_recycler_view) as RecyclerView

    var pdfAdapter : PdfAdapter? = null

    init {
        initRecyclerView()
    }

    fun setRenderer(renderer: PdfRenderer){
        pdfAdapter?.setRenderer(renderer)
    }

    private fun initRecyclerView(){
        val layoutManger = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        pdfAdapter = PdfAdapter(pageWidth, Glide.with(context))
        pdfRecyclerView.layoutManager = layoutManger
        pdfRecyclerView.adapter = pdfAdapter
    }
}