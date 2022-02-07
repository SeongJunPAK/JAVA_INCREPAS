package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.CartVo;

public class CartDaoImpl implements CartDao {

	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<CartVo> selectList(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("cart.cart_list",m_idx);
	}

	@Override
	public CartVo selectOne(CartVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("cart.cart_one",vo);
	}

	@Override
	public int selectTotalAmount(int m_idx) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("cart.cart_total_amount",m_idx);
	}

	@Override
	public int insert(CartVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.insert("cart.cart_insert",vo);
	}

	@Override
	public int update(CartVo vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("cart.cart_update",vo);
	}

	@Override
	public int delete(int c_idx) {
		// TODO Auto-generated method stub
		return sqlSession.delete("cart.cart_delete",c_idx);
	}

}
