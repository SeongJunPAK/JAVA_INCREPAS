package vo;

public class MemberVo {

	int    m_idx;
	String m_name;
	String m_id;
	String m_pwd;
	String m_zipcode;
	String m_addr;
	String m_ip;
	String m_regdate;
	String m_grade;
	
	//조회용
	String m_pwd_hidden;
	
	public String getM_pwd_hidden() {
		
		/*
           0123456 <- i 
	m_pwd  1234   -> 12**
		   12345  -> 12***
		   123456 -> 123***
		   1234567-> 123**** 
		*/
		//전체길이
		int len = m_pwd.length();
		StringBuffer sb = new StringBuffer();
		
		//방법1)
		/*
		for(int i=0;i<len;i++) {
		
			if(i < len/2)
			   sb.append(m_pwd.charAt(i));
			else
			   sb.append("*");
		}
		*/
		
		//방법2)
		sb.append(m_pwd.substring(0, len/2));
		
		for(int i=0; i< (len-len/2);i++)
			sb.append("*");
		
				
		return sb.toString();
	}

	public MemberVo() {
		// TODO Auto-generated constructor stub
	}
	
	//추가
	public MemberVo(String m_name, String m_id, String m_pwd, String m_zipcode, String m_addr, String m_ip,
			String m_grade) {
		super();
		this.m_name = m_name;
		this.m_id = m_id;
		this.m_pwd = m_pwd;
		this.m_zipcode = m_zipcode;
		this.m_addr = m_addr;
		this.m_ip = m_ip;
		this.m_grade = m_grade;
	}

	//수정
	public MemberVo(int m_idx, String m_name, String m_id, String m_pwd, String m_zipcode, String m_addr, String m_ip,
			String m_grade) {
		super();
		this.m_idx = m_idx;
		this.m_name = m_name;
		this.m_id = m_id;
		this.m_pwd = m_pwd;
		this.m_zipcode = m_zipcode;
		this.m_addr = m_addr;
		this.m_ip = m_ip;
		this.m_grade = m_grade;
	}

	
	
	
	public int getM_idx() {
		return m_idx;
	}
	public void setM_idx(int m_idx) {
		this.m_idx = m_idx;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pwd() {
		return m_pwd;
	}
	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}
	public String getM_zipcode() {
		return m_zipcode;
	}
	public void setM_zipcode(String m_zipcode) {
		this.m_zipcode = m_zipcode;
	}
	public String getM_addr() {
		return m_addr;
	}
	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}
	public String getM_ip() {
		return m_ip;
	}
	public void setM_ip(String m_ip) {
		this.m_ip = m_ip;
	}
	public String getM_regdate() {
		return m_regdate;
	}
	public void setM_regdate(String m_regdate) {
		this.m_regdate = m_regdate;
	}
	public String getM_grade() {
		return m_grade;
	}
	public void setM_grade(String m_grade) {
		this.m_grade = m_grade;
	}
	
	
	
}
