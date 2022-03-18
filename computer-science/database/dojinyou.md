# Database 면접 질문

## 데이터베이스 기본 및 설계
<details>
  <summary>데이터베이스를 사용하는 이유는 무엇인가요?</summary>
  </br>
  <p>데이터베이스 이전 파일 시스템에서 데이터를 관리할 경우 발생하는 데이터의 종속성, 중복성, 무결성의 문제점들을 해결하기 위해서 사용하게 되었습니다.</p>
  <p>
    데이터베이스 특징은 다음과 같습니다.
    <ul>
      <li>데이터의 독립성을 보장할 수 있습니다. 데이터베이스의 성능 향상을 위한 데이터베이스의 변화가 생기더라도 응용프로그램을 수정하지 않아도 됨을 의미합니다.</li>
      <li>데이터의 여러 경로로 생성되어 입력되는 상황에서 유효성을 검증하여 데이터의 무결성을 보장할 수 있습니다. </li>
      <li>사용자들의 인가와 권한 부여를 통해 데이터베이스 내 자원을 접근을 제한하여 데이터의 보안성을 지킬 수 있습니다.</li>
      <li>연관된 정보를 논리적 구조로 관리하여 데이터 변경에 따른 불일치성을 배제할 수 있습니다. 이를 통해 데이터의 일관성을 보장합니다.</li>
      <li>데이터 베이스를 통해 데이터를 통합적으로 관리함으로써, 데이터의 중복을 최소화할 수 있습니다.</li>
    </ul>
  </p>
</details>

<details>
  <summary>Key란 무엇이고 어떤 종류가 있는 지 설명해주세요.</summary>
  </br>
  <p>Key란 Tuple을 구분할 수 있는 기준이 되는 Attribute를 의미합니다.</p>
  <p>Key의 종류에는 Candidate key(후보키), Primary Key(기본키), Alternate key(대체키), Super key(슈퍼키), Foreign Key(외래키)가 있습니다.</p>
  <p>
    <ul>
      <li>Candidate Key의 경우에는 Tuple을 유일하게 식별하기 위해 사용되는 Attribute(속성)들의 부분 집합으로 유일성과 최소성을 만족해야합니다.
      유일성은 key로 하나의 tupe을 유일하게 식별할 수 있다는 것을 의미하며, 최소성은 꼭 필요한 attribute(속성)로 구성된 것을 의미합니다.</li>
      <li>Candiate key에서 선택된 하나의 key가 Primary key(기본키)입니다. 기본키는 Null값을 가질 수 없으며, 중복된 값을 추가할 수 없습니다.</li>
      <li>Primary Key를 제외한 나머지 Candiate Key를 Alternate key라고 합니다.</li>
      <li>Candiate key에서 최소성을 만족하지 못하는 키의 경우 Super Key라고 합니다.</li>
      <li>Foreign key는 다른 Relation(Entity, Table)의 Primary Key를 참조하고 있는 Attribute를 의미합니다</li>
    </ul>
  </p>
</details>

<details>
  <summary>정규화에 대해서 설명해주세요.</summary>
  </br>
  <p>정규화는 데이터의 중복방지, 무결성을 충족시키기 위해 데이터베이스를 설계하는 것을 의미합니다.</p>
  <p>
    <ul>
      <li>제1정규화(1NF)는 반복되는 데이터가 없도록 테이블을 분리하고, 분리된 테이블의 모든 데이터가 식별자로 완전히 구분되도록 하는 것을 말합니다.</li>
      <li>제2정규화(2NF)는 Key가 아닌 모든 컬럼들은 key 전체에 종속되도록 하는 것을 의미합니다. 즉, pk의 일부로 식별할 수 있는 데이터가 있다면 분리해야하는 것입니다.</li>
      <li>제3정규화(3NF)는 key가 아닌 모든 컬럼들은 key 전체에만 종속되어야 한다는 것을 의미합니다. 즉, key가 아닌 컬럼이 key가 아닌 다른 컬럼에 의해서 종속된다면 이는 분리되어야 한다는 것을 의미합니다.</li>
    </ul>
  </p>
