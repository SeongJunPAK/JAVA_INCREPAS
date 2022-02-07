package dao;

import org.apache.ibatis.session.SqlSession;

public class CampProductDaoImpl implements CampProductDao {

	SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	
}
