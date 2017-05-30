
package controllers.actor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import controllers.AbstractController;
import domain.Actor;
import forms.ActorForm;

@Controller
@RequestMapping("/profile/actor")
public class ProfileActorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public ProfileActorController() {
		super();
	}

	// Displaying -------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Actor actor;

		actor = this.actorService.findByPrincipal();

		result = new ModelAndView("profile/display");
		result.addObject("requestURI", "profile/actor/display.do");
		result.addObject("actor", actor);
		result.addObject("isManager", this.actorService.checkAuthority(actor, "MANAGER"));

		return result;
	}

	// Register ---------------------------------------------------------------		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		ActorForm actorForm;

		actorForm = this.actorService.desreconstructProfile();

		result = this.createEditModelAndView(actorForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm actorForm, final BindingResult binding) {

		ModelAndView result;
		Actor actor;

		if (binding.hasErrors()) {
			System.out.println(binding.toString());
			result = this.createEditModelAndView(actorForm);

		} else
			try {
				actor = this.actorService.reconstructProfile(actorForm);
				this.actorService.save(actor);
				result = new ModelAndView("redirect:display.do");

			} catch (final Throwable oops) {
				System.out.println(oops);

				result = this.createEditModelAndView(actorForm, "profile.commit.error");

			}
		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm, final String message) {
		final ModelAndView result;

		result = new ModelAndView("profile/edit");
		result.addObject("editProfileForm", actorForm);
		result.addObject("actorForm", "editProfileForm");
		result.addObject("isManager", false);
		result.addObject("requestURI", "profile/actor/edit.do");
		result.addObject("message", message);

		return result;
	}
}
