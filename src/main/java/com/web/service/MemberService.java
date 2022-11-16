package com.web.service;

import com.web.domain.dto.MemberDto;
import com.web.domain.entity.MemberEntity;
import com.web.domain.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // 해당 클래스가 service 명시
public class MemberService {

    //------------------전역객체------------//
    @Autowired
    private MemberRepository memberRepository; //리포지토리 객체
    @Autowired // 스프링 컨테이너 [메모리] 위임
    private HttpServletRequest request; //요청 객체


    //-------------서비스 메소드------------??
    @Transactional // 트랜잭션
    public int setmember(MemberDto memberDto) {
        //1. q비지니스 로직 [ 알고리즘 - 기능 ]
        //2. DAO처리 [Dto --> entity]
        MemberEntity entity = memberRepository.save(memberDto.toEntity());
        //memberRepository.save(엔티티 객체 ) : 해당 엔티티 객체가 insert 생성된 엔티티 객체
        //entity.setMemail("asd@asda");
        //2. 결과반환 [생성된 엔티티의 pk값 반환]
        return entity.getMno();
    }

    //로그인
    @Transactional
    public int getmember(MemberDto memberDto) {
        //Dao처리[ select] findById : 아이디 검색 , findAll :select
        // 모든 엔티티 = 레코드 호출 findAll :select * from]
        List<MemberEntity> entityList = memberRepository.findAll();
        System.out.println(memberDto);
        // 입력 데이터와 일치값 찾기
        for (MemberEntity entity : entityList) {
            System.out.println(entity.getMemail());
            if (entity.getMemail().equals(memberDto.getMemail())) { //엔티티 = 레코드의 이메일과 입력받은 이메일과 같으면
                if (entity.getMpassword().equals(memberDto.getMpassword())) { // 엔티티 = 레코드의 패스워드와 같으면
                    //로그인 성공시 : 세션부여[ 로그인 성공시 ' loginMno '이름으로 회원번호 세션 저장]
                    request.getSession().setAttribute("loginMno", entity.getMno());
                    return 1; // 로그인 성공
                } else {
                    return 2; // 패스워드 틀림
                }
            }
        }
        return 0; // 로그인 실패

    }

    //3. 비밀번호 찾기
    @Transactional
    public String getpassword(String memail) {
        //1. 모든 레코드/엔티티를 꺼내온다
        List<MemberEntity> entityList = memberRepository.findAll();
        //2. 리스트에 찾기
        for (MemberEntity entity : entityList) {
            if (entity.getMemail().equals(memail)) {
                return entity.getMpassword();
            }
        }
        return null;
    }

    //4. 회원탈퇴
    @Transactional
    public int setdelete(String mpassword) {
        //1 .로그인된 회원의 엔티티필요 !
        //1. 세션 호출
        Object object = request.getSession().getAttribute("loginMno");

        //세션확인
        if (object != null) {
            int mno = (Integer) object;   // 형변환
            //3. 세션에 있는 회원번호[pk]로 리포지토리 찾기 [findById : select * from ]
            Optional<MemberEntity> optional = memberRepository.findById(mno);
            if (optional.isPresent()) { // optional 객체내 엔티티 존재여부 판단

                //optional 클랙스 :null 관련 메소드 제공
                //4. optinal 객체에서 데이터 [엔티티] 빼오기
                MemberEntity entity = optional.get();

                //5. 탈퇴 [ delete ]
                memberRepository.delete(entity);
                //6. 세션 [ 세션삭제 == 로그아웃]
                request.getSession().setAttribute("loginMno", null);
                return 1;
            }

        }
        return 0; // 로그인 실�
    }

    @Transactional // 수정시 꼭필요
    //5. 회원수정

    public int setupdate(String mpassword) {
        //1. 세션호출

        Object object = request.getSession().getAttribute("loginMno");
        //2. 세션 존재여부 판단
        if (object != null) {
            int mno = (Integer) object;
            //3. pk값을 가지고 엔티티 검색
            //래퍼클래스
            Optional<MemberEntity> optional = memberRepository.findById(mno);
            //4. 검색된 결과 여부 판단
            if (optional.isPresent()) {
                MemberEntity entity = optional.get();
                //5. 찾은 엔티티의 필드값 변경 [update member set 필드명 = 값 where 필드명 = 값]
                entity.setMpassword(mpassword);
                return 1;
            }
        }
        return 0;
    }

    //6 세션 로그인 여부 판단
    public int getloginMno() {
        Object object = request.getSession().getAttribute("loginMno");

        if (object != null) {
            return (Integer) object;
        } else {
            return 0;
        }

    }

    //7. 로그아웃
    public int logout() {
        Object object = request.getSession().getAttribute("loginMno");
        if (object != null) {
            request.getSession().setAttribute("loginMno", null);
            return 1;
        }
        return 0;
    }
    //8. 회원목록 서비스
    public List<MemberDto>list(){
        //1. jpa 이용한 모든 엔티티 호출
      List<MemberEntity>list =  memberRepository.findAll();
        //2. 엔티티 --> DTO
            //Dto list 선언
        List<MemberDto>dtolist = new ArrayList<>();
        for(MemberEntity entity : list){
            dtolist.add(entity.toDto());    // 형변환

        }
        return dtolist;
    }
}





