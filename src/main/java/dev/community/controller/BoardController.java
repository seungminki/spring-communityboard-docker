package dev.community.controller;

import dev.community.dto.BoardRequestDto;
import dev.community.dto.BoardResponseDto;
import dev.community.dto.MemberRequestDto;
import dev.community.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

	@Operation(description = "모든 게시글 조회")
	@GetMapping("")
	List<BoardResponseDto> allBoards() {
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

	@Operation(description = "특정 멤버가 쓴 게시글 조회")
	@GetMapping("")
	List<BoardResponseDto> allBoardsByMember(@RequestBody @Valid MemberRequestDto memberRequestDto) {
		return boardService.getBoardsByMember(memberRequestDto.getEmail());
	}

	@Operation(description = "1개의 게시글 조회")
	@GetMapping("/{id}")
	BoardResponseDto getBoard(@PathVariable @Valid Long id) {
		return boardService.getSingleBoard(id);
	}

	@Operation(description = "게시글 생성")
	@PostMapping("")
	BoardResponseDto newBoard(Principal principal, @RequestBody @Valid BoardRequestDto boardRequestDto) {
		return boardService.createBoard(principal.getName(), boardRequestDto);
	}

	@Operation(description = "게시글 수정")
	@PutMapping("/{id}")
	BoardResponseDto updateBoard(Principal principal, @PathVariable Long id, @RequestBody @Valid BoardRequestDto boardRequestDto) {
		return boardService.updateBoard(principal.getName(), id, boardRequestDto);
	}

	@Operation(description = "게시글 삭제")
	@DeleteMapping("/{id}")
	void deleteBoard(Principal principal, @PathVariable Long id) {
		boardService.deleteBoard(principal.getName(), id);
	}

}
