package ru.evneeinc.navigation.commands

import android.os.Bundle
import com.github.terrakok.cicerone.Command
import ru.evneeinc.navigation.type.NavigationScreen

internal data class Replace(
    val screen: NavigationScreen,
    val bundle: Bundle? = null,
) : Command
