package com.qx.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class CodeUtil {
	
	private static final AtomicInteger atomicInteger2 = new AtomicInteger(100);
	
	private static final AtomicInteger atomicInteger1 = new AtomicInteger(1000);
	
	private static final AtomicInteger atomicInteger3 = new AtomicInteger(1000);
	
	public static synchronized String getCardCode(String no) {
		Integer uuidHashCode = UUID.randomUUID().toString().hashCode();
		
		if (uuidHashCode < 0) {
			uuidHashCode = uuidHashCode * (-1);
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = simpleDateFormat.format(new Date());
		atomicInteger2.getAndIncrement();
		int i = atomicInteger2.get();
		return no+ i + date ;
	}
	
	public static synchronized String getGoodsCode(String no) {
		Integer uuidHashCode = UUID.randomUUID().toString().hashCode();
		
		if (uuidHashCode < 0) {
			uuidHashCode = uuidHashCode * (-1);
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
		String date = simpleDateFormat.format(new Date());
		atomicInteger1.getAndIncrement();
		int i = atomicInteger1.get();
		return no + date + i ;
	}
	
	public static synchronized String getEquipCode(String no) {
		Integer uuidHashCode = UUID.randomUUID().toString().hashCode();
		
		if (uuidHashCode < 0) {
			uuidHashCode = uuidHashCode * (-1);
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
		String date = simpleDateFormat.format(new Date());
		atomicInteger3.getAndIncrement();
		int i = atomicInteger3.get();
		return no + date + i ;
	}

	public static void main(String[] args) {
		System.out.println(getGoodsCode("SP"));
	}
}
