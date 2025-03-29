package dev.community.board;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;

	@GetMapping("/boards")
	List<Board> allBoards() {
		return service.getBoards();
	}

	@PostMapping("/boards")
	Board newBoard(@RequestBody Board newBoard) {
		return service.createBoard(newBoard);
	}

	@GetMapping("/boards/{id}")
	Board getBoard(@PathVariable Long id) {
		return service.getSingleBoard(id);
	}

	@PutMapping("/boards/{id}")
	Board updateBoard(@PathVariable Long id, @RequestBody Board newBoard) {
		return service.updateBoard(id, newBoard);
	}

	@DeleteMapping("/boards/{id}")
	void deleteBoard(@PathVariable Long id) {
		service.deleteBoard(id);
	}

}
