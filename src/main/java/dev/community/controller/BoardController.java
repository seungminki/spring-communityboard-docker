package dev.community.controller;

import dev.community.entity.Member;
import dev.community.service.BoardService;
import dev.community.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/all")
	List<Board> allBoards() {
		return boardService.getBoards();
	}

	@GetMapping("/all/bymember")
	List<Board> allBoardsByMember(@RequestBody Member member) {
		return boardService.getBoardsByMember(member.getId());
	}

	@GetMapping("/{id}")
	Board getBoard(@PathVariable Long id) {
		return boardService.getSingleBoard(id);
	}

	@PostMapping("/create")
	Board newBoard(Principal principal, @RequestBody Board newBoard) {
		return boardService.createBoard(principal.getName(), newBoard);
	}

	@PutMapping("/update/{id}")
	Board updateBoard(Principal principal, @PathVariable Long id, @RequestBody Board newBoard) {
		return boardService.updateBoard(principal.getName(), id, newBoard.getTitle(), newBoard.getContent());
	}

	@DeleteMapping("/delete/{id}")
	void deleteBoard(Principal principal, @PathVariable Long id) {
		boardService.deleteBoard(principal.getName(), id);
	}

}
