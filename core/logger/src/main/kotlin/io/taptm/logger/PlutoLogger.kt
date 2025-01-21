package io.taptm.logger

import AppLoggerInterface
import com.pluto.plugins.logger.PlutoTimberTree

internal class PlutoLogger : AppLoggerInterface {

    override fun d(tag: String, message: String) {
        PlutoTimberTree().d("$tag : $message")
    }

    override fun e(t: Throwable?, message: String) {
        PlutoTimberTree().e(t, message)
    }

    override fun e(t: Throwable?, message: String?, vararg args: Any?) {
        PlutoTimberTree().e(t, message, args)
    }
}