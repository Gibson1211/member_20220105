package com.icia.member.service;

import com.icia.member.dto.MemberDetailDTO;
import com.icia.member.dto.MemberLoginDTO;
import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.entity.MemberEntity;
import com.icia.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository mr;

    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {

        /*
        1. MemberSaveDTO -> MemberEntity에 옮기기(MemberEntity의 saveMember 메서드)
        2. MemberRepository의 save 메서드 호출하면서 MemberEntity 객체 전달달
        */

        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);
        // 사용자가 입력한 이메일의 중복 여부 체크
        MemberEntity emailCheckResult = mr.findByMemberEmail(memberSaveDTO.getMemberEmail());
        // 이메일 중복체크 결과가 null이 아니라면 예외를 발생시킴.
        // 예외 종류: IllegalStateException, 예외메세지: 중복된 이메일입니다.!!
        if(emailCheckResult != null){
            throw new IllegalStateException("중복된 이메일입니다.");
        }
       return mr.save(memberEntity).getId();
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {
        /*
         1. MemberRepository로 부터 해당회원의 정보를 MemberEntity로 가져옴.
         2. MemberEntity를 MemberDetailDTO로 바꿔서 컨트롤러로 리턴.
         */
        // 1.
        MemberEntity member = mr.findById(memberId).get();

        //2
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(member);
        System.out.println("memberDetailDTO.toString() = " + memberDetailDTO.toString());
        return memberDetailDTO;
    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        // 1. 사용자가 입력한 이메일을 조건으로  DB에서 조회(select * from member_table where member_email=?)
        // member_email은 PK가 아니기에 repository에 정보가 없다.
        // 권고하는 pk는 항상 번호의 사용을 권고한다. email을 pk로 하게 되면 로직이 더 복잡해진다.
        //email을 조회하는 메서드를 repository에 먼저 정의를 해 줘야 한다.
        // 호출 후 Entity로 리턴을 받아줘야 한다.
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        // 2. 비밀번호 일치 여부 확인
        if(memberEntity != null) {
            if (memberLoginDTO.getMemberPassword().equals(memberEntity.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntityList = mr.findAll();
        // List<MemberEntity> -> List<MemberDetailDTO> 옮겨담기
        List<MemberDetailDTO> memberList = new ArrayList<>();
        for(MemberEntity m: memberEntityList) {
            // Entity 객체를 MemberDetailDTO로 변환하고 memberList에 담음.
               memberList.add(MemberDetailDTO.toMemberDetailDTO(m));
//             MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(m);
//             memberList.add(memberDetailDTO);
        }
        return memberList;
    }




}
