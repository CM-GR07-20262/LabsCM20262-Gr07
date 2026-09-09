package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.screens

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr07_20262.lab1.R
import co.edu.udea.compumovil.gr07_20262.lab1.di.UseCasesProvider
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.AddressInput
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.CitySelector
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.CountrySelector
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.LoadingDialog
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.MailInput
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.PhoneInput
import co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata.TopBar

@Composable
fun ContactDataScreen() {
  val provider = remember { UseCasesProvider() }
  val fetchCities = remember { provider.fetchCitiesProvider() }
  val fetchCuntries = remember { provider.fetchCuntriesProvider() }

  var selectedCountry by remember { mutableStateOf("") }
  val onSelectCountry: (String) -> Unit = { country -> selectedCountry = country }

  var countries by remember { mutableStateOf<List<String>>(emptyList()) }
  var cities by remember { mutableStateOf<List<String>>(emptyList()) }

  var isLoadingCountries by remember { mutableStateOf(true) }
  var isLoadingCities by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    isLoadingCountries = true
    countries = fetchCuntries()
    isLoadingCountries = false
  }

  LaunchedEffect(selectedCountry) {
    isLoadingCities = true
    cities = if (selectedCountry.isNotEmpty()) {
      fetchCities(selectedCountry)
    } else {
      emptyList()
    }
    isLoadingCities = false
  }

  Scaffold(
    topBar = { TopBar() }) {
    Content(
      paddingValues = it,
      cities = cities,
      onSelectCountry = onSelectCountry,
      countries = countries,
      selectedCountry = selectedCountry
    )
  }

  LoadingDialog(isLoading = isLoadingCountries || isLoadingCities)
}

@Composable
fun Content(
  paddingValues: PaddingValues,
  onSelectCountry: (String) -> Unit,
  countries: List<String>,
  cities: List<String>,
  selectedCountry: String
) {
  val scrollState = rememberScrollState()

  Column(
    Modifier
      .padding(paddingValues)
      .verticalScroll(scrollState)
  ) {
    var phone by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val onNext = {
      val logText = buildLogString(phone, address, mail, selectedCountry, city)
      Log.i("Información de contacto", logText)
    }

    val validAddress: () -> Boolean = {
      val addressRegex = Regex(
        """^(Calle|Cra?\.?|Carrera|Cl\.?|Transversal|Tv\.?|Diagonal|Dg\.?)\s+\d+[A-Za-z]?\s*#\s*\d+[A-Za-z]?\s*-\s*\d+$"""
      )
      address.isNotEmpty() && !address.matches(addressRegex)
    }

    val invalidPhoneNumber: () -> Boolean = {
      val regex = Regex(
        "^(?:\\+?57\\s?)?3\\d{2}[\\s-]?\\d{3}[\\s-]?\\d{4}\$"
      )
      phone.isNotEmpty() && !phone.matches(regex)
    }

    val invalidEmail: () -> Boolean = {
      val emailRegex = Regex(
        """^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$"""
      )
      mail.isNotEmpty() && !mail.matches(emailRegex)
    }

    val configuration = LocalConfiguration.current
    val isVertical = configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    val onPhoneChange: (String) -> Unit = { phone = it }
    val onMailChange: (String) -> Unit = { mail = it }
    val onCityChange: (String) -> Unit = { city = it }
    val onAddressChange: (String) -> Unit = { address = it }

    FormInputs(
      phone,
      invalidPhoneNumber,
      onPhoneChange,
      mail,
      invalidEmail,
      onMailChange,
      countries,
      onSelectCountry,
      cities,
      onCityChange,
      address,
      validAddress,
      onAddressChange,
      isVertical,
    )

    Text(
      modifier = Modifier.padding(16.dp),
      text = stringResource(R.string.los_campos_marcados_con_son_obligatorios),
      style = MaterialTheme.typography.labelSmall
    )

    Box(
      Modifier
        .padding(16.dp)
        .fillMaxWidth()
    ) {
      Button(
        { onNext() },
        enabled = !invalidPhoneNumber() && phone.isNotEmpty() && !invalidEmail()
            && mail.isNotEmpty() && selectedCountry.isNotBlank() && !validAddress()
      ) {
        Text(stringResource(R.string.next))
      }
    }
  }
}

@Composable
private fun FormInputs(
  phone: String,
  invalidPhoneNumber: () -> Boolean,
  onPhoneChange: (String) -> Unit,
  mail: String,
  invalidEmail: () -> Boolean,
  onMailChange: (String) -> Unit,
  countries: List<String>,
  onSelectCountry: (String) -> Unit,
  cities: List<String>,
  onCityChange: (String) -> Unit,
  address: String,
  validAddress: () -> Boolean,
  onAddressChange: (String) -> Unit,
  isVertical: Boolean
) {
  FlowRow(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    maxItemsInEachRow = if (isVertical) 1 else 2
  ) {

    Box(Modifier.weight(1f)) { PhoneInput(phone, invalidPhoneNumber, onPhoneChange) }
    Box(Modifier.weight(1f)) { MailInput(mail, invalidEmail, onMailChange) }
    Box(Modifier.weight(1f)) { CountrySelector(countries, onSelectCountry) }
    Box(Modifier.weight(1f)) { CitySelector(cities, onCityChange) }
    Box(Modifier.weight(1f)) {
      AddressInput(
        address,
        invalidAddress = validAddress,
        onAddressChange = onAddressChange
      )
    }
  }
}

private fun buildLogString(
  phone: String, address: String, mail: String, selectedCountry: String, city: String
): String {
  val stringBuilder = StringBuilder()

  stringBuilder.append("Información de contacto\n")
  stringBuilder.append("Teléfono: $phone\n")

  if (address.isNotEmpty()) stringBuilder.append("Dirección: $address\n")

  stringBuilder.append("Email: $mail\n")
  stringBuilder.append("Pais: $selectedCountry\n")

  if (city.isNotEmpty()) stringBuilder.append("Ciudad: $city\n")

  val logText = stringBuilder.toString()
  return logText
}

