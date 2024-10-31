# 일정 관리 시스템

### 프로젝트 구조
* config: JWT와 비밀번호 인코더 설정
* controller: 사용자, 일정, 댓글 리소스를 위한 REST 컨트롤러
* dto: 요청/응답 데이터 전송 객체
* entity: JPA 엔티티 (데이터베이스 테이블과 매핑)
* repository: 데이터베이스 상호작용을 위한 인터페이스
* service: 애플리케이션 기능을 위한 비즈니스 로직

### API 명세

사용자 API
* POST /api/users/register: 새 사용자 등록
* POST /api/users/login: 사용자 인증 및 JWT 토큰 발급

일정 API
* GET /api/schedules: 일정 목록 페이징 조회
* POST /api/schedules: 새 일정 생성 (JWT 필요)
* PUT /api/schedules/{id}: 일정 수정 (관리자 전용)
* DELETE /api/schedules/{id}: 일정 삭제 (관리자 전용)

댓글 API
* GET /api/comments: 특정 일정에 대한 모든 댓글 조회
* POST /api/comments: 일정에 댓글 추가
* PUT /api/comments/{id}: 댓글 수정
* DELETE /api/comments/{id}: 댓글 삭제

### ERD
![ERD](https://github.com/user-attachments/assets/82b7f166-ae2d-47bf-8291-de16e3028458)

