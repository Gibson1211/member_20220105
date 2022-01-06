package com.icia.member;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberTest {

    /*---
   테스트 목적은 MemberServiceImpl.save()에서 만든 코드가 정상동작을 하는지 확인
   기존 회원가입 테스트는  save.html에서 회원정보 입력 후 가입을 클릭해서 테스트
   그리고 DB에 데이터가 넘어왔는지 확인

    */

   @Autowired
    private MemberService ms;

   @Test
    @DisplayName("회원가입 테스트")
    public void memberSaveTest(){
       MemberSaveDTO memberSaveDTO = new MemberSaveDTO();
       memberSaveDTO.setMemberEmail("테스트회원이메일1");
       memberSaveDTO.setMemberPassword("테스트비번1");
       memberSaveDTO.setMemberName("테스트회원이름1");

       ms.save(memberSaveDTO);

   }

    @Test
    @DisplayName("회원조회 테스트")
    @Transactional // 테스트 시작할때 새로운 트랜잭션이 시작
    @Rollback // 테스트 종료 후 테스트 시 사용한 데이터를 삭제(롤백)
    public void memberDetailTest(){
        // 1.1 db에 데이터가 없을수도 있기에 새로운 회원을 등록하고
        // 해당회원의 번호(member_id)를 가져오는 형태로 진행되어야 테스트가 될 수 있음.
        MemberSaveDTO memberSaveDTO = new MemberSaveDTO("조회용회원이메일", "조회용회원비번", "조회용회원이름");
       // 1.2 테스트용 데이터를 DB에 저장하고 member_id를 가져옴.
        Long memberId = ms.save(memberSaveDTO);
        // when: 테스트 수행
            // 2. 위에서 가져온 회원번호를 가지고 조회 기능 수행
        MemberDetailDTO findMember = ms.findById(memberId);
        // then: 테스트 결과 검증
            // 3. 1번에서 가입한 회원의 정보와 2번에서 조회한 정보가 일치하면 테스트 통과
            //    일치하지 않으면 테스트 실패
        // memberSaveDTO의 이메일과 findMember의 이메일 값이 일치하는지 확인
        assertThat(memberSaveDTO.getMemberEmail()).isEqualTo(findMember.getMemberEmail());


    }

}