</details>

<details>
  <summary>정규화가 되지 않았을 경우 발생할 수 있는 이상현상에 대해서 설명해주세요</summary>
  <p>
    <ul>
      <li>Insert를 위해서 불필요한 데이터를 추가해야지 삽입이 가능한 현상</li>
      <li>Update 시 일부 데이터만 변경하여 데이터의 불일치가 발생하는 현상</li>
      <li>Delete 시 꼭 필요한 데이터들이 함께 삭제되는 문제</li>
    </ul>
  </p>
</details>

<details>
  <summary>JOIN에 대해서 설명해주세요.</summary>
  </br>
  <p>두 개 이상의 테이블을 연결하여 데이터를 탐색하는 방법입니다.</p>
  <p>Join 방법으로는 inner join, outer join, cross join, self join이 있습니다.
    <ul>
      <li>inner join은 join 시 기준되는 attribute가 join에 참여하는 모든 테이블에 존재하는 데이터만 조인하는 것을 의미합니다.</li>
      <li>outer join의 경우에는 join시 기준 되는 attribute가 특정 테이블에만 존재하더라도 join을 하는 것을 의미합니다. 이때 특정 테이블에 대한 선택에 따라서 left outer join과 right outer join, full outer join으로 나눠지게 됩니다. left outer join과 right outer join은 특정 한 테이블에 대해서만 attribute의 존재 유무를 확인하는 것이고 full outer join의 경우 양쪽 테이블 모두 attribute 존재 유뮤를 확인하다는 차이가 있습니다.</li>
      <li>Cross Join의 경우 Join을 위한 기준 attribute에 대해서 두 테이블의 모든 조합 경우의 수를 표현하는 방식입니다. 위의 inner, outer join과 다르게 두 테이블의 기준 attribute가 다르더라도 출력될 수 있습니다.</li>
      <li>Self join의 경우 동일한 테이블 사이의 조인을 의미하며 SQL의 문법상으로 FROM절에 같은 Table이 2번 이상 나타나게 됩니다. 동일한 테이블은 테이블과 컬럼 이름이 모두 동일하기 때문에 식별을 위해 Alias(별칭)을 반드시 사용해야 합니다. </li>
    </ul>
  </p>
</details>

<details>
  <summary>JOIN의 동작 대해서 설명해주세요.</summary>
  </br>
  <p>두 개 이상의 테이블을 연결하여 데이터를 탐색하는 방법입니다.</p>
  <p>Join 방법으로는 inner join, outer join, cross join, self join이 있습니다.
    <ul>
      <li>inner join은 join 시 기준되는 attribute가 join에 참여하는 모든 테이블에 존재하는 데이터만 조인하는 것을 의미합니다.</li>
      <li>outer join의 경우에는 join시 기준 되는 attribute가 특정 테이블에만 존재하더라도 join을 하는 것을 의미합니다. 이때 특정 테이블에 대한 선택에 따라서 left outer join과 right outer join, full outer join으로 나눠지게 됩니다. left outer join과 right outer join은 특정 한 테이블에 대해서만 attribute의 존재 유무를 확인하는 것이고 full outer join의 경우 양쪽 테이블 모두 attribute 존재 유뮤를 확인하다는 차이가 있습니다.</li>
      <li>Cross Join의 경우 Join을 위한 기준 attribute에 대해서 두 테이블의 모든 조합 경우의 수를 표현하는 방식입니다. 위의 inner, outer join과 다르게 두 테이블의 기준 attribute가 다르더라도 출력될 수 있습니다.</li>
      <li>Self join의 경우 동일한 테이블 사이의 조인을 의미하며 SQL의 문법상으로 FROM절에 같은 Table이 2번 이상 나타나게 됩니다. 동일한 테이블은 테이블과 컬럼 이름이 모두 동일하기 때문에 식별을 위해 Alias(별칭)을 반드시 사용해야 합니다. </li>
    </ul>
  </p>
