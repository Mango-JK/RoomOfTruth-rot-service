# 진실의 방 블록체인 ⛓ 기술 가이드

<br/>

## # 하이퍼레저 패브릭

하이퍼레저 패브릭은 엔터프라이즈 수준의 설계를 고려한 **허가형 블록체인**입니다. 허가형, 기업형이라는 특성을 가지고 있어 다양한 기술적 요소들이 내재되어 있습니다. 각각을 살펴보기 이전에 하이퍼레저 패브릭의 전체적인 구성 요소 및 인프라에 대해 먼저 알아야 할 필요가 있습니다.

<br/>

- **Membership Service** : **권한 부여**, 인증과 관련된 모듈로 패브릭 네트워크에서 신뢰 근원이 되는 모듈입니다. 네트워크 참여자들의 신원 인증시 사용되며 공개키 기반 구조를 활용하여 인증 키, 인증서의 발급과 배포 및 관리를 수행합니다.

  <br/>

- **Transaction** : 데이터를 장부에 기록하거나 함수를 호출할 때 **블록체인에 전송하는 요청이자 거래**입니다. 패브릭에서의 트랜잭션은 이더리움과 다르게 모두 체인코드에 의해 통신 채널 별로 구현됩니다.

  <br/>

- **Chaincode** : 체인코드는 패브릭에서 제공하는 스마트 컨트랙트입니다. 체인코드는 패브릭 네트워크의 **전역상태를 관리할 수 있는 트랜잭션을 발생**시키며 **보안이 적용된 도커 컨테이너를 실행하여 동작**합니다.

  <br/>

- **Consensus** : 블록체인에서 가장 중요한 것 중 하나인 **합의를 담당하는 요소**입니다.  하이퍼레저에서는 **정렬 서비스를 이용하여 합의를 수행**합니다. 정렬 서비스는 **Orderer** 라는 역할의 구성원이 제공해 주며 수신한 트랜잭션을 블록으로 묶어 일괄적으로 처리합니다.

  <br/>

- **Legder** :  패브릭에서는 데이터 저장 및 관리를 위해 데이터베이스를 운용하고 있습니다.

  <br/>

- **Hyperledger Fabric CA(Certificate Authority) :  **멤버십 서비스를 구축하는 용도**입니다. 기관, 관리자 및 사용자들의 인증서를 발급할 수 있습니다.

<br/>

<br/>

```javascript
var ContractRecord = {
    contract_id: args[0],				// PK
    around_around_id: args[1],			 // address Key
    exclusive: args[2],					// 전용면적
    floor: args[3],						// 층
    ho: args[4],						// 호
    kind: args[5], 						// 건물 유형
    detail: args[6],					// 거래 내용
    cost: args[7],						// 비용
    monthly: args[8],					// 월세
    license: args[9],					// 공인중개사
    image: args[10],					// 이미지
    contract_date: args[11],			 // 계약 일시
    created_at: timestampString,		 // 원장 저장 일시
    is_expired: args[12]				// 만료 날짜
};
```

<br/>

```javascript
var StatusRecord = {
    status_id: args[0], 				// 유지보수 이력 num
    around_around_id: args[1],			 // address Key
    floor: args[2],						// 층
    ho: args[3],						// 호
    category: args[4]					// 유지보수 내용 [시설, 환경, 유지]
    detail: args[5],					// 상세 내용    
    cost: args[6],						// 비용
    license: args[7],					// 공인중개사
    image: args[8], 					// 이미지
    start_date: args[9],				// 시작 날짜
    end_date: args[10],					// 종료 날짜
    created_at: timestampString,	 	// 등록 일시
    is_expired: args[11]				// 만료 일시
};

```

<br/>

# 체인코드 Install

```javascript
sudo docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode install -n rot02 -v 1.0.4 -l node -p /var/hyperledger/chaincode/node
sudo docker exec -e "CORE_PEER_LOCALMSPID=HFTeamMSP" -e
```

<br/>

# 체인코드 인스턴스화

