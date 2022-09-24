package id.test.ApiRest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.test.ApiRest.model.Siebel;
import id.test.ApiRest.service.SiebelServicio;
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


 

@WebMvcTest(controllers = SiebelControlador.class)
@ActiveProfiles("test")
class SiebelControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiebelServicio servicio;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Siebel> lista;

    @BeforeEach
    void configurar() {
        CrearLista();
        
        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void Listar() throws Exception {
            

        given(servicio.listarTodosLosSiebel()).willReturn(lista);

        this.mockMvc.perform(get("/api/siebel"))
                .andExpect(status().isOk()) 
                ;      
        
        var resultado= servicio.listarTodosLosSiebel();
        
        assertThat(resultado).isNotNull();     
    }

    @Test
    void ObtenerporId() throws Exception {
        final Long id = 1L;
        final Siebel datos = crearDatos();

        given(servicio.obtenerSiebelPorId(id)).willReturn(Optional.of(datos));

        this.mockMvc.perform(get("/api/siebel/{id}", id))
                .andExpect(status().isOk());
        
        var resultado= servicio.obtenerSiebelPorId(id);
        
        assertThat(resultado).isNotNull();     
    }

 

    @Test
    void Crear() throws Exception {
        given(servicio.actualizarSiebel(any(Siebel.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Siebel datos = crearDatos();

        this.mockMvc.perform(post("/api/siebel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().is(201)); //estado 201 en http es creado
        
                      
    }

     

    @Test
    void Actualizar() throws Exception {
        Long  Id = 60L;
        Siebel datos = crearDatos();
        
        given(servicio.obtenerSiebelPorId(Id)).willReturn(Optional.of(datos));
        given(servicio.actualizarSiebel(any(Siebel.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/api/siebel/{id}", datos.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().isOk());               

        var resultado= servicio.actualizarSiebel(datos);
        
        assertThat(resultado).isNotNull();      
    }

     

   

    @Test
    void Borrar() throws Exception {
        Long Id = 1L;
        given(servicio.obtenerSiebelPorId(Id)).willReturn(Optional.empty());

        this.mockMvc.perform(delete("/api/siebel/{id}", Id))
                .andExpect(status().isNotFound());      
        
    }
    
    
    
    //crea datos de prueba
    private void CrearLista()
    {
        Siebel datos = new Siebel();
        datos=crearDatos(); 
        
        this.lista = new ArrayList<>();
        lista.add( datos);                 
    }
    
     //crea datos de prueba
    private Siebel crearDatos()
    {
        Siebel datos = new Siebel();
        
         datos.setId(60L);
        datos.setTipo_documento("CC");
        datos.setDocumento("900001");
       
        
        return datos;
    }

}