</details>

## Index
<details>
  <summary>데이터베이스에서 인덱스를 사용하는 이유 및 장단점에 대해 설명해주세요.</summary>
  </br>
  <p>데이터베이스에서 인덱스를 사용하는 이유는 검색성능을 향상시키기 위함입니다.</p>
  <p>하지만 검색성능을 실질적으로 향상시키기 위해서는 해당 쿼리가 index를 사용하는지, 카디널리티, Selectivity 같은 요소들이 고려된 인덱스가 생성되어야 합니다.</p>
  <p>일반적인 경우의 장점으로는 빠른 검색 성능을 들 수 있습니다.</p>
  <p>일반적인 경우의 단점으로는 인덱스를 구성하는 비용 즉, 추가, 수정, 삭제 연산시에 인덱스를 형성하기 위한 추가적인 연산이 수행됩니다.</p>
  <p>따라서, 인덱스를 생성할 때에는 트레이드 오프 관계에 놓여있는 요소들을 종합적으로 고려하여 생성해야합니다.</p>
</details>

<details>
  <summary>데이터베이스에서 인덱스 관리를 위해서 어떤 자료구조를 사용하고 있는 지 말씀해주세요</summary>
  </br>
  <p>인덱스가 되는 컬럼의 값을 B-tree 계열의 자료구조를 이용해서 저장 및 탐색합니다. Hash Table의 자료구조를 이용하여 Index를 지원하는 경우도 있는 데, 이는 데이터의 크기가 작고 매우 빠른 속도를 요구할 경우 적용할 수 있습니다. 다만, Hash Table의 경우 등호 연산에 최적화 되어 있는 자료구조이기 때문에 부등호 연산시에 속도가 느려질 수 있습니다.</p>
</details>


## Transaction
<details>
  <summary>트랜잭션에 대해서 설명해주세요.</summary>
  </br>
  <p>트랜잭션이란 데이터베이스의 상태를 변화시키는 하나의 논리적인 작업 단위라고 할 수 있으며, 트랜잭션에는 여러개의 연산이 수행될 수 있습니다.</p>
  <p>트랜잭션은 수행중에 한 작업이라도 실패하면 전부 실패하고, 모두 성공해야 성공이라고 할 수 있습니다.</p>
</details>

<details>
  <summary>ACID에 대해서 설명해주세요.</summary>
  </br>
  <p>ACID는 트랜잭션이 안전하게 수행된다는 것을 보장하기 위한 성질입니다.</p>
  <p>
    <ul>
      <li>Atomicity(원자성): 트랜잭션의 연산은 모든 연산이 완벽히 수행되어야 하며, 한 연산이라도 실패하면 트랜잭션은 실패해야 합니다.</li>
      <li>Consistency(일관성): 트랜잭션은 유효한 상태로만 변경될 수 있습니다.</li>
      <li>Isolation(고립성): 트랜잭션은 동시에 실행될 경우 다른 트랜잭션에 의해 영향을 받지 않고 독립적으로 실행되어야 합니다.</li>
      <li>Durability(내구성): 트랜잭션이 커밋된 이후에는 시스템 오류가 발생하더라도 커밋된 상태로 유지되는 것을 보장해야 합니다. (일반적으로 비휘발성 메모리에 데이터가 저장되는 것을 의미)</li>
    </ul>
  </p>
</details>

<details>
  <summary>트랜잭션 격리 수준(Transaction Isolation Levels)에 대해서 설명해주세요.</summary>
  </br>
  <p>트랜잭션 격리수준은 고립도와 성능의 트레이드 오프를 조절합니다.</p>
  <p>
    <ul>
      <li>READ UNCOMMITTED: 다른 트랜잭션에서 커밋되지 않은 내용도 참조할 수 있다.</li>
      <li>READ COMMITTED: 다른 트랜잭션에서 커밋된 내용만 참조할 수 있다.</li>
      <li>REPEATABLE READ: 트랜잭션에 진입하기 이전에 커밋된 내용만 참조할 수 있다.</li>
      <li>SERIALIZABLE: 트랜잭션에 진입하면 락을 걸어 다른 트랜잭션이 접근하지 못하게 한다.(성능 매우 떨어짐)</li>
    </ul>
  </p>
