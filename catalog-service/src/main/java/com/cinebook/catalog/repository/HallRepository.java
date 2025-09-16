package com.cinebook.catalog.repository;

import com.cinebook.catalog.entity.Cinema;
import com.cinebook.catalog.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, String> {
    // Ví dụ: Tìm tất cả các phòng chiếu thuộc về một rạp phim cụ thể
    List<Hall> findByCinemaId(String cinemaId);

    // Tìm tất cả các phòng chiếu thuộc về một danh sách các rạp phim
    List<Hall> findByCinemaIn(List<Cinema> cinemas);
}
