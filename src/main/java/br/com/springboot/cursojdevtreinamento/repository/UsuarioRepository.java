package br.com.springboot.cursojdevtreinamento.repository;

import br.com.springboot.cursojdevtreinamento.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select u from Usuario u where lower(trim(u.nome)) like %?1%")
    List<Usuario> buscarPorNome(String nome);
}
