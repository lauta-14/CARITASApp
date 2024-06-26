package modelo.usuario

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert
    fun insert(usuario: Usuario?)

    @Query("SELECT * FROM Usuario")
    fun getAllUsuarios(): LiveData<List<Usuario>>

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre")
    fun getUsuarioPorNombre(nombre: String): LiveData<List<Usuario>>
}

