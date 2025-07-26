# 📦 프로젝트 구조 및 설명

## 1. 패키지 구조

```
com.mcandle.member
├── MainActivity.kt                # 메인 진입 화면, 시나리오 선택(멤버/주문)
├── MemberListActivity.kt          # 멤버 리스트 조회 및 상세 다이얼로그 호출
├── MemberAdapter.kt               # 멤버 리스트 RecyclerView 어댑터
├── MemberInfo.kt                  # 멤버 데이터 모델
├── MemberInfoDialog.kt            # 멤버 상세 정보 다이얼로그
├── OrderListActivity.kt           # 주문 리스트 조회 및 상세/결제 추천 다이얼로그 호출
├── OrderAdapter.kt                # 주문 리스트 RecyclerView 어댑터
├── OrderInfo.kt                   # 주문 데이터 모델
├── OrderConfirmDialog.kt          # 주문 결제 추천 확인 다이얼로그
├── OrderDetailDialog.kt           # 주문 상세/결제 추천 다이얼로그
├── SupabaseClientProvider.kt      # Supabase API 클라이언트 제공
└── ui/theme/                      # (테마 관련 파일)
```

## 2. 주요 소스코드 설명

- **MainActivity.kt**
  - 앱의 메인 진입점. "멤버" 또는 "주문" 시나리오 버튼을 통해 각 기능 화면으로 이동합니다.

- **MemberListActivity.kt**
  - 멤버 목록을 Supabase에서 조회하여 RecyclerView로 표시합니다.
  - 멤버 클릭 시 `MemberInfoDialog`를 통해 상세 정보를 보여줍니다.

- **MemberAdapter.kt**
  - 멤버 리스트를 위한 RecyclerView 어댑터.
  - 각 멤버 아이템 클릭 시 콜백으로 상세 다이얼로그 호출.

- **MemberInfoDialog.kt**
  - 멤버의 상세 정보를 보여주는 다이얼로그.
  - 이름, 등급, 전화번호, 카카오 사용 여부, 멤버십/신용카드 정보, 프로모션 등 표시.

- **OrderListActivity.kt**
  - 주문 목록을 Supabase에서 조회하여 RecyclerView로 표시합니다.
  - 주문 클릭 시 `OrderConfirmDialog` → "확인" 시 `OrderDetailDialog`로 상세/결제 추천 정보 표시.

- **OrderAdapter.kt**
  - 주문 리스트를 위한 RecyclerView 어댑터.
  - 각 주문 아이템 클릭 시 콜백으로 결제 추천 다이얼로그 호출.

- **OrderConfirmDialog.kt**
  - 주문 결제 추천 안내 및 "확인/취소" 버튼 제공.
  - "확인" 시 주문 상세 재조회 후 상세 다이얼로그로 이동.

- **OrderDetailDialog.kt**
  - 주문 상세 정보, 추천 결제 방식, 프로모션, 결제금액 등 표시.
  - "결제하기" 버튼 제공(추후 결제 로직 구현 가능).

- **OrderInfo.kt / MemberInfo.kt**
  - 각각 주문/멤버의 데이터 모델 클래스.

- **SupabaseClientProvider.kt**
  - Supabase API 연동을 위한 클라이언트 객체 제공.

## 3. 주요 레이아웃 파일 설명

- **activity_main.xml**
  - 메인 화면. "멤버"와 "주문" 시나리오 버튼 2개로 구성.

- **activity_member_list.xml**
  - 상단 툴바와 멤버 리스트(RecyclerView)로 구성.

- **activity_order_list.xml**
  - 상단 툴바와 주문 리스트(RecyclerView)로 구성.

- **item_member.xml**
  - 멤버 리스트의 각 아이템 레이아웃. 이름, 전화번호, 멤버십 번호 표시.

- **order_item.xml**
  - 주문 리스트의 각 아이템 레이아웃. Order Key, User ID 표시.

- **dialog_member_info.xml**
  - 멤버 상세 정보 다이얼로그 레이아웃. 이름, 등급, 전화번호, 카드 정보 등 표시.

- **dialog_order_confirm.xml**
  - 주문 결제 추천 안내 다이얼로그. 안내 메시지와 "확인/취소" 버튼.

- **order_detail_dialog.xml**
  - 주문 상세/결제 추천 다이얼로그. 매장명, POS ID, 직원, 상품, 수량, 금액, 프로모션, 추천 결제, 결제금액, 하단에 "취소/결제하기" 버튼.
