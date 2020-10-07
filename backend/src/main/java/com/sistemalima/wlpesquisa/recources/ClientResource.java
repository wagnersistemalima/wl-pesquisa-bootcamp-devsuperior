package com.sistemalima.wlpesquisa.recources;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemalima.wlpesquisa.entities.Client;

@RestController
@RequestMapping(value = "/clientes")
public class ClientResource {
	
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		List<Client> list = new ArrayList<>();
		list.add(new Client(1L, "Maria", "044.147.455-78", 990.0, Instant.now(), 1));
		list.add(new Client(2L, "Joao", "045.148.456-79", 1000.0, Instant.now(), 2));
		return ResponseEntity.ok().body(list);
	}

}
