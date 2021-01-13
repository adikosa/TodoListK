package com.adikosa.todolistk.uitests.loginpage

import com.adikosa.todolistk.uitests.BaseUiTest
import com.adikosa.todolistk.uitests.Robot
import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.Selenide.screenshot
import com.codeborne.selenide.SelenideElement
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.openqa.selenium.By

const val LOGIN_PAGE_URL = "http://localhost:3000/login"

class LoginPageUiTest : BaseUiTest<LoginPageRobot>() {

    @Test
    fun shouldLoginWithValidCredentials() {
        withRobot {
            openLoginPage()
            focusOut()
            capture("UX_001_Login_Page")
            inputUsername(VALID_USERNAME)
            inputPassword(VALID_PASSWORD)
            clickLoginButton()
            assertLoggedIn()
            capture("UX_001_Logged_In_Page")
        }
    }

    companion object {
        const val VALID_USERNAME = "admin"
        const val VALID_PASSWORD = "admin"
    }

    override fun createRobot() = LoginPageRobot()
}

class LoginPageRobot : Robot() {
    fun openLoginPage() {
        open(LOGIN_PAGE_URL)
    }

    fun capture(filename: String) {
        screenshot(filename)
    }

    fun inputUsername(username: String) {
        `$`(By.id("username")).`val`(username)
    }

    fun inputPassword(password: String) {
        `$`(By.id("password")).`val`(password)
    }

    fun clickLoginButton() {
        `$`(By.id("login-button")).click()
    }

    fun assertLoggedIn() {
        val welcomeSnackbar: SelenideElement = `$`(By.className("MuiSnackbar-root")).shouldBe(Condition.visible)
        val welcomeMessage: SelenideElement = `$`(By.className("MuiAlert-message")).shouldBe(Condition.visible)
        assertThat(welcomeSnackbar.isDisplayed).isTrue()
        assertThat(welcomeMessage.text).isEqualTo("Logged in successfully!")
    }
}