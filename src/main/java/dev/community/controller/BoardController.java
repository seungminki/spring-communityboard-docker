package dev.community.controller;

import dev.community.entity.Member;
import dev.community.service.BoardService;
import dev.community.entity.Board;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
	@Operation(description = "모든 게시글 조회")
	@GetMapping("")
		return boardService.getBoards();
	}

	@Operation(description = "게시글 페이징 조회") // boards?page=0&size=10&sort=createdAt,desc
	@GetMapping("")
	Page<BoardResponseDto> getBoardList(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "createdAt") String sortBy,
		@RequestParam(defaultValue = "false") boolean asc) {
		return boardService.getBoards(page, size, sortBy, asc);
	}

	@GetMapping("/all/bymember")
	List<Board> allBoardsByMember(@RequestBody Member member) {
	@Operation(description = "특정 멤버가 쓴 게시글 조회")
	@GetMapping("")
		return boardService.getBoardsByMember(member.getId());
	}

	@Operation(description = "1개의 게시글 조회")
	@GetMapping("/{id}")
	Board getBoard(@PathVariable Long id) {
		return boardService.getSingleBoard(id);
	}

	@PostMapping("/create")
	@Operation(description = "게시글 생성")
	@PostMapping("")
	Board newBoard(Principal principal, @RequestBody Board newBoard) {
		return boardService.createBoard(principal.getName(), newBoard);
	}

	@PutMapping("/update/{id}")
	@Operation(description = "게시글 수정")
	@PutMapping("/{id}")
	Board updateBoard(Principal principal, @PathVariable Long id, @RequestBody Board newBoard) {
		return boardService.updateBoard(principal.getName(), id, newBoard.getTitle(), newBoard.getContent());
	}

	@DeleteMapping("/delete/{id}")
	@Operation(description = "게시글 삭제")
	void deleteBoard(Principal principal, @PathVariable Long id) {
		boardService.deleteBoard(principal.getName(), id);
	}

}
