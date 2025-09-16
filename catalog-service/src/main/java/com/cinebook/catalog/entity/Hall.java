package com.cinebook.catalog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "halls")
@Getter
@Setter
public class Hall {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    /**
     * Nhiều phòng chiếu (Hall) thuộc về một rạp (Cinema).
     * @JoinColumn chỉ định cột khóa ngoại trong bảng 'halls'.
     * nullable = false: Bắt buộc mỗi Hall phải thuộc về một Cinema.
     * referencedColumnName = "id": Khóa ngoại này tham chiếu đến cột 'id' của bảng 'cinemas'.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id", nullable = false, referencedColumnName = "id")
    private Cinema cinema;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "seat_capacity", nullable = false)
    private int seatCapacity;

    @Column(name = "seat_map", columnDefinition = "json")
    private String seatMap; // Lưu trữ dưới dạng chuỗi JSON

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    // Lưu ý: Mối quan hệ OneToMany với Show không được định nghĩa ở đây
    // để tránh vòng lặp và giữ cho Hall là một entity độc lập hơn.
    // Mối quan hệ sẽ được quản lý từ phía Show (ManyToOne).
}
