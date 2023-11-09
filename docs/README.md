# 🎄크리스마스 프로모션 🎄
## 📝 기능 목록
- [ ] 어플리케이션이 시작되면 메뉴, 특별 할인 일자를 csv 파일에서 받아 온다.
- [ ] 예상 방문 일자를 입력 받고 유효한 일자인지 확인 한다.
- [ ] 메뉴를 입력 받고 유효한 메뉴인지 확인 한다.
- [ ] 주문 메뉴를 출력한다
- [ ] 총 주문 금액을 구하고 + 출력하고, 10,000원 이상인지 확인 한다.
- [ ] 💵 만 원 이하이면, 모든 혜택 적용 불가하므로 없음, 0원 등으로 출력한다.
- [ ] 💵 만 원 이상이면,
  - [ ] 12만원 이상이면 샴페인을 증정한다.(증정 메뉴 출력)
  - [ ] 총 혜택 내역 및 금액을 계산하고 출력한다(각 혜택 관련 메서드 + 총 확인 메서드 + 출력 메서드)
    - [ ] 크리스마스 디데이 할인이 가능한지 확인 한다.(1일 ~ 25일)
    - [ ] 평일 할인 / 주말 할인 중 적용 가능한 혜택을 확인 한다.
    - [ ] 특별 할인(⭐️ 있는 일자) 적용 가능한지 확인 한다.
    - [ ] 증정 이벤트 
    - [ ] 총 혜택 금액을 출력한다
  - [ ] 할인 후 예상 결제 금액을 출력한다
  - [ ] 총 혜택 금액에 따라 이벤트 배지를 증정한다

## ✏️ 요구 사항 정리
- [ ] 메뉴를 csv 파일에서 읽어오기(애피타이저, 메인, 디저트, 음료 각각 메뉴 이름, 가격순으로 정리)
- [ ] 중복된 할인, 증정
- [ ] 크리스마스 디데이 할인(1일 ~ 25일) : 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 추가
  - (1000 + 100n : 25일에 3400원 할인)
- [ ] 평일 할인 / 주말 할인
  - [ ] 평일 할인(일 ~ 금) : `디저트 메뉴` 1개당 2,023원 할인
  - [ ] 주말 할인(금, 토) : `메인 메뉴` 1개당 2,023원 할인
- [ ] 특별 할인(3일 ,10일, 17일, 24일, 31일, 25일) : 총 주문 금액에서 1,000원 할인
  - 🤔 일자를 추가/변경 하더라도 손쉽게
- [ ] 증정 이벤트 : `할인 전 총 주문 금액`이 12만 원 이상 ➡️ 샴페인 1개 증정
- [ ] 이벤트 기간 : `크리스마스 디데이 할인`을 제외한 다른 이벤트는 12월 내내 적용
- [ ] 이벤트 배지 부여 : 혜택 금액에 따라 다른 이벤트 배지
  - [ ] 5천 원 이상 : ⭐️ 별
  - [ ] 1만 원 이상 : 🎄 트리
  - [ ] 2만 원 이상 : 🤶 산타
- [ ] 총 주문 금액 `10,000원 이상`부터 이벤트 적용
- [ ] 음료만 주문 시, 주문 불가
- [ ] 메뉴는 한 번에 최대 20개까지 주문 가능(개수로 확인)
- [ ] 주문 메뉴, 할인 전 총 주문 금액, 증정 메뉴, 혜택 내역, 총 혜택 금액, 할인 후 예상 결제 금액, 12월 이벤트 배지 내용
  - [ ] 총 혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
  - [ ] 할인 후 예상 결제 금액 = 할인 전 총 주문 금액 - 할인 금액
  - [ ] 증정 메뉴 : 해당 ❌ ➡️ 없음
  - [ ] 혜택 내역 : 고객에게 적용된 이벤트 내역만, 해당 ❌ ➡️ 없음
  - [ ] 이벤트 배지 : 해당 ❌ ➡️ 없음



## 👿 예외 사항
- [ ] 식당 방문 일자
  - [ ] 숫자가 아님
  - [ ] 공백 : 
  - [ ] 1 ~ 31이 아님
  - [ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.
- [ ] 메뉴
  - [ ] 공백 : [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
  - [ ] 개수 1 이상의 숫자가 아님 : [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
  - [ ] 메뉴 형식이 다름 : [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
  - [ ] 메뉴판에 없는 메뉴 : [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.
  - [ ] 음료만 주문
  - [ ] 메뉴 개수가 20개 이상
  - [ ] 중복 메뉴 : [ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.

## 🎂 메뉴
~~~markdown
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
~~~

## 😎 예시
~~~markdown
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
26 
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
타파스-1,제로콜라-1 
12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
타파스 1개
제로콜라 1개

<할인 전 총주문 금액>
8,500원
 
<증정 메뉴>
없음
 
<혜택 내역>
없음
 
<총혜택 금액>
0원
 
<할인 후 예상 결제 금액>
8,500원
 
<12월 이벤트 배지>
없음
~~~
~~~markdown
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
3
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
티본스테이크 1개
바비큐립 1개
초코케이크 2개
제로콜라 1개
 
<할인 전 총주문 금액>
142,000원
 
<증정 메뉴>
샴페인 1개
 
<혜택 내역>
크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원
 
<총혜택 금액>
-31,246원
 
<할인 후 예상 결제 금액>
135,754원
 
<12월 이벤트 배지>
산타
~~~