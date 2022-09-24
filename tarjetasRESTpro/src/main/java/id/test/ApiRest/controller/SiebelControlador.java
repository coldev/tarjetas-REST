package id.test.ApiRest.controller;

import id.test.ApiRest.model.Siebel;
import id.test.ApiRest.service.SiebelServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

 

@RestController
@RequestMapping("/api/siebel")
@Slf4j   //para activar los mensajes de log internos libreria log4j
public class SiebelControlador {

    private final SiebelServicio servicio;

    public SiebelControlador(SiebelServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Siebel> Listar() {
        return servicio.listarTodosLosSiebel();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Siebel> obtenerSiebelporId(@PathVariable Long id) {
        return servicio.obtenerSiebelPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Siebel createUser(@RequestBody @Valid Siebel user) {
        return servicio.guardarSiebel(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Siebel> actualizarSiebel(@PathVariable Long id, @RequestBody @Valid Siebel user) {
        return servicio.obtenerSiebelPorId(id)
                .map(userObj -> {
                    userObj.setId(id);
                    return ResponseEntity.ok(servicio.actualizarSiebel(userObj));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Siebel> borrarSiebel(@PathVariable Long id) {
        return servicio.obtenerSiebelPorId(id)
                .map(user -> {
                    servicio.eliminarSiebel(id);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
