package cl.programacion.examen.exa.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import cl.programacion.examen.exa.R
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import cl.programacion.examen.exa.viewmodel.RegistroViewModel




@Composable
fun ListadoScreen(navController: NavController, viewModel: RegistroViewModel) {
    val registros by viewModel.registros.collectAsState(initial = emptyList())

    // Configuración del formateador para usar punto como separador de miles
    val decimalFormatSymbols = DecimalFormatSymbols().apply {
        groupingSeparator = '.'
    }
    val decimalFormat = DecimalFormat("###,###,###", decimalFormatSymbols)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("formulario") },
                containerColor = Color(0xFFBBDEFB)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            items(registros) { registro ->
                // Formateando datos
                val valorFormateado = decimalFormat.format(registro.valor)
                val fechaFormateada = dateFormat.format(registro.fecha)

                // Diseño de cada fila
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Ícono del tipo de gasto
                    Icon(
                        painter = getGastoIcon(registro.tipoGasto),
                        contentDescription = registro.tipoGasto,
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 2.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    // Tipo de gasto
                    Text(
                        text = registro.tipoGasto,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.weight(1f)
                    )
                    // Valor
                    Text(
                        text = valorFormateado,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    // Fecha
                    Text(
                        text = fechaFormateada,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                // Línea divisoria entre elementos
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            }
        }
    }
}

// Icono correspondiente según el tipo de gasto
@Composable
fun getGastoIcon(tipoGasto: String): Painter {
    return when (tipoGasto.lowercase()) {
        "luz" -> painterResource(id = R.drawable.ic_luz)
        "agua" -> painterResource(id = R.drawable.ic_agua)
        "gas" -> painterResource(id = R.drawable.ic_gas)
        else -> painterResource(id = R.drawable.ic_launcher_foreground)
    }
}



