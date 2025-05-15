package dev.community;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardController {

	private final BoardService boardService;

	@GetMapping("")
	public List<Board> getBoards() {
		return boardService.getAllBoards();
	}

	@GetMapping("/{id}")
	public Board getBoard(@PathVariable Long id) {
		return boardService.getBoardById(id);
	}

	@PostMapping("")
	public Board createBoard(@RequestBody @Valid BoardCreateRequestDto board) {
		return boardService.saveBoard(board);
	}

}
