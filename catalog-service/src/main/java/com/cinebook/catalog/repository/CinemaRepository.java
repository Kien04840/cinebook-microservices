package com.cinebook.catalog.repository;

import com.cinebook.catalog.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, String> {
    // Tìm tất cả các rạp phim tại một địa điểm (thành phố)
    // Sử dụng Containing để tìm kiếm linh hoạt hơn (LIKE '%location%')
    List<Cinema> findByLocationContaining(String location);
}
