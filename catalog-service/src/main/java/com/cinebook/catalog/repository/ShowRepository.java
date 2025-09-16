package com.cinebook.catalog.repository;

import com.cinebook.catalog.entity.Hall;
import com.cinebook.catalog.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, String> {
    // Ví dụ: Tìm tất cả các suất chiếu của một phim trong một khoảng thời gian
    List<Show> findByMovieIdAndStartTimeBetween(String movieId, LocalDateTime start, LocalDateTime end);

    // Tìm tất cả các suất chiếu của một phim, trong một danh sách các phòng chiếu và trong một khoảng thời gian
    @EntityGraph(attributePaths = {"hall", "hall.cinema"})
    List<Show> findByMovieIdAndHallInAndStartTimeBetween(String movieId, List<Hall> halls, LocalDateTime start, LocalDateTime end);

    // Ví dụ: Tìm tất cả các suất chiếu trong một phòng chiếu cụ thể
    List<Show> findByHallId(String hallId);
}
