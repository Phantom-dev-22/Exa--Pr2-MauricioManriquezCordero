package cl.programacion.examen.exa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.programacion.examen.exa.dao.RegistroDao
import cl.programacion.examen.exa.entities.Registro
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date

class RegistroViewModel(private val dao: RegistroDao) : ViewModel() {
    val registros: Flow<List<Registro>> = dao.obtenerRegistros()

    fun insertarRegistro(tipo: String, valor: Double, fecha: Date) {
        viewModelScope.launch {
            dao.insertarRegistro(Registro(tipoGasto = tipo, valor = valor, fecha = fecha))
        }
    }

}


