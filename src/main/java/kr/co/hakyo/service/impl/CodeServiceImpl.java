package kr.co.hakyo.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hakyo.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService  {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<Map<String, Object>> list(Map<String, Object> dataMap){
		return sqlSession.selectList("Code.list", dataMap);
	}
}
