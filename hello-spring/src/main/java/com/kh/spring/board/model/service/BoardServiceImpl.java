package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Attachment;
import com.kh.spring.board.model.vo.Board;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Override
	public List<Board> selectBoardList(Map<String, Object> param) {
		return boardDao.selectBoardList(param);
	}

	@Override
	public int getTotalContents() {
		return boardDao.getTotalContents();
	}

	/**
	 * @Transactional
	 * -class level
	 * -method level : 해당메소드 실행결과 Runtime예외가 던져지면, rollback.
	 * 
	 * 
	 * */
	
	//모든것은 rollback해달라
	//rollbackFor = Exception.class 익셉션이 발생하면 rollback
	//@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertBoard(Board board) {
		int result = 0;
		//1. board객체 등록
		result = boardDao.insertBoard(board);
		log.debug("board.no = {}", board.getNo());
		//2. attachment객체 등록
		//insert into attachment (no, board_no, original_filename, rename_filename)
		//values(seq_attachment_no.nextval, #{boardNo}, #{originalFileName},#{renamedFileName})
		if(!board.getAttachList().isEmpty()) {
			for(Attachment attach : board.getAttachList()) {
				attach.setBoardNo(board.getNo());
				result = boardDao.insertAttachment(attach);
			}
		}
		return result;
	}

	@Override
	public Board selectOneBoard(int no) {
		//게시글 조회
		Board board = boardDao.selectOneBoard(no);
		//첨부파일 목록조회
		List<Attachment> attachList = boardDao.selectAttchmentList(no);
		//board객체에 attachList setting
		board.setAttachList(attachList);
		return board;
	}

	@Override
	public Board selectOneBoardCollection(int no) {
		return boardDao.selectOneBoardCollection(no);
	}

	@Override
	public Attachment selectOneAttachment(int no) {
		return boardDao.selectOneAttachment(no);
	}
	
	

}
