package com.devsuperior.dslist.controllers;

import java.util.List;

import com.devsuperior.dslist.services.GameListService;
import com.devsuperior.dslist.services.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.dto.ReplacementDTO;
import com.devsuperior.dslist.services.impl.GameListServiceImpl;
import com.devsuperior.dslist.services.impl.GameServiceImpl;

@RestController
@RequestMapping(value = "/lists")
@Tag(name = "Listas de Jogos", description = "Endpoints para gerenciar listas de jogos e posições dos jogos")
public class GameListController {


	private final GameListService gameListService;
	private final GameService gameService;

    public GameListController(GameListService gameListService, GameService gameService) {
        this.gameListService = gameListService;
        this.gameService = gameService;
    }

	@GetMapping(value = "/games")
	@Operation(summary = "Buscar jogos por lista", description = "Retorna todos os jogos que pertencem a uma lista específica.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Jogos encontrados com sucesso"),
			@ApiResponse(responseCode = "404", description = "Nenhum jogo encontrado para esta lista")
	})
	public ResponseEntity<List<GameListDTO>> findAll() {
		List<GameListDTO> list = gameListService.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{listId}/games")
	public ResponseEntity<List<GameMinDTO>> findByList(@PathVariable Long listId) {
		List<GameMinDTO> list = gameService.findByGameList(listId);
		return ResponseEntity.ok(list);
	}

	@PostMapping(value = "/{listId}/replacement")
	@Operation(summary = "Mover jogo na lista", description = "Reordena os jogos de uma " +
			"lista trocando a posição entre dois índices.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Ordem atualizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Índices inválidos fornecidos"),
			@ApiResponse(responseCode = "404", description = "Lista não encontrada")
	})
	public ResponseEntity<Void> move(@PathVariable Long listId, @RequestBody ReplacementDTO dto) {
		gameListService.move(listId, dto.sourceIndex(), dto.destinationIndex());
		return ResponseEntity.noContent().build();
	}
}