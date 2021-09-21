package com.alorma.dropdownmenusample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.alorma.dropdownmenusample.components.DropdownIconMenu
import com.alorma.dropdownmenusample.components.MenuItem
import com.alorma.dropdownmenusample.ui.theme.DropdownMenuSampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      DropdownMenuSampleTheme {
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()

        Scaffold(
          scaffoldState = scaffoldState,
          topBar = {
            TopAppBar(
              title = { Text(stringResource(R.string.app_name)) },
              actions = {
                MoreAction(
                  onItem1Click = {
                    coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar("Item 1 clicked") }
                  },
                  onItem2Click = {
                    coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar("Item 2 clicked") }
                  }
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
    DropdownIconMenu(
      anchorIcon = {
        Icon(imageVector = Icons.Default.Star, contentDescription = null)
      }
    ) {
      MenuItem(onClick = onItem1Click) { Text(text = "Item 1") }
      MenuItem(onClick = onItem2Click) { Text(text = "Item 2") }
    }
  }
}