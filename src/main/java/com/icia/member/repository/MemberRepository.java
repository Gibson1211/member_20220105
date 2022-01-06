package com.icia.member.repository;

import com.icia.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<해당Entity클래스이름, PK타입>, 공식임
// JpaRepository를 상속받아 사용하는 경우 @Repository 어노테이션은 필요가 없음
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // 이메일을 조건으로 회원을 조회하는 메서드를 정의(select * from member_table where member_email=?)
    /*
        메서드 리턴타입: MemberEntity(JPA의 리턴은 무조건 Entity)
        메서드 이름: findByMemberEmail(JPA에서 권고하는 이름을 사용)
        메서드 매개변수: String memberEmail
     */
    MemberEntity findByMemberEmail(String memberEmail);

}
