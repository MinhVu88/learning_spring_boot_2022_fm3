package com.linkedin_learning.frank_moley.learning_spring_boot_2022.web;

import com.linkedin_learning.frank_moley.learning_spring_boot_2022.business.ReservationService;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.business.RoomReservation;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class RoomReservationsController {
	private final DateUtils dateUtils;
	private final ReservationService reservationService;

	public RoomReservationsController(DateUtils dateUtils, ReservationService reservationService) {
		this.dateUtils = dateUtils;
		this.reservationService = reservationService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getReservations(
		@RequestParam(value = "date", required = false) String dateStr,
		Model model
	) {
		Date date = this.dateUtils.createDateFromDateString(dateStr);

		List<RoomReservation> roomReservations = this.reservationService.getRoomReservationsForDate(date);

		model.addAttribute("roomReservations", roomReservations);

		return "roomReservations";
	}
}
