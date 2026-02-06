package com.devsuperior.dslist.dto;


import com.devsuperior.dslist.entities.GameList;

public record GameListDTO (
		Long id,
		String name
){
	public GameListDTO(GameList entity) {
		this(entity.getId(), entity.getName());
	}
}
