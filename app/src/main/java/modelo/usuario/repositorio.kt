package modelo.usuario

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class Repositorio(application: Application) {
    private val usuarioDao: UsuarioDao
    private val executor: Executor

    init {
        val database: AppDatabase = AppDatabaseProvider.getInstance(application)
        usuarioDao = database.usuarioDao()
        executor = Executors.newSingleThreadExecutor()
    }

    val allUsuarios: LiveData<List<Usuario>>
        get() = usuarioDao.getAllUsuarios()

    fun getUsuarioPorNombre(nombre: String): LiveData<List<Usuario>> {
        return usuarioDao.getUsuarioPorNombre(nombre)
    }

    fun insertUsuario(usuario: Usuario) {
        executor.execute { usuarioDao.insert(usuario) }
    }
}


