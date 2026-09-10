package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.contactdata

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import co.edu.udea.compumovil.gr07_20262.lab1.R

@Composable
fun DepartmentSelector(
    department: String,
    onDepartmentChange: (String) -> Unit
) {
    TextField(
        value = department,
        onValueChange = onDepartmentChange,
        label = { Text(stringResource(R.string.department)) },
        leadingIcon = {
            Icon(
                painterResource(R.drawable.building), "Department icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        maxLines = 1,
        singleLine = true,
        readOnly = true, // Para Colombia se llena vía diálogo, pero lo dejamos visible
        modifier = Modifier.fillMaxWidth()
    )
}
