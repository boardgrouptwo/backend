## 📝 프로젝트 시작하기



## 👉🏻 API 키 추가

```
## 예시 REACT_APP_KAKAO_ADMIN_KEY
KAKAO_PAY_KEY=여기에 KEY값 입력

## 예시 front의 REACT_APP_KAKAO_API_KEY와 동일
KAKAO_API_KEY=여기에 KEY값 입력
```



#### application-oauth.properties 추가

```
spring.security.oauth2.client.registration.google.client-id=여기에 KEY값 입력
spring.security.oauth2.client.registration.google.client-secret=여기에 KEY값 입력
```



## 📖 프로젝트 기간

- 2023.04.10 ~ 2023.05.02 



## 👉🏻 프로젝트 소개 

- 요양사와 보호자간의 정보 교환과 보호자들이 믿고 맡길 수 있는 신뢰감을 주는 요양원 사이트 구축



### 패키지 구조

```java
src/main/java
 - config
 - controller
 - dao
 - dto
 - exception
 - mapper
 - service

src/main/resources
 - mybatis // query 
 - application.propreties
 - application-oauth.properites
 - logback-spring.xml
```



## ⚙ 기술 스택

✔ **Front-end**

<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=React&logoColor=white"><img src="https://img.shields.io/badge/React Router-CA4245?style=for-the-badge&logo=React Router&logoColor=white"><img src="https://img.shields.io/badge/Redux-764ABC?style=for-the-badge&logo=Redux&logoColor=white"><img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"><img src="https://img.shields.io/badge/axios-5A29E4?style=for-the-badge&logo=Axios&logoColor=white"><img src="https://img.shields.io/badge/BootStrap-7952B3?style=for-the-badge&logo=BootStrap&logoColor=white">



✔ **Back-end**

<img src="https://img.shields.io/badge/Spring Boot-E6db33f?style=for-the-badge&logo=Spring Boot&logoColor=white"><img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white"><img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"><img src="https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=MariaDB&logoColor=white">



✔ **Dev tools**

<img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=for-the-badge&logo=Visual Studio Code&logoColor=white"><img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=Intellij IDEA&logoColor=white"><img src="https://img.shields.io/badge/Eclipse IDE-2C2255?style=for-the-badge&logo=Eclipse IDE&logoColor=white"><img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"><img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"><img src="https://img.shields.io/badge/KaKaoTalk-FFCD00?style=for-the-badge&logo=KaKaoTalk&logoColor=white">

<img src="https://img.shields.io/badge/Google-4285F4?style=for-the-badge&logo=Google&logoColor=white"><img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white">







## **✔ 팀원 역할 분배**

| Role | Name   | Github                         |
| ---- | ------ | ------------------------------ |
| 팀장 | 박성민 | https://github.com/tjdals3862  |
| 팀원 | 강동현 | https://github.com/welsper53   |
| 팀원 | 박소연 | https://github.com/Soyeonthdus |
| 팀원 | 송우진 | https://github.com/woojinyy    |
| 팀원 | 이현승 | https://github.com/luckyhs38   |





## **✔ 프로젝트 산출물**

- [요구사항정의서](https://docs.google.com/spreadsheets/d/15IHNjJvoLBEFn5JaiGhAUxsiFc2S6mDKLLArp42Pev8/edit#gid=1045911684)

- [UI정의서](https://docs.google.com/presentation/d/1taMQIQVIIUbKciAosMXd9PU7EmB6ztK9/edit#slide=id.g22ca028f6ed_0_0)
- [Git](https://github.com/boardgrouptwo)

- [DB 설계서](https://docs.google.com/spreadsheets/d/1MjiErZFr97BfX107vSUmxzGKAdPtiInAGx1_9dI55kc/edit#gid=0)

- [ERD](https://www.erdcloud.com/d/dQa5NzDnWLhvQEjX9)

- [프로젝트 공정표](https://docs.google.com/spreadsheets/d/132oxmY693t43PDR4D6TqRY8AHEloH4ws/edit?rtpof=true&sd=true#gid=1155388715)

- [형상관리 보고서](https://docs.google.com/presentation/d/1KVlzXW3wtp_-9wZxbRzmCjCHtRw_1MkfP4BTQhySae8/edit#slide=id.g22cc164f8c3_0_0)



## **✔ 시연 GIF**

- **로그인**

![image-20230328095640042](/readme/login.gif)



- **회원가입**

![image-20230328095640042](/readme/register.gif)



- **내원자 추가**

![image-20230328095640042](/readme/elder_register.gif)



- **chatbot**

![image-20230328095640042](/readme/chatbot.gif)



- **식단표**

![image-20230328095640042](/readme/meal.gif)



- **공지사항**

![image-20230328095640042](/readme/notice.gif)



- **qna 게시판**

![image-20230328095640042](/readme/qna.gif)



- **mypage 추가**

![image-20230328095640042](/readme/mypage_add.gif)





- **mypage 결제리스트**

![image-20230328095640042](/readme/mypage_paylist.gif)



- **mypage 수정**

![image-20230328095640042](/readme/mypage_update.gif)



- **mypage 면회신청**

![image-20230328095640042](/readme/mypage_visit.gif)



- **자원봉사관리**

![image-20230328095640042](/readme/serviceAdmin.gif)



- **자원봉사신청**

![image-20230328095640042](/readme/serviceForm.gif)



- **자원봉사 리뷰게시판**

![image-20230328095640042](/readme/serviceReview.gif)



- **상품페이지**

![image-20230328095640042](/readme/shop.gif)



- **상품주문**

![image-20230328095640042](/readme/shop_order.gif)





- **후원관리 엑셀다운**

![image-20230328095640042](/readme/spon_excel.gif)



- **후원관리 검색**

![image-20230328095640042](/readme/spon_search.gif)



- **후원하기**

![image-20230328095640042](/readme/sponsorform.gif)



- **후원리스트**

![image-20230328095640042](/readme/sponsorList.gif)