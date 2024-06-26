package modelo.usuario

import androidx.room.Entity
import androidx.annotation.NonNull

@Entity(primaryKeys = ["nombre"])
data class Usuario(
    @NonNull
    var nombre: String = "",
    var pass: String = ""
)