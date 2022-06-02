package com.linkedin_learning.frank_moley.learning_spring_boot_2022.business;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.linkedin_learning.frank_moley.learning_spring_boot_2022.data.Guest;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.data.GuestRepository;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.data.Reservation;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.data.ReservationRepository;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.data.Room;
import com.linkedin_learning.frank_moley.learning_spring_boot_2022.data.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
	// @Autowired
	// private RoomRepository roomRepository;
	private final RoomRepository roomRepository;

	// @Autowired
	// private GuestRepository guestRepository;
	private final GuestRepository guestRepository;

	// @Autowired
	// private ReservationRepository reservationRepository;
	private final ReservationRepository reservationRepository;

	@Autowired
	public ReservationService(
		RoomRepository roomRepository,
		GuestRepository guestRepository,
		ReservationRepository reservationRepository
	) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = reservationRepository;
	}

	public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository) {
		this.roomRepository = roomRepository;
		this.guestRepository = guestRepository;
		this.reservationRepository = null;
	}

	/*
	@Autowired
	public void setRoomRepository(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	@Autowired
	public void setGuestRepository(GuestRepository guestRepository) {
		this.guestRepository = guestRepository;
	}

	@Autowired
	public void setReservationRepository(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
	*/

	public List<RoomReservation> getRoomReservationsForDate(Date date) {
		Iterable<Room> rooms = this.roomRepository.findAll();

		Map<Long, RoomReservation> roomReservationMap = new HashMap();

		rooms.forEach(room -> {
			RoomReservation roomReservation = new RoomReservation();

			roomReservation.setRoomId(room.getId());
			roomReservation.setRoomName(room.getName());
			roomReservation.setRoomNumber(room.getRoomNumber());

			roomReservationMap.put(room.getId(), roomReservation);
		});

		Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));

		reservations.forEach(reservation -> {
			RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());

			roomReservation.setDate(date);

			Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();

			roomReservation.setFirstName(guest.getFirstName());
			roomReservation.setLastName(guest.getLastName());
			roomReservation.setGuestId(guest.getId());
		});

		List<RoomReservation> roomReservations = new ArrayList<>();

		for (Long id : roomReservationMap.keySet()) {
			roomReservations.add(roomReservationMap.get(id));
		}

		roomReservations.sort(new Comparator<RoomReservation>() {
			@Override
			public int compare(RoomReservation roomReservation1, RoomReservation roomReservation2) {
				if (roomReservation1.getRoomName().equals(roomReservation2.getRoomName())) {
					return roomReservation1.getRoomNumber().compareTo(roomReservation2.getRoomNumber());
				}

				return roomReservation1.getRoomName().compareTo(roomReservation2.getRoomName());
			}
		});

		return roomReservations;
	}

	public List<Room> getRooms() {
		Iterable<Room> rooms = this.roomRepository.findAll();

		List<Room> roomList = new ArrayList<>();

		rooms.forEach(room -> roomList.add(room));

		roomList.sort(new Comparator<Room>() {
			@Override
			public int compare(Room room1, Room room2) {
				return room1.getRoomNumber().compareTo(room2.getRoomNumber());
			}
		});

		return roomList;
	}

	public List<Guest> getHotelGuests() {
		Iterable<Guest> repoGuests = this.guestRepository.findAll();

		List<Guest> guestList = new ArrayList<>();

		repoGuests.forEach(guest -> guestList.add(guest));

		guestList.sort(new Comparator<Guest>() {
			@Override
			public int compare(Guest guest1, Guest guest2) {
				if(guest1.getLastName().equals(guest2.getLastName())) {
					return guest1.getFirstName().compareTo(guest2.getFirstName());
				}

				return guest1.getLastName().compareTo(guest2.getLastName());
			}
		});

		return guestList;
	}

	public void addGuest(Guest guest) {
		if(guest == null) {
			throw new RuntimeException("guest can't be null");
		}

		this.guestRepository.save(guest);
	}
}

