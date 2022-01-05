package com.icia.member.service;

import com.icia.member.dto.MemberSaveDTO;
import com.icia.member.entity.MemberEntity;
import com.icia.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository mr;

    @Override
    public void save(MemberSaveDTO memberSaveDTO) {

        /*
        1. MemberSaveDTO -> MemberEntity에 옮기기(MemberEntity의 saveMember 메서드)
        2. MemberRepository의 save 메서드 호출하면서 MemberEntity 객체 전달달
        */

        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);
        mr.save(memberEntity);
    }
}
