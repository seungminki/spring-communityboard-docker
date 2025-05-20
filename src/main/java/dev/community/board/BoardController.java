package dev.community.board;

import dev.community.BoardResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardController {

	private final BoardService boardService;

	@GetMapping
	public List<Board> getBoards() {
		return boardService.getAllBoards();
	}

	@PostMapping
	public void createBoard(@RequestBody @Valid BoardCreateRequestDto board) {
		boardService.saveBoard(board);
	}

	@GetMapping("/{id}")
	public Board getBoard(@PathVariable Long id) {
		return boardService.getBoardById(id);
	}

	@PutMapping("/{id}")
	public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody @Valid BoardCreateRequestDto board) {
		return null;
	}

	@DeleteMapping("/{id}")
	public void deleteBoard(@PathVariable Long id) {	}
}

