package ru.evneeinc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import kotlinx.coroutines.channels.Channel
import ru.evneeinc.feature.ComposeFragment
import ru.evneeinc.navigation.base.FragmentType
import ru.evneeinc.navigation.type.NavigationScreen
import ru.evneeinc.theme.FunTheme

class TestFragment : ComposeFragment(){

    @Composable
    override fun Create(arguments: Bundle?, resultChannel: Channel<Bundle>) {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "TestScreen",
                fontSize = 30.sp,
            )
        }
    }

    override fun getType(): FragmentType = FragmentType.TEST
}
