package id.test.ApiRest.service;

import id.test.ApiRest.model.Siebel;
import id.test.ApiRest.repository.SiebelRepositorio;
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
class SiebelServicioTest {    
   
    @Mock
    private SiebelRepositorio repositorio;

    @InjectMocks
    private SiebelServicio servicio;

    @Test
    void Crear() {
        final Siebel datos = crearDatos();                 

         given(repositorio.findById(datos.getId())).willReturn(Optional.empty());
        given(repositorio.save(datos)).willAnswer(invocation -> invocation.getArgument(0));

        Siebel resultado = servicio.guardarSiebel(datos);

        assertThat(resultado).isNotNull();     
    }

   

    @Test
    void Actualizar() {
        final Siebel datos = crearDatos();
        
        given(repositorio.save(datos)).willReturn(datos);

        final Siebel resultado = servicio.actualizarSiebel(datos);

        assertThat(resultado).isNotNull();
       
    }

    @Test
    void ListarTodos() {
        List<Siebel> listado = new ArrayList();  
        
        final Siebel datos = crearDatos();

        listado.add(datos);
        
        given(repositorio.findAll()).willReturn(listado);

        List<Siebel> resultado = servicio.listarTodosLosSiebel();
        
        assertThat(resultado).isNotNull();
    }

    @Test
    void BuscarporId(){   
        final Long id= 1L;
        final Siebel datos = crearDatos();

        given(repositorio.findById(id)).willReturn(Optional.of(datos));

        final Optional<Siebel> resultado  =servicio.obtenerSiebelPorId(id);

        assertThat(resultado).isNotNull();

    }

    @Test
    void Borrar() {
        final Long SiebelId=1L;

        servicio.eliminarSiebel(SiebelId);        

        verify(repositorio, times(1)).deleteById(SiebelId);
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