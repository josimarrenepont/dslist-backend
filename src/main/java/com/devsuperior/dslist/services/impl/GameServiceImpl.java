package com.devsuperior.dslist.services.impl;

import java.util.List;

import com.devsuperior.dslist.services.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import com.devsuperior.dslist.services.exceptions.ResourceNotFoundException;



@Service
public class GameServiceImpl implements GameService {
	

	private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
	@Override
    @Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		Game result = gameRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Game not found with id: " + id));
		return new GameDTO(result);
	}
	@Override
	@Transactional(readOnly = true)
	public Page<GameMinDTO> findAll(Pageable pageable){
		Page<Game>result = gameRepository.findAll(pageable);
		return result.map(GameMinDTO::new);
		
	}
	@Override
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByGameList(Long listId){
		List<GameMinProjection> games = gameRepository.searchByList(listId);
		
		if(games.isEmpty()) {
			throw new ResourceNotFoundException("No games found for list with id: " + listId);
		}
		return games.stream().map(GameMinDTO::new).toList();
	}
}
