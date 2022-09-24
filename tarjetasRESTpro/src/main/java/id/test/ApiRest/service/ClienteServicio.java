/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.test.ApiRest.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.test.ApiRest.model.Cliente;
import id.test.ApiRest.repository.ClienteRepositorio;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClienteServicio  
{

	@Autowired
	private ClienteRepositorio repositorio;

	
	public List<Cliente> listarTodosLosCliente() {
		return repositorio.findAll();
	}

	
	public Cliente guardarCliente(Cliente solicitud) {
		return repositorio.save(solicitud);
	}

	
	public Optional<Cliente> obtenerClientePorId(Long id) {
		return repositorio.findById(id);
	}

	
	public Cliente actualizarCliente(Cliente solicitud) {
		return repositorio.save(solicitud);
	}

	
	public void eliminarCliente(Long id) {
		repositorio.deleteById(id);

	}

}