</details>

<details>
  <summary>낮은 단계의 Isolation Level을 적용할 때 발생할 수 있는 현상들은 어떤 게 있나요?</summary>
  </br>
  <p>
    <ul>
      <li>Dirty Read: 커밋되지 않은 수정 중인 데이터를 다른 트랜잭션에서 읽을 수 있도록 허용할 때 발생하는 현상.   
      어떤 트랜잭션에서 아직 실행이 끝나지 않은 다른 트랜잭션에 의한 변경사항을 보게되는 경우</li>
      <li>Non-Repeatable Read: 한 트랜잭션에서 같은 쿼리를 두번 수행할 때, 그 사이에 다른 트랜잭션 값을 수정 또는 삭제하면서 두 쿼리의 결과가 상이하게 나타나는 일관성이 깨진 현상</li>
      <li>Phantom Read: 한 트랜잭션 안에서 일정 범위의 레코드를 두번 이상 읽었을 때, 첫번째 쿼리에서는 없던 레코드가 두번째 쿼리에서 나타나는 현상</li>
    </ul>
  </p>
</details>

<details>
  <summary>UNDO 복구와 REDO복구의 차이점에 대해서 설명해주세요</summary>
  </br>
  <p>UNDO 복구는 이전 상태로 돌아가는 복구로 수정된 데이터들에 대해서 수정되기 전 데이터로 변경시키는 복구를 의미합니다.</p>
  <p>REDO 복구는 UNDO 복구와 반대로 이미 commit된 트랜잭션의 수정을 재반영하는 복구작업을 REDO 복구라고 합니다.</p>

  <p>DBMS는 디스크에 저장된 데이터를 메모리에서 처리할 수 있도록 페이지단위로 읽고 작업을 처리하게 됩니다. 이때 아직 완료되지 않은 트랜잭션이 수정한 페이지들이 있게 됩니다. 이러한 트랜잭션 완료 전 수정된 페이지들을 이전 상태로 되돌리는 작업이 UNDO 작업입니다.</p>
  <p>UNDO 작업의 경우 DBMS의 buffer 관리자의 정책에 따라 수정된 페이지를 디스크에 쓸 수 있습니다. 이를 STEAL 정책이라고 하며, 일반적으로 DBMS가 채택하는 버퍼 관리 정책입니다. 이러한 STEAL 정책 사용 시에는 수정된 페이지가 메모리뿐만 아니라 디스크에도 저장 있기 때문에 UNDO 로깅과 복구가 필요합니다.</p>
  <p>
    UNDO 데이터는 다음과 같은 값이 기록 됩니다.
    <ul>
      <li>Insert 시에는 insert된 데이터의 row id가 기록됩니다.</li>
      <li>Update 시에는 update된 데이터 row의 변경 전 값이 기록됩니다.</li>
      <li>Delete 시에는 delete된 데이터의 모든 값이 기록됩니다.</li>
    </ul>
  </p>
  <p>트랜잭션의 일관성 원칙에 따라서커밋한 트랜잭션의 수정은 유지되어야 합니다. 트랜잭션이 종료되는 시점에 해당 트랜잭션의 수정한 페이지들을 디스크에 쓰는 지 여부에 따라 영향을 받게 됩니다.</p>
  <p>수정했던 모든 페이지를 트랜잭션 커밋 시점에 디스크에 반영하는 정책을 FORCE 정책이라고 합니다. 이와 다르게 커밋 시점에 디스크에 반영하지 않는 정책은 NO-FORCE 정책이라고 합니다. FORCE 정책 사용 시에는 트랜잭션 완료 시 모든 수정데이터가 디스크에 반영되기 떄문에 REDO 복구가 필요 없습니다. 하지만 NO-FORCE 정책의 경우에는 커밋한 트랜잭션의 내용이 아직 디스크 반영되지 않았기 때문에 반드시 REDO 복구가 필요하게 됩니다. 일반적으로 DBMS는 NO-FORCE 정책을 채택하고 있습니다. 추가적으로 FORCE 정책의 경우에는 백업으로부터 복구 시에는 REDO 복구가 요구됩니다.</p>

