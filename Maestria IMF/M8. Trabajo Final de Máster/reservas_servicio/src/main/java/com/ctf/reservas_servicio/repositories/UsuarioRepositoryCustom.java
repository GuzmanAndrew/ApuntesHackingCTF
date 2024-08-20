package com.ctf.reservas_servicio.repositories;

import com.ctf.reservas_servicio.entities.Usuario;

public interface UsuarioRepositoryCustom {

   Usuario findUsuarioInseguro(String username);
}
