package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.repositories.GameListRepository;



@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	
	@Transactional(readOnly = true) // assegurando que eu não vou fazer nenhuma operação de escrita, não bloqueando o banco
	public List<GameListDTO> findAll(){
		List<GameList>result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList(); // MAP TRANSFORMAR OBJETOS DE UMA COISA PARA OUTRA
		
	}
	
	
}
