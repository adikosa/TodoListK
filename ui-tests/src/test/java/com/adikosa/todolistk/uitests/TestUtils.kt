package com.adikosa.todolistk.uitests

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.`$`
import org.junit.Before
import org.openqa.selenium.By

abstract class Robot {
    fun focusOut() {
        `$`(By.cssSelector("html")).click()
    }
}

abstract class BaseUiTest<R : Robot> {
    @Before
    fun setUp() {
        Configuration.startMaximized = true
        Configuration.holdBrowserOpen = true
        Configuration.reportsFolder = "test-result/reports";
    }

    abstract fun createRobot(): R

    fun withRobot(actions: R.() -> Unit) {
        actions(createRobot())
    }
}

