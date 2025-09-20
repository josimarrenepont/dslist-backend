package com.devsuperior.dslist.controllers;

import java.util.List;

import com.devsuperior.dslist.services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;

@RestController
@RequestMapping(value = "/games")
@Tag(name = "Jogos", description = "Endpoints de jogos")
public class GameController {


	private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
	@GetMapping("/{id}")
	@Operation(summary = "Buscar jogo por ID", description = "Retorna os detalhes de um jogo específico com base no ID fornecido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Jogo encontrado com sucesso"),
			@ApiResponse(responseCode = "404", description = "Jogo não encontrado")
	})
	public ResponseEntity<GameDTO> findById(@PathVariable Long id) {
		GameDTO dto = gameService.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	@Operation(summary = "Listar todos os jogos", description = "Retorna a lista completa de jogos cadastrados.")
	@ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
	public ResponseEntity<List<GameMinDTO>> findAll() {
		List<GameMinDTO> list = gameService.findAll();
		return ResponseEntity.ok(list);
	}
	@GetMapping("/list/{listId}")
	@Operation(summary = "Buscar jogos por lista", description = "Retorna os jogos que pertencem a uma lista específica.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Jogos encontrados com sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum jogo encontrado para a lista")
	})
	public ResponseEntity<List<GameMinDTO>> findByGameList(@PathVariable Long listId){
		List<GameMinDTO> list = gameService.findByGameList(listId);
		return ResponseEntity.ok(list);
	}
}
