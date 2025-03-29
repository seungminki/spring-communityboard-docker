package dev.community.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
class CommentController {

	private final CommentService service;

	@GetMapping("/comments")
	List<Comment> allComments() {
		return service.getComments();
	}

	@PostMapping("/comments")
	Comment newComment(@RequestBody Comment newComment) {
		return service.createComment(newComment);
	}

	@GetMapping("/comments/{id}")
	Comment getComment(@PathVariable Long id) {
		return service.getSingleComment(id);
	}

	@PutMapping("/comments/{id}")
	Comment updateComment(@PathVariable Long id, @RequestBody Comment newComment) {
		return service.updateComment(id, newComment);
	}

	@DeleteMapping("/comments/{id}")
	void deleteComment(@PathVariable Long id) {
		service.deleteComment(id);
	}

}
