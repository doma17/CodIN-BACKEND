# codin-backend

![428320201-f84fba5c-a561-4a45-a585-8520d885e7e7](https://github.com/user-attachments/assets/fb8123a8-5a4e-411d-9992-51414e572255)
![428320227-35a2c2ea-4d05-4f47-85e9-38a9b177ab5b](https://github.com/user-attachments/assets/2721b453-65e1-4342-8cc7-70aa97fc6e11)
![428320239-b06c1792-be61-479f-bdb9-050ef84af51a](https://github.com/user-attachments/assets/72b91fe4-354d-4cf1-9fe0-8b7b5a494f24)
![428320250-5b4513d7-4f24-4818-b7f1-3b1690c4d7f1](https://github.com/user-attachments/assets/ef70ab14-77ef-45ce-98b0-7b306002aee3)
![428320253-3551bf4a-4c88-4524-acd1-c2481868c75d](https://github.com/user-attachments/assets/7d99fe55-1e73-43b6-aa11-46d49b56f30c)
![428320257-fee6b0b6-9178-4f9d-8ca1-f0b51cc8ad8e](https://github.com/user-attachments/assets/43708e78-a7be-4cd3-a542-6e510f711af6)
![428320258-161cd849-f392-4082-a384-113e08da0a95](https://github.com/user-attachments/assets/5cfda62a-4353-4e4e-8027-869bd1690765)
![428320261-c073839f-3805-4b87-b2f7-3b27b225c893](https://github.com/user-attachments/assets/0c23982d-228a-4599-be7e-b8f51f8551e8)

## 목차
- [codin-backend](#codin-backend)
  - [목차](#목차)
  - [프로젝트-개요](#프로젝트-개요)
  - [기술 스택](#기술-스택)
  - [주요 기능](#주요-기능)
  - [프로젝트 구조](#프로젝트-구조)
  - [주요 기여 내용](#주요-기여-내용)
    - [2. 알림 서비스 개발 (SC-64)](#2-알림-서비스-개발-sc-64)
    - [3. 사용자 관리 기능 (SC-56)](#3-사용자-관리-기능-sc-56)
    - [4. 시스템 개선 및 최적화](#4-시스템-개선-및-최적화)
    - [5. 서버 환경 마이그레이션](#5-서버-환경-마이그레이션)
    - [6. 모니터링 시스템 구축](#6-모니터링-시스템-구축)
    - [7. CI/CD 파이프라인 구축](#7-cicd-파이프라인-구축)

## 프로젝트 개요

[프로젝트 설명](https://github.com/CodIN-INU) / [메인 페이지](https://codin.inu.ac.kr/login) / [Figma](https://www.figma.com/design/Yd7fxwf1Y0LL03i6arejFt/CodIN?node-id=1806-19&t=RCzqo4PNunBsrZSI-1)

<img width="929" alt="428320368-91e06d3f-db49-4d00-854c-a2d9d6f0c8b0" src="https://github.com/user-attachments/assets/8b5f94bf-1e2a-4b17-b91e-20ce50c05fbd" />

## 기술 스택
- Java 17
- Spring Boot 3.3.5
- Spring Security
- MongoDB
- Redis
- AWS S3
- WebSocket
- JWT
- Firebase Cloud Messaging (FCM)
- OAuth2

## 주요 기능
- 사용자 인증 및 권한 관리 (JWT, OAuth2)
- 실시간 통신 (WebSocket)
- 파일 저장 및 관리 (AWS S3)
- 이메일 서비스
- API 문서화 (Swagger)
- 요청 제한 (Rate Limiting - Bucket4j)
- 외부 서비스 연동 (Feign Client)

## 프로젝트 구조
```
src/main/java/inu/codin/codin/
├── common/     # 공통 유틸리티 및 설정
│   ├── config/         # 애플리케이션 설정
│   ├── exception/      # 공통 예외 처리
│   ├── ratelimit/      # 요청 제한 관련
│   ├── response/       # 공통 응답 형식
│   ├── security/       # 보안 관련 설정
│   └── util/           # 유틸리티 클래스
├── domain/     # 도메인 모델 및 비즈니스 로직
│   ├── chat/           # 채팅 관련 기능
│   ├── email/          # 이메일 서비스
│   ├── info/           # 정보 관리
│   ├── notification/   # 알림 서비스
│   ├── post/           # 게시판 기능
│   └── user/           # 사용자 관리
└── infra/      # 인프라 관련 코드
    ├── fcm/            # FCM 설정
    ├── redis/          # Redis 설정
    └── s3/             # AWS S3 설정

```

## 주요 기여 내용

### 2. 알림 서비스 개발 (SC-64)
- Firebase Cloud Messaging(FCM) 통합
  - 토큰 기반 및 토픽 기반 알림 전송
  - 알림 우선순위 및 이미지 첨부 기능
- 알림 관리 시스템
  - 알림 설정 및 관리 기능
  - 알림 엔티티 및 저장소 구현
  - 실시간 푸시 알림 지원

### 3. 사용자 관리 기능 (SC-56)
- 회원가입 및 이메일 인증 시스템
  - 이메일 인증 코드 발송 및 검증
  - 회원가입 프로세스 구현
- 사용자 프로필 관리
  - 프로필 정보 수정
  - 프로필 이미지 업로드
- 학과 정보 통합 및 권한 관리

### 4. 시스템 개선 및 최적화
- Rate Limiting 구현 (Bucket4j)
  - API 요청 제한 설정
  - IP 기반 요청 제한
- Redis 캐싱 및 세션 관리
  - 토큰 저장소
  - 세션 데이터 관리
- API 응답 형식 표준화
  - 공통 응답 객체 구현
  - 예외 처리 체계 구축

### 5. 서버 환경 마이그레이션
- AWS EC2에서 교내 로컬 서버로 마이그레이션
  - 서버 환경 구성 및 설정
  - 데이터베이스 마이그레이션
  - 네트워크 설정 최적화
- Docker 컨테이너화
  - 컨테이너 이미지 빌드
  - 컨테이너 배포 자동화

### 6. 모니터링 시스템 구축
- Grafana/Prometheus 통합
  - 시스템 메트릭 수집
  - 실시간 모니터링 대시보드
- 성능 모니터링
  - API 응답 시간 추적
  - 리소스 사용량 모니터링

### 7. CI/CD 파이프라인 구축
- GitHub Actions 워크플로우 설정
  - 자동 빌드 및 테스트
  - 배포 자동화
