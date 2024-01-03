package ru.evneeinc.navigation

import android.os.Bundle
import ru.evneeinc.navigation.type.NavigationDialog
import ru.evneeinc.navigation.type.NavigationScreen

interface CustomRouter {
    fun navigateTo(screen: NavigationScreen, data: Bundle? = null)
    fun replaceScreen(screen: NavigationScreen, data: Bundle? = null)
    fun backTo(screen: NavigationScreen?, data: Bundle? = null, inclusive: Boolean = false)
    fun exit(data: Bundle? = null)
    fun showDialog(screen: NavigationDialog, data: Bundle? = null)
}
