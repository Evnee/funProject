package ru.evneeinc.navigation.base

import android.os.Bundle
import ru.evneeinc.navigation.type.NavigationScreen

interface NavigationFragmentInterface : NavigationInterface {
    override fun getType(): NavigationScreen
    fun setResult(result: Bundle?)
}
