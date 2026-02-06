package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameService {

    GameDTO findById(Long id);
    Page<GameMinDTO> findAll(Pageable pageable);
    List<GameMinDTO> findByGameList(Long listId);
}
