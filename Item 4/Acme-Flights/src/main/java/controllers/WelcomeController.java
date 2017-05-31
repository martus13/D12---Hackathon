/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.ExchangeRateService;
import services.FlightService;
import domain.Banner;
import domain.ExchangeRate;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BannerService		bannerService;

	@Autowired
	private FlightService		flightService;

	@Autowired
	private ExchangeRateService	exchangeRateService;


	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		Banner banner;
		Collection<Object[]> flightsMostBooked;
		Collection<ExchangeRate> exchangeRates;

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		banner = this.bannerService.display();
		flightsMostBooked = this.flightService.findFlightsOfferAndSeasonNotCancelledMostBook();
		exchangeRates = this.exchangeRateService.findAll();

		result = new ModelAndView("welcome/index");
		result.addObject("moment", moment);
		result.addObject("banner", banner);
		result.addObject("flightsMostBooked", flightsMostBooked);
		result.addObject("exchangeRates", exchangeRates);

		return result;
	}
}
