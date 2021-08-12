package com.android.olxapplication.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.pd.chocobar.ChocoBar

fun Context.toast(msg: String) {

    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

}

fun String.log(tag:String){
    Log.e(tag,"""
            
            $this
            
        """");
}

fun Activity.setStatusBarColor(color: Int) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        this.window.statusBarColor = ContextCompat.getColor(this, color)
    }
}

fun View.setVisible(visible: Boolean = true) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@SuppressLint("ResourceAsColor")
fun View.snackBar(msg: String) {
    try {
        (this as AppCompatActivity).hideKeyboard()
    } catch (e: Exception) {
    }

    val chocoBar = ChocoBar.builder()
    chocoBar.setView(this)
    chocoBar.apply {
        setBackgroundColor(Color.parseColor("#de6e71"))
        setTextSize(16F)
        setTextColor(Color.parseColor("#FFFFFF"))
        setText(msg)
        setView(this@snackBar)
        setMaxLines(4)
        setActionTextColor(Color.parseColor("#FFFFFF"))
        setActionTextSize(18F)
        setActionTextTypefaceStyle(Typeface.BOLD)
        setDuration(ChocoBar.LENGTH_LONG)
    }

    chocoBar.build().show()


}

@SuppressLint("ResourceAsColor")
inline fun View.snackBar(
    msg: String,
    actionName: String,
    crossinline action: ((ChocoBar.Builder) -> Unit)
) {
    try {
        (this as AppCompatActivity).hideKeyboard()
    } catch (e: Exception) {
    }


    val chocoBar = ChocoBar.builder()
    chocoBar.setView(this)
    chocoBar.apply {
        setBackgroundColor(Color.parseColor("#de6e71"))
        setTextSize(16F)
        setTextColor(Color.parseColor("#FFFFFF"))
        setText(msg)
        setView(this@snackBar)
        setMaxLines(4)
        setActionTextColor(Color.parseColor("#FFFFFF"))
        setActionTextSize(18F)
        setActionTextTypefaceStyle(Typeface.BOLD)
        setActionText(actionName)
        setDuration(ChocoBar.LENGTH_INDEFINITE)
        setActionClickListener {
            action.invoke(chocoBar)
        }
    }

    chocoBar.build().show()


}


fun Activity.hideKeyboard() {
    try {
        val imm: InputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = this.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (e: Exception) {
    }
}
