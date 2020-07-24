package com.viewersample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.viewer.core.Viewer
import com.viewer.model.Item
import java.io.File


class MainActivity : AppCompatActivity() {

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Viewer.VIEWER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Toast.makeText(this
                , "Result size: ${data?.getStringArrayListExtra(Viewer.KEY_VIEWER_SELECTED_LIST)?.size}"
                , Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val items = getItems()
//        if(items.isNotEmpty())
//            startViewer(items)
        startList()
    }

    private fun startList(){
        val viewer = Viewer(this)
        viewer.startList()
    }

    private fun startViewer(items : ArrayList<Item>){
        val viewer = Viewer(this)
        viewer.startViewer(items)
    }

    private fun getItems() : ArrayList<Item>{
        val list = ArrayList<Item>()
        getDemoFiles()?.forEach {file ->
            convertToItem(file)?.let{ item ->
                list.add(item)
            }
        }
        return list
    }

    private fun getDemoFiles() : Array<File>?{
        val fileName = "Test file"
        val file = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ), fileName
        )

        return if(file.exists()){
            file.listFiles()
        }else{
            file.mkdirs()
            null
        }
    }

    private fun convertToItem(file : File) : Item?{
        return try{
            val mimeType = getMimeType(file.path)
            val item = when {
                mimeType.contains("image") -> {
                    Item(file.path, Item.Type.Photo)
                }
                mimeType.contains("video") -> {
                    Item(file.path, Item.Type.Video)
                }
                mimeType.contains("pdf") -> {
                    Item(file.path, Item.Type.PDF)
                }
                else -> {
                    null
                }
            }
            item
        }catch (e : KotlinNullPointerException){
            null
        }
    }

    @Throws(KotlinNullPointerException::class)
    private fun getMimeType(path : String) : String{
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(path)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type!!
    }
}