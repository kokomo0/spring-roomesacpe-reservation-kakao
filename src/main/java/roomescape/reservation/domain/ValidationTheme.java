package roomescape.reservation.domain;

import roomescape.reservation.repository.ThemeRepository;

public class ValidationTheme {
    private final ThemeRepository themeRepository;

    public ValidationTheme(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public boolean exists(Long id) {
        return themeRepository.get(id) != null;
    }

    public boolean exists(String name) {
        return themeRepository.get(name) != null;
    }
}
