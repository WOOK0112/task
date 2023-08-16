# pre-onboarding 사전과제
#### 지원자: 김동욱
#### 개발언어: Java & Spring Boot
#### API 명세서:
#### 배포링크: 
---
## 💡 애플리케이션 실행 플로우
📌 엔드포인트 호출의 상세내용은 API명세서 참고바람
1. IDE를 이용하여 애플리케이션을 실행
2. API개발 테스트툴(ex. POSTMAN)을 사용하여 애플리케이션 서버와 통신
3. 회원가입 진행
4. 가입한 계정으로 로그인
5. 로그인 후 게시글 작성, 조회, 수정, 삭제 실행 가능
   - 게시글을 작성한 계정에 한하여  게시글 작성, 수정, 삭제 가능
   - 게시글 단일 조회, 목록 조회는 로그인 없이 가능
---
## 🛢 데이터베이스 테이블 구조
![image](https://github.com/WOOK0112/wanted-pre-onboarding-backend/assets/124886494/55953134-4710-48fb-871c-156cbd7c95c0)

------------

## 💼 API 동작 데모 영상
#### 📌회원가입
![회원가입](https://github.com/WOOK0112/wanted-pre-onboarding-backend/assets/124886494/92ce6141-7174-40bc-aaf4-d0f11402b359)
#### 📌로그인
![로그인](https://github.com/WOOK0112/wanted-pre-onboarding-backend/assets/124886494/8f060c6f-04d9-4bc5-8397-ec7dd83fcc40)
#### 📌게시글 작성
![게시글 작성](https://github.com/WOOK0112/wanted-pre-onboarding-backend/assets/124886494/8d82f193-5464-4a7c-9f8d-4d5665417080)
#### 📌게시글 수정
![게시글 수정](https://github.com/WOOK0112/wanted-pre-onboarding-backend/assets/124886494/4551bfaf-ffa5-4bc3-8162-f0d103665044)
#### 📌게시글 삭제
![게시글 삭제](https://github.com/WOOK0112/wanted-pre-onboarding-backend/assets/124886494/9192829f-3046-4ed4-99ed-08b8d0fd53c5)
#### 📌게시글 단일조회
![게시글 단일조회](https://github.com/WOOK0112/wanted-pre-onboarding-backend/assets/124886494/d4c574e8-d8ae-4932-a215-b317faa2264f)
#### 📌게시글 목록조회
![게시글 목록조회](https://github.com/WOOK0112/wanted-pre-onboarding-backend/assets/124886494/234d9787-266d-455e-9576-df9502808e52)

---
## 📖 구현 방법 (구현 방법 및 이유에 대한 간략한 설명)
#### 테이블 설계
 - 회원기반으로 운영되는 게시판관리 애플리케이션인 만큼 테이블은 회원정보를 관리하는 'member'와 게시글 정보를 관리하는 'note'의 두 개로 설계
 - 한 회원이 여러 게시글을 작성할 수 있고 게시글은 작성자의 정보를 가지고 있어야 하므로, 'member'와 'note'의 연관관계는 일(member) 대 다(note)로 설정
 - 게시글은 작성자의 정보를 가지고 있어야 하므로 'note'테이블은 'member_id'를 외래키로 가지도록 설계
#### 기능 기반 패키지 구조
 - 재사용성, 확장성, 코드 유지보수, 재사용성 등의 장점을 위해 본 프로젝트 개발 시 기능 기반 패키지 구조를 사용
#### 인증 및 보안
 - 요구사항에서 사용자 인증을 위한 기술로 JWT를 요구하여, 이에 따라 SpringSecurity JWT를 사용하여 인증 및 보안 구현
 - AccessToken과 RefreshToken을 사용하도록 설계하였으나, 현재 구현 단계에서 RefreshToken의 유효성은 없음, 추후 ResfreshToken도 사용하도록 인증 및 보안 기능을 보완할 예정
#### JPA
 - '간단한 데이터베이스 조작', '객체 지향적 프로그래밍', '코드 간결성', '유지보수 용이성' 등의 장점이 있어 ORM으로 JPA를 활용하여 애플리케이션을 구현
 - 요구사항에서 RDBMS로 MYSQL을 사용할 것을 요구하였는데, 개발생산성 측면에서 JPA와의 긍정적 상호작용이 있을 것으로 기대
#### 예외처리
 - 원활한 예외처리를 위해 @RestControllerAdvice 애너테이션을 활용하여 전역 예외처리 로직 모듈화
 - 커스텀 예외 및 특정 예외에 대한 핸들링, 예외가 발생했을 때 클라이언트와의 원활한 소통과 서버사이드에서의 빠른 피드백 등의 효과를 기대

