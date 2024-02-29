package ru.evneeinc.ui

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import ru.evneeinc.navigation.base.DialogType
import ru.evneeinc.ui.base.ComposeBottomSheetDialogFragment

@AndroidEntryPoint
class TestBottomSheetFragment : ComposeBottomSheetDialogFragment() {

    override fun getType() = DialogType.TEST

    @Composable
    override fun Create(arguments: Bundle?) {

        OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(16.dp),value = "", onValueChange = {})
    }
}