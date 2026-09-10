package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr07_20262.lab1.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColombiaLocationDialog(
    fetchStates: suspend (String) -> List<String>,
    fetchCitiesByState: suspend (String, String) -> List<String>,
    onDismiss: () -> Unit,
    onAccept: (departamento: String, ciudad: String, direccion: String) -> Unit
) {
    // raw guarda el nombre exacto del API, display el nombre bonito para la UI
    var departamentoRaw by rememberSaveable { mutableStateOf("") }
    var ciudad by rememberSaveable { mutableStateOf("") }
    var direccion by rememberSaveable { mutableStateOf("") }
    var expandedDepartamento by remember { mutableStateOf(false) }
    var expandedCiudad by remember { mutableStateOf(false) }

    var departamentosRaw by remember { mutableStateOf<List<String>>(emptyList()) }
    var citiesForDepartment by remember { mutableStateOf<List<String>>(emptyList()) }

    val departamentoDisplay = remember(departamentoRaw) {
        formatDepartmentName(departamentoRaw)
    }

    LaunchedEffect(Unit) {
        departamentosRaw = fetchStates("Colombia")
    }

    LaunchedEffect(departamentoRaw) {
        if (departamentoRaw.isNotBlank()) {
            citiesForDepartment = fetchCitiesByState("Colombia", departamentoRaw)
            ciudad = ""
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.colombia_location_title)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                // Departamento
                ExposedDropdownMenuBox(
                    expanded = expandedDepartamento,
                    onExpandedChange = { expandedDepartamento = !expandedDepartamento }
                ) {
                    TextField(
                        value = departamentoDisplay,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text(stringResource(R.string.department)) },
                        placeholder = { Text("Cargando departamentos...") },
                        modifier = Modifier
                            .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedDepartamento,
                        onDismissRequest = { expandedDepartamento = false }
                    ) {
                        departamentosRaw.forEach { dep ->
                            DropdownMenuItem(
                                text = { Text(formatDepartmentName(dep)) },
                                onClick = {
                                    departamentoRaw = dep
                                    expandedDepartamento = false
                                }
                            )
                        }
                    }
                }

                // Ciudad o Municipio
                ExposedDropdownMenuBox(
                    expanded = expandedCiudad,
                    onExpandedChange = {
                        if (departamentoRaw.isNotBlank()) {
                            expandedCiudad = !expandedCiudad
                        }
                    }
                ) {
                    TextField(
                        value = ciudad,
                        onValueChange = {
                            ciudad = it
                            expandedCiudad = true
                        },
                        label = { Text(stringResource(R.string.city)) },
                        placeholder = {
                            if (departamentoRaw.isBlank()) Text("Seleccione primero un departamento")
                            else Text("Cargando ciudades...")
                        },
                        enabled = departamentoRaw.isNotBlank(),
                        modifier = Modifier
                            .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable)
                            .fillMaxWidth()
                    )
                    val filtered = citiesForDepartment.filter {
                        it.lowercase().contains(ciudad.lowercase())
                    }
                    ExposedDropdownMenu(
                        expanded = expandedCiudad,
                        onDismissRequest = { expandedCiudad = false }
                    ) {
                        filtered.forEach { c ->
                            DropdownMenuItem(
                                text = { Text(c) },
                                onClick = {
                                    ciudad = c
                                    expandedCiudad = false
                                }
                            )
                        }
                    }
                }

                // Dirección
                TextField(
                    value = direccion,
                    onValueChange = { direccion = it },
                    label = { Text(stringResource(R.string.address)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onAccept(departamentoDisplay, ciudad, direccion) },
                enabled = departamentoRaw.isNotBlank() && ciudad.isNotBlank() && direccion.isNotBlank()
            ) {
                Text(stringResource(R.string.accept))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

private fun formatDepartmentName(name: String): String {
    if (name.isBlank()) return ""
    return name
        .replace(" Department", "")
        .replace("Archipelago of Saint Andréws,Providence and Saint Catalina", "San Andrés y Providencia")
        .replace("Archipelago of San Andres, Providencia and Santa Catalina", "San Andrés y Providencia")
        .replace("North Santander", "Norte de Santander")
        .replace("Capital District of Bogota", "Bogotá D.C.")
        .replace("Bogota D.C.", "Bogotá D.C.")
        .trim()
}
