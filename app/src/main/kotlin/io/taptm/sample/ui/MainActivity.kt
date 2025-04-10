package io.taptm.sample.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import io.taptm.common.Flavor
import io.taptm.designsystem.theme.AppTheme
import io.taptm.sample.R
import io.taptm.sample.navigation.AppNavigation
import io.taptm.themeSwicher.ThemeSwitcher
import io.taptm.themeSwicher.ThemeVM
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.getKoin

class MainActivity : ComponentActivity(), ThemeSwitcher {

    private val themeVM: ThemeVM  by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        enableEdgeToEdge()
        setContent {
            RequestPermissionForPluto()
            val darkTheme = themeVM.darkTheme.collectAsStateWithLifecycle()
            AppTheme(darkTheme.value) {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }

    @Composable
    private fun RequestPermissionForPluto() {
        val flavor = Flavor.parse(getKoin().getProperty("FLAVOR"))
        if (flavor != Flavor.DEV) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }
    }

    override fun switchTheme() {
        themeVM.onSwitchTheme(!themeVM.darkTheme.value)
    }
}


@Preview
@Composable
private fun MainScreenPreview() {
    AppTheme {
        AppNavigation(rememberNavController())
    }
}
