package com.mysite.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	// URL �ο�.(URL ��û �߻��� ������ �޼���� url����)
	@RequestMapping("/hell")
	@ResponseBody // �Ʒ��� �޼����� ���� ����� ���ڿ� �� ��ü��.
	public String hello() {
		return "������ �°� ȯ���Ѵ� ������";
			
	}
	// ���� 
	// url�� : quiz�� ���� �޼��� quiz�� ����ð�
	// �ش� �������� ������� ȭ�鿡 ���� ���� �Դϴ� ��� �޼����� ���������.
	@RequestMapping("/quiz")
	@ResponseBody // �Ʒ��� �޼����� ���� ����� ���ڿ� �� ��ü��.
	public String quiz() {
		return "���� ���� �Դϴ�";
			
	}
	
	
	
}
