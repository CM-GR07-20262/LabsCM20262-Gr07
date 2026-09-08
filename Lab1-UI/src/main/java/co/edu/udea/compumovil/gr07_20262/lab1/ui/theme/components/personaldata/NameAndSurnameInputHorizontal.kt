package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
 fun NameAndSurnameInputHorizontal(
  nombres: String,
  apellidos: String,
  onChangeName: (String) -> Unit,
  onChangeLastName: (String) -> Unit
) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    NameInput(
      label = "Nombres *",
      value = nombres,
      modifier = Modifier.weight(1f),
      onValueChange = onChangeName
    )

    NameInput(
      label = "Apellidos *",
      value = apellidos,
      modifier = Modifier.weight(1f),
      onValueChange = onChangeLastName
    )
  }
}