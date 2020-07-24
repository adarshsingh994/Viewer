package com.viewer.core

import android.app.Activity
import android.content.Intent
import com.viewer.ListActivity
import com.viewer.ViewerActivity
import com.viewer.model.Item

class Viewer (private val activity : Activity) {
    companion object{
        const val VIEWER_REQUEST_CODE = 9874
        const val KEY_VIEWER_SELECTED_LIST = "viewer_list_fkajn"
    }

    fun startList(){
        val listActivity = Intent(activity, ListActivity::class.java)
        activity.startActivityForResult(listActivity, VIEWER_REQUEST_CODE)
    }

    fun startViewer(items : ArrayList<Item>){
        val viewerActivity = Intent(activity, ViewerActivity::class.java)
        ViewerActivity.list.clear()
        ViewerActivity.list.addAll(items)
        activity.startActivity(viewerActivity)
    }
}