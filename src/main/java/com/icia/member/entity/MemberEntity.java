package com.icia.member.entity;

import com.icia.member.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
    // Entity에는 절대 절대 언더바(_)를 쓰면 안된다.
    // 카멜케이스로만 작성을 해야 한다.
@Entity
@Getter
@Setter
@Table(name = "member_table")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(name = "member_id") //별도 컬럼이름 지정 시 사용
    private Long id;

    // memberEmail: 크기 50, unique
    // 카멜케이스로 작성한 경우 db의 명칭은 대문자 앞에서 _소문자 형태로 변경되어 입력된다.
    // memberEmail -> member_email로 db에 입력
    @Column(length = 50, unique = true)
    private String memberEmail;

    // memberPassword: 크기 20
    @Column(length = 20)
    private String memberPassword;

   // @Column을 생략하는 경우 default 길이는 255로 지정됨됨    private String memberName;
   private String memberName;

    /*
        DTO클래스 객체를 전달받아 Entity 클래스 필드값으로 세팅하고
        Entity 객체를 리턴하는 메서드 선언

        static 메서드(정적 메서드)

     */

    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        return memberEntity;
    }

}