</details>

## CAP이론
<details>
  <summary>CAP 이론과, Eventual Consistency에 대해서 설명해주세요.</summary>
  </br>
  <p>CAP 이론은 분산 환경에서 모두를 만족하는 시스템은 없다는 이론입니다.</p>
  <p>
    <ul>
      <li>Consitenty(일관성): ACID의 일관성과는 약간 다릅니다. 모든 노드가 같은 시간에 같은 데이터를 보여줘야 한다는 것입니다.</li>
      <li>Availability(가용성): 모든 동작에 대한 응답이 리턴되어야 합니다.</li>
      <li>Partition Tolerance(분할 내성): 시스템 일부가 네트워크에서 연결이 끊기더라도 동작해야 합니다.</li>
    </ul>
  </p>
  <p>CAP는 해당 시스템이 이거다 하고 말하기 곤란한게 어떻게 클러스터링 하느냐에 따라 달라질 수 있습니다. 그렇기 때문에 어떤 전략을 취할 때 어떤 것을 선택했는가를 잘 알아야 합니다. (단순히 MySQL이 CA입니다. 보다는 어떤 이유로 CA인지 근거를 생각해보기) 그리고 어느정도 한계가 있는 이론이고 PACELC 이론이라고 또 있습니다.</p>
  <p>Eventual Consistency는 이 Consistency를 보장해주지 못하기 때문에 나온 개념으로, Consistency를 완전히 보장하지는 않지만, 결과적으로 언젠가는 Conssistency가 보장됨을 의미합니다.</p>
</details>

## RDBMS, NOSQL
<details>
  <summary>RDBMS vs NOSQL에 대해서 설명해주세요.</summary>
  </br>
  <p>RDBMS는 데이터베이스를 이루는 객체들의 릴레이션을 통해서 데이터를 저장하는 데이터베이스입니다. SQL을 사용해 데이터의 저장, 질의, 수정, 삭제를 할 수 있으며 데이터를 효율적으로 보관하는 것을 목적으로 하고 구조화가 굉장히 중요합니다.</p>
  <p>장점으로는 명확한 데이터 구조를 보장하고, 중복을 피할 수 있습니다.</p>
  <p>NOSQL은 RDBMS에 비해 자유로운 형태로 데이터를 저장합니다. 또한 수평확장을 할 수 있고 분산처리를 지원합니다. 다양한 형태의 NOSQL 데이터베이스가 있고, 대표적으로 key-value store, bigtable, dynamo, document db, graph db 등이 있습니다.</p>
  <p>둘은 대체될 수 있는 것이 아니고, 각각 필요한 시점에 적절히 선택해서 사용해야 합니다. 둘 다 같이쓰는 상호보완적인 존재가 될 수도 있습니다.</p>
</details>

