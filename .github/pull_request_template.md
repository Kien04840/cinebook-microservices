## Mô tả ngắn
<!-- Viết ngắn gọn: mục tiêu của PR và thay đổi chính. Ví dụ: "feat(movie): add search endpoint by title" -->

## Loại thay đổi
- [ ] feat (tính năng mới)
- [ ] fix (sửa lỗi)
- [ ] docs (tài liệu)
- [ ] chore (công việc hạ tầng/ci)
- [ ] test (thêm/sửa test)
- [ ] ci (CI/CD)

## Checklist trước khi merge
- [ ] Build cục bộ pass: `mvn -B -T1C clean verify`
- [ ] Đã chạy unit tests liên quan (ví dụ `mvn -pl booking-service test`)
- [ ] README / HOWTO_RUN cập nhật (nếu thay đổi cách chạy)
- [ ] Không commit secrets / credentials
- [ ] Nếu thay đổi schema DB, đã thêm migration / note

## Hướng dẫn kiểm thử (How to test)
1. Checkout branch: `git checkout <branch>`
2. Ví dụ chạy module: `mvn -pl movie-service spring-boot:run`
3. Gọi endpoint X để verify: `GET http://localhost:8081/api/movies?q=...`

## Ghi chú / Issue liên quan
- Issue: #<số nếu có>
- Các note khác (nêu rõ migration, breaking changes, v.v.)
