package com.cinebook.catalog.service;

import com.cinebook.catalog.dto.MovieInfoDTO;
import com.cinebook.catalog.dto.MovieNotFoundException;
import com.cinebook.catalog.dto.MovieShowsResponseDTO;
import com.cinebook.catalog.dto.ShowInfoDTO;
import com.cinebook.catalog.dto.ShowsByCinemaDTO;
import com.cinebook.catalog.entity.Cinema;
import com.cinebook.catalog.entity.Hall;
import com.cinebook.catalog.entity.Movie;
import com.cinebook.catalog.entity.Show;
import com.cinebook.catalog.repository.CinemaRepository;
import com.cinebook.catalog.repository.MovieRepository;
import com.cinebook.catalog.repository.HallRepository;
import com.cinebook.catalog.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final HallRepository hallRepository;
    private final ShowRepository showRepository;

    @Transactional(readOnly = true)
    public MovieShowsResponseDTO findShowsByMovieAndLocation(String movieId, String city, LocalDate date) {
        // 1. Query MongoDB để lấy thông tin phim
        Movie movie = movieRepository.findByMovieId(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with id: " + movieId));

        // 2. Query MySQL để lấy danh sách rạp và phòng chiếu
        List<Cinema> cinemasInCity = cinemaRepository.findByLocationContaining(city);
        if (cinemasInCity.isEmpty()) {
            // Trả về thông tin phim nhưng không có suất chiếu
            return buildResponseWithNoShows(movie);
        }

        // Thay vì duyệt qua collection LAZY, hãy truy vấn trực tiếp từ repository
        List<Hall> hallsInCity = hallRepository.findByCinemaIn(cinemasInCity);
        if (hallsInCity.isEmpty()) {
            return buildResponseWithNoShows(movie);
        }

        // 3. Query MySQL để lấy các suất chiếu phù hợp
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        List<Show> shows = showRepository.findByMovieIdAndHallInAndStartTimeBetween(movieId, hallsInCity, startOfDay, endOfDay);

        if (shows.isEmpty()) {
            return buildResponseWithNoShows(movie);
        }

        // 4. Tổng hợp dữ liệu
        // Nhóm theo cinemaId (String) thay vì đối tượng Cinema để đảm bảo tính duy nhất của key
        Map<String, List<Show>> showsGroupedByCinemaId = shows.stream()
                .collect(Collectors.groupingBy(show -> show.getHall().getCinema().getId()));

        List<ShowsByCinemaDTO> showsByCinemaDTOs = showsGroupedByCinemaId.entrySet().stream()
                .map(entry -> {
                    Cinema cinema = entry.getValue().get(0).getHall().getCinema(); // Lấy thông tin cinema từ show đầu tiên
                    List<ShowInfoDTO> showInfos = entry.getValue().stream()
                                .map(this::mapToShowInfoDTO)
                                .collect(Collectors.toList());
                    return ShowsByCinemaDTO.builder()
                            .cinemaId(cinema.getId())
                            .cinemaName(cinema.getName())
                            .cinemaLocation(cinema.getLocation())
                            .shows(showInfos)
                            .build();
                })
                .collect(Collectors.toList());

        return buildResponseWithNoShows(movie).toBuilder().showsByCinema(showsByCinemaDTOs).build();
    }

    private MovieShowsResponseDTO buildResponseWithNoShows(Movie movie) {
        MovieInfoDTO movieInfo = MovieInfoDTO.builder()
                .id(movie.getMovieId()).title(movie.getTitle()).posterUrl(movie.getPosterUrl())
                .durationMinutes(movie.getDurationMinutes()).build();

        return MovieShowsResponseDTO.builder().movie(movieInfo).showsByCinema(List.of()).build();
    }

    private ShowInfoDTO mapToShowInfoDTO(Show show) {
        return ShowInfoDTO.builder()
                .showId(show.getId())
                .hallName(show.getHall().getName())
                .startTime(show.getStartTime())
                .endTime(show.getEndTime())
                .language(show.getLanguage())
                .subtitle(show.getSubtitle())
                .basePrice(show.getBasePrice())
                .build();
    }
}
