package io.taptm.network.mock

/**
 * Global switch for request mocking.
 *
 * Initialized from the [io.taptm.network.httpClientAndroid] configuration
 * (build config `MOCK_ENABLED`). Can also be changed at runtime, for example
 * from a debug settings screen.
 */
object MockSettings {

    @Volatile
    var isEnabled: Boolean = false
}
