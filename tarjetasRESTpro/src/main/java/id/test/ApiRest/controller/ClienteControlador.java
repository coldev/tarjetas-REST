package id.test.ApiRest.controller;

import id.test.ApiRest.model.Cliente;
import id.test.ApiRest.service.ClienteServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

 

@RestController
@RequestMapping("/api/cliente")
@Slf4j   //para activar los mensajes de log internos libreria log4j
public class ClienteControlador {

    private final ClienteServicio servicio;

    public ClienteControlador(ClienteServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Cliente> Listar() {
        return servicio.listarTodosLosCliente();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClienteporId(@PathVariable Long id) {
        return servicio.obtenerClientePorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createUser(@RequestBody @Valid Cliente user) {
        return servicio.guardarCliente(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody @Valid Cliente user) {
        return servicio.obtenerClientePorId(id)
                .map(userObj -> {
                    userObj.setId(id);
                    return ResponseEntity.ok(servicio.actualizarCliente(userObj));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> borrarCliente(@PathVariable Long id) {
        return servicio.obtenerClientePorId(id)
                .map(user -> {
                    servicio.eliminarCliente(id);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
