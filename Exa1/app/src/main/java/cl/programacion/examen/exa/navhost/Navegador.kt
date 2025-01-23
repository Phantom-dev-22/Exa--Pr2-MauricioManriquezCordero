package cl.programacion.examen.exa.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import cl.programacion.examen.exa.pantallas.FormularioScreen
import cl.programacion.examen.exa.pantallas.ListadoScreen
import cl.programacion.examen.exa.viewmodel.RegistroViewModel


@Composable
fun AppNavigation(navController: NavHostController, viewModel: RegistroViewModel) {
    NavHost(navController = navController, startDestination = "listado") {
        composable("listado") { ListadoScreen(navController, viewModel) }
        composable("formulario") { FormularioScreen(navController, viewModel) }
    }
}

