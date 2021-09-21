package com.alorma.dropdownmenusample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.alorma.dropdownmenusample.ui.theme.DropdownMenuSampleTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      DropdownMenuSampleTheme {
        Scaffold(
          topBar = {
            TopAppBar(
              title = { Text(stringResource(R.string.app_name)) },
              actions = {
                MoreAction(
                  onItem1Click = {},
                  onItem2Click = {}
                )
              }
            )
          }
        ) {

        }
      }
    }
  }

  @Composable
  private fun MoreAction(
    onItem1Click: () -> Unit,
    onItem2Click: () -> Unit,
  ) {
    var showMenu by remember { mutableStateOf(false) }

    IconButton(onClick = { showMenu = true }) {
      Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
    }
    DropdownMenu(
      expanded = showMenu,
      onDismissRequest = { showMenu = false },
    ) {
      DropdownMenuItem(onClick = {
        showMenu = false
        onItem1Click()
      }) {
        Text(text = "Item 1")
      }
      DropdownMenuItem(onClick = {
        showMenu = false
        onItem2Click()
      }) {
        Text(text = "Item 2")
      }
    }
  }
}