package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr07_20262.lab1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
  TopAppBar(
    title = { Text(stringResource(R.string.contact_information)) },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer,
      titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
      )
  )
}


@Preview
@Composable
private fun PreviewTopBar() {
  TopBar()
}