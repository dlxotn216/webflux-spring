package io.crscube.webfluxspring.chap12

import org.slf4j.LoggerFactory

/**
 * Created by itaesu on 2024/03/14.
 *
 * @author Lee Tae Su
 * @version webflux-spring
 * @since webflux-spring
 */
class SyncDebugStacktrace {
    private val fruits = mapOf(
        "banana" to "바나나",
        "apple" to "사과",
        "pear" to "배",
        "grape" to "포도"
    )

    fun process() {
        arrayOf("bananas", "apples", "pears", "melons").forEach {
            val key = it.lowercase().substring(0, it.lastIndex)
            val fruit = fruits[key]!!
            log.info("foreach: {}", fruit)
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
    }
}

fun main() {
    SyncDebugStacktrace().process()
    Thread.sleep(1000)
}
