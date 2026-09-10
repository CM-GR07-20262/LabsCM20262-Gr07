package co.edu.udea.compumovil.gr07_20262.lab1.repository.dto

data class StatesResponse(
  val error: Boolean,
  val data: CountryStatesData
)

data class CountryStatesData(
  val name: String,
  val states: List<StateInfo>
)

data class StateInfo(
  val name: String,
  val state_code: String
)
