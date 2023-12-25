package com.pupilJ.domain.usecase.teacher

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.pupilJ.domain.models.PhotoVideo
import com.pupilJ.domain.repo.teacherrepo.TeacherRepo
import java.io.ByteArrayOutputStream
import java.io.File
import javax.inject.Inject


class TeacherUploadImageAndVideoUseCase @Inject constructor(private val teacherRepo: TeacherRepo) {
    suspend fun uploadImage(bitmap: Bitmap,context: Context,activityType: String,childId:String) : PhotoVideo {
        return teacherRepo.uploadImage(convertBitMapToUri(bitmap,context),activityType,childId)
    }
    suspend fun uploadVideo(file: File,activityType: String,childId:String) : PhotoVideo{
        return teacherRepo.uploadVideo(file,activityType,childId)
    }

    private fun convertBitMapToUri(bitmap: Bitmap, context: Context):File{
        val file = File(context.cacheDir,"photoFile") //Get Access to a local file.
        file.delete() // Delete the File, just in Case, that there was still another File
        file.createNewFile()
        val fileOutputStream = file.outputStream()
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,10,byteArrayOutputStream)
        val bytearray = byteArrayOutputStream.toByteArray()
        fileOutputStream.write(bytearray)
        fileOutputStream.flush()
        fileOutputStream.close()
        byteArrayOutputStream.close()
        Log.d("image", file.path)
        return file
    }

//    private fun convertUriToFile(uri: Uri, context: Context):File{
//        val file = File(context.cacheDir,"photoFile") //Get Access to a local file.
//        file.delete() // Delete the File, just in Case, that there was still another File
//        file.createNewFile()
//        val fileOutputStream = file.outputStream()
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        uri.compress(Bitmap.CompressFormat.PNG,10,byteArrayOutputStream)
//        val bytearray = byteArrayOutputStream.toByteArray()
//        fileOutputStream.write(bytearray)
//        fileOutputStream.flush()
//        fileOutputStream.close()
//        byteArrayOutputStream.close()
//        Log.d("image", file.path)
//        return file
//    }


//    private fun saveBitmap(bmp: Bitmap): File? {
//    val extStorageDirectory = Environment.getExternalStorageDirectory().toString()
//    var outStream: OutputStream? = null
//    // String temp = null;
//    var file = File(extStorageDirectory, "temp.png")
//    if (file.exists()) {
//        file.delete()
//        file = File(extStorageDirectory, "temp.png")
//    }
//    try {
//        outStream = FileOutputStream(file)
//        bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream)
//        outStream.flush()
//        outStream.close()
//    } catch (e: Exception) {
//        e.printStackTrace()
//        return null
//    }
//    Log.d("image", file.path.toString())
//
//    return file
//}



}