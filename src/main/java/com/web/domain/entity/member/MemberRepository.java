package com.web.domain.entity.member;

import com.web.domain.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 해당 인터퓨ㅔ이스가 리포지토리 임을 명시
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    // * findBy필드명( 데이터 ) 필드검색
    // 1. 이메일 이용한 엔티티 검색 메소드
    Optional<MemberEntity> findByMemail(String memail );          //extends JpaRepository<매핑할클래스명, @ID필드의 자료형>
}
