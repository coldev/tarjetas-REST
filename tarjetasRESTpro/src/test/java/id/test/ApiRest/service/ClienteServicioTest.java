package id.test.ApiRest.service;

import id.test.ApiRest.model.Cliente;
import id.test.ApiRest.repository.ClienteRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

 

@ExtendWith(MockitoExtension.class)    
@MockitoSettings(strictness = Strictness.LENIENT)       
class ClienteServicioTest {    
   
    @Mock
    private ClienteRepositorio repositorio;

    @InjectMocks
    private ClienteServicio servicio;

    @Test
    void Crear() {
        final Cliente datos = crearDatos();                 

         given(repositorio.findById(datos.getId())).willReturn(Optional.empty());
        given(repositorio.save(datos)).willAnswer(invocation -> invocation.getArgument(0));

        Cliente resultado = servicio.guardarCliente(datos);

        assertThat(resultado).isNotNull();     
    }

   

    @Test
    void Actualizar() {
        final Cliente datos = crearDatos();
        
        given(repositorio.save(datos)).willReturn(datos);

        final Cliente resultado = servicio.actualizarCliente(datos);

        assertThat(resultado).isNotNull();
       
    }

    @Test
    void ListarTodos() {
        List<Cliente> listado = new ArrayList();  
        
        final Cliente datos = crearDatos();

        listado.add(datos);
        
        given(repositorio.findAll()).willReturn(listado);

        List<Cliente> resultado = servicio.listarTodosLosCliente();
        
        assertThat(resultado).isNotNull();
    }

    @Test
    void BuscarporId(){   
        final Long id= 1L;
        final Cliente datos = crearDatos();

        given(repositorio.findById(id)).willReturn(Optional.of(datos));

        final Optional<Cliente> resultado  =servicio.obtenerClientePorId(id);

        assertThat(resultado).isNotNull();

    }

    @Test
    void Borrar() {
        final Long ClienteId=1L;

        servicio.eliminarCliente(ClienteId);        

        verify(repositorio, times(1)).deleteById(ClienteId);
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