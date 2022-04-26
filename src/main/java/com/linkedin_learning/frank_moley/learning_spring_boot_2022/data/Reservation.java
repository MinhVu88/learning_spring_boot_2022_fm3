package com.linkedin_learning.frank_moley.learning_spring_boot_2022.data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "RESERVATION")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESERVATION_ID")
	private long reservationId;

	@Column(name = "ROOM_ID")
	private long roomId;

	@Column(name = "GUEST_ID")
	private long guestId;

	@Column(name = "RES_DATE")
	private Date reservationDate;

	public long getReservationId() {
		return reservationId;
	}

	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public long getGuestId() {
		return guestId;
	}

	public void setGuestId(long guestId) {
		this.guestId = guestId;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	@Override
	public String toString() {
		return "Reservation id: " + reservationId +
					 " | room id: " + roomId +
					 " | guest id: " + guestId +
					 " | reservation date: " + reservationDate;
	}
}
