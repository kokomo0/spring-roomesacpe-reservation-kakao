package roomescape.reservation.service;

import org.springframework.stereotype.Service;
import roomescape.reservation.domain.ValidationReservation;
import roomescape.reservation.domain.ValidationTheme;
import roomescape.reservation.exception.BusinessException;
import roomescape.reservation.exception.ErrorCode;
import roomescape.reservation.repository.ReservationRepository;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.dto.ReservationDto;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ValidationReservation validationReservation;
    private final ValidationTheme validationTheme;

    public ReservationService(ReservationRepository reservationRepository, ValidationReservation validationReservation, ValidationTheme validationTheme) {
        this.reservationRepository = reservationRepository;
        this.validationReservation = validationReservation;
        this.validationTheme = validationTheme;
    }

    public Long addReservation(ReservationDto reservationDto) {
        if (validationReservation.exists(reservationDto.getDate(), reservationDto.getTime()))
            throw new BusinessException(ErrorCode.DUPLICATE_RESERVATION);
        if(!validationTheme.exists(reservationDto.getThemeId()))
            throw new BusinessException(ErrorCode.NOT_FOUND_THEME);
        return reservationRepository.add(new Reservation(reservationDto)).getId();
    }

    public Reservation getReservation(Long id) {
        if (!validationReservation.exists(id))
            throw new BusinessException(ErrorCode.NOT_FOUND_RESERVATION);
        return reservationRepository.get(id);
    }

    public void removeReservation(Long id) {
        if (!validationReservation.exists(id))
            throw new BusinessException(ErrorCode.NOT_FOUND_RESERVATION);
        reservationRepository.remove(id);
    }
}
