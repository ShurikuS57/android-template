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
import dagger.hilt.android.HiltAndroidApp
import io.taptm.common.Flavor

@HiltAndroidApp
class App : Application(), SingletonImageLoader.Factory {

    override fun onCreate() {
        super.onCreate()
        val flavor = Flavor.parse(BuildConfig.FLAVOR)
        initPluto()
        initLogger(flavor)
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
