package com.linkedin_learning.frank_moley.learning_spring_boot_2022.web_services;

import com.linkedin_learning.frank_moley.learning_spring_boot_2022.business.ReservationService;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.business.RoomReservation;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.data.Guest;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.data.Room;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebServicesController {
	private final DateUtils dateUtils;
	private final ReservationService reservationService;

	public WebServicesController(DateUtils dateUtils, ReservationService reservationService) {
		this.dateUtils = dateUtils;
		this.reservationService = reservationService;
	}

	@RequestMapping(path = "/reservations", method = RequestMethod.GET)
	public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateStr) {
		Date date = this.dateUtils.createDateFromDateString(dateStr);

		return this.reservationService.getRoomReservationsForDate(date);
	}

	@GetMapping("/guests")
	public List<Guest> getGuests() {
		return this.reservationService.getHotelGuests();
	}

	@GetMapping("/rooms")
	public List<Room> getRooms() {
		return this.reservationService.getRooms();
	}

	@PostMapping("/guests")
	@ResponseStatus(HttpStatus.CREATED)
	public void addGuest(@RequestBody Guest guest) {
		this.reservationService.addGuest(guest);
	}
}
