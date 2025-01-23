package cl.programacion.examen.exa.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "registros")
data class Registro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipoGasto: String, //Corresponde a: agua, luz, gas
    val valor: Double,
    val fecha: Date
)