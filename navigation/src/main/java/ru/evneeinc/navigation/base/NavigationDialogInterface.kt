package ru.evneeinc.navigation.base

import ru.evneeinc.navigation.type.NavigationDialog

interface NavigationDialogInterface : NavigationInterface {
    override fun getType(): NavigationDialog
}
