# Q-Words 게임 시작 가이드 🎯

Q-Words는 Wordle과 유사한 6글자 단어 추측 게임입니다. 플레이어는 5번의 시도로 숨겨진 단어를 맞춰야 합니다!

## 🛠️ 사용된 프레임워크

### 백엔드
- **Spring Boot 2.7.2** - 웹 애플리케이션 프레임워크
- **Spring MVC** - 웹 계층 아키텍처
- **Thymeleaf** - 서버 사이드 템플릿 엔진
- **Lombok** - 코드 생성 자동화
- **Log4j2** - 로깅 프레임워크

### 프론트엔드
- **HTML5/CSS3** - 기본 웹 기술
- **JavaScript** - 클라이언트 사이드 로직
- **Bootstrap** (추정) - UI 스타일링

### 개발 도구
- **Maven** - 빌드 및 의존성 관리
- **JUnit 4** - 단위 테스트
- **AWS SDK** - 클라우드 서비스 연동

## 🏗️ 애플리케이션 구조

```
src/main/java/com/sample/qwords/
├── controller/          # 웹 요청 처리
│   ├── GameController.java
│   ├── HomeController.java
│   └── GlobalExceptionHandler.java
├── model/              # 데이터 모델
│   ├── Word.java
│   └── GameStatus.java
├── service/            # 비즈니스 로직
│   └── WordSelectionService.java
├── repository/         # 데이터 접근
│   └── WordList.java
├── utils/              # 유틸리티
│   ├── Math.java
│   └── RandomUtils.java
└── QWordsApplication.java  # 메인 애플리케이션

src/main/resources/
├── templates/          # Thymeleaf 템플릿
│   ├── home.html
│   ├── game.html
│   └── error.html
├── static/             # 정적 리소스
│   ├── js/game.js
│   └── Q.png
├── words.txt           # 단어 사전
└── application.properties
```

## 🎮 주요 클래스와 기능

### 핵심 게임 로직
- **`Word.java`** - 추측 검증 및 피드백 생성 (`+`, `?`, `X`)
- **`GameController.java`** - 게임 흐름 제어 및 상태 관리
- **`WordSelectionService.java`** - 랜덤 단어 선택

### 지원 클래스
- **`GameStatus.java`** - 게임 상태 열거형 (INPROGRESS, SUCCESS, FAILED)
- **`WordList.java`** - 단어 사전 관리
- **`HomeController.java`** - 홈페이지 라우팅

### 게임 규칙
- **5번의 시도**로 **6글자 단어** 추측
- 피드백 시스템:
  - `+`: 정확한 글자, 정확한 위치
  - `?`: 단어에 있지만 잘못된 위치  
  - `X`: 단어에 없는 글자

## 🚀 앱 작업 시작하기

### 1. 사전 요구사항
```bash
# Java 8+ 설치 확인
java -version

# Maven 설치 확인
mvn --version
```

### 2. 프로젝트 클론 및 빌드
```bash
# 프로젝트 디렉토리로 이동
cd Q-Words

# 의존성 설치 및 컴파일
mvn clean compile

# 테스트 실행
mvn test

# 애플리케이션 패키징
mvn clean package
```

### 3. 애플리케이션 실행
```bash
# Spring Boot 애플리케이션 실행
mvn spring-boot:run

# 또는 JAR 파일 실행
java -jar target/QWordsService-0.0.1.jar
```

### 4. 게임 접속
- 브라우저에서 `http://localhost:8080` 접속
- 사용자명 입력 후 게임 시작
- 6글자 단어 추측 시작!

### 5. 개발 환경 설정
```bash
# 로컬 프로파일로 실행
mvn spring-boot:run -Dspring-boot.run.profiles=local

# 프록시 서버 실행 (선택사항)
cd proxy
npm install
node server.js
```

## 💡 애플리케이션 개선 아이디어

### 🎯 게임 기능 향상
- [ ] **난이도 선택** - 4글자, 5글자, 7글자 단어 옵션
- [ ] **힌트 시스템** - 글자 수 또는 카테고리 힌트
- [ ] **타이머 모드** - 시간 제한 추가
- [ ] **멀티플레이어** - 실시간 대전 기능
- [ ] **일일 도전** - 매일 새로운 단어

### 📊 사용자 경험
- [ ] **점수 시스템** - 시도 횟수 기반 점수 계산
- [ ] **통계 대시보드** - 승률, 평균 시도 횟수
- [ ] **리더보드** - 전체 사용자 순위
- [ ] **게임 기록** - 이전 게임 결과 저장
- [ ] **소셜 공유** - 결과 공유 기능

### 🎨 UI/UX 개선
- [ ] **반응형 디자인** - 모바일 최적화
- [ ] **다크 모드** - 테마 선택 옵션
- [ ] **애니메이션** - 부드러운 전환 효과
- [ ] **키보드 지원** - 가상 키보드 UI
- [ ] **접근성** - 스크린 리더 지원

### 🔧 기술적 개선
- [ ] **Spring Boot 3.x 업그레이드** - 최신 버전 적용
- [ ] **데이터베이스 연동** - 사용자 데이터 영구 저장
- [ ] **Redis 캐싱** - 세션 관리 개선
- [ ] **REST API** - 모바일 앱 지원
- [ ] **Docker 컨테이너화** - 배포 간소화

### 🌐 확장 기능
- [ ] **다국어 지원** - 영어, 한국어 단어 게임
- [ ] **단어 카테고리** - 동물, 음식, 과학 등
- [ ] **AI 힌트** - 머신러닝 기반 도움말
- [ ] **음성 인식** - 말로 단어 입력
- [ ] **단어 학습 모드** - 교육적 기능 추가

### 📈 모니터링 & 분석
- [ ] **게임 분석** - 사용자 행동 패턴 분석
- [ ] **성능 모니터링** - APM 도구 연동
- [ ] **A/B 테스트** - 기능 실험 프레임워크
- [ ] **에러 추적** - 실시간 오류 모니터링

## 🤝 기여하기

1. 이슈 생성 또는 기존 이슈 확인
2. 기능 브랜치 생성: `git checkout -b feature/새기능`
3. 변경사항 커밋: `git commit -m '새 기능 추가'`
4. 브랜치 푸시: `git push origin feature/새기능`
5. Pull Request 생성

---

**즐거운 단어 게임을 즐기세요!** 🎯📚✨