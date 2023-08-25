# Vone_BE

## API Docs

| Method | URI                                               | Description    |
|--------|---------------------------------------------------|----------------|
| POST   | /issuer/context                                   | Context 생성     |
| POST   | /issuer/vc                                        | VC 발행          |
| POST   | /issuer/hash                                      | Hash 값 불러오기    |
| GET    | /issuer/context-list                              | Context 목록     |
| GET    | /issuer/context-list/{issuerId}                   | 발행한 VC의 Context 목록 |
| GET    | /issuer/vc-list                                   | 발행한 VC 목록      |
| GET    | /holder/vc-list/{holderId}                        | Holder가 보유한 VC 목록 |
| GET    | /holder/submitted-vc-list/{holderId}              | 제출한 VC 목록      |
| GET    | /holder/post-list                                 | 채용공고 목록        |
| GET    | /holder/file/{id}                                 | 셀프등록한 인증서 다운로드 |
| POST   | /holder/vc-list/{hodlerId}                        | 인증서 셀프등록       |
| POST   | /holder/submitted-vc                              | 인증서 제출         |
| POST   | /verifier/verify                                  | 인증서 검증         |
| POST   | /verifier/post                                    | 채용공고 등록        |
| GET    | /verifier/post-list/{verifierId}                  | 채용공고 목록        |
| GET    | /verifier/submitted-vc-list/{verifierId}/{postId} | 받은 인증서 목록      |
| POST   | /user/info                                        | 유저 정보 불러오기     |
| GET    | /user/join                                        | 회원가입           |
| GET    | /user/login                                       | 로그인            |

# Issuer

## Context 생성

### URI

```
/issuer/context
```

### req.body
```json
{
  "context": "contextName",
  "credentialSubject": [
    "credentialSubject1", 
    "credentialSubject2", 
    "credentialSubject3"
  ]
}
```

### res.body
```json
{
  "context": "contextName",
  "credentialSubject": [
    "credentialSubject1",
    "credentialSubject2",
    "credentialSubject3"
  ]
}
```

## VC 발행

### URI

```
/issuer/vc
```

### req.body
```json
[
  {
    "holder": "holderId",
    "vc": {
      "context": "contextName",
      "credentialSubject": {
        "0": "value1",
        "1": "value2",
        "2": "value3"
      },
      "issuer": "issuerId"
    }
  },
  {
    "holder": "holderId",
    "vc": {
      "context": "contextName2",
      "credentialSubject": {
        "0": "value1",
        "1": "value2",
        "2": "value3",
        "3": "value4",
        "4": "value5"
      },
      "issuer": "issuerId"
    }
  }
]
```

### res.body
"id" : {hash}
```json
{
  "7": "0AYjgQb/0YM0M0qXegr0ps3JDzq3Uu1UMcTIUvXQx3I=",
  "8": "0AYjgQb/0YM0M0qXegr0ps3JDzq3Uu1UMcTIUvXQx3I="
}
```

## Hash 값 불러오기

### URI

```
/issuer/hash
```

### req.body

```json
[
  "value1",
  "value2",
  "value3",
  "value4",
  "value5",
  "value6",
  "value7",
  "value8"
]
```

### res.body

```json
89IWC4Jge57QNOD4aKs5PkZrg7mdV2FyBNl7gsuH5uM=
```

## Context 목록

### URI

```
/issuer/context-list
```

### res.body

```json
[
  {
    "context": "contextName",
    "credentialSubject": [
      "credentialSubject1",
      "credentialSubject2",
      "credentialSubject3"
    ]
  },
  {
    "context": "contextName2",
    "credentialSubject": [
      "credentialSubject1",
      "credentialSubject2",
      "credentialSubject3",
      "credentialSubject4",
      "credentialSubject5"
    ]
  }
]
```

## 발행한 VC의 Context 목록

### URI

```
/issuer/context-list/{issuerId}
```

### res.body

```json
[
  {
    "context": "contextName",
    "credentialSubject": [
      "credentialSubject1",
      "credentialSubject2",
      "credentialSubject3"
    ]
  },
  {
    "context": "contextName2",
    "credentialSubject": [
      "credentialSubject1",
      "credentialSubject2",
      "credentialSubject3",
      "credentialSubject4",
      "credentialSubject5"
    ]
  }
]
```

# Holder

## Holder가 보유한 VC 목록

### URI

```
/holder/vc-list/{holderId}
```

### res.body

```json
[
  {
    "vcId": 1,
    "context": "contextName",
    "issuer": "issuerId",
    "credentialSubject": {
      "credentialSubject1": "value1",
      "credentialSubject2": "value2",
      "credentialSubject3": "value3"
    }
  },
  {
    "vcId": 2,
    "context": "contextName2",
    "issuer": "issuerId",
    "credentialSubject": {
      "credentialSubject1": "value1",
      "credentialSubject2": "value2",
      "credentialSubject3": "value3",
      "credentialSubject4": "value2",
      "credentialSubject5": "value3"
    }
  }
]
```

