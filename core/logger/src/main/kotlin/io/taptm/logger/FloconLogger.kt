package io.taptm.logger

import AppLoggerInterface
import io.github.openflocon.flocon.Flocon
import io.github.openflocon.flocon.plugins.analytics.analytics
import io.github.openflocon.flocon.plugins.analytics.model.AnalyticsEvent
import io.github.openflocon.flocon.plugins.analytics.model.analyticsProperty

class FloconLogger : AppLoggerInterface {

    override fun d(tag: String, message: String) {
        Flocon.analytics("Log").logEvents(
            AnalyticsEvent(
                eventName = "debug",
                "tag" analyticsProperty tag,
                "message" analyticsProperty message
            )
        )
    }

    override fun e(t: Throwable?, message: String) {
        Flocon.analytics("Log").logEvents(
            AnalyticsEvent(
                eventName = "error",
                "message" analyticsProperty message,
                "throw" analyticsProperty t.toString()
            )
        )
    }

    override fun e(t: Throwable?, message: String?, vararg args: Any?) {
        Flocon.analytics("Log").logEvents(
            AnalyticsEvent(
                eventName = "error",
                "message" analyticsProperty message.orEmpty(),
                "tag" analyticsProperty t.toString(),
                "args" analyticsProperty args.toString()
            )
        )
    }
}