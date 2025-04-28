package dev.community.controller;

import dev.community.entity.Board;
import dev.community.entity.Member;
import dev.community.service.CommentService;
import dev.community.entity.Comment;
import io.swagger.v3.oas.annotations.Operation;
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
	@GetMapping("")
	List<Comment> allComments() {
		return commentService.getComments();
	}

	@Operation(description = "특정 멤버가 쓴 게시글 조회")
	@GetMapping("")
	List<Comment> allCommentsByMember(@RequestBody Member member) {
		return commentService.getCommentsByMember(member.getId());
	}

	@Operation(description = "특정 게시글에 달린 댓글 조회")
	@GetMapping("")
	List<Comment> allCommentsByBoard(@RequestBody Board board) {
		return commentService.getCommentsByBoard(board.getId());
	}

//	@Operation(description = "특정 멤버, 특정 게시글 조회")
//	@GetMapping("/all/bymemberandboard")
//	List<Comment> allCommentsByMemberAndBoard(@RequestBody Member member, Board board) {
//		return commentService.getCommentsByMemberAndBoard(member.getId(), board.getId());
//	}

	@Operation(description = "1개의 댓글 조회")
	@GetMapping("/{id}")
	Comment getComment(@PathVariable Long id) {
		return commentService.getSingleComment(id);
	}

	@Operation(description = "댓글 생성")
	@PostMapping("")
	Comment newComment(Principal principal, @RequestBody Board board, Comment comment) {
		return commentService.createComment(principal.getName(), board.getId(), comment);
	}

	@Operation(description = "댓글 수정")
	@PutMapping("/{id}")
	Comment updateComment(Principal principal,  @PathVariable Long id, @RequestBody Comment newComment) {
		return commentService.updateComment(principal.getName(), id, newComment.getContent());
	}

	@Operation(description = "댓글 삭제")
	@DeleteMapping("/{id}")
	void deleteComment(Principal principal, @PathVariable Long id) {
		commentService.deleteComment(principal.getName(), id);
	}

}
