# 조성연 작업 목록
1. [ ] test/FlowChartTest.java 테스트 다 통과하게 만들기.
   1. Test 3, Test 4는 그냥 더미데이터 반환해서 통과하는 거니까 다시 짜야함.
   2. Test 12는 블록체인 연동해서 Suceess, Failed 중 하나 반환해야하므로 일단 패스. 그냥 status를 Pending에서 Success로 바꾸는 걸로 하면 될 듯
2. [ ] 로그인/회원가입 구현
3. [ ] 로그인/회원가입 구현 후에는 미들웨어 만들어서 verifier, issuer, holder 권한별로 정해진 api만 호출할 수 있도록 하기
3. [ ] 예외처리해야할 것 굉장히 많음. 플로우차트 잘 보고 생각해서 추가하기.
   1. 예외처리 예시 ) vcId가 2인 vc가 존재하지 않는데 vcId가 2인 인증서를 제출하려하는 경우
   2. postId가 2인 Post(채용공고)가 존재하지 않는데 postId가 2인 Post에 인증서를 제출하려 하는 경우
   3. context를 생성하는데 해당 이름의 context가 이미 존재하는 경우
   4. _**위 경우 외에 다른 예외케이스 찾아서 상황에 적합한 error code와 메시지 반환 (404, 500 400 등등)**_
4. [ ] 코드 굉장히 더러움. refactoring 필히 요함. 더럽게 짜서 ㅈㅅ
5. [ ] 다 하고 나면 오브젝트 스토리지 + 트래픽 처리 생각하기

# DB SCHEMA

![](/doc/img/DB-Schema.png)

# REST API

| Method | URI                                        | Description                     |
| ------ |--------------------------------------------| ------------------------------- |
| POST   | /issuer/create                             | VC Context 생성                 |
| POST   | /issuer/issue                              | VC 발행                         |
| GET    | /issuer/context-list/:issuerId    *        | Issuer가 생성한 Context 목록    |
| GET    | /context-list                              | context 목록                    |
| GET    | /issuer/vc-list/:context     *             | 해당 context에 해당하는 VC 목록 |
| POST   | /holder/submit                             | 해당 vc와 내용을 제출           |
| GET    | /holder/vc-list/:holderId                  | Holder가 가지고 있는 VC 목록    |
| GET    | /holder/submit-list/:holderId              | 제출한 vc 목록                  |
| GET    | /holder/posting-list                       | 채용 공고 목록                  |
| POST   | /verifier/post                             | 채용공고 등록                   |
| GET    | /verifier/postList/:verifierId             | 내 채용공고 목록                |
| GET    | /verifier/submit-list//:verifierId/:postId | 해당 공고에서 제출받은 vc 목록  |
| POST   | /verifier/verify                           | 인증서 검증                     |

# Issuer URI

## Context 생성

### URI

```
/issuer/create
```

### req.body

context는 입력값
"1"은 필수값

```json
{
  "context": "graduate-certificate",
  "credentialSubject": {
    "title": "1",
    "name": "1",
    "date": "1",
    "major": "1",
    "doubleMajor": "0",
    "minor": "0"
  }
}
```

### res.body

```json
{ "id": "00010001" }
```

## Context 목록 확인

### URI

```
/context-list
```

### res.body

```json
[
  {
    "context": "graduate-certificate",
    "credentialSubject": {
      "title": "1",
      "name": "1",
      "date": "1",
      "major": "1",
      "doubleMajor": "0",
      "minor": "0"
    }
  }
]
```

## VC발행

### URI

```
/issuer/issue
```

### req.body

```json
[
  {
    "holderId": "1",
    "vc": {
      "context": "graduate-certificate",
      "issuer": "0001",
      "credentialSubject": {
        "title": "아주대학교 졸업증명서",
        "name": "오동재",
        "date": "2022-02-10",
        "major": "소프트웨어학",
        "doubleMajor": "",
        "minor": ""
      }
    }
  },
  {
    "holderId": "2",
    "vc": {
      "context": "graduate-certificate",
      "issuer": "0001",
      "credentialSubject": {
        "title": "아주대학교 졸업증명서",
        "name": "서종현",
        "date": "2022-02-10",
        "major": "응화생",
        "doubleMajor": "",
        "minor": ""
      }
    }
  }
]
```

