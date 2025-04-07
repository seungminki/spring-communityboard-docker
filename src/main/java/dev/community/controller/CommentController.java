package dev.community.controller;

import dev.community.entity.Board;
import dev.community.entity.Member;
import dev.community.service.CommentService;
import dev.community.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
class CommentController {

	private final CommentService commentService;

	@GetMapping("/all")
	List<Comment> allComments() {
		return commentService.getComments();
	}

	@GetMapping("/all/bymember")
	List<Comment> allCommentsByMember(@RequestBody Member member) {
		return commentService.getCommentsByMember(member.getId());
	}

	@GetMapping("/all/byboard")
	List<Comment> allCommentsByBoard(@RequestBody Board board) {
		return commentService.getCommentsByBoard(board.getId());
	}

	@GetMapping("/all/bymemberandboard")
	List<Comment> allCommentsByMemberAndBoard(@RequestBody Member member, Board board) {
		return commentService.getCommentsByMemberAndBoard(member.getId(), board.getId());
	}

	@GetMapping("/{id}")
	Comment getComment(@PathVariable Long id) {
		return commentService.getSingleComment(id);
	}

	@PostMapping("/create")
	Comment newComment(Principal principal, @RequestBody Board board, Comment comment) {
		return commentService.createComment(principal.getName(), board.getId(), comment);
	}


	@PutMapping("/update/{id}")
	Comment updateComment(Principal principal,  @PathVariable Long id, @RequestBody Comment newComment) {
		return commentService.updateComment(principal.getName(), id, newComment.getContent());
	}

	@DeleteMapping("/delete/{id}")
	void deleteComment(Principal principal, @PathVariable Long id) {
		commentService.deleteComment(principal.getName(), id);
	}

}
