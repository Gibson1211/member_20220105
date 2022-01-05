package com.icia.member.repository;

import com.icia.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<해당Entity클래스이름, PK타입>, 공식임
// JpaRepository를 상속받아 사용하는 경우 @Repository 어노테이션은 필요가 없음
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {


}
