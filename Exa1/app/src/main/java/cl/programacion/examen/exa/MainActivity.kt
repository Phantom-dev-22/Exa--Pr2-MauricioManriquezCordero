package cl.programacion.examen.exa

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import cl.programacion.examen.exa.navhost.AppNavigation
import cl.programacion.examen.exa.viewmodel.RegistroViewModel
import cl.programacion.examen.exa.db.Appdatabase
import android.os.Bundle
import androidx.room.Room


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = Room.databaseBuilder(
            applicationContext,
            Appdatabase::class.java,
            "registros.db"
        ).build()
        val dao = database.registroDao()

        setContent {
            val navController = rememberNavController()
            val viewModel = RegistroViewModel(dao)
            AppNavigation(navController, viewModel)
        }
    }
}
