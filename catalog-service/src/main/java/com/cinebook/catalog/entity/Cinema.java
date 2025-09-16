package com.cinebook.catalog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "cinemas")
@Getter
@Setter
public class Cinema {

    @Id
    @Column(columnDefinition = "CHAR(36)")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    /**
     * Một rạp (Cinema) có nhiều phòng chiếu (Hall).
     * 'mappedBy = "cinema"' chỉ ra rằng mối quan hệ này được quản lý bởi trường 'cinema' trong entity Hall.
     * CascadeType.ALL: Các thao tác (persist, remove, refresh, merge, detach) trên Cinema sẽ được áp dụng cho các Hall liên quan.
     * orphanRemoval = true: Nếu một Hall bị xóa khỏi danh sách 'halls' của Cinema, nó cũng sẽ bị xóa khỏi database.
     */
    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Hall> halls;
}
