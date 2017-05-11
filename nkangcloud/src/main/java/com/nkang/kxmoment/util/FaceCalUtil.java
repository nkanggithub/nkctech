package com.nkang.kxmoment.util;

import com.nkang.kxmoment.baseobject.FaceObj;

public class FaceCalUtil {
	public static FaceObj toCal(FaceObj fo){
		float smile=Float.parseFloat(fo.getSmile());
		float age=Float.parseFloat(fo.getAge());
		float result =0;
		if(age<=18){
			result += 0.8*40 ;
		}else if(age>18&&age<=30){
			result += 1*40 ;
		}else if(age>30&&age<=40){
			result += 0.8*40 ;
		}else if(age>40&&age<=50){
			result += 0.6*40 ;
		}else if(age>50&&age<=60){
			result += 0.5*40 ;
		}else if(age>60){
			result += 0.2*40 ;
		}
		
		if(smile<=0.1){
			result += smile*10 ;
		}else if(smile>0.1&&smile<=0.2){
			result += smile*15 ;
		}else if(smile>0.2&&smile<=0.5){
			result += smile*20 ;
		}else if(smile>0.5&&smile<=0.8){
			result += smile*30 ;
		}else if(smile>0.8){
			result += smile*40 ;
		}
		
		
		
		
		
		result+=20;
		
		
		fo.setLevelNum(result);
		return fo;
	}
}
