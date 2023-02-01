package roomescape.reservation.domain;

import roomescape.reservation.repository.ReservationRepository;

public class ValidationReservation {
    private final ReservationRepository reservationRepository;

    public ValidationReservation(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public boolean exists(String date, String time) {
        return reservationRepository.get(date, time) != null;
    }

    public boolean exists(Long id) {
        return reservationRepository.get(id) != null;
    }
}
