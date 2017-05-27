
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService	userService;


	// Constructors -----------------------------------------------------------

	public UserController() {
		super();
	}

	// Register ---------------------------------------------------------------		
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		UserForm userForm;
		User user;

		user = this.userService.create();
		userForm = this.userService.desreconstructCreate(user);

		result = this.createEditModelAndView(userForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final UserForm userForm, final BindingResult binding) {

		ModelAndView result;
		User user;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(userForm);

		} else
			try {
				user = this.userService.reconstructCreate(userForm);
				this.userService.save(user);
				result = new ModelAndView("redirect:../welcome/index.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(userForm, "user.commit.error");

			}
		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final UserForm userForm) {
		ModelAndView result;

		result = this.createEditModelAndView(userForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final UserForm userForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("profile/registerUser");
		result.addObject("userForm", userForm);
		result.addObject("registerForm", "userForm");
		result.addObject("isManager", false);
		result.addObject("requestURI", "user/register.do");
		result.addObject("message", message);

		return result;
	}
}
