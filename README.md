# 📰 Kafka 기반 Newsletter 구독 플랫폼
🔗[(추가 작성 중) 더 자세한 고민이 궁금하다면, ***Click!!!***](https://iamheaming.notion.site/from-f60fba4d0ebc49b98801a21e18fb22ae) 

IT, 경제, Job Skill 등 다양한 분야의 전문가들이 발행하는 뉴스레터를 받을 수 있는 구독 플랫폼입니다. <br/>
뉴스레터 구독 시, 발행자가 설정한 요금이 매월 부과되며, 구독한 뉴스레터들을 플랫폼 내에서 확인할 수 있습니다. 

<br/>

## Tech Stack
- Open JDK 17.0.10, Spring Boot 3.2.0, Spring Webflux 3.2.0, Spring Data Jpa 3.2.0
- Apache Zookeeper 3.7.2, Apache kafka 3.6.0, Kafka-ui
- Elasticsearch 7.10.2, Kibana 7.10.2, logstash 7.10.2, filebeat 7.10.2
- Redis 6.0.2, MySql 8.0.35, MongoDB 7.0.3
- Docker, Openai API(chatgpt 3.5-turbo)

<br/>

## 주요 서비스
- 콘텐츠 플랫폼 구축
- 선착순 쿠폰 이벤트 대응
- 로그 수집 파이프라인 구축

<br/>

## 아키텍처

### 1. 헥사고날 아키텍처
![image](https://github.com/user-attachments/assets/7acce95d-c559-414e-aadb-ee70e397f826)
<br/>
**Hexagonal Architecture**는 포트 앤드 어댑터 아키첵처라고도 불리며, 고수준의 비즈니스 로직을 표현하는 내부영역(Port) + 인터페이스 처리를 담당하는 저수준 외부 영역(Adapter)으로 이루어져 있다. 

**외부 요청을 처리하는 인터페이스 Port**와 **실질적인 구현체 adapter**를 분리함으로써, 내-외부의 의존성을 낮출 수 있고, 코드 재사용성 및 확장성도 증가한다.

<br/>

- **프로젝트 구조**

```markdown
fromdot
├─adapter
│  ├─chat-gpt-client
│  ├─elasticsearch
│  ├─kafka
│  ├─metadata-client
│  ├─mongodb
│  ├─mysql
│  └─redis
├─common
├─domain
│	 ├─coupon
│  │  └─model
│  ├─inspectedpost
│  │  └─model
│  └─post
│     └─model
├─usecase <@Service>
│  ├─core
│  │  └─port <interface> 
│  ├─coupon-usecase 
│  ├─inspected-post-usecase
│  ├─post-resolving-help-usecase
│  ├─post-search-usecase
│  ├─post-usecase
│  └─subscribing-post-usecase
└─worker
    ├─auto-inspection-worker
    ├─content-caching-worker
    ├─content-indexing-worker
    ├─content-subscribing-worker
    └─coupon-issuing-worker
```
### 2. 콘텐츠 플랫폼

### 3. 선착순 쿠폰 이벤트 모듈

### 4. 로그 수집 파이프 라인
