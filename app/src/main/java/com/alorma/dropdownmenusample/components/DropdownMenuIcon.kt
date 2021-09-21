package com.alorma.dropdownmenusample.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MenuDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

interface DropdownScope : ColumnScope {
  fun dismiss()
}

class DropdownScopeImpl(
  private val columnScope: ColumnScope,
  private val dismissCallback: () -> Unit = { }
) : DropdownScope, ColumnScope by columnScope {

  override fun dismiss() {
    dismissCallback()
  }
}

@Composable
fun DropdownIconMenu(
  anchorIcon: @Composable () -> Unit = {
    Icon(
      imageVector = Icons.Default.MoreVert,
      contentDescription = null,
    )
  },
  content: @Composable DropdownScope.() -> Unit,
) {
  var showPopup by remember { mutableStateOf(false) }

  val onDismiss: () -> Unit = { showPopup = false }

  Box {
    IconButton(
      onClick = { showPopup = true }
    ) {
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        anchorIcon()
      }
    }
    DropdownMenu(
      expanded = showPopup,
      onDismissRequest = onDismiss,
      content = {
        DropdownScopeImpl(
          columnScope = this,
          dismissCallback = onDismiss,
        ).content()
      },
    )
  }
}

@Composable
fun DropdownScope.MenuItem(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  contentPadding: PaddingValues = MenuDefaults.DropdownMenuItemContentPadding,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  content: @Composable RowScope.() -> Unit
) {
  DropdownMenuItem(
    onClick = {
      onClick()
      dismiss()
    },
    modifier = modifier,
    enabled = enabled,
    contentPadding = contentPadding,
    interactionSource = interactionSource,
    content = content,
  )
}