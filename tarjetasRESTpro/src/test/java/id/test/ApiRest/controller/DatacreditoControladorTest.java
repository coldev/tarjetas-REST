package id.test.ApiRest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.test.ApiRest.model.Datacredito;
import id.test.ApiRest.service.DatacreditoServicio;
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


 

@WebMvcTest(controllers = DatacreditoControlador.class)
@ActiveProfiles("test")
class DatacreditoControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DatacreditoServicio servicio;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Datacredito> lista;

    @BeforeEach
    void configurar() {
        CrearLista();
        
        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void Listar() throws Exception {
            

        given(servicio.listarTodosLosDatacredito()).willReturn(lista);

        this.mockMvc.perform(get("/api/datacredito"))
                .andExpect(status().isOk()) 
                ;      
        
        var resultado= servicio.listarTodosLosDatacredito();
        
        assertThat(resultado).isNotNull();     
    }

    @Test
    void ObtenerporId() throws Exception {
        final Long id = 1L;
        final Datacredito datos = crearDatos();

        given(servicio.obtenerDatacreditoPorId(id)).willReturn(Optional.of(datos));

        this.mockMvc.perform(get("/api/datacredito/{id}", id))
                .andExpect(status().isOk());
        
        var resultado= servicio.obtenerDatacreditoPorId(id);
        
        assertThat(resultado).isNotNull();     
    }

 

    @Test
    void Crear() throws Exception {
        given(servicio.actualizarDatacredito(any(Datacredito.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Datacredito datos = crearDatos();

        this.mockMvc.perform(post("/api/datacredito")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().is(201)); //estado 201 en http es creado
        
                      
    }

     

    @Test
    void Actualizar() throws Exception {
        Long  Id = 60L;
        Datacredito datos = crearDatos();
        
        given(servicio.obtenerDatacreditoPorId(Id)).willReturn(Optional.of(datos));
        given(servicio.actualizarDatacredito(any(Datacredito.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/api/datacredito/{id}", datos.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().isOk());               

        var resultado= servicio.actualizarDatacredito(datos);
        
        assertThat(resultado).isNotNull();      
    }

     

   

    @Test
    void Borrar() throws Exception {
        Long Id = 1L;
        given(servicio.obtenerDatacreditoPorId(Id)).willReturn(Optional.empty());

        this.mockMvc.perform(delete("/api/datacredito/{id}", Id))
                .andExpect(status().isNotFound());      
        
    }
    
    
    
    //crea datos de prueba
    private void CrearLista()
    {
        Datacredito datos = new Datacredito();
        datos=crearDatos(); 
        
        this.lista = new ArrayList<>();
        lista.add( datos);                 
    }
    
     //crea datos de prueba
    private Datacredito crearDatos()
    {
        Datacredito datos = new Datacredito();
        
         datos.setId(60L);
        datos.setTipo_documento("CC");
        datos.setDocumento("900001");
 
        
        return datos;
    }

}