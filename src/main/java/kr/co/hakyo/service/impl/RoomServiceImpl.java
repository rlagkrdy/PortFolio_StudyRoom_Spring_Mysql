package kr.co.hakyo.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.co.hakyo.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private SqlSession sqlSession;

	public List<Map<String, Object>> list(Map<String, Object> dataMap) throws Exception {
		return sqlSession.selectList("Room.list", dataMap);
	}
	
	public int totalCount(Map<String, Object> dataMap) throws Exception {
		return sqlSession.selectOne("Room.totalCount", dataMap);
	}

	public Map<String, Object> info(Long id) throws Exception {
		return (Map<String, Object>)sqlSession.selectOne("Room.info", id);
	}

	public int insert(Map<String, Object> dataMap) throws Exception {
		return sqlSession.insert("Room.insert", dataMap);
	}

	public int update(Map<String, Object> dataMap) throws Exception {
		return sqlSession.update("Room.update", dataMap);
	}

	public int delete(Long id) throws Exception {
		return sqlSession.delete("Room.delete", id);
	}

}
