package id.test.ApiRest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.test.ApiRest.model.SolicitudProducto;
import id.test.ApiRest.service.SolicitudProductoServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


 

@WebMvcTest(controllers = SolicitudProductoControlador.class)
@ActiveProfiles("test")
class SolicitudProductoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SolicitudProductoServicio servicio;

    @Autowired
    private ObjectMapper objectMapper;

    private List<SolicitudProducto> lista;

    @BeforeEach
    void configurar() {
        CrearLista();
        
        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void Listar() throws Exception {
            

        given(servicio.listarTodosLosSolicitudProducto()).willReturn(lista);

        this.mockMvc.perform(get("/api/solicitudproducto"))
                .andExpect(status().isOk()) 
                ;      
        
        var resultado= servicio.listarTodosLosSolicitudProducto();
        
        assertThat(resultado).isNotNull();     
    }

    @Test
    void ObtenerporId() throws Exception {
        final Long id = 1L;
        final SolicitudProducto datos = crearDatos();

        given(servicio.obtenerSolicitudProductoPorId(id)).willReturn(Optional.of(datos));

        this.mockMvc.perform(get("/api/solicitudproducto/{id}", id))
                .andExpect(status().isOk());
        
        var resultado= servicio.obtenerSolicitudProductoPorId(id);
        
        assertThat(resultado).isNotNull();     
    }

 

    @Test
    void Crear() throws Exception {
        given(servicio.actualizarSolicitudProducto(any(SolicitudProducto.class))).willAnswer((invocation) -> invocation.getArgument(0));

        SolicitudProducto datos = crearDatos();

        this.mockMvc.perform(post("/api/solicitudproducto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().is(201)); //estado 201 en http es creado
        
                      
    }

     

    @Test
    void Actualizar() throws Exception {
        Long  Id = 60L;
        SolicitudProducto datos = crearDatos();
        
        given(servicio.obtenerSolicitudProductoPorId(Id)).willReturn(Optional.of(datos));
        given(servicio.actualizarSolicitudProducto(any(SolicitudProducto.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/api/solicitudproducto/{id}", datos.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().isOk());               

        var resultado= servicio.actualizarSolicitudProducto(datos);
        
        assertThat(resultado).isNotNull();      
    }

     

   

    @Test
    void Borrar() throws Exception {
        Long Id = 1L;
        given(servicio.obtenerSolicitudProductoPorId(Id)).willReturn(Optional.empty());

        this.mockMvc.perform(delete("/api/solicitudproducto/{id}", Id))
                .andExpect(status().isNotFound());      
        
    }
    
    
    
    //crea datos de prueba
    private void CrearLista()
    {
        SolicitudProducto datos = new SolicitudProducto();
        datos=crearDatos(); 
        
        this.lista = new ArrayList<>();
        lista.add( datos);                 
    }
    
     //crea datos de prueba
    private SolicitudProducto crearDatos()
    {
        SolicitudProducto datos = new SolicitudProducto();
        
         datos.setId(60L);
        datos.setTipo_documento("CC");
        datos.setDocumento("900001");
        datos.setNombre("MARIA");
        datos.setDireccion("cll 89 33 22");
        datos.setCelular("300112233");
        datos.setEmail("jojo@mail.com");
        datos.setEstado_civil("C");
        datos.setFecha_nacimiento("2000-01-01");
        datos.setSexo("F");
        datos.setAprobado("S");
        datos.setNumero("0000000000");
        
        return datos;
    }

}