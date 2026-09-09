package co.edu.udea.compumovil.gr07_20262.lab1.repository.dto

import co.edu.udea.compumovil.gr07_20262.lab1.model.Country

data class CountryResponse(
  val error: Boolean,
  val data: List<Country>
) {
}