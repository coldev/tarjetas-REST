package id.test.ApiRest.service;

import id.test.ApiRest.model.Datacredito;
import id.test.ApiRest.repository.DatacreditoRepositorio;
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
class DatacreditoServicioTest {    
   
    @Mock
    private DatacreditoRepositorio repositorio;

    @InjectMocks
    private DatacreditoServicio servicio;

    @Test
    void Crear() {
        final Datacredito datos = crearDatos();                 

         given(repositorio.findById(datos.getId())).willReturn(Optional.empty());
        given(repositorio.save(datos)).willAnswer(invocation -> invocation.getArgument(0));

        Datacredito resultado = servicio.guardarDatacredito(datos);

        assertThat(resultado).isNotNull();     
    }

   

    @Test
    void Actualizar() {
        final Datacredito datos = crearDatos();
        
        given(repositorio.save(datos)).willReturn(datos);

        final Datacredito resultado = servicio.actualizarDatacredito(datos);

        assertThat(resultado).isNotNull();
       
    }

    @Test
    void ListarTodos() {
        List<Datacredito> listado = new ArrayList();  
        
        final Datacredito datos = crearDatos();

        listado.add(datos);
        
        given(repositorio.findAll()).willReturn(listado);

        List<Datacredito> resultado = servicio.listarTodosLosDatacredito();
        
        assertThat(resultado).isNotNull();
    }

    @Test
    void BuscarporId(){   
        final Long id= 1L;
        final Datacredito datos = crearDatos();

        given(repositorio.findById(id)).willReturn(Optional.of(datos));

        final Optional<Datacredito> resultado  =servicio.obtenerDatacreditoPorId(id);

        assertThat(resultado).isNotNull();

    }

    @Test
    void Borrar() {
        final Long DatacreditoId=1L;

        servicio.eliminarDatacredito(DatacreditoId);        

        verify(repositorio, times(1)).deleteById(DatacreditoId);
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