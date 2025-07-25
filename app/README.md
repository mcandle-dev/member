# SupabaseMemberApp 🌟

SupabaseMemberApp·은 Kotlin으로 개발된 Android 앱으로, [Supabase](https://supabase.com)의 REST API가 연결되어 멤버 정보를 가져오고 목록으로 표시합니다.

목록 항목을 클릭하면 모든 정보를 다음과 같은 데이어 드릴리쥬를 통해 표시하며, Supabase 가이드를 관찰하고 가능한 Android 시스템의 구분을 허용합니다.

---

## 📄 기능

- Supabase 데이터베이스의 `user` 테이블에서 멤버 목록 가져오기
- 목록바로 보기 (RecyclerView)
- 클릭시 모든 정보 (DialogFragment)
- Kotlin, Coroutine, ViewBinding, Supabase SDK와 같이 구현

---

## 📊 Supabase 테이블 설정

경로: `user`

```sql
create table public.user (
  id text primary key,
  name text not null,
  grade text not null,
  phone text not null,
  kakao_use boolean not null,
  member_card_no text not null,
  credit_card_name text not null,
  credit_card_no text not null,
  promotion text
);
```

Row Level Security (RLS) 허용 보호 설정:

```sql
alter table public.user enable row level security;
create policy "Allow read access" on public.user for select using (true);
```

---

## 🚀 설치 방법

### ✅ 전제 조건

- Android Studio Hedgehog 이상
- JDK 17
- Supabase 프로젝트 생성 + anon key 확당

### 📁 파일 구조

```
SupabaseMemberApp/
 ├─ app/
 ├─ local.properties  ← 필요!
 └─ build.gradle.kts (project-level)
```

### 📅 local.properties

```properties
SUPABASE_URL=https://your-project-id.supabase.co
SUPABASE_API_KEY=your-anon-key
```

> git 에 커미팅 x (`.gitignore` 포함)

### ⚙️ build.gradle.kts (앱 모듈)

```kotlin
buildFeatures {
    viewBinding = true
}

buildConfigField("String", "SUPABASE_URL", "\"${localProps.getProperty("SUPABASE_URL")}\"")
buildConfigField("String", "SUPABASE_API_KEY", "\"${localProps.getProperty("SUPABASE_API_KEY")}\"")

dependencies {
    implementation("io.github.jan-tennert.supabase:postgrest-kt:1.2.0")
    implementation("io.ktor:ktor-client-okhttp:2.3.5")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
}
```

### 📆 Build & Run

1. `File > Sync with Gradle`
2. `Build > Clean Project`
3. `Run > Run app`

---

## 🔹 Kotlin Source Code (주요 방향)

```
├─ MainActivity.kt             → Supabase에서 멤버 목록 가져오고 RecyclerView에 보여주기
├─ MemberInfo.kt              → Supabase 값을 다룰 data class (@Serializable)
├─ SupabaseClientProvider.kt → SupabaseClient가 URL + Key 것을 기반으로 생성함
├─ MemberAdapter.kt           → RecyclerView adapter에서 click event와 binding 처리
└─ MemberInfoDialog.kt       → 목록을 클릭할 경우 DialogFragment로 정보 표시
```

---

## 🚫 오류 처리

- ❌ `UnauthorizedRestException` → local.properties의 KEY 설정 확인
- ❌ `No HTTP client engine` → ktor-client-okhttp 배포 필요
- ❌ 목록이 표시되지 않음 → Supabase RLS 확인 + 데이터 존재 확인

---

## 🌟 기획안

- auth(Gotrue) 방해 구현 가능
- 새 멤버 등록/수정/삭제 가능
- Jetpack Compose 구성로 이시 가능

---

✨ Supabase + Kotlin과 XML UI 의 가능성을 보여주는 탭스트 개발 플랫폼입니다!
