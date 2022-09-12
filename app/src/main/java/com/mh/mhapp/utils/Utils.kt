package com.mh.mhapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

enum class TypeValid {
    EMPTY,
    DISTINCT_TEXT
}

data class Field(
    var type: TypeValid = TypeValid.EMPTY,
    var input: TextInputLayout,
    var edit: TextInputEditText? = null,
    var auto: AutoCompleteTextView? = null,
    var expected: String = "",
    var error: String = "Campo obligatorio"
)

object Utils {

    fun Context.isOnline(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } /* else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }*/
            }
        } else {
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo != null && netInfo.isConnected
        }

        return false
    }

    fun validInputs(vararg fields: Field): Boolean {
        var valid = true
        fields.forEach {
            when (it.type) {
                TypeValid.EMPTY -> {
                    if (it.edit?.text.toString().isEmpty() || it.auto?.text.toString().isEmpty()) {
                        it.input.error = it.error
                        valid = false
                    } else {
                        it.input.error = null
                    }
                }

                TypeValid.DISTINCT_TEXT -> {
                    if (it.edit?.text.toString() == it.expected) {
                        it.input.error = it.error
                        valid = false
                    } else {
                        it.input.error = null
                    }
                }
            }
        }
        return valid
    }
}
