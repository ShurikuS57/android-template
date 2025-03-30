package io.taptm.themeSwicher

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.taptm.prefs.PrefKeys
import io.taptm.prefs.repos.PrefsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ThemeVM @Inject constructor(
    private val prefs: PrefsRepository
) : ViewModel() {

    private val _darkTheme = MutableStateFlow(false)
    val darkTheme: StateFlow<Boolean> = _darkTheme

    init {
        initCurrentTheme()
    }

    fun onSwitchTheme(enable: Boolean) {
        prefs.saveBool(PrefKeys.IS_DARK_THEME, enable)
        _darkTheme.value = enable
    }

    private fun initCurrentTheme() {
        _darkTheme.value = prefs.loadBool(PrefKeys.IS_DARK_THEME, false)
    }

}