package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;

import java.util.List;

public interface GameService {

    GameDTO findById(Long id);
    List<GameMinDTO> findAll();
    List<GameMinDTO> findByGameList(Long listId);
}
