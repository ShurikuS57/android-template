package io.taptm.designsystemapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.taptm.designsystem.theme.AppTheme
import io.taptm.designsystemapp.navigation.DesignAppNavigation

class MainDesignActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                DesignAppNavigation()
            }
        }
    }
}