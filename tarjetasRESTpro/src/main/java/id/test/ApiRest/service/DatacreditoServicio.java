/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.test.ApiRest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.test.ApiRest.model.Datacredito;
import id.test.ApiRest.repository.DatacreditoRepositorio;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DatacreditoServicio    {
      
	@Autowired
	private DatacreditoRepositorio repositorio;

	
	public List<Datacredito> listarTodosLosDatacredito() {
		return repositorio.findAll();
	}

	
	public Datacredito guardarDatacredito(Datacredito solicitud) {
		return repositorio.save(solicitud);
	}

	
	public Optional<Datacredito> obtenerDatacreditoPorId(Long id) {
		return repositorio.findById(id) ;
	}

	
	public Datacredito actualizarDatacredito(Datacredito solicitud) {
		return repositorio.save(solicitud);
	}

	
	public void eliminarDatacredito(Long id) {
		repositorio.deleteById(id);

	}
                
        
	public  boolean BuscarListaNegra(String tipodocumento, String documento, String tiposolicitud)
        {
          List<Datacredito> lista=  repositorio.BuscarListaNegra(tipodocumento,documento );
          
          if ( lista.isEmpty() )  //no esta reportado a datacredito
              return false;
          
          return true;         
   
	}           
}
