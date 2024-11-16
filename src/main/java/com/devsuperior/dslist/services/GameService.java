package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

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
	public GameDTO FindById(@PathVariable Long id) {
		Game result = gameRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
		return new GameDTO(result);
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll(){
		List<Game>result = gameRepository.findAll();
		return result.stream().map(GameMinDTO::new).toList();
		
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByGameList(Long listId){
		List<GameMinProjection> games = gameRepository.searchByList(listId);
		
		if(games.isEmpty()) {
			throw new ResourceNotFoundException("No games found for list with id: " + listId);
		}
		return games.stream().map(GameMinDTO::new).toList();
	}
	
}
