package com.ctf.reservas_servicio.services.impl;

import com.ctf.reservas_servicio.entities.Usuario;
import com.ctf.reservas_servicio.repositories.UsuarioRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

   @PersistenceContext
   private EntityManager em;

   @Override
   public Usuario findUsuarioInseguro(String username) {
      String hql = "SELECT u FROM Usuario u WHERE u.username= '" + username + "'";
      Query query = em.createQuery(hql);
      return (Usuario) query.getSingleResult();
   }
}
