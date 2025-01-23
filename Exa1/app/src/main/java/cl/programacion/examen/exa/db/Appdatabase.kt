package cl.programacion.examen.exa.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cl.programacion.examen.exa.dao.RegistroDao
import cl.programacion.examen.exa.entities.Registro

@Database(entities = [Registro::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class Appdatabase : RoomDatabase() {
    abstract fun registroDao(): RegistroDao
}

