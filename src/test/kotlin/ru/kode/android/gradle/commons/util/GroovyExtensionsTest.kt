package ru.kode.android.gradle.commons.util

import groovy.lang.Closure
import groovy.lang.GroovyShell
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GroovyExtensionsTest {
    class Target {
        var value: String = "unset"
    }

    @Test
    fun `configureGroovy delegates closure to target`() {
        val shell = GroovyShell()

        @Suppress("UNCHECKED_CAST")
        val closure = shell.evaluate("def c = { value = 'configured' }; c") as Closure<in Target>

        val target = Target()
        configureGroovy(closure, target)

        assertEquals("configured", target.value)
    }
}
