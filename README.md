# 도서 예약 시스템 API
spring boot CRUD REST API example with JPA, MySQL, Gradle

### 버전
- java 11
- spring boot 2.6.3
- gradle 7.4
- MySQL 8.0.28

### 데이터
- Model
  - Author
    - id
    - firstName
    - lastName
  - Book
    - id
    - name
    - isbn
  - Member
    - id
    - firstName
    - lastName
    - status (active/inactive)
  - Lend
    - id
    - startOn
    - dueOn
    - status (available/unavailable)
    
### 구현할 기능 목록
- 데이터간의 관계
  - 하나의 저자는 여러 책을 집필할 수 있다.
    - 공저의 경우는 배제한다.
  - 같은 책이 여러 권 존재한다. 따라서 하나의 책을 여러명이 대출할 수 있다. (개수 미정, 추후 추가 예정)
  - 하나의 회원은 여러 책을 대출할 수 있다.
- 서비스
  - id로 책을 조회한다.
  - id로 책을 삭제한다.
  - 저장된 모든 책을 조회한다.
  - 책을 생성한다.
  - 회원을 생성한다.
  - 저자를 생성한다.
  - 도서를 대출한다.
