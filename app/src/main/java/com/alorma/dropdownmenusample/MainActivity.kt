package com.alorma.dropdownmenusample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
                IconButton(onClick = { /*TODO*/ }) {
                  Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                }
              }
            )
          }
        ) {

        }
      }
    }
  }
}