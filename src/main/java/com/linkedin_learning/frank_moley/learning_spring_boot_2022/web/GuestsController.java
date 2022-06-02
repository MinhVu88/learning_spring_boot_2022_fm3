package com.linkedin_learning.frank_moley.learning_spring_boot_2022.web;

import com.linkedin_learning.frank_moley.learning_spring_boot_2022.business.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guests")
public class GuestsController {
	private final ReservationService reservationService;

	public GuestsController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getGuests(Model model) {
		model.addAttribute("guests", this.reservationService.getHotelGuests());

		return "hotelGuests";
	}
}
