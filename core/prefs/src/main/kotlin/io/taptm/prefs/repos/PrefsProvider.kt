package io.taptm.prefs.repos

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

interface PrefsProvider {
    fun get(name: String): SharedPreferences
}

internal class PrefsProviderImpl(
    private val context: Context
) : PrefsProvider {

    override fun get(name: String) =
        MasterKey
            .Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
            .let { masterKeyAlias ->
                EncryptedSharedPreferences.create(
                    context,
                    name,
                    masterKeyAlias,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
            }
}
