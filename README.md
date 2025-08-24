# MemberApp - 멤버 및 주문 관리 앱

Android 기반의 멤버십 및 주문 관리 애플리케이션입니다. Supabase를 백엔드로 사용하여 멤버 정보 조회와 주문 처리, 결제 추천 기능을 제공합니다.

## 🚀 주요 기능

- **멤버 관리**: 멤버 목록 조회 및 상세 정보 확인
- **주문 처리**: 주문 목록 조회 및 결제 추천
- **실시간 데이터**: Supabase를 통한 실시간 데이터베이스 연동

## 📋 사전 요구사항

- **Android Studio** Arctic Fox (2020.3.1) 이상
- **JDK 17** 이상
- **Android SDK** (API 26 이상, Target API 34)
- **Supabase** 계정 및 프로젝트

## 🔧 프로젝트 설정

### 1. 프로젝트 클론

```bash
git clone <repository-url>
cd member
```

### 2. Supabase 설정

#### 2.1 Supabase 프로젝트 생성
1. [Supabase 웹사이트](https://supabase.com)에 접속하여 계정 생성
2. "New Project" 클릭하여 새 프로젝트 생성
3. 프로젝트 이름, 데이터베이스 비밀번호, 지역 선택
4. 프로젝트 생성 완료까지 대기 (약 2분)

#### 2.2 데이터베이스 테이블 생성
Supabase 대시보드의 SQL Editor에서 다음 쿼리 실행:

```sql
-- 사용자(멤버) 테이블 생성
CREATE TABLE user (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    grade TEXT NOT NULL,
    phone TEXT NOT NULL,
    kakao_use BOOLEAN DEFAULT FALSE,
    member_card_no TEXT,
    credit_card_name TEXT,
    credit_card_no TEXT,
    promotion TEXT
);

-- 주문 테이블 생성
CREATE TABLE orders (
    order_key TEXT PRIMARY KEY,
    store_name TEXT NOT NULL,
    pos_id TEXT NOT NULL,
    staff_name TEXT NOT NULL,
    product_name TEXT NOT NULL,
    product_qty INTEGER NOT NULL,
    amount INTEGER NOT NULL,
    promotions TEXT,
    recommended TEXT,
    pay_amount INTEGER NOT NULL,
    user_id TEXT REFERENCES user(id)
);

-- 샘플 데이터 삽입 (선택사항)
INSERT INTO user (id, name, grade, phone, kakao_use, member_card_no, credit_card_name, credit_card_no, promotion) VALUES
('user001', '김철수', 'VIP', '010-1234-5678', true, 'MC001234', '신한카드', '1234-5678-9012-3456', '10% 할인'),
('user002', '이영희', 'GOLD', '010-9876-5432', false, 'MC005678', '국민카드', '9876-5432-1098-7654', '5% 할인');

INSERT INTO orders (order_key, store_name, pos_id, staff_name, product_name, product_qty, amount, promotions, recommended, pay_amount, user_id) VALUES
('ORD001', '강남점', 'POS01', '박직원', '아메리카노', 2, 8000, '멤버십 할인 10%', '신한카드 결제', 7200, 'user001'),
('ORD002', '홍대점', 'POS02', '최직원', '카페라떼', 1, 4500, NULL, '국민카드 결제', 4500, 'user002');
```

#### 2.3 API 키 및 URL 확인
1. Supabase 프로젝트 대시보드에서 "Settings" → "API" 메뉴 이동
2. 다음 정보 복사:
   - **Project URL**: `https://your-project-ref.supabase.co`
   - **Anon public key**: `eyJhbGciOiJIUzI1NiIsI...` (긴 JWT 토큰)

### 3. 로컬 설정 파일 생성

프로젝트 루트 디렉토리에 `local.properties` 파일을 생성하고 다음 내용을 추가:

```properties
# Android SDK 경로 (Android Studio에서 자동 생성되는 부분)
sdk.dir=C:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk

# Supabase 설정 (아래 값들을 실제 값으로 변경)
SUPABASE_URL=https://your-project-ref.supabase.co
SUPABASE_API_KEY=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.your-actual-anon-key-here
```

⚠️ **중요**: `local.properties` 파일은 `.gitignore`에 포함되어 있으므로 Git에 커밋되지 않습니다. 팀원들은 각자 이 파일을 생성해야 합니다.

### 4. 프로젝트 빌드 및 실행

```bash
# 의존성 다운로드 및 프로젝트 빌드
./gradlew build

# 디버그 APK 설치 (연결된 기기 또는 에뮬레이터)
./gradlew installDebug

# 앱 실행
adb shell am start -n com.mcandle.member/.MainActivity
```

## 🏗️ 프로젝트 구조

```
app/src/main/java/com/mcandle/member/
├── MainActivity.kt                 # 메인 화면 (멤버/주문 선택)
├── MemberListActivity.kt           # 멤버 목록 화면
├── MemberAdapter.kt                # 멤버 목록 어댑터
├── MemberInfo.kt                   # 멤버 데이터 모델
├── MemberInfoDialog.kt             # 멤버 상세 다이얼로그
├── OrderListActivity.kt            # 주문 목록 화면
├── OrderAdapter.kt                 # 주문 목록 어댑터
├── OrderInfo.kt                    # 주문 데이터 모델
├── OrderConfirmDialog.kt           # 주문 확인 다이얼로그
├── OrderDetailDialog.kt            # 주문 상세 다이얼로그
└── SupabaseClientProvider.kt       # Supabase 클라이언트 제공자
```

## 🔧 개발 명령어

```bash
# 프로젝트 클린 빌드
./gradlew clean build

# 유닛 테스트 실행
./gradlew test

# Android 계측 테스트 실행
./gradlew connectedAndroidTest

# 릴리즈 APK 빌드
./gradlew assembleRelease

# 의존성 확인
./gradlew dependencies
```

## 🗄️ 데이터베이스 스키마

### User 테이블
| 컬럼 | 타입 | 설명 |
|------|------|------|
| id | TEXT (PK) | 사용자 ID |
| name | TEXT | 사용자 이름 |
| grade | TEXT | 멤버십 등급 |
| phone | TEXT | 전화번호 |
| kakao_use | BOOLEAN | 카카오 사용 여부 |
| member_card_no | TEXT | 멤버십 카드 번호 |
| credit_card_name | TEXT | 신용카드 이름 |
| credit_card_no | TEXT | 신용카드 번호 |
| promotion | TEXT | 프로모션 정보 |

### Orders 테이블
| 컬럼 | 타입 | 설명 |
|------|------|------|
| order_key | TEXT (PK) | 주문 키 |
| store_name | TEXT | 매장명 |
| pos_id | TEXT | POS ID |
| staff_name | TEXT | 직원명 |
| product_name | TEXT | 상품명 |
| product_qty | INTEGER | 수량 |
| amount | INTEGER | 금액 |
| promotions | TEXT | 프로모션 |
| recommended | TEXT | 추천 결제방식 |
| pay_amount | INTEGER | 결제 금액 |
| user_id | TEXT (FK) | 사용자 ID |

## 🔒 보안 주의사항

- `local.properties` 파일은 절대 Git에 커밋하지 마세요
- Supabase API 키는 anon/public 키만 사용하세요
- 민감한 정보는 환경 변수나 안전한 저장소를 사용하세요

## 🚨 문제 해결

### 빌드 에러
- `local.properties` 파일이 올바르게 설정되었는지 확인
- Android SDK 경로가 정확한지 확인
- JDK 17이 설치되어 있는지 확인

### 네트워크 에러
- Supabase URL과 API 키가 정확한지 확인
- 인터넷 연결 상태 확인
- Supabase 프로젝트가 활성 상태인지 확인

### 데이터베이스 연결 실패
- Supabase 대시보드에서 테이블이 생성되었는지 확인
- RLS(Row Level Security) 정책이 올바르게 설정되었는지 확인

## 🤝 기여하기

1. 이 저장소를 포크합니다
2. 새로운 기능 브랜치를 생성합니다 (`git checkout -b feature/AmazingFeature`)
3. 변경사항을 커밋합니다 (`git commit -m 'Add some AmazingFeature'`)
4. 브랜치에 푸시합니다 (`git push origin feature/AmazingFeature`)
5. Pull Request를 생성합니다

## 📝 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.