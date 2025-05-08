package dev.community.controller;

import dev.community.dto.*;
import dev.community.service.BoardService;
import dev.community.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

	private final LikeService likeService;
	private final BoardService boardService;

	@Operation(description = "특정 멤버가 좋아요 누른 글 목록 ")
	@GetMapping("/member")
	List<LikeResponseDto> LikesBoardByMember(@RequestBody @Valid MemberEmailRequestDto memberRequestDto) {
		return likeService.getMemberLikes(memberRequestDto.email());

	}

	@Operation(description = "특정 글의 좋아요 수")
	@GetMapping("")
	Long countLikes(@RequestBody @Valid BoardIdRequestDto boardIdRequestDto) {
		return likeService.countLikesByBoard(boardIdRequestDto.id());
	}

	@Operation(description = "좋아요 생성")
	@PostMapping("")
	LikeResponseDto boardLike(Principal principal, @RequestBody @Valid BoardIdRequestDto boardIdRequestDto) {
		return likeService.createLike(principal.getName(), boardIdRequestDto.id());
	}

	@Operation(description = "좋아요 삭제")
	@DeleteMapping("")
	void boardUnlike(Principal principal, @RequestBody @Valid BoardIdRequestDto boardIdRequestDto) {
		likeService.deleteLike(principal.getName(), boardIdRequestDto.id());
	}
}

