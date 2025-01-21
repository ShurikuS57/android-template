package io.taptm.common

import android.content.res.Resources.NotFoundException

enum class Flavor(val value: String) {
    DEV("dev"),
    PROD("prod");

    companion object {
        fun parse(flavorStr: String?): Flavor {
            return when (flavorStr) {
                DEV.value -> DEV
                PROD.value -> PROD
                else -> throw NotFoundException("Flavor not found: $flavorStr")
            }
        }
    }
}