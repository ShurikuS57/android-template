
import io.taptm.common.Flavor
import io.taptm.logger.FloconLogger
import io.taptm.logger.PlutoLogger

internal interface AppLoggerInterface {

    fun d(tag: String, message: String)

    fun e(t: Throwable? = null, message: String)

    fun e(t: Throwable?, message: String?, vararg args: Any?)

}

object AppLogger: AppLoggerInterface {

    private val logList: MutableList<AppLoggerInterface> = mutableListOf()

    fun init(flavor: Flavor) {
        if (flavor == Flavor.DEV) {
            logList.add(PlutoLogger())
            logList.add(FloconLogger())
        }
    }

    override fun d(tag: String, message: String) {
        logList.forEach { it.d(tag, message) }
    }

    override fun e(t: Throwable?, message: String) {
        logList.forEach { it.e(t, message) }
    }

    override fun e(t: Throwable?, message: String?, vararg args: Any?) {
        logList.forEach { it.e(t, message, args) }
    }
}