package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.CartDao;
import vo.CartVo;
import vo.MemberVo;

@Controller
public class CartController {

	@Autowired
	HttpSession session;
	
	CartDao cart_dao;

	public void setCart_dao(CartDao cart_dao) {
		this.cart_dao = cart_dao;
	}
	
	@RequestMapping("/product/cart_delete.do")
	public String cart_delete(int c_idx) {
		
		int res = cart_dao.delete(c_idx);
		
		return "redirect:cart_list.do";
	}
	
	@RequestMapping("/product/cart_insert.do")
	@ResponseBody
	public Map cart_insert(CartVo paramVo) {
		
		CartVo vo = cart_dao.selectOne(paramVo);
		
		//Map -> JSON변환
		Map map = new HashMap();
		
		if(vo != null) {
			//4-1.얻어온 vo가 null이 아니면 이미 등록된 상태(결과전송 : exist)
			map.put("result", "exist");
		}else {
			//4-2. null이면 (등록이 안돼있으면 등록) -> 등록후(결과전송 : success)
			int res = cart_dao.insert(paramVo);
			
			map.put("result","success");
		}
		
		return map;
	}
	
	@RequestMapping("/product/cart_list.do")
	public String cart_list(Model model) {
		
		MemberVo user = (MemberVo) session.getAttribute("user");
		
		if(user==null) {
			
			//query로 이용
			model.addAttribute("reason","logout");
			
			return "redirect:list.do";
		}
		//회원일련번호
		int m_idx = user.getM_idx();
		
		//장바구니 목록 조회
		List<CartVo> list = cart_dao.selectList(m_idx);
		
		int total_amount = cart_dao.selectTotalAmount(m_idx);
		
		model.addAttribute("list",list);
		model.addAttribute("total_amount",total_amount);
		
		return "product/cart_list";
	}
	
	@RequestMapping("/product/cart_update.do")
	public String cart_update(CartVo vo) {
		
		int res = cart_dao.update(vo);
		
		return "redirect:cart_list.do";
	}
	
}
