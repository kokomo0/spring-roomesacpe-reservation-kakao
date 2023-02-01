package roomescape.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import roomescape.reservation.domain.ValidationReservation;
import roomescape.reservation.domain.ValidationTheme;
import roomescape.reservation.repository.ReservationRepository;
import roomescape.reservation.repository.ThemeRepository;

@Configuration
public class ReservationConfig {
    @Bean
    public ValidationTheme validationTheme(ThemeRepository themeRepository) {
        return new ValidationTheme(themeRepository);
    }

    @Bean
    public ValidationReservation validationReservation(ReservationRepository reservationRepository) {
        return new ValidationReservation(reservationRepository);
    }
}
