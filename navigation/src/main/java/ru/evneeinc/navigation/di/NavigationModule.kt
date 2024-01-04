package ru.evneeinc.navigation.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.evneeinc.navigation.CustomRouter
import ru.evneeinc.navigation.impl.CustomRouterImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NavigationModule {
    @Provides
    @Singleton
    internal fun provideCicerone(router: CustomRouterImpl): Cicerone<CustomRouterImpl> {
        return Cicerone.create(router)
    }

    @Provides
    @Singleton
    internal fun provideNavigatorHolder(cicerone: Cicerone<CustomRouterImpl>): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModuleBind {

    @Binds
    fun provideCicerone(router: CustomRouterImpl): CustomRouter
}
