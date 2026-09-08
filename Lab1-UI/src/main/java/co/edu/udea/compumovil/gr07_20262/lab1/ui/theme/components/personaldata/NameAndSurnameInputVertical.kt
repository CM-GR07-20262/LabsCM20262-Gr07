package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata

import androidx.compose.runtime.Composable

@Composable
 fun NameAndSurnameInputVertical(
  nombres: String,
  apellidos: String,
  onNameChange: (String) -> Unit,
  onLastNameChange: (String) -> Unit
) {

  NameInput(
    label = "Nombres *",
    value = nombres,
    onValueChange = onNameChange,
  )

  NameInput(
    label = "Apellidos *",
    value = apellidos,
    onValueChange = onLastNameChange
  )
}