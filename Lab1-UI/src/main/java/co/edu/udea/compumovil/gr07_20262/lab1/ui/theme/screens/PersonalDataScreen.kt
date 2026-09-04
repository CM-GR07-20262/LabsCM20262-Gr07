package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.screens

import android.graphics.Paint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr07_20262.lab1.R

@Composable
fun PersonalDataScreen() {

  Scaffold(
    topBar = { TopBar() }

  ) { paddingValues ->
    Content(paddingValues)
  }
}

@Preview
@Composable
private fun PreviewNameInput() {
  NameInput()
}

@Composable
fun NameInput() {
  var name by remember { mutableStateOf("") }
  var onChangeName = { new: String -> name = new }

  TextField(
    label = { Text("Escribe tu nombre") },
    value = name,
    onValueChange = onChangeName,
    leadingIcon = {
      Icon(
        painter = painterResource(R.drawable.user),
        contentDescription = "Icono de usuario",
        tint = MaterialTheme.colorScheme.primary
      )
    }
  )
}


@Preview
@Composable
fun PreviewTopBar() {
  TopBar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
  TopAppBar(
    title = { Text(stringResource(R.string.app_title)) },
    colors = TopAppBarDefaults.topAppBarColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer,
      titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    )
  )
}

@Composable
fun Content(paddingValues: PaddingValues) {
  Box(Modifier.padding(paddingValues)) {

    Column(
      Modifier.padding(16.dp),
      horizontalAlignment = Alignment.Start,
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      NameInput()
      NameInput()
      NameInput()
      NameInput()
      NameInput()

    }
  }
}