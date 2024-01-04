package ru.evneeinc.funProject.navigation

import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import ru.evneeinc.navigation.base.DialogType
import ru.evneeinc.navigation.type.NavigationDialog

fun NavigationDialog.create(): Fragment = if (this is DialogType) {
    when (this) {
        DialogType.TEST -> Fragment()
    }
} else {
    throw NotFoundException()
}