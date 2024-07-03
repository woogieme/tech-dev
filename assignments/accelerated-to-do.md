# Assignment - Accelerated To-Do

- 해당 과제는 총 7개의 Step으로 구성되어 있습니다.
- 모든 과제는 C4-Cometrue 깃허브 레포에서 관리되어야 하며, 반드시 각 Step이 종료될 때 마다 PR 요청을 날려야 합니다.
  - 물론, 다른 사람들의 PR에 대한 리뷰도 가능합니다.
- 서버는 필요할 때 편하게 말씀해 주세요.
- 상당히 고민이 많이 필요한 주제입니다. 일부 Step에 대해서 제공하는 키워드가 힌트가 될 수 있으니, 키워드와 관련한 학습을 진행하는 것을 권장합니다.
- 궁금증이 있으면 주저하지 말고 서로 커뮤니케이션하고, 그럼에도 해결되지 않는다면 질문을 남겨주세요.
- 각 PR 마다 설계 의도를 작성해주세요. 좀 더 효율적인 리뷰가 가능할 겁니다.

## 프로젝트 설명
`다양한 캘린더 서비스와 호환되는 To-Do 서비스 만들기`

- iCal 국제 표준 (RFC 5545)을 준수하는 캘린더/To-Do 서비스를 개발해 봅시다.
- 일정과 관련한 복잡한 로직 개발 및, 외부 캘린더와의 import/export 기능도 완벽하게 지원해야 합니다.

### Step 1. 기본 일정 구현
#### 구현사항
- 기본 일정 등록 기능을 구현합니다.
- 다음과 같은 요소를 반드시 포함해야 합니다. (이외의 요소들도 필요할 수 있겠죠? 어떤게 필요할지 직접 고민해 봅시다.)
    - 일정 이름
    - 날짜, 시간
- 사용하는 데이터베이스의 종류의 제한은 없습니다.
- 유저 정보는 등록하면 좋겠죠? 다만 빡빡한 인증을 구현할 필요는 없습니다. 식별의 기능만 한다고 생각해 보세요.

#### 프로그래밍 요구사항
- 일정은 단순히 특정 날짜의 특정 시간으로 끝나지 않습니다. 여러 날짜를 걸치고 있는 일정은 어떻게 저장해야 할지 생각해 보세요.
- 등록만 언급했지만, 실제 캘린더를 생각해보면 등록 이외의 기능도 구현해야 합니다. 어떤 것들이 필요할지, 어떤 제약조건이 있어야 할지 생각해 봅시다.
- 일정 입력 시, 반드시 Validation 이 포함되어야 합니다.

### Step 2. iCal Import/Export
#### 구현사항
- iCal import/export 기능을 구현합시다.
    - 참고 1: https://developers.worksmobile.com/kr/document/1007011?lang=ko
    - 참고 2: https://icalendar.org/resources.html
- 외부 캘린더 (Google Calendar, 네이버 캘린더, 웍스모바일 캘린더 등) 에서 추출한 iCal 파일을 서버에 올렸을 때, 정상적으로 일정을 DB에 저장할 수 있어야 합니다.
- 반대로, 서버에 있는 일정을 iCal 형식으로 export 할 수 있어야 하며, 이 파일은 역으로 외부 캘린더에서 읽어들일 수 있어야 합니다.

#### 프로그래밍 요구사항
- 현실적으로 저 형식을 직접 파싱해서 처리할 수 있을까요? 대안을 생각해 봅시다.
- 추가적으로, import 및 export 처리를 위해선 서버에 업로드/다운로드 기능을 구현해야 합니다.
    - 다만, 한 번 처리된 파일은 계속 서버에 남아있을 필요가 없다는 것을 고려해야 합니다.
    - 좀 더 나아가서, 실제 사용 시나리오도 함께 고려해 봅시다. 파일이 언제까지 남아있어야 할까요?

