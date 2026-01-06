# 🚀 Spring Boot CRUD & Redis Project

스프링 부트를 기반으로 한 기본적인 **게시판(CRUD)** 프로젝트입니다.
단순한 DB 저장 기능을 넘어, **Redis를 활용한 실시간 조회수 최적화**와 **페이징 처리**를 학습하기 위해 구축되었습니다.

---

## 🛠 Tech Stack

- **Framework**: Spring Boot 3.x
- **Language**: Java 17
- **Database**: H2 (In-memory)
- **Caching**: Redis
- **ORM**: Spring Data JPA
- **Build Tool**: Gradle
- **Lombok**: Boilerplate 코드 최적화

---

## ✨ Key Features

### 1. 게시글 관리 (CRUD)
- 게시글 작성, 상세 조회, 수정, 삭제 기능 제공
- **Paging & Sorting**: `Pageable`을 활용한 효율적인 목록 조회

### 2. Redis 기반 조회수 시스템
- 게시글 상세 조회 시 DB에 즉시 반영하는 대신 **Redis의 `increment` 함수**를 사용
- 빈번한 쓰기 작업으로부터 DB 부하를 줄이고 실시간 응답 속도 개선

### 3. Entity & DTO 분리
- **Dirty Checking**: JPA 변경 감지를 통한 데이터 업데이트
- **DTO(PostResponse)**: 계층 간 데이터 전송 시 엔티티를 직접 노출하지 않고 필요한 필드만 포함

---

## ⚙️ Configuration & Testing

### H2 Console
서버 실행 후 웹 브라우저에서 실시간 데이터를 확인할 수 있습니다.
- **URL**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa` / **Password**: (empty)

### Redis Settings
조회수 데이터는 Redis 서버의 `6379` 포트를 사용합니다.
- **Key Format**: `post:{id}`

---

## 📝 API Endpoints

| Method | Endpoint       | Description |
|:---:|:---------------|:---|
| **GET** | `/all`         | 전체 게시글 목록 조회 (Paging) |
| **GET** | `/{id}`        | 게시글 상세 조회 (조회수 증가 포함) |
| **POST** | `/create`      | 게시글 신규 등록 |
| **PUT** | `/update/{id}` | 게시글 정보 수정 |
| **DELETE** | `/delete/{id}` | 게시글 삭제 |

---

## 💡 Troubleshooting
- **Lombok Setup**: 인텔리제이에서 `Annotation Processing` 활성화 필요
- **Safe Directory**: Git Dubious Ownership 발생 시 `git config` 예외 등록 처리
- **Refspec Match**: 최초 푸시 전 커밋(`git commit`) 내역 생성 필수