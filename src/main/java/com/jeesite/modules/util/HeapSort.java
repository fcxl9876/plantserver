package com.jeesite.modules.util;

public class HeapSort {
	
	public static void main(String args[]){
		int []arr=new int[]{6, 1, 14, 7, 5, 8};
		//O(Nlog(N))
		for(int i=arr.length;i>1;--i){//O(N)		
			maxCreat(arr, i);//O(logN)
			swap(arr, 0, i-1);
		}
	 
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
			
		}
	
		for(int i=arr.length;i>1;--i){
			minCreat(arr, i);
			swap(arr, 0, i-1);
		}
		
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
		}
	}
	
	public static void minCreat(int []arr,int len){
		
		for(int i=len/2;i>=0;i--){
			
			mintz(arr,len,i);
		}
	}
    public static void maxCreat(int []arr,int len){
		
		for(int i=len/2;i>=0;i--){
			
			maxtz(arr,len,i);
		}
	}

	
	public static void swap(int []arr,int c,int current){
		int tmp=arr[c];
		arr[c]=arr[current];
		arr[current]=tmp;
		
	}
	
	public static void  maxtz(int []arr,int len,int current){
		
		int left,right,lagre;//下标
		left=2*current+1;
		right=2*current+2;
	     if(left<len && arr[left]>arr[current]){
	    	 lagre=left;
	     }else{
	    	 lagre=current;
	     }
	     if(right<len &&arr[right]>arr[lagre]){
	    	 lagre=right;
	     }
	     if(lagre!=current){
	    	 swap(arr,lagre,current);
	    	 maxtz(arr,len,lagre);
	     }
		
	}
	
	public static void  mintz(int []arr,int len,int current){
		
		int left,right,min;//下标
		left=2*current+1;
		right=2*current+2;
	     if(left<len && arr[left]<arr[current]){
	    	 min=left;
	     }else{
	    	 min=current;
	     }
	     if(right<len &&arr[right]<arr[min]){
	    	 min=right;
	     }
	     if(min!=current){
	    	 swap(arr,min,current);
	    	 mintz(arr,len,min);
	     }
		
	}

}
