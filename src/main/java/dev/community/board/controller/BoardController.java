package dev.community.board.controller;

import dev.community.board.controller.dto.BoardCreateRequest;
import dev.community.board.controller.dto.BoardUpdateRequest;
import dev.community.board.service.dto.BoardServiceResponse;
import dev.community.board.service.BoardCommandService;
import dev.community.board.service.BoardQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardController {

	private final BoardCommandService boardCommandService;
	private final BoardQueryService boardQueryService;

	@GetMapping
	public List<BoardServiceResponse> getBoards() { return boardQueryService.findAllBoards(); }

	@GetMapping("/{id}")
	public BoardServiceResponse getBoard(@PathVariable Long id) {
		return boardQueryService.getBoard(id);
	}

	@PostMapping
	public Long createBoard(Principal principal, @RequestBody @Valid BoardCreateRequest request) {
		return boardCommandService.create(principal, request);
	}


	@PutMapping("/{id}")
	public Long updateBoard(Principal principal, @PathVariable Long id, @RequestBody @Valid BoardUpdateRequest request) {
		return boardCommandService.update(principal, id, request);
	}

	@DeleteMapping("/{id}")
	public void deleteBoard(Principal principal, @PathVariable Long id) { boardCommandService.delete(principal, id); }
}

