package dev.community.controller;

import dev.community.dto.BoardResponseDto;
import dev.community.service.BoardService;
import dev.community.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

	private final LikeService likeService;
	private final BoardService boardService;

	@PostMapping("/boards/{boardId}")
	BoardResponseDto boardLike(Principal principal, @PathVariable Long boardId) {
		likeService.createLike(principal.getName(), boardId);
		return boardService.getBoardInfo(boardId);
	}

	@DeleteMapping("/boards/{boardId}")
	BoardResponseDto boardUnlike(Principal principal, @PathVariable Long boardId) {
		likeService.deleteLike(principal.getName(), boardId);
		return boardService.getBoardInfo(boardId);
	}

	@GetMapping("/boards/{boardId}/count")
	Long countLikes(@PathVariable Long boardId) {
		return likeService.countLikes(boardId);
	}
}

