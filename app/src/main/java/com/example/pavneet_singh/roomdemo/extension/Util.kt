package com.artproficiencyapp.extension

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.net.ConnectivityManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import com.example.pavneet_singh.roomdemo.R
import com.example.pavneet_singh.roomdemo.util.MyCustomProgressDialog
import java.io.File
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * Created by Admin on 26-11-2016.
 */


fun validCellPhone(number: String): Boolean {
    return Patterns.PHONE.matcher(number).matches()
}

private val TAG = "Util"
val SD_CARD_PATH = "/.ArtProficiency"
/*public final static String SD_CARD_PATH_PICTURE = "/storage/emulated/0/.BeaverQ";*/
val isCalncelable = false
val REQUEST_CAMERA = 1
val REQUEST_GALLERY = 2
private val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"
private val PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[@#$%]).{6,20})"
//private val PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
val BIRTHDAY_FORMAT_PARSER = SimpleDateFormat("yyyy-MM-dd")

val EMAIL_ADDRESS_PATTERN = Pattern.compile(

        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$")

val PASSWORD_VALIDATION = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z*\\d]).{6,20})")

fun getProgressDialog(context: Context): ProgressDialog {
    val myCustomProgressDialog = MyCustomProgressDialog(context)
    myCustomProgressDialog.isIndeterminate = true
    myCustomProgressDialog.setCancelable(isCalncelable)
    myCustomProgressDialog.show()
    return myCustomProgressDialog

}

fun dismissDialog(context: Context, mProgressDialog: ProgressDialog) {
    try {
        if (mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    } catch (e: Exception) {

    }

}

fun parseDateString(date: String): Calendar {
    val calendar = Calendar.getInstance()
    BIRTHDAY_FORMAT_PARSER.isLenient = false
    try {
        calendar.time = BIRTHDAY_FORMAT_PARSER.parse(date)
    } catch (e: ParseException) {
    }

    return calendar
}

fun isValidBirthday(birthday: String): Boolean {
    val calendar = parseDateString(birthday)
    val year = calendar.get(Calendar.YEAR)
    val thisYear = Calendar.getInstance().get(Calendar.YEAR)
    return year >= 1900 && year < thisYear
}


fun getRegistrationId(context: Context): String {
    val prefs = getGcmPreferences(context)

    return prefs.getString(context.packageCodePath, "")

}

fun isValidUrls(url: String): Boolean {
    val p = Patterns.WEB_URL
    val m = p.matcher(url.toLowerCase())
    return m.matches()
}

fun isValidPassword(password_string: String): Boolean {
    return password_string.matches(PASSWORD_PATTERN.toRegex())
}

fun storeRegistrationId(context: Context) {
    val prefs = getGcmPreferences(context)
    val editor = prefs.edit()
    //        editor.putString(MyFirebaseMessagingService.PROPERTY_REG_ID, regId);
    editor.commit()
}

fun getGcmPreferences(context: Context): SharedPreferences {

    return context.getSharedPreferences(context.toString(), Context.MODE_PRIVATE)
}

fun checkEmail(email: String): Boolean {
    return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
}

fun getlanguage(): String {
    var lang = Locale.getDefault().language

    if (lang.equals("fr", ignoreCase = true)) {
        lang = "fr"
    } else {
        lang = "en"
    }
    return lang
}

fun deleteFile(filePath: String) {

    try {
        val file = File(filePath)
        if (file.exists()) {
            val deleteCmd = "rm -r " + filePath
            val runtime = Runtime.getRuntime()
            try {
                runtime.exec(deleteCmd)
            } catch (e: IOException) {
            }

        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun hideSoftKeyboard(context: Context) {
    try {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow((context as Activity).currentFocus!!.windowToken, 0)
    } catch (e: NullPointerException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun openSoftKeyboard(context: Context) {
    try {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    } catch (e: NullPointerException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }

}


fun getRealPathFromURI(context: Context, contentUri: Uri): String {
    var cursor: Cursor? = null
    try {
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        cursor = context.contentResolver.query(contentUri, proj, null, null, null)
        val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    } finally {
        if (cursor != null) {
            cursor.close()
        }
    }


}

fun getRandomString(sizeOfRandomString: Int): String {
    val random = Random()
    val sb = StringBuilder(sizeOfRandomString)
    for (i in 0 until sizeOfRandomString)
        sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
    return sb.toString()
}


fun parseDate(time: String): String? {
    val inputPattern = "yyyy-MM-dd"//2017-10-28
    val outputPattern = "dd-MMM"
    val inputFormat = SimpleDateFormat(inputPattern)
    val outputFormat = SimpleDateFormat(outputPattern)

    var date: Date? = null
    var str: String? = null

    try {
        date = inputFormat.parse(time)
        str = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return str
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null
}


fun isNetWork(context: Context): Boolean {
    var flag = false
    if (isNetworkAvailable(context)) {
        flag = true
    } else {
        flag = false
        Toast(context.getString(R.string.toast_net_work), false, context)
    }
    return flag
}

fun isNullString(response: String): Boolean {
    return try {
        response.trim { it <= ' ' }.equals("null", ignoreCase = true) || response.trim { it <= ' ' }.length < 0 || response.trim { it <= ' ' } == ""
    } catch (e: Exception) {
        true
    }

}

