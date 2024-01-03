package ru.evneeinc.navigation.commands

import android.os.Bundle
import android.transition.Transition
import com.github.terrakok.cicerone.Command
import ru.evneeinc.navigation.type.NavigationScreen

internal data class Forward(
    val screen: NavigationScreen,
    val bundle: Bundle? = null,
    val enterTransition: Transition? = null,
    val sharedElementTransitionName: String? = null,
    val sharedElementEnterTransition: Transition? = null,
) : Command