```javascript
sudo docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode instantiate -o orderer0.ordererorg:7050 -C team1channel -n rot02 -v 1.0.1 -c '{"Args":["init"]}' -P "OR('HFTeam1MSP.member')"
```

<br/>

<br/>

# AllBuilding 조회

```javascript
docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode query -o orderer0.ordererorg:7050 -C team1channel -n bloom20 -c '{"Args":"queryAllBuilding"}'
```

<br/>

# version Up 

```javascript
sudo docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode upgrade -o orderer0.ordererorg:7050 -C team1channel -n rot02 -v 1.0.4 -c '{"Args":["init"]}' -P "OR('HFTeam1MSP.member')"
```

<br/>

# 동작중인 chainCode List 조회

```java
docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode list --instantiated -C team1channel 
```

<br/>

<br/>

# building 등록

```javascript
docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode invoke -o orderer0.ordererorg:7050 -C team1channel -n bloom20 -c '{"Args":["registerBuildingInfo","BB58","경기 성남시 분당구 장안로 5","103","701","37.5671483618","127.0224231487","42.98","49.59","월세","45","2017-04-06","2019-04-06","황호숙","대전-SSAFY-005","default.png","2020-05-05","0"]}'
```

<br/>

# building 조회

```javascript
docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode query -o orderer0.ordererorg:7050 -C team1channel -n rot01 -c '{"Args":["query", "1"]}'
```

<br/>

# maintenance 등록

```javascript
docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode invoke -o orderer0.ordererorg:7050 -C team1channel -n bloom20 -c '{"Args":["registerMaintenanceInfo","Test1","대전 유성구 학하서로121번길 31-7","101","101","36.3486117096","127.2993806741","시설","발코니 확장", "300", "대전-SSAFY-001", "default.png","2020-04-29","2020-04-29","2020-04-29", "0"]}'
```

<br/>

# maintenance 조회

```javascript
docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode query -o orderer0.ordererorg:7050 -C team1channel -n bloom20 -c '{"Args":["queryMaintenance", "M3"]}'
```

<br/>

<br/>

<br/>

[Fabric경로]

ssh hfteam1@l02bch4.p.ssafy.io
ssafy1234!

<br/>

orderer port 7050 

peer port 7051

node >=8.4.0

<br/>

Error: could not assemble transaction, err proposal response was not successful, error code 500, msg chaincode registration failed: container exited with 1

해결방법 : package-lock.json 파일을 기존의 파일과 동일하게 맞추었음. 우리같은 경우는 임의로 수정한 부분이 있었던 걸로 판단됨.

<br/>

[AWS경로]

ubuntu@i02b201.p.ssafy.io
docker exec -it bloom /bin/bash
mysql접속
mysql -u root -p
password







# contract 등록

```javascript
docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode invoke -o orderer0.ordererorg:7050 -C team1channel -n rot01 -c '{"Args":["registerContract","TEST002","경기 성남시 분당구 장안로 5","경기도 성남시","분당구","장안로","1","2","27.09","2층","1호","아파트","전세","3800","30","대전-SSAFY-005","default.png","2020-06-06"]}'
```

<br/>



# contract 조회

```javascript
docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode query -o orderer0.ordererorg:7050 -C team1channel -n rot02 -c '{"Args":["queryContract", "CONTRACT162850"]}'
```

<br/>



# status 등록

```javascript
docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode invoke -o orderer0.ordererorg:7050 -C team1channel -n rot02 -c '{"Args":["registerStatus","TSS003","22184","1","101","시설","발코니 확장","500","대전-SSAFY-005","default.png","24","2020-06-06", "2020-06-12"]}'
```

<br/>



# status조회

```javascript
docker exec -e "CORE_PEER_LOCALMSPID=HFTeam1MSP" -e "CORE_PEER_MSPCONFIGPATH=/var/hyperledger/users/msp" peer0.HFTeam1 peer chaincode query -o orderer0.ordererorg:7050 -C team1channel -n rot02 -c '{"Args":["queryStatus", "TEST_S1"]}'
```

<br/>