package com.web.domain.entity.Room;


import lombok.*;

import javax.persistence.*;

@NoArgsConstructor //빈생성자
@AllArgsConstructor // 풀생성자
@ToString //객체내 필드 정보확인
@Getter
@Setter // get set
@Builder //객체 생성 안정성 보장
@Entity //해당 연결된 DB의 테이블과 매핑[ 연경 ]
@Table(name = "roomimg") // 테이블 명  DB 테이블 이름
public class RoomImgEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int rimgno;
    private String rimg;

    @ManyToOne
    @JoinColumn(name="rno")
    @ToString.Exclude
    private RoomEntity roomEntity;

}