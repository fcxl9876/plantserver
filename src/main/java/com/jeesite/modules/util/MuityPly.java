package com.jeesite.modules.util;

public class MuityPly {
	
	public static String getResult(String x1,String x2){
		
		char ch1[]=x1.toCharArray();
		char ch2[]=x2.toCharArray();
		
		int n1[]=new int[ch1.length];
		int n2[]=new int[ch2.length];
		int result[]=new int[ch1.length+ch2.length];
		
		for(int i=0;i<ch1.length;i++){
			n1[i]=ch1[i]-'0';
			
		}
		for(int i=0;i<ch2.length;i++){
			
			n2[i]=ch2[i]-'0';
		}
		for(int i=0;i<ch1.length;i++){
			for(int j=0;j<ch2.length;j++){
				
				result[i+j]+=n1[i]*n2[j];
			}
		}
		
		for(int i=result.length-1;i>0;i--){
			
			result[i-1]+=result[i]/10;
			result[i]=result[i]%10;
		}
		String string="";
		for(int i=0;i<result.length-1;i++){
			string+=result[i];
			
		}
		return string;
	}

}
