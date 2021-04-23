package com.kh.spring.memo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.memo.model.dao.MemoDao;
import com.kh.spring.memo.model.vo.Memo;

@Service
public class MemoServicelmpl implements MemoService {

	@Autowired
	//인터페이스 타입으로 제어
	private MemoDao memoDao;
	
	@Override
	public List<Memo> selectMemoList() {
		List<Memo> list = memoDao.selectMemoList();
		return list;
	}

	@Override
	public int insertMemo(Memo memo) {
		int result = memoDao.insertMemo(memo);
		return result;
	}

}
