package ru.evneeinc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.evneeinc.navigation.CustomRouter
import ru.evneeinc.navigation.base.FragmentType
import ru.evneeinc.theme.FunTheme
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {


    @Inject
    lateinit var router: CustomRouter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                FunTheme {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .clickable { router.navigateTo(FragmentType.TEST)},
                            text = "Home screen",
                            fontSize = 30.sp,
                        )
                    }
                }
            }
        }
    }
}
