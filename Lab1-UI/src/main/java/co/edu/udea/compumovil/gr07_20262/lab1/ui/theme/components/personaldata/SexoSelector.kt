package co.edu.udea.compumovil.gr07_20262.lab1.ui.theme.components.personaldata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr07_20262.lab1.R


@Composable
fun SexoSelector(sexo: String, onSexoChange: (String) -> Unit) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Label()
    Row(verticalAlignment = Alignment.CenterVertically) {
      val maleString = stringResource(R.string.male)
      val femaleString = stringResource(R.string.female)

      RadioButton(
        selected = sexo == maleString,
        onClick = { onSexoChange(maleString) }
      )
      Text(maleString)

      Spacer(modifier = Modifier.width(16.dp))

      RadioButton(
        selected = sexo == femaleString,
        onClick = { onSexoChange(femaleString) }
      )
      Text(femaleString)
    }
  }
}

@Composable
private fun Label() {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Icon(
      painterResource(R.drawable.gender),
      "Gender icon",
      tint = MaterialTheme.colorScheme.primary
    )
    Text(stringResource(R.string.gender))
  }
}

@Preview(showBackground = true)
@Composable
private fun PrevewSexoSelector() {
  SexoSelector("") { }
}