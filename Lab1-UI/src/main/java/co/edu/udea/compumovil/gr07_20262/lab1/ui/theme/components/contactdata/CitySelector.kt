package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import co.edu.udea.compumovil.gr07_20262.lab1.R
import java.util.Locale.getDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitySelector(
  city: String,
  cities: List<String>,
  onSelectCity: (String) -> Unit
) {
  var expanded by remember { mutableStateOf(false) }
  var citiesFilter by remember { mutableStateOf(city) }

  // Sincronizar el filtro si el valor externo cambia
  LaunchedEffect(city) {
    citiesFilter = city
  }

  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { expanded = !expanded }
  ) {
    TextField(
      value = citiesFilter,
      onValueChange = {
        citiesFilter = it
        onSelectCity(it) // Informar cambio manual también
        expanded = true
      },
      label = { Text(stringResource(R.string.city)) },
      leadingIcon = {
        Icon(
          painterResource(R.drawable.building), "Building icon",
          tint = MaterialTheme.colorScheme.primary
        )
      },
      maxLines = 1,
      singleLine = true,
      modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable)
        .fillMaxWidth()
    )

    val filteredCities = cities.filter {
      it.lowercase(getDefault()).contains(citiesFilter.lowercase(getDefault()))
    }
    ExposedDropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false }
    ) {
      filteredCities.forEach {
        DropdownMenuItem(
          text = { Text(it) },
          onClick = {
            citiesFilter = it
            onSelectCity(it)
            expanded = false
          }
        )
      }
    }
  }
}

@Preview
@Composable
private fun PreviewCitySelector() {
  val cities = listOf("Bogotá", "Medellín", "Cali")
  var city by remember { mutableStateOf("") }

  Column(
    Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    CitySelector(city, cities) { city = it }
  }
}
