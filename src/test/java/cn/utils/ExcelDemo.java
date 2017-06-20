package cn.utils;

import java.io.IOException;

public class ExcelDemo {
	
	public static void main(String[] args) {
		try {
			ExecelUtils.createSheet("123", "1.xls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
