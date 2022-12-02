package com.web.service;

import com.web.domain.dto.MemberDto;
import com.web.domain.dto.OauthDto;
import com.web.domain.entity.member.MemberEntity;
import com.web.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Service // 해당 클래스가 service 명시
public class MemberService
        implements UserDetailsService ,
        OAuth2UserService<OAuth2UserRequest , OAuth2User> {
    //UserDetailsService : 일반회원 -->LoadUserByUsername
    //  OAuth2UserService<OAuth2UserRequest , OAuth2User>  : 소셜회원
    @Override // 로그인 성공한 소셜 회원정보 받는 메소드
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        System.out.println("1."+userRequest.toString());
        //1. 인증 결과 정보 요청
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
            OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
            System.out.println("2."+oAuth2User.toString());
        //2. oauth2 클라이언트 식별 [ 카카오 vs네이버 vs 구글]
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        System.out.println("3.oauth2 회사명 " + registrationId);
        //3. 회원정보 담는 객체
        String oauth2UserInfo = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

            System.out.println("회원정보" + oauth2UserInfo);
            System.out.println("인증결과" +oAuth2User.getAttributes());
            //4. 요청 정보 원본

            //4.dto처리
        OauthDto oauthDto = OauthDto.of(registrationId,oauth2UserInfo,oAuth2User.getAttributes());


        //*.DB 처리
        // 1 이메일로 엔티티 검색 [ 가입 or기존회원 구분]
        Optional<MemberEntity>optional
                = memberRepository.findByMemail(oauthDto.getMemail());
                MemberEntity memberEntity = null;
              if(optional.get().getMrol().equals(registrationId)){
                memberEntity = optional.get();
            }else{ // 기존회원이 아니면 [ 가입 ]
                memberEntity =  memberRepository.save(oauthDto.toentity());
            }


/*
        memberRepository.findByMemail(oauthDto.getMemail())
                .orElseThrow(()->);
*/


        //권한
        Set<GrantedAuthority>authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(memberEntity.getMrol()));

        //5. 반환
        MemberDto memberDto = new MemberDto();
            memberDto.setMemail(memberEntity.getMemail());
            memberDto.setAuthorities(authorities);
            memberDto.setAttributes(oauthDto.getAttributes());
        return memberDto;
    }
    //------------------전역객체------------//
    @Autowired
    private MemberRepository memberRepository; //리포지토리 객체
    @Autowired // 스프링 컨테이너 [메모리] 위임
    private HttpServletRequest request; //요청 객체

    // 메일전송 객체
    @Autowired
    private JavaMailSender javaMailSender;

    //-------------서비스 메소드------------??

   public MemberEntity getEntity() {
       //1. 로그인 정보확인 [ 세션 =loginMno]
       Object object = request.getSession().getAttribute("loginMno");
       if (object == null) {
           return null;
       }
       //2.로그인된 회원정보 호출
       int mno = (Integer) object;
       //3. 회원번호 ---> 회원정보 호출
       Optional<MemberEntity> optional = memberRepository.findById(mno);
       if (!optional.isPresent()) {
           return null;
       }
       MemberEntity memberEntity = optional.get();
       return memberEntity;

   }

    @Transactional
    public int setmember(MemberDto memberDto ){
        // 암호화 : 해시함수 사용하는 암호화 기법중 하나 [ BCrypt ]
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setMpassword( passwordEncoder.encode( memberDto.getPassword() ) );
        // 1. DAO 처리 [ insert ]
        MemberEntity entity = memberRepository.save( memberDto.toEntity() );
        // memberRepository.save( 엔티티 객체 ) : 해당 엔티티 객체가 insert 생성된 엔티티객체 반환
        // 회원 등급 넣어주기
        entity.setMrol("user");

        // 2. 결과 반환 [ 생성된 엔티티의 pk값 반환 ]
        return entity.getMno();
    }
    // 2. [ 시큐리티 사용시 ] 로그인 인증 메소드 재정의
    @Override
    public UserDetails loadUserByUsername(String memail ) throws UsernameNotFoundException {
        // 1. 입력받은 아이디 [ memail ] 로 엔티티 찾기
        MemberEntity memberEntity = memberRepository.findByMemail( memail )
                .orElseThrow( ()-> new UsernameNotFoundException("사용자가 존재하지 않습니다,") ); // .orElseThrow : 검색 결과가 없으면 화살표함수[람다식]를 이용한
            //소셜 회원도 [일반회원처럼 로그인하게 해줄건지]

        // 2. 검증된 토큰 생성 [일반회원]
        Set<GrantedAuthority>  authorities = new HashSet<>();
        authorities.add(
                new SimpleGrantedAuthority( memberEntity.getMrol() )
        ); // 토큰정보에 일반회원 내용 넣기
        // 3.
        MemberDto memberDto = memberEntity.toDto(); // 엔티티 --> Dto
        memberDto.setAuthorities( authorities );       // dto --> 토큰 추가
        return memberDto; // Dto 반환 [ MemberDto는 UserDetails 의 구현체 ]
        // 구현체 : 해당 인터페이스의 추상메소드[선언만]를 구현해준 클래스의 객체
    }



    //로그인 [ 시큐리티 사용시 필요없음 ]
   /* @Transactional
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

    }*/

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
   /* public int getloginMno() {
        Object object = request.getSession().getAttribute("loginMno");

        if (object != null) {
            return (Integer) object;
        } else {
            return 0;
        }

    }*/
    // 6. 로그인 여부 판단 메소드 [ principal 세션 ]
    public String getloginMno(){
        //1. 인증된 토큰 확인      [ SecurityContextHolder 인증된 토큰 보관소 ---> UserDetails(MemberDto) ]
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //2. 인증된 토큰 내용 확인
        Object principal = authentication.getPrincipal(); // Principal:접근주체 [ UserDetails(MemberDto) ]
        System.out.println("토큰 내용확인 : " + principal );
        // 3. 토큰 내용에 따른 제어
        if( principal.equals("anonymousUser") ){ // anonymousUser 이면 로그인전
            return null;
        }else{ // anonymousUser 아니면 로그인후
            MemberDto memberDto = (MemberDto) principal;
            return memberDto.getMemail()+"_"+memberDto.getAuthorities();
        }
    }
    //7. 로그아웃
   /* public int logout() {
        Object object = request.getSession().getAttribute("loginMno");
        if (object != null) {
            request.getSession().setAttribute("loginMno", null);
            return 1;
        }
        return 0;
    }*/
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

    //9. 인증코드발송
    public String getauth(String toemail){

        String auth ="";
        String html = "<html><body><h1>EZENWEB 회원가입 이메일 인증코드입니다</h1>";
        Random rendom = new Random(); //1. 난수 객체
        for(int i=0;i<6;i++){/* //2. 6회전
            char randchar  = (char)(rendom.nextInt(10)+48);*/ //48~57
            char randchar  = (char)(rendom.nextInt(26)+97); //97~122 : 알파벳 소문자
            auth += randchar;
        }
        html += "<div>인증코드 : "+auth+"</div>";
        html += "</body></html>";
        mailsend(toemail , "어쩔냉장고" ,auth);
        return auth;
    }
    //. 메일 전송 서비스
    public void mailsend(String toemail, String title, String content){

       try {
           //1. MIme프로토콜 객체 생성
           MimeMessage message = javaMailSender.createMimeMessage();
           //2. Mime 설정 객체 생성   new MimeMessageHelper (mime객체명 , 첨부파일여부 , 인코딩타입)
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "utf-8");
            //-------헬퍼에 세팅
           //3. 보내는 사람 정보
           mimeMessageHelper.setFrom("rlarudwn900@naver.com", "안녕은 영원한");
           //4. 받는 사람
           mimeMessageHelper.setTo(toemail);
           //5. 메일제목
           mimeMessageHelper.setSubject(title);
           //6. 메일 내용
           mimeMessageHelper.setText(content.toString(), true); // html형식
            //---------------------
           //7. 메일전송
           javaMailSender.send(message);
       }catch (Exception e){
           System.out.println("메일전송실패" +e);
       }
    }


}






