package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameListDTO;

import java.util.List;

public interface GameListService {

    List<GameListDTO> findAll();
    void move(Long listId, int sourceIndex, int destinationIndex);
    GameListDTO findById(Long id);
}
