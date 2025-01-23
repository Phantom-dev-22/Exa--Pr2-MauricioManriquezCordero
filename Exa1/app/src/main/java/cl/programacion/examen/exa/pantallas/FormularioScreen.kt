package cl.programacion.examen.exa.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.programacion.examen.exa.R
import cl.programacion.examen.exa.viewmodel.RegistroViewModel
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun FormularioScreen(navController: NavController, viewModel: RegistroViewModel) {
    // Contexto local para acceder a los recursos de cadenas
    val context = LocalContext.current
    val resources = context.resources

    // Variables locales para el formulario
    var medidor by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var tipoSeleccionado by remember { mutableStateOf("AGUA") }
    val opciones = listOf(
        resources.getString(R.string.option_agua),
        resources.getString(R.string.option_luz),
        resources.getString(R.string.option_gas)
    )

    // Definimos el formato de fecha (yyyy-MM-dd)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(60.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    resources.getString(R.string.title),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            // Campo de texto para el medidor
            TextField(
                value = medidor,
                onValueChange = { medidor = it },
                label = { Text(resources.getString(R.string.label_medidor)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Campo de texto para la fecha
            TextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = { Text(resources.getString(R.string.label_fecha)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            // Título para el tipo de medidor
            Text(resources.getString(R.string.title_tipo_medidor), fontSize = 18.sp)

            // Opciones de tipo de medidor con RadioButton
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                opciones.forEach { opcion ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (opcion == tipoSeleccionado),
                                onClick = { tipoSeleccionado = opcion }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (opcion == tipoSeleccionado),
                            onClick = { tipoSeleccionado = opcion }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(opcion)
                    }
                }
            }

            // Botón para registrar la medición
            Button(
                onClick = {
                    val medidorNumerico = medidor.toDoubleOrNull()
                    if (medidorNumerico != null) {
                        val fechaDate = try {
                            dateFormat.parse(fecha)
                        } catch (e: Exception) {
                            println(resources.getString(R.string.error_fecha))
                            return@Button
                        }
                        if (fechaDate != null) {
                            viewModel.insertarRegistro(tipoSeleccionado, medidorNumerico, fechaDate)
                            navController.navigateUp()
                        } else {
                            println(resources.getString(R.string.error_fecha))
                        }
                    } else {
                        println(resources.getString(R.string.error_numero))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(48.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(resources.getString(R.string.btn_registrar))
            }
        }
    }
}
