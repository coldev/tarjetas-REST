package id.test.ApiRest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.test.ApiRest.model.Cliente;
import id.test.ApiRest.service.ClienteServicio;
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


 

@WebMvcTest(controllers = ClienteControlador.class)
@ActiveProfiles("test")
class ClienteControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteServicio servicio;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Cliente> lista;

    @BeforeEach
    void configurar() {
        CrearLista();
        
        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @Test
    void Listar() throws Exception {
            

        given(servicio.listarTodosLosCliente()).willReturn(lista);

        this.mockMvc.perform(get("/api/cliente"))
                .andExpect(status().isOk()) 
                ;      
        
        var resultado= servicio.listarTodosLosCliente();
        
        assertThat(resultado).isNotNull();     
    }

    @Test
    void ObtenerporId() throws Exception {
        final Long id = 1L;
        final Cliente datos = crearDatos();

        given(servicio.obtenerClientePorId(id)).willReturn(Optional.of(datos));

        this.mockMvc.perform(get("/api/cliente/{id}", id))
                .andExpect(status().isOk());
        
        var resultado= servicio.obtenerClientePorId(id);
        
        assertThat(resultado).isNotNull();     
    }

 

    @Test
    void Crear() throws Exception {
        given(servicio.actualizarCliente(any(Cliente.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Cliente datos = crearDatos();

        this.mockMvc.perform(post("/api/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().is(201)); //estado 201 en http es creado
        
                      
    }

     

    @Test
    void Actualizar() throws Exception {
        Long  Id = 60L;
        Cliente datos = crearDatos();
        
        given(servicio.obtenerClientePorId(Id)).willReturn(Optional.of(datos));
        given(servicio.actualizarCliente(any(Cliente.class))).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/api/cliente/{id}", datos.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datos)))
                .andExpect(status().isOk());               

        var resultado= servicio.actualizarCliente(datos);
        
        assertThat(resultado).isNotNull();      
    }

     

   

    @Test
    void Borrar() throws Exception {
        Long Id = 1L;
        given(servicio.obtenerClientePorId(Id)).willReturn(Optional.empty());

        this.mockMvc.perform(delete("/api/cliente/{id}", Id))
                .andExpect(status().isNotFound());      
        
    }
    
    
    
    //crea datos de prueba
    private void CrearLista()
    {
        Cliente datos = new Cliente();
        datos=crearDatos(); 
        
        this.lista = new ArrayList<>();
        lista.add( datos);                 
    }
    
     //crea datos de prueba
    private Cliente crearDatos()
    {
        Cliente datos = new Cliente();
        
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
        
        return datos;
    }

}