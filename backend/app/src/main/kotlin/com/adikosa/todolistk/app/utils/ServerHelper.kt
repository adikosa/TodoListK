package com.adikosa.todolistk.app.utils

import java.net.InetAddress
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component


interface ServerHelper {
    val hostname: String
    val port: Int
    val address: String
}

@Component
class ServerHelperImpl : ServerHelper {

    override val address: String
        get() {
            return "http://$hostname:$port"
        }

    override val hostname: String
        get() {
            return InetAddress.getLoopbackAddress().hostName
        }

    override val port: Int
        get() = 8080
}
