package br.com.springboot.cursojdevtreinamento.controller;

import br.com.springboot.cursojdevtreinamento.model.Usuario;
import br.com.springboot.cursojdevtreinamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "listaTodos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listaUsuarios(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    }

    @PostMapping(value = "salvar")
    @ResponseBody // Descrição da resposta
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        Usuario user = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "atualizar")
    @ResponseBody // Descrição da resposta
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){

        if (usuario.getId() == null){
            return new ResponseEntity<String>("Id não foi informado", HttpStatus.OK);
        }

        Usuario user = usuarioRepository.saveAndFlush(usuario);

        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete")
    @ResponseBody // Descrição da resposta
    public ResponseEntity<String> delete(@RequestParam Long id){

        usuarioRepository.deleteById(id);

        return new ResponseEntity<String>("User Deletado com Sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "buscarUserId")
    @ResponseBody // Descrição da resposta
    public ResponseEntity<Usuario> buscarUserId(@RequestParam Long id){

        Usuario user = usuarioRepository.findById(id).get();

        return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }

    @GetMapping(value = "buscarPorNome")
    @ResponseBody // Descrição da resposta
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam String nome){

        List<Usuario> users = usuarioRepository.buscarPorNome(nome.trim().toLowerCase());

        return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
    }
}
