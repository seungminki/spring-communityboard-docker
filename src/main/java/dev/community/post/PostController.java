package dev.community.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PostController {

	private final PostRepository posts;

	public PostController(PostRepository postRepository) {
		this.posts = postRepository;
	}

	@GetMapping("/posts.html")
	public String showPostList(@RequestParam(defaultValue = "1") int page, Model model) {
		Posts posts = new Posts();
		Page<Post> paginated = findPaginated(page);
		posts.getPostList().addAll(paginated.toList());
		return addPaginationModel(page, paginated, model);

	}

	private String addPaginationModel(int page, Page<Post> paginated, Model model) {
		List<Post> listPosts = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listPosts", listPosts);
		return "posts/postList";
	}

	private Page<Post> findPaginated(int page) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return posts.findAll(pageable);
	}

	@GetMapping({ "/posts" })
	public @ResponseBody Posts showResourcesPostList() {
		Posts posts = new Posts();
		posts.getPostList().addAll(this.posts.findAll());
		return posts;
	}

}
