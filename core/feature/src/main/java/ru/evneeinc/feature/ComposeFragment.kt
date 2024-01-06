package ru.evneeinc.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import ru.evneeinc.navigation.base.NavigationFragmentInterface
import ru.evneeinc.theme.FunTheme

abstract class ComposeFragment : Fragment(), NavigationFragmentInterface {
    @Stable
    private val resultChannel = Channel<Bundle>(onBufferOverflow = BufferOverflow.DROP_OLDEST)


    override fun setResult(result: Bundle?) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                result?.let { resultChannel.send(it) }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply{
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                FunTheme{
                    Create(arguments, resultChannel)
                }
            }
        }
    }
    @Composable
    abstract fun Create(arguments: Bundle?, resultChannel: Channel<Bundle>)
}