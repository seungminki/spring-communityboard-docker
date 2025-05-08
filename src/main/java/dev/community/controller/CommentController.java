package dev.community.controller;

import dev.community.dto.board.BoardIdRequestDto;
import dev.community.dto.comment.CommentRequestDto;
import dev.community.dto.comment.CommentResponseDto;
import dev.community.dto.member.MemberEmailRequestDto;
import dev.community.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
class CommentController {

	private final CommentService commentService;

	@Operation(description = "모든 댓글 조회")
	@GetMapping("/all")
	List<CommentResponseDto> allComments() {
		return commentService.getComments();
	}

	@Operation(description = "특정 멤버가 쓴 게시글 조회")
	@GetMapping("/member")
	List<CommentResponseDto> allCommentsByMember(@RequestBody MemberEmailRequestDto memberRequestDto) {
		return commentService.getCommentsByMember(memberRequestDto.email());
	}

	@Operation(description = "특정 게시글에 달린 댓글 조회")
	@GetMapping("/board")
	List<CommentResponseDto> allCommentsByBoard(@RequestBody BoardIdRequestDto boardIdRequestDto) {
		return commentService.getCommentsByBoard(boardIdRequestDto.id());
	}

	@Operation(description = "1개의 댓글 조회")
	@GetMapping("/{id}")
	CommentResponseDto getComment(@PathVariable Long id) {
		return commentService.getSingleComment(id);
	}

	@Operation(description = "댓글 생성")
	@PostMapping("")
	CommentResponseDto newComment(Principal principal, @RequestBody @Valid BoardIdRequestDto boardIdRequestDto, CommentRequestDto commentRequestDto) {
		return commentService.createComment(principal.getName(), boardIdRequestDto.id(), commentRequestDto);
	}

	@Operation(description = "댓글 수정")
	@PutMapping("/{id}")
	CommentResponseDto updateComment(Principal principal,  @PathVariable Long id, @RequestBody @Valid CommentRequestDto commentRequestDto) {
		return commentService.updateComment(principal.getName(), id, commentRequestDto);
	}

	@Operation(description = "댓글 삭제")
	@DeleteMapping("/{id}")
	void deleteComment(Principal principal, @PathVariable Long id) {
		commentService.deleteComment(principal.getName(), id);
	}

}
