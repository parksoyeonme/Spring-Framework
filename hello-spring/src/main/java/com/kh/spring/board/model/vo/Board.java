package com.kh.spring.board.model.vo;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

   private int no;
   private String title;
   private String memberId;
   private String content;
   private Date regDate;
   //시분초 정보가 필요하면 util.date
   //아니면 sql.date로 ,,, 둘 다 가능0
   private int readCount;
  
   
   //위에는 테이블 구조
   //아래는 우리가 하기 수월하게 추가한 것 어플리케이션에만 존재하는 것
   
   private int attachCount; //첨부파일 개수
   //파일 여러개인것을 board에 담아둔다.
   private List<Attachment> attachList;
   
}