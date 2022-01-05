package com.icia.member;

import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {

    /*
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

}
