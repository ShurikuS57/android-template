package io.taptm.sample

import AppLogger
import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.request.crossfade
import com.pluto.Pluto
import com.pluto.plugins.logger.PlutoLoggerPlugin
import com.pluto.plugins.network.PlutoNetworkPlugin
import io.taptm.common.Flavor
import io.taptm.network.di.networkModule
import io.taptm.prefs.di.prefsModules
import io.taptm.themeSwicher.di.themeSwitcherModule
import io.taptm.tops.di.topsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration

@OptIn(KoinExperimentalAPI::class)
class App : Application(), KoinStartup, SingletonImageLoader.Factory {

    override fun onCreate() {
        super.onCreate()
        val flavor = Flavor.parse(BuildConfig.FLAVOR)
        initPluto()
        initLogger(flavor)
    }

    override fun onKoinStartup() = koinConfiguration {
        androidLogger()
        androidContext(this@App)
        properties(
            mapOf(
                "FLAVOR" to BuildConfig.FLAVOR,
                "URL" to BuildConfig.URL,
            )
        )
        modules(
            networkModule,
            prefsModules(),
            topsModule(),
            themeSwitcherModule()
        )
    }

    private fun initPluto() {
        Pluto.Installer(this)
            .addPlugin(PlutoNetworkPlugin())
            .addPlugin(PlutoLoggerPlugin())
            .install()
        Pluto.showNotch(true)
    }

    private fun initLogger(flavor: Flavor) {
        AppLogger.init(flavor)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }
}
