package ru.evneeinc.navigation.impl

import android.os.Bundle
import com.github.terrakok.cicerone.BaseRouter
import ru.evneeinc.navigation.CustomRouter
import ru.evneeinc.navigation.commands.Back
import ru.evneeinc.navigation.commands.BackTo
import ru.evneeinc.navigation.commands.Forward
import ru.evneeinc.navigation.commands.OpenDialog
import ru.evneeinc.navigation.commands.Replace
import ru.evneeinc.navigation.type.NavigationDialog
import ru.evneeinc.navigation.type.NavigationScreen

class CustomRouterImpl : BaseRouter(), CustomRouter {
    override fun navigateTo(screen: NavigationScreen, data: Bundle?) {
        executeCommands(Forward(screen, data))
    }

    override fun replaceScreen(screen: NavigationScreen, data: Bundle?) {
        executeCommands(Replace(screen, data))
    }

    override fun backTo(screen: NavigationScreen?, data: Bundle?, inclusive: Boolean) {
        executeCommands(BackTo(screen, data, inclusive))
    }

    override fun exit(data: Bundle?) {
        executeCommands(Back(data))
    }

    override fun showDialog(screen: NavigationDialog, data: Bundle?) {
        executeCommands(OpenDialog(screen, data))
    }
}
