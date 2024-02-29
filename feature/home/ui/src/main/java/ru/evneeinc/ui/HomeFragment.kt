package ru.evneeinc.ui

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.Channel
import ru.evneeinc.feature.ComposeFragment
import ru.evneeinc.navigation.CustomRouter
import ru.evneeinc.navigation.base.DialogType
import ru.evneeinc.navigation.base.FragmentType
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : ComposeFragment() {


    @Inject
    lateinit var router: CustomRouter

    @Composable
    override fun Create(arguments: Bundle?, resultChannel: Channel<Bundle>) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Button(
                onClick = {router.showDialog(DialogType.TEST)},
                modifier = Modifier
                    .align(Alignment.Center)
            ){
                Text(text = "Open broken dialog")
        }
        }
    }

    override fun getType(): FragmentType = FragmentType.HOME
}
