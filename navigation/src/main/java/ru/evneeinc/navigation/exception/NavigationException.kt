package ru.evneeinc.navigation.exception

import com.github.terrakok.cicerone.Command

data class NavigationException(
    private val command: Command,
    val exception: RuntimeException,
) : RuntimeException("Error execute $command", exception)
