package com.example.domain.models


data class ChildrenActivity(
    val activityType:String,
    val activityDate:String,
    val txtDescription : String?=null,
    val image: String?=null,
    val napType:String?=null,
    val foodType: String?=null,
    val eatType:String?=null,
    val wasteType:String?=null,
    val wasteShape:String?=null,
    val nameToFaceType:String?=null,
    val temperature:String?=null,
    val createdAt:String,
    val meals:List<String>?=null,
    val id:Int
)

data class CustomizeChildrenActivity(
    val activityDate:String,
    val txtDescription : String,
    val image: String?=null,
    val imgLogo:Int
    )

fun ChildrenActivity.toCustomize(imgLogo:Int):CustomizeChildrenActivity{
    return CustomizeChildrenActivity(
        activityDate = activityDate,
        txtDescription = activityType,
        image = image,
        imgLogo = imgLogo
    )
}
fun ChildrenActivity.toCustomizeNoteAchievementMeds(imgLogo:Int,note:String?):CustomizeChildrenActivity{
    return CustomizeChildrenActivity(
            activityDate = activityDate,
            txtDescription = "$note" ,
            image = image,
            imgLogo = imgLogo
        )
}

fun ChildrenActivity.toCustomizePhoto(imgLogo:Int,studentName:String):CustomizeChildrenActivity{
    return CustomizeChildrenActivity(
        activityDate = activityDate,
        txtDescription = "$studentName was in a photo" ,
        image = image,
        imgLogo = imgLogo
    )
}
fun ChildrenActivity.toCustomizeVideo(imgLogo:Int,studentName:String):CustomizeChildrenActivity{
    return CustomizeChildrenActivity(
        activityDate = activityDate,
        txtDescription = "$studentName was in a video" ,
        image = image,
        imgLogo = imgLogo
    )
}

fun ChildrenActivity.toCustomizeIncidents(imgLogo:Int,note:String?):CustomizeChildrenActivity{
    return CustomizeChildrenActivity(
        activityDate = activityDate,
        txtDescription = "$note" ,
        image = image,
        imgLogo = imgLogo
    )
}

fun ChildrenActivity.toCustomizeNap(imgLogo:Int, napType: String):CustomizeChildrenActivity{
    return CustomizeChildrenActivity(
        activityDate = activityDate,
        txtDescription = napType,
        image = image,
        imgLogo = imgLogo
    )
}
fun ChildrenActivity.toCustomizeHealthCheck(imgLogo:Int, temp:String? ,description: String?):CustomizeChildrenActivity{
    return CustomizeChildrenActivity(
        activityDate = activityDate,
        txtDescription = "temperature is $temp\n$description",
        image = image,
        imgLogo = imgLogo
    )
}

fun ChildrenActivity.toCustomizePhoto(imgLogo:Int, image: String?,description :String):CustomizeChildrenActivity{
    return CustomizeChildrenActivity(
        activityDate = activityDate,
        txtDescription = description,
        image = image,
        imgLogo = imgLogo
    )
}

fun ChildrenActivity.toCustomizeFood(imgLogo:Int):CustomizeChildrenActivity{
    return CustomizeChildrenActivity(
        activityDate = activityDate,
        txtDescription = "$foodType / $eatType \n${meals.toString()} ",
        image = image,
        imgLogo = imgLogo
    )
}

fun ChildrenActivity.toCustomizePotty(imgLogo:Int,note: String?):CustomizeChildrenActivity{
    return if(note.isNullOrEmpty()) {
        CustomizeChildrenActivity(
            activityDate = activityDate,
            txtDescription = "$wasteType , $wasteShape" ,
            image = image,
            imgLogo = imgLogo
        )
    }else{
        CustomizeChildrenActivity(
            activityDate = activityDate,
            txtDescription = "$note \n$wasteType , $wasteShape" ,
            image = image,
            imgLogo = imgLogo
        )
    }
}

fun ChildrenActivity.toCustomizeNameToFace(imgLogo:Int,note: String?):CustomizeChildrenActivity{
    return if(note.isNullOrEmpty()) {
        CustomizeChildrenActivity(
            activityDate = activityDate,
            txtDescription = "$nameToFaceType" ,
            image = image,
            imgLogo = imgLogo
        )
    }else{
        CustomizeChildrenActivity(
            activityDate = activityDate,
            txtDescription = "$note \n$nameToFaceType" ,
            image = image,
            imgLogo = imgLogo
        )
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//fun formatDate(time:String):String{
////    val currentTime = LocalDateTime.now()
//    val date = Date(time)
//    Log.d("date", date.toString())
//    val format = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
////    currentTime.format(format).toString()
//    return LocalDateTime.parse(time).format(format).toString()
//}


