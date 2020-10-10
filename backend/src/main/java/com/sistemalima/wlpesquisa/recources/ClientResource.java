package com.sistemalima.wlpesquisa.recources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistemalima.wlpesquisa.dto.ClientDTO;
import com.sistemalima.wlpesquisa.services.ClientService;

@RestController
@RequestMapping(value = "/clientes")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {//encapsula uma resposta http / busca todos clientes
		List<ClientDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {//encapsula uma resposta http / busca clientes id
		ClientDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) { // encapsula uma resposta http/ inserir um cliente
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto) { 
		dto = service.update(id, dto);     //           atualizar um recurso
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> delete(@PathVariable Long id) { // deletar um client
		service.delete(id);     //           
		return ResponseEntity.noContent().build();                   // resposta 204
	}
	
	
}
