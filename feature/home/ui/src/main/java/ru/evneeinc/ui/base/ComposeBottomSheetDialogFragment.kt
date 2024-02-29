package ru.evneeinc.ui.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import ru.evneeinc.feature.ui.R
import ru.evneeinc.navigation.base.NavigationDialogInterface
import ru.evneeinc.theme.FunTheme
import ru.evneeinc.ui.components.ModalBottomSheet
import ru.evneeinc.ui.components.rememberModalBottomSheetState

abstract class ComposeBottomSheetDialogFragment : AppCompatDialogFragment(),
    NavigationDialogInterface {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                FunTheme {
                    val sheetState: SheetState = rememberModalBottomSheetState(
                        skipPartiallyExpanded = true
                    )
                    val windowInsets = WindowInsets.safeDrawing
                    ModalBottomSheet(
                        sheetState = sheetState,
                        sheetContent = {
                            Column(
                                modifier = Modifier.wrapContentHeight(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Create(arguments)
                                Spacer(modifier = Modifier.windowInsetsBottomHeight(windowInsets))
                            }
                        },
                        scrimColor = Color.Transparent,
                        tonalElevation = 0.dp,
                        onDismissRequest = { dismiss() },
                        windowInsets = WindowInsets(0.dp)
                    )
                }
            }
        }
    }

    @Composable
    abstract fun Create(arguments: Bundle?)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        }
    }

    override fun getTheme(): Int {
        return R.style.ComposeBottomSheetDialog
    }
}

private class BottomSheetDialog(context: Context, theme: Int = 0) : AppCompatDialog(context, theme) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window = window
        if (window != null) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }
}
