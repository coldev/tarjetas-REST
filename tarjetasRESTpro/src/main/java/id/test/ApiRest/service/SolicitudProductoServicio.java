/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.test.ApiRest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.test.ApiRest.model.SolicitudProducto;
import id.test.ApiRest.repository.SolicitudProductoRepositorio;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SolicitudProductoServicio {
          
                     
    
	@Autowired
	private SolicitudProductoRepositorio repositorio;

	
	public List<SolicitudProducto> listarTodosLosSolicitudProducto() {
		return repositorio.findAll();
	}

	
	public SolicitudProducto guardarSolicitudProducto(SolicitudProducto solicitud) {
		return repositorio.save(solicitud);
	}

	
	public Optional<SolicitudProducto> obtenerSolicitudProductoPorId(Long id) {
		return repositorio.findById(id);
	}

	
	public SolicitudProducto actualizarSolicitudProducto(SolicitudProducto solicitud) {
		return repositorio.save(solicitud);
	}

	
	public void eliminarSolicitudProducto(Long id) {
		repositorio.deleteById(id);

	}
        
    
      

}
