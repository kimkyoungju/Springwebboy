package com.web.service;

import com.web.domain.dto.BcategoryDto;
import com.web.domain.dto.BoardDto;
import com.web.domain.dto.NomemberDto;
import com.web.domain.dto.NwriteDto;
import com.web.domain.entity.bcategory.BcategoryEntity;
import com.web.domain.entity.bcategory.BcategoryRepository;
import com.web.domain.entity.board.BoardEntity;
import com.web.domain.entity.board.BoardRepository;
import com.web.domain.entity.member.MemberEntity;
import com.web.domain.entity.member.MemberRepository;
import com.web.domain.entity.nomember.NomemberEntity;
import com.web.domain.entity.nomember.NomemberRepository;
import com.web.domain.entity.nomember.NwriteEntity;
import com.web.domain.entity.nomember.NwriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Boardservice {
    // ------------1.전역변수---------------//
    @Autowired
    private BoardRepository boardRepository; // 게시물 리포지토리 객체 선언
    @Autowired
    private MemberRepository memberRepository; // 회원리포지토리 객체 선언
    @Autowired
    private HttpServletRequest request; // 요청객체 선언
    @Autowired
    private HttpServletResponse response; //응답객체 선언
    @Autowired
    private BcategoryRepository bcategoryRepository; //

    @Autowired
    private NwriteRepository nwriteRepository; //
    @Autowired
    private NomemberRepository repository;
    @Autowired
    private MemberService service;
    //첨부파일 경로
    String path = "C:\\Users\\504\\Desktop\\springweb\\Springwebboy\\src\\main\\resources\\static\\buploard\\";



    // @Transactional : 엔티티 DML 적용 할때 사용되는 어노테이션
    // 1. 메소드
            /*
                1. insert : boardRepository.save( 삽입할엔티티 )            BoardEntity entity
                2. select : boardRepository.findAll()                List<BoardEntity> elist
                3. select : boardRepository.findById( pk번호 )        Optional<BoardEntity> optional
                4. delete : boardRepository.delete( 삭제할엔티티 )
             */
    // ------------ 2. 서비스 ------------- //
    //0. 첨부파일 다운로드
    public  void filedownload(String filename) {
        String realfilename = "";
        String[] split = filename.split("_"); //1. _기준으로 자르기
        for(int i = 1; i < split.length; i++){ //uuid 제외한 반복문 돌리기
            realfilename +=split[i];            //3. 뒤자리 문자열
            //마지막 인덱스가 아니면
            if(split.length-1  != i){
                realfilename += "_";    //문자열[1] _문자열[2]_ 문자열[3].확장자명
            }
        }
        //1. 경로찾기
        String filepath = path + filename;
        //2. 헤더 구성
        try {
            response.setHeader(
                    "Content-Disposition", // 다운로드 형식
                    "attachment;filename=" + URLEncoder.encode(realfilename, "UTF-8"));
            File file = new File(filepath);

            //3.다운로드 스트림 []
            //1. 다운로드 할 파일 바이트 읽어오기
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));

            //2. 읽어온 바이트 저장
            byte[] bytes = new byte[(int)file.length()];
            //3.  파일의 길이만큼 읽어와서 바이트를 배열에 저장
            fin.read(bytes);
            //4.출력스트림
            BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream());
            //5. 응답하기
            fout.write(bytes);
            //6. 버퍼 초기화 혹은 스트림 닫기
            fout.flush(); fout.close(); fin.close();



        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //첨부파일 업로드
    @Transactional
    public  boolean fileupload(BoardDto boardDto,BoardEntity boardEntity){

        if(boardDto.getBfile() != null) {
            String uuid = UUID.randomUUID().toString();
            String filename = uuid + "_" + boardDto.getBfile().getOriginalFilename(); //업로드된 파일의 이름
            //1. pk+ 파일명 [uuid(범용 고유 식별자 클래스)]
            //2.uuid + 파일명

            //3. 업로드 날짜/ 시간 + 파일명
            //4 중복된 파일명 검색해서 파일명 뒤에 + 중복수 + 1


            //첨부파일명 db에 등록
            boardEntity.setBfile(filename);//난수 + 파일명 엔티티저장


            //업로드 3. 저장할 경로
            try {
                File uploadfile = new File(path + filename); //4. 경로 + 파일명[ 객체화]
                boardDto.getBfile().transferTo(uploadfile);  // 5. 해당 객체 경로로 업로드

            } catch (Exception e) {
                System.out.println("첨부파일 업로드");
            }
            return true;
        }else{
            return false;
        }


    }
     // 1. 게시물 쓰기
    @Transactional
    public boolean setboard(BoardDto boardDto) {

        MemberEntity memberEntity = service.getEntity();
        if(memberEntity == null) {return false;}
        System.out.println(boardDto);
        System.out.println(memberEntity);

        Optional<BcategoryEntity>optional = bcategoryRepository.findById(boardDto.getBcno());
        if(!optional.isPresent()){return false;}
        BcategoryEntity bcategoryEntity = optional.get();


         BoardEntity boardEntity = boardRepository.save(boardDto.toEntity());  // 1. dto --> entity [ INSERT ] 저장된 entity 반환
        if (boardEntity.getBno() != 0) {       // 2. 생성된 entity의 게시물번호가 0 이 아니면  성공
            //첨부파일 등록
            //.getOriginalFilename() : 해당 인터페이스에 연결(주소)된 파일의 이름 호출
            //.transferTo() : 파일이동 [사용자 pc --> 개발자 pc]
                //.transferTo(파일객체)
                //file : java 외 파일을 객체화 클래스
                    //new file("경로") : 해당 경로의 파일을 저장

            fileupload(boardDto ,boardEntity);
            //fk 대입
            boardEntity.setMemberEntity(memberEntity);
            //양방향 [pk필드에 fk연결]
            memberEntity.getBoardEntityList().add(boardEntity);
            boardEntity.setBcategoryEntity(bcategoryEntity);
            bcategoryEntity.getBoardEntityList().add(boardEntity);
            return true;
        } else {
            return false;
        } // 2. 0 이면 entity 생성 실패
    }

    // 2. 게시물 목록 조회
    @Transactional
    public List<BoardDto> boardlist(int bcno) {
        List<BoardEntity> elist = null;
        if(bcno == 0){ //0이면 전체보기
           elist =  boardRepository.findAll(); // 1. 모든 엔티티 호출한다.
        }else{ // 0이 아니면 선택된 카테고리별 보기
           BcategoryEntity bcEntity =  bcategoryRepository.findById(bcno).get();
            elist = bcEntity.getBoardEntityList();
        }

        List<BoardDto> dlist = new ArrayList<>(); // 2. 컨트롤에게 전달할때 형변환[ entity->dto ] : 역할이 달라서
        for (BoardEntity entity : elist) { // 3. 변환
            dlist.add(entity.toDto());
        }
        return dlist;  // 4. 변환된 리스트 dist 반환
    }

    // 3. 게시물 개별 조회
    @Transactional
    public BoardDto getboard(int bno) {
        Optional<BoardEntity> optional = boardRepository.findById(bno); // 1. 입력받은 게시물번호로 엔티티 검색 [ Optional]
        if (optional.isPresent()) {// 2. Optional 안에 있는 내용물 확인 .isPresent()
            BoardEntity boardEntity = optional.get(); // 3. 엔티티 꺼내기 .get();
            return boardEntity.toDto(); // 4.형변환 반환
        } else {
            return null; // 4. 없으면 null
        }
    }

    // 4. 게시물 삭제
    @Transactional
    public boolean delboard(int bno) {
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        if (optional.isPresent()) {
            BoardEntity entity = optional.get();
           //첨부파일 같이 삭제
            if(entity.getBfile() != null) {
                File file = new File(path + entity.getBfile());
                if (file.exists()) {
                    file.delete();
                }
            }


            boardRepository.delete(entity); // 찾은 엔티티를 삭제한다.
            return true;
        } else {
            return false;
        }
    }

    // 5. 게시물 수정 [ 첨부파일 1.첨부파일 있을때 -> 첨부파일변경, 2. 첨부파일없을때 -> 첨부파일 추가 ]
    @Transactional
    public boolean upboard(BoardDto boardDto) {
        // 1. DTO에서 수정할 PK번호 이용해서 엔티티 찾기
        Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());
        if (optional.isPresent()) {  // 2.
            BoardEntity entity = optional.get();

            //기존 첨부파일있을때


            //1. 수정할 첨부파일이 있을때--> 기존첨부파일 삭제후 새로운파일 업로드->
            if(boardDto.getBfile() != null){
                if(entity.getBfile() != null) {
                    File file = new File(path + entity.getBfile());
                    if (file.exists()) {
                        file.delete();
                    }
                     entity.setBfile(null); //db처리
                }
                    //기존 첨부파일 없을떄
                fileupload(boardDto, entity);
            }

            // * 수정처리 [ 메소드 별도 존재x /  엔티티 객체 <--매핑--> 레코드 / 엔티티 객체 필드를 수정 : @Transactional ]
            entity.setBtitle(boardDto.getBtitle());
            entity.setBcontent(boardDto.getBcontent());
            //entity.setBfile(boardDto.getBfile());
            return true;
        } else {
            return false;
        }
    }

    //6. 카테고리 등록
    public boolean setbcategory(BcategoryDto bcategoryDto) {
        BcategoryEntity bc = bcategoryRepository.save(bcategoryDto.toEntity());
        if (bc.getBcno() != 0) {
            return true;
        } else {
            return false;
        }
    }

    //7.모든 카테고리 출력
    public List<BcategoryDto> bcategoryList() {
        List<BcategoryEntity> entityList = bcategoryRepository.findAll();
        List<BcategoryDto>dtolist = new ArrayList<>();

        entityList.forEach(e -> dtolist.add(e.getDto()));
        return  dtolist;
    }


    //비회원글등록

    @Transactional //카테고리
    public boolean nomember(NomemberDto nomemberDto){
        NomemberEntity nc = repository.save(nomemberDto.entity());
        System.out.println(nomemberDto);
        if(nc.getNno() != 0){
            return true;
        }else{return false;}
    }


    @Transactional //
    public boolean nfileupload(NwriteDto nwriteDto ,NwriteEntity nwriteEntity){
        if(nwriteDto.getCfile()!=null){
            String uuid = UUID.randomUUID().toString();
            String cfilename = uuid +"_"+nwriteDto.getCfile().getOriginalFilename();
            //nc.setCfile(cfilename);
            //첨부파일명 db에 등록
            nwriteEntity.setCfile(cfilename);
            try {
                File upload = new File(path + cfilename);
                nwriteDto.getCfile().transferTo(upload);
            }catch (Exception e){System.out.println("첨부실패");}

        }
        return false;
    }





        public  boolean nwrite(NwriteDto nwriteDto) {

            NwriteEntity nc = nwriteRepository.save(nwriteDto.toEntity());
            System.out.println(nc.toString() + "111");
            Optional<NomemberEntity> optional = repository.findById(nwriteDto.getNno());
            if (!optional.isPresent()) {
                return false;
            }
            NomemberEntity nomemberEntity = optional.get();
            NwriteEntity nwriteEntity = nwriteRepository.save(nwriteDto.toEntity());
            if (nc.getWno() != 0) {

                nfileupload(nwriteDto, nwriteEntity);

                nwriteEntity.setNomemberEntity(nomemberEntity);

               //nomemberEntity.getNentityList().add(nomemberEntity);


                return true;
            } else {
                return false;
            }

        }
        //비회원 리스트
        public List<NomemberDto> clist() {
            List<NomemberEntity> entityList =repository.findAll();
            List<NomemberDto>dtolist = new ArrayList<>();
            System.out.println(dtolist+"11");
            System.out.println(entityList.toString());
            entityList.forEach(e -> dtolist.add(e.noDto()));
            return  dtolist;
        }





}
//for (int i = 0; i < entityList.size(); i++) {
//            BcategoryEntity e = entityList.get(i);
//            System.out.println(e.toString());
//        }