package id.test.ApiRest.service;

import id.test.ApiRest.model.SolicitudProducto;
import id.test.ApiRest.repository.SolicitudProductoRepositorio;
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
class SolicitudProductoServicioTest {    
   
    @Mock
    private SolicitudProductoRepositorio repositorio;

    @InjectMocks
    private SolicitudProductoServicio servicio;

    @Test
    void Crear() {
        final SolicitudProducto datos = crearDatos();                 

         given(repositorio.findById(datos.getId())).willReturn(Optional.empty());
        given(repositorio.save(datos)).willAnswer(invocation -> invocation.getArgument(0));

        SolicitudProducto resultado = servicio.guardarSolicitudProducto(datos);

        assertThat(resultado).isNotNull();     
    }

   

    @Test
    void Actualizar() {
        final SolicitudProducto datos = crearDatos();
        
        given(repositorio.save(datos)).willReturn(datos);

        final SolicitudProducto resultado = servicio.actualizarSolicitudProducto(datos);

        assertThat(resultado).isNotNull();
       
    }

    @Test
    void ListarTodos() {
        List<SolicitudProducto> listado = new ArrayList();  
        
        final SolicitudProducto datos = crearDatos();

        listado.add(datos);
        
        given(repositorio.findAll()).willReturn(listado);

        List<SolicitudProducto> resultado = servicio.listarTodosLosSolicitudProducto();
        
        assertThat(resultado).isNotNull();
    }

    @Test
    void BuscarporId(){   
        final Long id= 1L;
        final SolicitudProducto datos = crearDatos();

        given(repositorio.findById(id)).willReturn(Optional.of(datos));

        final Optional<SolicitudProducto> resultado  =servicio.obtenerSolicitudProductoPorId(id);

        assertThat(resultado).isNotNull();

    }

    @Test
    void Borrar() {
        final Long SolicitudProductoId=1L;

        servicio.eliminarSolicitudProducto(SolicitudProductoId);        

        verify(repositorio, times(1)).deleteById(SolicitudProductoId);
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