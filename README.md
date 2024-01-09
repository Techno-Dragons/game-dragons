# GameDragons 프로젝트 

<hr>

## 1. 프로젝트 개요
<hr>


## 2. 사용한 기술 스택
<hr>


![사용기술스택](사용기술스택.png)




## 3. 프로젝트 구조 
<hr>

### ERD

![ERD](ERD.png)

### 파일 구조 

```
📦 game-dragons
├─ .github
│  ├─ ISSUE_TEMPLATE
│  │  └─ 이슈-생성-템플릿.md
│  ├─ pull_request_template.md
│  └─ workflows
│     ├─ workflow-client-1.yml
│     └─ workflow-server-1.yml
├─ .gitignore
├─ .gitmessage.txt
├─ Dockerfile
├─ build.gradle
├─ demo-repository
│  ├─ .eslintignore
│  ├─ .eslintrc.cjs
│  ├─ .gitignore
│  ├─ .npmrc
│  ├─ .prettierignore
│  ├─ .prettierrc
│  ├─ Dockerfile
│  ├─ README.md
│  ├─ package-lock.json
│  ├─ package.json
│  ├─ playwright.config.ts
│  ├─ postcss.config.js
│  ├─ src
│  │  ├─ app.css
│  │  ├─ app.html
│  │  ├─ app.js
│  │  ├─ lib
│  │  │  ├─ images
│  │  │  │  ├─ github.svg
│  │  │  │  └─ svelte-logo.svg
│  │  │  └─ login_stores.js
│  │  └─ routes
│  │     ├─ +layout.svelte
│  │     ├─ +page.svelte
│  │     ├─ article
│  │     │  ├─ +page.svelte
│  │     │  ├─ [id]
│  │     │  │  └─ +page.svelte
│  │     │  └─ write
│  │     │     └─ +page.svelte
│  │     ├─ footer.svelte
│  │     ├─ header.svelte
│  │     └─ member
│  │        ├─ login
│  │        │  └─ +page.svelte
│  │        ├─ login_check.js
│  │        ├─ logout
│  │        │  └─ +page.svelte
│  │        ├─ mypage
│  │        │  └─ +page.svelte
│  │        └─ signup
│  │           └─ +page.svelte
│  ├─ static
│  │  ├─ favicon.png
│  │  └─ robots.txt
│  ├─ svelte.config.js
│  ├─ tailwind.config.js
│  ├─ tests
│  │  └─ test.ts
│  ├─ tsconfig.json
│  └─ vite.config.ts
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ package-lock.json
├─ package.json
├─ settings.gradle
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ example
   │  │        └─ techit7
   │  │           ├─ Techit7Application.java
   │  │           ├─ article
   │  │           │  ├─ controller
   │  │           │  │  ├─ ArticleController.java
   │  │           │  │  ├─ ArticleControllerRest.java
   │  │           │  │  ├─ ArticleRestController.java
   │  │           │  │  └─ ControllerAdvice.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ ArticleDetailResponseDto.java
   │  │           │  │  ├─ ArticleRequestDto.java
   │  │           │  │  ├─ ArticleResponseDto.java
   │  │           │  │  ├─ ImageRequestDto.java
   │  │           │  │  └─ ImageResponseDto.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ Article.java
   │  │           │  │  └─ Image.java
   │  │           │  ├─ errormessage
   │  │           │  │  └─ ErrorMessage.java
   │  │           │  ├─ exception
   │  │           │  │  ├─ BaseException.java
   │  │           │  │  ├─ BaseExceptionType.java
   │  │           │  │  ├─ ExceptionController.java
   │  │           │  │  ├─ article_exception
   │  │           │  │  │  ├─ ArticleException.java
   │  │           │  │  │  └─ ArticleExceptionType.java
   │  │           │  │  └─ image_exception
   │  │           │  │     ├─ ImageException.java
   │  │           │  │     └─ ImageExceptionType.java
   │  │           │  ├─ repository
   │  │           │  │  ├─ ArticleRepository.java
   │  │           │  │  └─ ImageRepository.java
   │  │           │  └─ service
   │  │           │     ├─ ArticleService.java
   │  │           │     ├─ ArticleServiceImpl.java
   │  │           │     ├─ ArticleServiceImpl_bak.java
   │  │           │     └─ ImageService.java
   │  │           ├─ comment
   │  │           │  ├─ controller
   │  │           │  │  └─ CommentRestController.java
   │  │           │  ├─ dto
   │  │           │  │  ├─ CommentRequestDto.java
   │  │           │  │  └─ CommentResponseDto.java
   │  │           │  ├─ entity
   │  │           │  │  └─ Comment.java
   │  │           │  ├─ repository
   │  │           │  │  └─ CommentRepository.java
   │  │           │  └─ service
   │  │           │     ├─ CommentRestService.java
   │  │           │     └─ CommentRestServiceImpl.java
   │  │           ├─ global
   │  │           │  ├─ config
   │  │           │  │  ├─ ApiSecurityConfig.java
   │  │           │  │  ├─ JwtAuthenticationFilter.java
   │  │           │  │  ├─ JwtProperties.java
   │  │           │  │  ├─ JwtUtil.java
   │  │           │  │  ├─ SecurityConfig.java
   │  │           │  │  ├─ SecurityUser.java
   │  │           │  │  └─ WebConfig.java
   │  │           │  ├─ entity
   │  │           │  │  └─ BaseEntity.java
   │  │           │  ├─ exception
   │  │           │  │  └─ DuplicateException.java
   │  │           │  ├─ response
   │  │           │  │  └─ GlobalResponse.java
   │  │           │  └─ util
   │  │           │     ├─ FileStore.java
   │  │           │     └─ InitData.java
   │  │           └─ user
   │  │              ├─ controller
   │  │              │  └─ MemberRestController.java
   │  │              ├─ dto
   │  │              │  ├─ LoginRequestDto.java
   │  │              │  ├─ LoginResponseDto.java
   │  │              │  ├─ MypageRequsetDto.java
   │  │              │  ├─ MypageResponseDto.java
   │  │              │  └─ UserCreateRequestDto.java
   │  │              ├─ entity
   │  │              │  ├─ Member.java
   │  │              │  └─ UserRole.java
   │  │              ├─ repository
   │  │              │  └─ MemberRepository.java
   │  │              └─ service
   │  │                 ├─ MemberRestService.java
   │  │                 └─ MemberRestServiceImpl.java
   │  └─ resources
   │     ├─ application-dev.yml
   │     ├─ application-prod.yml
   │     ├─ application-test.yml
   │     ├─ application.yml
   │     ├─ schema.sql
   │     ├─ static
   │     │  ├─ bootstrap.min.css
   │     │  ├─ bootstrap.min.js
   │     │  └─ style.css
   │     └─ templates
   │        ├─ article
   │        │  ├─ article_detail.html
   │        │  ├─ article_form.html
   │        │  ├─ article_modify_form.html
   │        │  ├─ articles.html
   │        │  └─ form_errors.html
   │        ├─ global
   │        │  ├─ index.html
   │        │  └─ layout.html
   │        └─ user
   │           ├─ layout.html
   │           ├─ login_form.html
   │           ├─ mypage_form.html
   │           └─ signup_form.html
   └─ test
      └─ java
         └─ com
            └─ example
               └─ techit7
                  └─ Techit7ApplicationTests.java
```

## 4. 기능
<hr>

### 구현된 기능 


## 5. 배포 
<hr>



