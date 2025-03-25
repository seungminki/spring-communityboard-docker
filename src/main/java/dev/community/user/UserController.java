package dev.community.user;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
class UserController {

	private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/createOrUpdateUserForm";

	private final UserRepository users;

	public UserController(UserRepository userRepository) {
		this.users = userRepository;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/users/signup")
	public String signup(User user) {
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/users/signup")
	public String signup(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Validation Errors:");
			result.getAllErrors().forEach(error -> {
				System.out.println(error.getDefaultMessage());
			});
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.users.save(user);
			return "redirect:/users/login";
		}
	}

	@GetMapping("/users/login")
	public String logIn(Map<String, Object> model) {
		model.put("users", new User());
		return "users/loginUser";
	}

}
