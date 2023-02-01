package roomescape.reservation.service;

import org.springframework.stereotype.Service;
import roomescape.reservation.domain.ValidationTheme;
import roomescape.reservation.exception.BusinessException;
import roomescape.reservation.exception.ErrorCode;
import roomescape.reservation.repository.ThemeRepository;
import roomescape.reservation.domain.Theme;
import roomescape.reservation.dto.ThemeDto;

import java.util.List;

@Service
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final ValidationTheme validationTheme;

    public ThemeService(ThemeRepository themeRepository, ValidationTheme validationTheme) {
        this.themeRepository = themeRepository;
        this.validationTheme = validationTheme;
    }

    public Long addTheme(ThemeDto themeDto) {
        if (validationTheme.exists(themeDto.getName()))
            throw new BusinessException(ErrorCode.DUPLICATE_THEME);
        return themeRepository.add(new Theme(themeDto)).getId();
    }

    public List<Theme> getAllTheme() {
        return themeRepository.get();
    }

    public void removeTheme(Long id) {
        if (!validationTheme.exists(id))
            throw new BusinessException(ErrorCode.NOT_FOUND_THEME);
        themeRepository.remove(id);
    }
}
