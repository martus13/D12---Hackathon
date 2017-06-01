
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
		UserForm actorForm;
		User user;

		user = this.userService.create();
		actorForm = this.userService.desreconstructCreate(user);

		result = this.createEditModelAndView(actorForm);

		return result;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final UserForm actorForm, final BindingResult binding) {

		ModelAndView result;
		User user;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(actorForm);

		} else
			try {
				user = this.userService.reconstructCreate(actorForm);
				this.userService.save(user);
				result = new ModelAndView("redirect:/security/login.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(actorForm, "profile.commit.error");

			}
		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final UserForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final UserForm userForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("profile/registerUser");
		result.addObject("userForm", userForm);
		result.addObject("actorForm", "userForm");
		result.addObject("isManager", false);
		result.addObject("requestURI", "user/register.do");
		result.addObject("message", message);

		return result;
	}
}
