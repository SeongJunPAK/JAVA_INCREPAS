package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.ProductVo;

public class ProductDaoImpl implements ProductDao {

	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<ProductVo> selectList(String category) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("product.product_list",category);
	}

	@Override
	public ProductVo selectOne(int p_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("product.product_one",p_idx);
	}

	@Override
	public int insert(ProductVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("product.product_insert",vo);
	}

	
}
