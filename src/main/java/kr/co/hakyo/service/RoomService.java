package kr.co.hakyo.service;

import java.util.List;
import java.util.Map;

public interface RoomService {

	public List<Map<String, Object>> list(Map<String, Object> dataMap) throws Exception;
	public int totalCount(Map<String, Object> dataMap) throws Exception;
	public Map<String, Object> info(Long id) throws Exception;
	public int insert(Map<String, Object> dataMap) throws Exception;
	public int update(Map<String, Object> dataMap) throws Exception;
	public int delete(Long id) throws Exception;

}
