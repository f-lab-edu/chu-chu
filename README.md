# chu-chu
### 이건 어때요? 저건 어때요? 세상 모든 것에 대한 추천을 하는 커뮤니티

## 프로젝트 목표
* 커뮤니티 서비스의 기능들을 구현함으로써 Back-End 구조를 학습
* 대규모 트래픽에도 안정적인 서비스 운영
* 나쁜 코드에 대해 지속적으로 리팩토링
* Github, Slack을 통한 활발한 소통을 바탕으로 효율적인 협업을 추구

## 프로젝트 서버 구조도

---

## 프로젝트 중점사항
* 인증/인가를 위한 JWT
* Redis Cache를 이용한 조회 성능 개선
* Jenkins를 이용한 CI/CD 자동화
* Mysql에서 인덱스 설정  쿼리 튜닝
* 네이버에서 개발한 오픈소스 플랫폼인 nGrinder를 이용해 성능테스트를 진행

## 프로젝트 정보

### 프로젝트 환경
* Java 17
* Gradle 7.4
* Springboot 2.7.4
* Jenkins
* Mysql 5.6
* Redis
* Naver Cloud Platform

### Code Convention
* [Google code Style](https://google.github.io/styleguide/javaguide.html) 준수

### Git-Flow 브랜치 전략
![initial](https://github.com/f-lab-edu/chu-chu/blob/develop/images/git_flow.png)
* master : 제품으로 출시될 수 있는 브랜치를 의미
* develop : feature에서 리뷰완료한 브랜치를 Merge
* feature : 기능을 개발하는 브랜치
* release : 이번 출시 버전을 준비하는 브랜치
* hotfix : 출시 버전에서 발생한 버그를 수정하는 브랜치

### CI/CD
* Jenkins을 이용하여 pr시 자동 Build 및 Test 적용

### 기술적 issue 해결과정
* [#1] Jenkins를 이용하여 CICD환경 구축하기

  https://velog.io/@korea3611/Spring-Boot-Jenkins-이용하여-CICD-구축하기
  https://velog.io/@korea3611/Spring-Boot-Jenkins-이용하여-CICD-구축하기2     

* [#2] Jwt 토큰 인증 방식을 통한 회원 가입 기능 만들기
  
  
 
* [#3] HashTag 기능 만들기

  https://velog.io/@korea3611/Spring-Boot-해시태그-기능을-가지고-있는-게시판-만들어보기


* [#4] 대댓글 기능 만들기
 
  https://velog.io/@korea3611/Spring-Boot-대댓글-기능-만들기


* [#5] 게시글 좋아요 기능 만들기

  https://velog.io/@korea3611/Spring-Boot게시글-좋아요-기능-만들기


* [#6] 게시글 조회수 증가, 중복방지 기능만들기

  https://velog.io/@korea3611/Spring-Boot게시글-조회수-증가-중복방지-기능-만들기


* [#7] 좋아요 수 증가 - 분산락을 이용하여 동시성 제어하기

  https://velog.io/@korea3611/Spring-boot-좋아요수-증가-분산락을-이용하여-동시성-제어하기-redis활용하기


* [#8] nGrinder를 이용하여 성능테스트를 하는 과정 -Index생성을 통한 성능개선

  https://velog.io/@korea3611/nGrinder를-이용하여-성능테스트를-하는-과정1-Index생성을-통한-성능개선

* [#9] Redis Cache를 적용으로 읽기 작업 성능 개선


---

## 화면 설계

---

## DB ERD
![initial](https://github.com/f-lab-edu/chu-chu/blob/develop/images/chchu_erd.png)

