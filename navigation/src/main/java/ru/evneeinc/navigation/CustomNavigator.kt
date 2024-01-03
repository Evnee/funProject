package ru.evneeinc.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import ru.evneeinc.navigation.commands.Back
import ru.evneeinc.navigation.commands.BackTo
import ru.evneeinc.navigation.commands.Forward
import ru.evneeinc.navigation.commands.OpenDialog
import ru.evneeinc.navigation.commands.Replace
import ru.evneeinc.navigation.exception.NavigationException
import ru.evneeinc.navigation.type.NavigationElement
import java.util.LinkedList

abstract class CustomNavigator(
    private val activity: FragmentActivity,
    private val containerId: Int,
    private val fragmentManager: FragmentManager = activity.supportFragmentManager,
) : Navigator {
    private var localStackCopy: LinkedList<String>? = null

    override fun applyCommands(commands: Array<out Command>) {
        fragmentManager.executePendingTransactions()

        copyStackToLocal()

        for (command in commands) {
            try {
                applyCommand(command)
            } catch (e: RuntimeException) {
                throw NavigationException(command, e)
            }
        }
        fragmentManager.executePendingTransactions()
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Forward -> fragmentForward(command)
            is BackTo -> backTo(command)
            is Back -> fragmentBack(command)
            is OpenDialog -> openDialog(command)
            is Replace -> fragmentReplace(command)
        }
    }

    private fun openDialog(command: OpenDialog) {
    }

    private fun fragmentBack(command: Back) {
    }

    private fun backTo(command: BackTo) {
        if (command.screen == null) {
            backToRoot()
        } else {
            val key = command.screen.name
            val index = localStackCopy!!.indexOf(key)
            val size = localStackCopy!!.size
            val flags = if (command.inclusive) FragmentManager.POP_BACK_STACK_INCLUSIVE else 0

            if (index != -1) {
                for (i in 1 until size - index) {
                    localStackCopy!!.removeLast()
                }
                fragmentManager.popBackStackImmediate(key, flags)
            } else {
                backToRoot()
            }
        }
    }

    private fun backToRoot() {
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        localStackCopy!!.clear()
    }

    private fun fragmentReplace(command: Replace) {
        val fragment = createFragment(command.screen, command.bundle)
        if (localStackCopy!!.size > 0) {
            fragmentManager.popBackStack()
            localStackCopy!!.removeLast()

            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction
                .replace(containerId, fragment)
                .addToBackStack(command.screen.name)
                .commit()
            localStackCopy!!.add(command.screen.name)
        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction
                .replace(containerId, fragment)
                .commit()
        }
    }

    private fun fragmentForward(command: Forward) {
        val fragment = createFragment(command.screen, command.bundle)
        if (command.enterTransition != null) {
            fragment.enterTransition = command.enterTransition
        }
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction
            .replace(containerId, fragment)
            .addToBackStack(command.screen.name)
            .commit()
        localStackCopy!!.add(command.screen.name)
    }

    abstract fun create(screen: NavigationElement): Fragment

    private fun createFragment(screen: NavigationElement, bundle: Bundle?): Fragment {
        return create(screen).apply {
            val dataNotNull = bundle ?: Bundle()
            arguments = when (arguments) {
                null -> dataNotNull
                else -> arguments?.apply { putAll(dataNotNull) }
            }
        }
    }

    private fun copyStackToLocal() {
        localStackCopy = LinkedList()
        val stackSize = fragmentManager.backStackEntryCount
        for (i in 0 until stackSize) {
            localStackCopy!!.add(fragmentManager.getBackStackEntryAt(i).name!!)
        }
    }
}
