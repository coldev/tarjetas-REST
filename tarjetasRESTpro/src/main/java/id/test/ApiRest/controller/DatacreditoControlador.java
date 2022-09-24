package id.test.ApiRest.controller;

import id.test.ApiRest.model.Datacredito;
import id.test.ApiRest.service.DatacreditoServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

 

@RestController
@RequestMapping("/api/datacredito")
@Slf4j   //para activar los mensajes de log internos libreria log4j
public class DatacreditoControlador {

    private final DatacreditoServicio servicio;

    public DatacreditoControlador(DatacreditoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Datacredito> Listar() {
        return servicio.listarTodosLosDatacredito();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Datacredito> obtenerDatacreditoporId(@PathVariable Long id) {
        return servicio.obtenerDatacreditoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Datacredito createUser(@RequestBody @Valid Datacredito user) {
        return servicio.guardarDatacredito(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Datacredito> actualizarDatacredito(@PathVariable Long id, @RequestBody @Valid Datacredito user) {
        return servicio.obtenerDatacreditoPorId(id)
                .map(userObj -> {
                    userObj.setId(id);
                    return ResponseEntity.ok(servicio.actualizarDatacredito(userObj));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Datacredito> borrarDatacredito(@PathVariable Long id) {
        return servicio.obtenerDatacreditoPorId(id)
                .map(user -> {
                    servicio.eliminarDatacredito(id);
                    return ResponseEntity.ok(user);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
