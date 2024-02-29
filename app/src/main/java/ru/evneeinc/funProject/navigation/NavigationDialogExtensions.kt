package ru.evneeinc.funProject.navigation

import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import ru.evneeinc.navigation.base.DialogType
import ru.evneeinc.navigation.type.NavigationDialog
import ru.evneeinc.ui.TestBottomSheetFragment

fun NavigationDialog.create(): Fragment = if (this is DialogType) {
    when (this) {
        DialogType.TEST -> TestBottomSheetFragment()
    }
} else {
    throw NotFoundException()
}