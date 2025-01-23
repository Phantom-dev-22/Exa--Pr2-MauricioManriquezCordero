package cl.programacion.examen.exa.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.programacion.examen.exa.entities.Registro
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistroDao {
    @Query("SELECT * FROM registros ORDER BY fecha DESC")
    fun obtenerRegistros(): Flow<List<Registro>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarRegistro(registro: Registro)

}