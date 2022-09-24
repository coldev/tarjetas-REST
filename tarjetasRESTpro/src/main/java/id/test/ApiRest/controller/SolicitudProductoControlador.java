package id.test.ApiRest.controller;

import id.test.ApiRest.model.SolicitudProducto;
import id.test.ApiRest.service.SolicitudProductoServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

 

@RestController
@RequestMapping("/api/solicitudproducto")
@Slf4j   //para activar los mensajes de log internos libreria log4j
public class SolicitudProductoControlador {

    private final SolicitudProductoServicio servicio;

    public SolicitudProductoControlador(SolicitudProductoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<SolicitudProducto> Listar() {
        return servicio.listarTodosLosSolicitudProducto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudProducto> obtenerSolicitudProductoporId(@PathVariable Long id) {
        return servicio.obtenerSolicitudProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudProducto createUser(@RequestBody @Valid SolicitudProducto user) {
        return servicio.guardarSolicitudProducto(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudProducto> actualizarSolicitudProducto(@PathVariable Long id, @RequestBody @Valid SolicitudProducto user) {
        return servicio.obtenerSolicitudProductoPorId(id)
                .map(userObj -> {
                    userObj.setId(id);
                    return ResponseEntity.ok(servicio.actualizarSolicitudProducto(userObj));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SolicitudProducto> borrarSolicitudProducto(@PathVariable Long id) {
        return servicio.obtenerSolicitudProductoPorId(id)
                .map(user -> {
                    servicio.eliminarSolicitudProducto(id);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
