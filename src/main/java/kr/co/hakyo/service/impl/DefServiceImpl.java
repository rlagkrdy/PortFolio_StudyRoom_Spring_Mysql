package kr.co.hakyo.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.co.hakyo.service.DefService;

@Service
public class DefServiceImpl implements DefService {

	@Autowired
	private SqlSession sqlSession;

	public List<Map<String, Object>> list(Map<String, Object> dataMap) throws Exception {
		return sqlSession.selectList("Def.list", dataMap);
	}
	
	public int totalCount(Map<String, Object> dataMap) throws Exception {
		return sqlSession.selectOne("Def.totalCount", dataMap);
	}

	public Map<String, Object> info(Long id) throws Exception {
		return (Map<String, Object>)sqlSession.selectOne("Def.info", id);
	}

	public int insert(Map<String, Object> dataMap) throws Exception {
		return sqlSession.insert("Def.insert", dataMap);
	}

	public int update(Map<String, Object> dataMap) throws Exception {
		return sqlSession.update("Def.update", dataMap);
	}

	public int delete(Long id) throws Exception {
		return sqlSession.delete("Def.delete", id);
	}

}
