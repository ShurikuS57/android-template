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
import io.github.openflocon.flocon.Flocon
import io.taptm.common.Flavor
import io.taptm.sample.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.koinConfiguration
import org.koin.ksp.generated.module

@OptIn(KoinExperimentalAPI::class)
class App : Application(), KoinStartup, SingletonImageLoader.Factory {

    override fun onCreate() {
        super.onCreate()
        val flavor = Flavor.parse(BuildConfig.FLAVOR)
        initPluto()
        initFlocon()
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
        modules(AppModule().module)
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

    private fun initFlocon() {
        Flocon.initialize(this)
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }
}
