/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.test.ApiRest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.test.ApiRest.model.Siebel;
import id.test.ApiRest.repository.SiebelRepositorio;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SiebelServicio  {
      
	@Autowired
	private SiebelRepositorio repositorio;

	
	public List<Siebel> listarTodosLosSiebel() {
		return repositorio.findAll();
	}

	
	public Siebel guardarSiebel(Siebel solicitud) {
		return repositorio.save(solicitud);
	}

	
	public Optional<Siebel> obtenerSiebelPorId(Long id) {
		return repositorio.findById(id) ;
	}

	
	public Siebel actualizarSiebel(Siebel solicitud) {
		return repositorio.save(solicitud);
	}

	
	public void eliminarSiebel(Long id) {
		repositorio.deleteById(id);

	}
                
        
	public  boolean BuscarListaNegra(String tipodocumento, String documento, String tiposolicitud)
        {
          List<Siebel> lista=  repositorio.BuscarListaNegra(tipodocumento,documento );
          
          if ( lista.isEmpty() )  //no esta reportado a siebel
              return false;
          
          return true;         
   
	}           
}