### res.body

```json
[
  { "holderId": "1", "vcId": "000100010001" },
  { "holderId": "2", "vcId": "000100010002" }
]
```

## 이슈어가 발행한 context 목록 확인

### URI

```
/issuer/context-list/:issuerId
```

### res.body

```json
[
  {
    "context": "graduate-certificate",
    "num": "2"
  }
]
```

## 해당 context에 해당하는 vc 목록 확인

### URI

```
/issuer/vc-list/:context
```

### res.body

```json
[
  {
    "holderId": "1",
    "vc": {
      "context": "graduate-certificate",
      "issuer": "0001",
      "credentialSubject": {
        "title": "아주대학교 졸업증명서",
        "name": "오동재",
        "date": "2022-02-10",
        "major": "소프트웨어학",
        "doubleMajor": "",
        "minor": ""
      }
    }
  },
  {
    "holderId": "2",
    "vc": {
      "context": "graduate-certificate",
      "issuer": "0001",
      "credentialSubject": {
        "title": "아주대학교 졸업증명서",
        "name": "서종현",
        "date": "2022-02-10",
        "major": "응화생",
        "doubleMajor": "",
        "minor": ""
      }
    }
  }
]
```

# Holder

## 채용 공고 목록

### URI

```
/holder/posting-list
```

### res.body

```json
[
  {
    "postId": "0001",
    "name": "대학원생 모집",
    "verifier": "ajou-graduate-school",
    "requirement": ["graduate-certificate"],
    "expired": "2022-11-21",
    "url": "https://~~"
  }
]
```

## 가지고 있는 VC 목록

### URI

```
 /holder/vc-list/:holderId
```

### res.body

```json
[
  {
    "context": "graduate-certificate",
    "issuer": "0001",
    "credentialSubject": {
      "title": "아주대학교 졸업증명서",
      "name": "오동재",
      "date": "2022-02-10",
      "major": "소프트웨어학",
      "doubleMajor": "",
      "minor": ""
    }
  }
]
```

## 해당 vc와 내용을 제출

### URI

```
/holder/submit
```

### req.body

```json
{
  "postId":"0001",
  "vcIds": 
  [
    "asdasd","asdsfa12"
  ]
}
```

### res.body

```json
{ "status": "200" }
```

## 제출한 VC 목록

### URI

```
/holder/submit-list/:holderId
```

### res.body

```json
[
  {
    "vcId": "",
    "postId": "",
    "verifier": "ajou-graduate-school",
    "title": "아주대학교 졸업증명서",
    "date": "2022-10-10",
    "status": "pending"
  }
]
```

# Verifier

## 채용공고 등록

### URI

```
/verifier/post
```

### req.body

```json
{
  "verifierId": "01",
  "name": "대학원생 모집",
  "expired": "2022-11-21",
  "required": ["graduate-certificate"],
  "url": "https://~~"
}
```

### res.body

```json
{ "status": "200" }
```

## 내 채용 공고 목록

### URI

```
/verifier/postList/:verifierId
```

### res.body

```json
[
  {
    "postId": "0001",
    "name": "아주대학교 대학원생 모집",
    "people": "2"
  }
]
```

## 제출 받은 VC 목록

### URI

```
/verifier/submit-list//:verifierId/:postId
```

### res.body

```json
[
  {
    "context": "graduate-certificate",
    "issuer": "0001",
    "credentialSubject": {
      "title": "아주대학교 졸업증명서",
      "name": "오동재",
      "date": "2022-02-10",
      "major": "소프트웨어학",
      "doubleMajor": "",
      "minor": ""
    }
  }
]
```

## 검증

### URI

```
/verifier/verify
```

### req.body

```json
{
  "context": "graduate-certificate",
  "issuer": "0001",
  "credentialSubject": {
    "title": "아주대학교 졸업증명서",
    "name": "오동재",
    "date": "2022-02-10",
    "major": "소프트웨어학",
    "doubleMajor": "",
    "minor": ""
  }
}
```

### res.body

```json
{ "status": "200" }
```