## 제출한 VC 목록

### URI

```
/holder/submitted-vc-list/{holderId} 
```

### res.body

```json
[
  {
    "vcIds": [
      1
    ],
    "postId": 1,
    "verifier": "verifierId",
    "title": "채용공고",
    "date": "2023-01-06T00:20:56.067053",
    "status": "pending"
  },
  {
    "vcIds": [
      2
    ],
    "postId": 1,
    "verifier": "verifierId",
    "title": "채용공고",
    "date": "2023-01-06T00:20:56.082938",
    "status": "pending"
  }
]
```

## 채용공고 목록

### URI

```
/holder/post-list
```

### res.body

```json
[
  {
    "id": 1,
    "title": "채용공고",
    "expired": "2023-01-06",
    "required": [
      "testContext",
      "testContext2"
    ],
    "url": "http://vone.kr",
    "verifierId": "verifierId"
  },
  {
    "id": 2,
    "title": "채용공고2",
    "expired": "2023-01-10",
    "required": [
      "testContext",
      "testContext2"
    ],
    "url": "http://vone.kr",
    "verifierId": "verifierId"
  }
]
```

## 셀프등록한 인증서 다운로드

### URI

```
/holder/file/{id}
```

### res.body

```json

```

## 인증서 셀프등록

### URI

```
/holder/vc-list/{hodlerId}
```

### req.body

```json

```

### res.body

```json

```

## 인증서 제출

### URI

```
/holder/submitted-vc
```

### req.body

```json
{
  "holder": "holderId",
  "postId": 1,
  "vcIds": [
    1, 
    2
  ]
}
```

### res.body

```json
success
```

# Verifier

## 인증서 검증

### URI

```
/verifier/verify
```

### req.body

```json
{
  "holder": "hodlerId",
  "postId": 1,
  "vcIds": [
    9, 10
  ]
}
```

### res.body

```json
[
  "true",
  "true"
]
```

## 채용공고 등록

### URI

```
/verifier/post
```

### req.body

```json
{
  "expired": "2023-01-06",
  "required": [
    "testContext",
    "testContext2"
  ],
  "title": "채용공고",
  "url": "http://vone.kr",
  "verifier": "verifierId"
}
```

### res.body

```json
{
  "id": 1,
  "title": "채용공고",
  "expired": "2023-01-06",
  "required": [
    "testContext",
    "testContext2"
  ],
  "url": "http://vone.kr",
  "verifierId": "verifierId"
}
```


## 채용공고 목록

### URI

```
/verifier/post-list/{verifierId}
```

### res.body

```json
[
  {
    "id": 1,
    "title": "채용공고",
    "expired": "2023-01-06",
    "required": [
      "testContext",
      "testContext2"
    ],
    "url": "http://vone.kr",
    "verifierId": "verifierId"
  },
  {
    "id": 2,
    "title": "채용공고2",
    "expired": "2023-01-10",
    "required": [
      "testContext",
      "testContext2"
    ],
    "url": "http://vone.kr",
    "verifierId": "verifierId"
  }
]
```

## 받은 인증서 목록

### URI

```
/verifier/submitted-vc-list/{verifierId}/{postId}
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
[
  {
    "vcIds": [
      1
    ],
    "postId": 1,
    "verifier": "verifierId",
    "title": "채용공고",
    "date": "2023-01-06T00:20:31.335501",
    "status": "pending"
  },
  {
    "vcIds": [
      2
    ],
    "postId": 1,
    "verifier": "verifierId",
    "title": "채용공고",
    "date": "2023-01-06T00:20:31.346584",
    "status": "pending"
  }
]
```

# User

## 유저 정보 불러오기

### URI

```
/user/info
```

### res.body

```json
{
  "memberId": "holderId",
  "memberRole": "HOLDER"
}
```

## 회원가입

### URI

```
/user/join
```

### req.body

```json
{
  "memberId": "holderId",
  "password": "holderPw",
  "role": "HOLDER"
}
```

### res.body

```json
HOLDER
```

## 로그인

### URI

```
/user/login
```

### req.body

```json
{
  "memberId": "holderId",
  "password": "holderPw",
  "role": "HOLDER"
}
```

### res.body

```json
{
  "grantType": "Bearer",
  "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJob2xkZXJJZCIsImF1dGgiOiJST0xFX0hPTERFUiIsImV4cCI6MTY3MzAyMjY2OH0.Ko8uxXZZTOS8W4e-DkvEIYvG5XcRzub7JBv1MEodLM8",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzMwMjI2Njh9.gkNqc6eUXpePsHLdYvfy-9UUtSG2YI7WZjjfsLvdqPw"
}
```
