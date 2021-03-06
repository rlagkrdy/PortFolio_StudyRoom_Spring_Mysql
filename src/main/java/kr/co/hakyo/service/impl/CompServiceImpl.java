package kr.co.hakyo.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import kr.co.hakyo.service.CompService;

@Service
public class CompServiceImpl implements CompService {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public List<Map<String, Object>> list(Map<String, Object> dataMap) throws Exception {
		return sqlSession.selectList("Comp.list", dataMap);
	}
	
	public int totalCount(Map<String, Object> dataMap) throws Exception {
		return sqlSession.selectOne("Comp.totalCount", dataMap);
	}

	public Map<String, Object> info(Long id) throws Exception {
		return (Map<String, Object>)sqlSession.selectOne("Comp.info", id);
	}

	public int insert(Map<String, Object> dataMap) throws Exception {
		if(dataMap.get("COMP_PW") != null) {
			dataMap.put("COMP_PW", passwordEncoder.encode(dataMap.get("COMP_PW").toString()));
		}
		return sqlSession.insert("Comp.insert", dataMap);
	}

	public int update(Map<String, Object> dataMap) throws Exception {
		if(dataMap.get("COMP_PW") != null) {
			System.err.println(dataMap.get("COMP_PW").toString());
			System.err.println(passwordEncoder);
			dataMap.put("COMP_PW", passwordEncoder.encode(dataMap.get("COMP_PW").toString()));
		}
		return sqlSession.update("Comp.update", dataMap);
	}

	public int delete(Long id) throws Exception {
		return sqlSession.delete("Comp.delete", id);
	}

}
