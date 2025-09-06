package io.taptm.prefs.repos

import android.content.Context
import android.content.SharedPreferences
import com.harrytmthy.safebox.SafeBox
import org.koin.core.annotation.Single

interface PrefsProvider {
    fun get(name: String): SharedPreferences
}

@Single
internal class PrefsProviderImpl(
    private val context: Context
) : PrefsProvider {

    override fun get(name: String) =
        SafeBox.create(context, name)
}
