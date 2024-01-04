package ru.evneeinc.funProject.navigation

import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import ru.evneeinc.navigation.base.FragmentType
import ru.evneeinc.navigation.type.NavigationScreen
import ru.evneeinc.ui.HomeFragment
import ru.evneeinc.ui.TestFragment

fun NavigationScreen.create(): Fragment = if (this is FragmentType) {
    when (this) {
        FragmentType.HOME -> HomeFragment()
        FragmentType.TEST -> TestFragment()
    }
} else {
    throw NotFoundException()
}
