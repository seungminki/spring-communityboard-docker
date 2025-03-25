# spring-communityboard-docker

### getting started in local
```
$ ./mvnw package
$ java -jar target/*.jar
```

### used docker-compose
```
$ docker-compose up
```

### Requirements

**회원**
* 닉네임 변경 
* 아이디, 비밀번호를 사용한 로그인, 로그 아웃
* 회원이 작성한 글 뷰
* 회원이 작성한 댓글 뷰
* 회원이 좋아요 표시한 글 뷰

**글**
* 페이징 정렬 -> 제목, 생성일, 글 번호, 조회수
* 좋아요 수
* 조회 수
* 작성, 수정, 삭제 기능

**댓글**
* 작성, 수정, 삭제 기능

### Exception
* 글 작성은 로그인 시 가능
* 글 수정은 해당 글을 작성한 회원만 가능
* 글 삭제는 해당 글을 작성한 회원만 가능
* 닉네임과 아이디는 겹칠 수 없음


### Tree

### Task

### TO-BE

### Environment
