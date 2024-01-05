package ru.evneeinc.funProject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.hilt.android.AndroidEntryPoint
import ru.evneeinc.funProject.navigation.create
import ru.evneeinc.navigation.CustomNavigator
import ru.evneeinc.navigation.CustomRouter
import ru.evneeinc.navigation.base.FragmentType
import ru.evneeinc.navigation.type.NavigationDialog
import ru.evneeinc.navigation.type.NavigationElement
import ru.evneeinc.navigation.type.NavigationScreen
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: CustomRouter

    private val navigator = object : CustomNavigator(this, R.id.fragment_container) {
        override fun create(screen: NavigationElement): Fragment {
            return when (screen) {
                is NavigationScreen -> screen.create()
                is NavigationDialog -> screen.create()
            }
        }
    }

    private fun initNavigation() {
        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                super.onStart(owner)
                navigatorHolder.setNavigator(navigator)
            }

            override fun onStop(owner: LifecycleOwner) {
                navigatorHolder.removeNavigator()
                super.onStop(owner)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initNavigation()
        super.onCreate(savedInstanceState)
        router.navigateTo(FragmentType.HOME)
    }
}