<details>
  <summary>Redis에 대해서 간단히 설명해주세요.</summary>
  </br>
  <p>Redis는 key-value store NOSQL DB입니다. 싱글스레드로 동작하며 자료구조를 지원합니다. 그리고 다양한 용도로 사용될 수 있도록 다양한 기능을 지원합니다. 데이터의 스냅샷 혹은 AOF 로그를 통해 복구가 가능해서 어느정도 영속성도 보장됩니다.</p>
  <p>스프링에서는 세션을 관리하거나, 캐싱을 하는데에 자주 사용되는 것으로 알고 있습니다.</p>
  <p>일반적인 데이터베이스의 경우 하드디스크나 SSD에 저장되는 반면에, Redis는 메모리(RAM)에 저장하여 디스크 스캐닝을 하지 않아 매우 빠릅니다.</p>
  <p>RAM은 휘발성이 있기 때문에 이를 막기위해 백업과정이 존재합니다. </p>

  - snapshot : 특정 지점을 설정하고 디스크에 백업
  - AOF(Append Only File) : 명령(쿼리)들을 저장해두고, 서버가 다운되면 재실행해서 다시 만들어 놓는 것
  <p>value는 총 5가지의 타입이 가능합니다. text/binary같은 string타입, set타입, sorted set, Hash, List</p>
  <p>캐싱도 가능하여 실시간 채팅에 적합하며 세션 공유를 위한 클러스터링에도 활용됩니다.</p>

</details>

<details>
  <summary>Redis와 Memcached의 차이에 대해서 설명해주세요.</summary>
  </br>
  <p>Redis는 싱글 스레드 기반으로 동작하고, Memcached는 멀티스레드를 지원해서 멀티 프로세싱이 가능합니다.</p>
  <p>Redis는 다양한 자료구조를 지원하고, Memcached는 문자열 형태로만 저장합니다.</p>
  <p>Redis는 여러 용도로 사용할 수 있도록 다양한 기능을 지원합니다.</p>
  <p>Redis는 스냅샷, AOF 로그를 통해서 데이터 복구가 가능합니다.</p>
</details>

<details>
  <summary>MongoDB에 대해서 간단히 설명해주세요.</summary>
  </br>
  <p>

    - 도큐먼트 데이터베이스
      - 도큐먼트는 HTML과 같은 특정 형식의 태그 구조를 의미하며, mongoDB는 JSON(JavaScript Object Notation) 형식으로 데이터를 관리하므로 NoSQL 데이터베이스 중 도큐먼트 데이터베이스로 분류됩니다. 또한 도큐먼트는 mongoDB가 데이터를 저장하는 최소 단위이기도 합니다.
      - 도큐먼트는 필드와 값의 쌍으로 구성되며, 관계를 갖는 데이터를 중첩 도큐먼트와 배열을 사용하여 1개의 도큐먼트로 표현할 수 있습니다.
      - 데이터 입출력 시에는 JSON 형식의 도큐먼트를 사용하나 데이터베이스 저장 시에는 이진 포맷으로 인코딩한 BSON(Binary JSON) 형식의 도큐먼트로 변환되어 저장됩니다.
    - 유연한 스키마
      - 스키마의 선언 없이 필드의 추가와 삭제가 자유로운 Schema-less 구조입니다.
      - 관계형 데이터베이스는 테이블 내 모든 로우(Row)의 칼럼 집합이 동일하고 같은 칼럼은 동일한 데이터 타입을 갖는 정형 스키마이나, mongoDB는 컬렉션 내 모든 도큐먼트들의 필드 집합이 동일하지 않고 같은 필드라도 데이터 타입이 다를 수 있는 비정형 스키마입니다.
    - 비 관계형 데이터베이스
      - mongoDB는 관계형 데이터베이스의 관계(Relationship) 개념이 없는 비 관계형 데이터베이스입니다.
    - mongoDB는 조인(Join)을 지원하지 않으며, 대신 임베디드 방식의 도큐먼트 구조를 사용하거나 레퍼런스 방식의 도큐먼트 구조를 사용한 후 애플리케이션에서 조인해야 합니다.
    - 비 트랜잭션
    - mongoDB는 트랜잭션을 지원하지 않고 각각의 도큐먼트 단위로 처리됩니다.
    - 트랜잭션을 지원하지 않으므로 Commit 또는 Rollback 개념이 없으며 모두 Auto Commit으로 처리됩니다.

  </p>
</details>