### Step 3. 세분화 된 일정 설계
#### 구현사항
- 일정 기능을 세분화 해 봅시다.
    - 단일 일정: 특정 날짜, 특정 시간, 특정 장소로 설정
    - 반복 일정: 매주 특정 요일의 특정 시간, 특정 장소로 설정
    - 종일 일정: 시간을 지정하지 않고, 그 하루 전체를 일정으로 설정
- 특히, 반복 일정의 경우 상황에 따라 하루만 예외로 두고 다른 요일로 변경하거나, 특정 날짜만 제거할 수 있어야 합니다.
#### 프로그래밍 요구사항
- 기존과 같은 건당 등록으로는 반복 일정을 처리할 수 없습니다. 데이터베이스 스키마를 전체적으로 재고해 봅시다.
- 구조상, 슬슬 DB에 무리가 갈 수 있는 쿼리가 등장할 수 있습니다. 이 경우, 어떻게 쿼리를 개선할 수 있을지 생각해 보세요.
- 이런 상황에선, 어떻게 iCal로 추출할 수 있을지 고민해 보세요.

### Step 4. 캘린더 연동, 마이그레이션 기능 개발
#### 구현사항
- Google Calendar, 네이버 캘린더의 경우 전체 마이그레이션 및 연동 기능을 제공하고 있습니다.
- 이를 활용하여, 실시간 연동 기능과 전체 일정 마이그레이션 기능을 개발해 봅시다.
#### 프로그래밍 요구사항
- 잘 찾아보면 알겠지만, 단순 API 기능을 통해 마이그레이션을 지원하는 구조가 아닙니다. 구체적으로 확인해보고, 일정 연동을 어떻게 할 수 있을지 찾아봅시다.
- 반대는 가능할까요? (즉, Google Calender 및 네이버 캘린더에 우리의 서버에 등록한 일정을 연동하는 방법) 요건 필수는 아니지만, 한 번 고민해보면 재밌을 겁니다.

### Step 5. 다양한 조회 기능 개발
#### 구현사항
- 우리야 프론트 개발자가 없이 서버만 만들고 있지만, 프론트가 API 명세를 요청했다고 가정해 봅시다.
    - 일정은 이제 일간, 주간, 월간으로 조회할 수 있어야 합니다.
        - 당연하지만, 일간 조회의 경우 구체적인 시간대에 맞춰서 반환해 주어야겠죠?
    - 달력을 넘기면 이전 일정도 조회할 수 있어야 합니다.
    - 일정을 새로 추가하는 화면에선, 이미 일정이 있는 시간대도 확인할 수 있어야 합니다.
#### 프로그래밍 요구사항
- 해당 요청들은 쿼리에 따른 성능 차이가 매우 극명하게 갈릴 수 있는 요청입니다.
    - 충분한 양의 더미 데이터를 생성하고, 쿼리 실행 계획을 통해 예상 성능을 분석해 봅시다.

### Step 6. 캘린더 초대 기능
#### 구현사항
- 캘린더의 참가자 기능을 추가하며, 동시에 다른 사람들에게 공유하는 기능을 만들어 봅시다.
- 전체 조회, 친구 조회, 비공개로 일정의 범위를 결정할 수 있습니다.
- 다른 사람들에게 일정 공유를 할 수 있는 링크를 만드는 기능이 있어야 합니다.
- 또한, 링크에는 유효기한이 존재합니다.
#### 프로그래밍 요구사항
- 링크 경로가 중복되어선 절대 안 됩니다. 어떻게 해야 할까요?
- 복잡한 인증을 구현하지 않아도 됩니다. 토큰 같은걸 사용하지 않고, 단순 헤더에 이메일만 넣어도 되니까 걱정말고 편하게 구현해 보세요.

### Step 7. 성능 테스트
#### 구현사항
- 실제로 개발한 서버의 성능이 어떨까요?
- 사용자의 사용 시나리오를 설계하고, 이를 활용해 스트레스 테스트 툴을 사용한 성능 테스트를 진행해봅시다.
    - 여기서는 nGrinder를 사용해 봅시다.