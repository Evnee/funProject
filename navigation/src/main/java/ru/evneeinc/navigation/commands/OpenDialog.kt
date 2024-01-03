package ru.evneeinc.navigation.commands

import android.os.Bundle
import com.github.terrakok.cicerone.Command
import ru.evneeinc.navigation.type.NavigationDialog

internal data class OpenDialog(
    val screen: NavigationDialog,
    val bundle: Bundle? = null
) : Command