## Elastic Search
<details>
  <summary>Elastic Search에 대해서 간단히 설명해주세요.</summary>
  </br>
  <p>Elastic Search는 자바로 개발된 오픈소스 검색엔진 입니다. 보통 단독으로 사용하기보다는 ELK 스택이라고 부르는 Logstash, Kibana, Beats를 추가적으로 사용합니다.</p>
  <p>Inverted Index 구조로 데이터를 저장해서, 전문(Full-text) 검색시에 RDBMS에 비해 뛰어난 성능을 보장합니다.</p>
  <p>다양한 용도로 사용할 수 있습니다. (데이터 저장, 문서 검색, 위치 검색, 머신 러닝 기반 검색, 로그 분석, 보안 감사 분석 등)</p>
</details>

<details>
  <summary>Elastic Search의 인덱스구조와 RDBMS의 인덱스 구조의 차이에 대해 설명해주세요.</summary>
  </br>
  <p>Elastic Search는 Inverted-Index 구조로 데이터를 저장합니다. 이는 책의 색인을 생각해보면 쉬운데, 특정 단어가 출현하는 doc을 저장하는 것입니다. 반면 RDBMS는 B-Tree와 그와 유사한 인덱스를 사용합니다. 데이터가 어디에 존재하는지 어떤 순서로 저장하는 지의 차이라고 생각합니다. RDBMS에도 다양한 인덱스 구조가 있으나 여기서 예로 든 것은 B-Tree 인덱스입니다.</p>
</details>

<details>
  <summary>Elastic Search의 키워드 검색과 RDBMS의 LIKE 검색의 차이에 대해 설명해주세요.</summary>
  </br>
  <p>Elastic Search의 키워드 검색은 document를 저장할 때 수행하는 알고리즘과 동일한 알고리즘으로 키워드를 분리합니다. 그 중에서 랭킹알고리즘을 통해서 가장 유사한 순서대로 결과를 나타냅니다.</p>
  <p>RDBMS에서의 LIKE 검색은 와일드카드로 시작하지 않는 경우에만 인덱스를 사용하고 나머지 경우는 전체를 탐색하기 때문에 상대적으로 느립니다.</p>
</details>



## SQL Injection
<details>
  <summary>SQL Injection 공격에 대해서 설명해주세요.</summary>
  </br>
  <p>SQL Injection은 조작된 SQL 쿼리문을 데이터베이스에 전달하여 비정상적인 명령을 실행시키는 공격 기법입니다.</p>
  <p>
    공격방법으로는 
    <ul>
      <li>인증 우회는 데이터베이스 로그인 시 ID와 Password를 input창에 입력하게 되는 데, 이때 다른 Query를 삽입하여 함께 입력하는 방식입니다.</li>
      <li> 데이터 노출: 시스템에서 발생하는 에러 메세지를 이용하여 공격하는 방법으로, 악의적으로 에러를 유발하면서 데이터베이스의 구조를 유추할 수 있고 해킹에 활용한다.</li>
    </ul>
  </p>
  <p>
    방어방법으로는 
    <ul>
      <li>input값을 받을 때, 특수문자 여부 검사하는 것</li>
      <li>SQL 서버 오류 발생 시, 해당하는 에러 메시지 감추기</li>
      <li>preparestatement 사용하기 : preparestatement를 사용하면 특수문자를 자동으로 escaping 해준다.</li>
    </ul>
  </p>
</details>

## OLTP, OLAP
<details>
  <summary>OLTP와 OLAP의 차이점에 대해서 말씀해주세요</summary>
  </br>
  <p>OLTP는 OnLine Transaction Processing의 약자로 트랜잭션이 중심이되는 처리방식을 의미합니다. 이는 데이터의 무결성과 트랜잭션을 효율화를 중요하게 생각하는 것을 의미합니다. OLAP는 OnLine Analytical Processing의 약자로 분석을 위한 프로세스로 최근 생산되는 데이터의 크기와 속도가 증가에 따라 데이터의 정확성보다는 속도를 중점으로 처리하는 것들을 의미합니다. </p>
</details>