package com.sistemalima.wlpesquisa.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistemalima.wlpesquisa.dto.ClientDTO;
import com.sistemalima.wlpesquisa.entities.Client;
import com.sistemalima.wlpesquisa.repositories.ClientRepository;
import com.sistemalima.wlpesquisa.services.exceptions.DatabaseException;
import com.sistemalima.wlpesquisa.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)    
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {               // metodo para buscar todas os clientes
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDTO(x));
	}
	
	@Transactional(readOnly = true)                 // metodo para buscar clientes por id
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);
		
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {   // metodo para inserir um cliente
		Client entity = new Client();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new ClientDTO(entity);
		
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {    // metodo para atualizar um recurso
		try {
			Client entity = repository.getOne(id);    // atualizar um dado
			if (dto.getName() != null) {
				entity.setName(dto.getName());	
			}
			if (dto.getCpf() != null) {
				entity.setCpf(dto.getCpf());
			}
			if (dto.getIncome() != null) {
				entity.setIncome(dto.getIncome());
			}
			if (dto.getBirthDate() != null) {
				entity.setBirthDate(dto.getBirthDate());
			}
			if (dto.getChildren() != null) {
				entity.setChildren(dto.getChildren());
			}
			
			entity = repository.save(entity);
			return new ClientDTO(entity);
				
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {                    // se o id não existir
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) { // tentar deletar um recurso que pode gerar inconsistencia
			throw new DatabaseException("Integrity violation"); 
		}
	}

}
