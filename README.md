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
  - 책
    - id로 책을 조회한다.
    - isbn으로 책을 조회한다.
    - id로 책을 삭제한다.
    - 저장된 모든 책을 조회한다.
    - 책을 생성한다.
  - 회원
    - id로 회원을 조회한다.
    - 저장된 모든 회원을 조회한다.
    - 회원을 생성한다.
    - id로 회원을 삭제한다.
    - 회원 정보를 업데이트한다.
  - 저자
    - 저자를 생성한다.
  - 대출
    - 도서를 대출한다.
    - id로 대출 정보를 조회한다.
    - 저장된 모든 대출 정보를 조회한다.

### API 테스트 
<details>
<summary>Postman 자세히 보기</summary>
 
<br>
  
- 저자 추가하기 기능 : /library/author (POST)

![image](https://user-images.githubusercontent.com/70425484/155282594-bd769feb-fa33-4b75-b4cd-cdb66b36da86.png)
  
- 책 추가하기 기능 : /library/book (POST)

![image](https://user-images.githubusercontent.com/70425484/155282750-35bee8ed-b327-4ef4-9481-29e7563853e2.png)
  
- 책 조회하기 기능 (isbn) : /library/book?isbn= (GET)

![image](https://user-images.githubusercontent.com/70425484/155282777-f6588840-6be3-477f-b217-b391b258cba4.png)

- 모든 책 조회하기 기능 /library/book/all (GET)

![image](https://user-images.githubusercontent.com/70425484/155282833-f8504c5d-5e7e-4ff0-9eb0-05ecbdbf1a45.png)
  
- id로 책 조회하기 기능 /library/book/{bookId} (GET)

![image](https://user-images.githubusercontent.com/70425484/155282865-293d864d-62fa-4a35-b893-d4edca2fe33e.png)
  
- 멤버 추가하기 기능 /library/member (POST)

![image](https://user-images.githubusercontent.com/70425484/155282880-2f10caa3-ff40-4cb5-a2e3-699b5d64a73f.png)

- 모든 멤버 조회하기 기능 /library/member/all (GET)

![image](https://user-images.githubusercontent.com/70425484/155282902-96c1d2a6-5974-4bec-a6af-187f17c78f76.png)

- id로 멤버 조회하기 기능 /library/member/{memberId} (GET)

![image](https://user-images.githubusercontent.com/70425484/155282915-073daa01-7454-4be1-aa20-1aa403d9837d.png)

- id로 멤버 이름 변경하기 기능 /library/member/update/{memberId} (PATCH)

![image](https://user-images.githubusercontent.com/70425484/155282930-0a983387-2192-4e63-b2f6-234cfb56ebfd.png)

- 책 대출하기 기능 /library/lend (POST)

![image](https://user-images.githubusercontent.com/70425484/155282950-a9cb466a-450f-41e8-b84c-2807229e9cb1.png)

- id로 책 대출 조회하기 기능 /library/lend/{lendId} (GET)

![image](https://user-images.githubusercontent.com/70425484/155282963-38ac977a-42b8-473f-82a6-b13ff32109ac.png)

- 모든 책 대출 조회하기 기능 /library/lend/all (GET)

![image](https://user-images.githubusercontent.com/70425484/155282986-c7c0df3e-26e2-4d2b-991b-c5b1002ad613.png)

</details>
