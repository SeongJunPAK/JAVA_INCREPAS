package controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dao.ProductDao;
import vo.ProductVo;

@Controller
public class ProductController {

	@Autowired
	ServletContext application;
	
	ProductDao product_dao;

	public void setProduct_dao(ProductDao product_dao) {
		this.product_dao = product_dao;
	}
	
	//목록보기
	// /product/list.do
	// /product/list.do?category = com001
	@RequestMapping("/product/list.do")
	public String list(@RequestParam(value="category",defaultValue="com001") String category, Model model) {
		
		List<ProductVo> list = product_dao.selectList(category);
		
		//model -> DispatcherServlet -> request binding
		model.addAttribute("list",list);
		
		return "product/product_list";
	}
	
	@RequestMapping("/product/view.do")
	public String product_view(int p_idx, Model model) {
		
		ProductVo vo = product_dao.selectOne(p_idx);
		
		model.addAttribute("vo",vo);
		
		return "product/product_content";
	}
	
	//상품등록폼 띄우기
	@RequestMapping("/product/insert_form.do")
	public String insert_form() {
		
		return "product/product_reg_form";
	}
	
	//상품등록하기 
	@RequestMapping("/product/insert.do")
	
	public String insert(ProductVo vo, @RequestParam("p_image") MultipartFile [] p_image_array,
						Model model) throws Exception {
	
		//상대경로 -> 절대(저장)경로 
		String webPath = "/resources/images/";
		String absPath = application.getRealPath(webPath);
		
		for(int i=0;i<p_image_array.length;i++) {//i=0,1
			
			MultipartFile p_image = p_image_array[i];
			String filename = "no_file";
			
			if(!p_image.isEmpty()) {//업로드된 파일이 존재하면
				
				filename = p_image.getOriginalFilename();
				
				//저장경로 설정
				File f = new File(absPath,filename);
				
				//동일이름의 파일이 존재하는지 여부 
				if(f.exists()) {
					long tm = System.currentTimeMillis();
					//파일명 = 시간_파일명
					filename = String.format("%d_%s", tm,filename);
					
					//저장경로 재설정 
					f = new File(absPath,filename);
				}
				
				//임시경로파일 -> 지정된 위치로 복사 
				p_image.transferTo(f);
				
			}//end : if(p_image.isEmpty())
			
			if(i==0) {//첫번째 이미지는 p_image_s
				vo.setP_image_s(filename);
			}
			if(i==1) {//두번째 이미지는 p_image_l
				vo.setP_image_l(filename);
			}
			
		}//end for
		
		//DB insert
		int res = product_dao.insert(vo);
		
		//model -> 결과적으로 query로 이용
		model.addAttribute("caregory",vo.getCategory());
		
		return "redirect:list.do";
	}
	
	
}






