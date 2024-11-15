package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.controllers.Optional;
import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import com.devsuperior.dslist.services.exceptions.ResourceNotFoundException;



@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional(readOnly = true) 
	public GameDTO FindById(Long id) {
		Game game = gameRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Game not foun with id: " + id));
		
		return new GameDTO(game);
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll(){
		List<Game>result = gameRepository.findAll();
		return result.stream().map(x -> new GameMinDTO(x)).toList();
		
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId){
		List<GameMinProjection> result = gameRepository.searchByList(listId);
		
		if(result.isEmpty()) {
			throw new ResourceNotFoundException("No games foound for list with id: " + listId);
		}
		return result.stream().map(x -> new GameMinDTO(x)).toList();
	}
	
}
