package com.itheima.utils;

import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 */
public final class UuidUtils {
	private UuidUtils(){}
	public static String getUuid(){
		return UUID.randomUUID().toString().replace("-","");
	}

	/*public static void main(String[] args) {
		System.out.println(UuidUtils.getUuid());
		System.out.println(UuidUtils.getUuid());
		System.out.println(UuidUtils.getUuid());
		System.out.println(UuidUtils.getUuid());
	}*/
	public static void main(String[] args) {
		//UUID 自带一套算法:算出一个36位的字符串
		//能保证所有人不重复= 绑定了mac地址(每一个人的mac不一样) + 时间戳 = 计算的公式
		String uuid = UUID.randomUUID().toString().replaceAll("-" , "");
		System.out.println(uuid.length());
		System.out.println(UUID.randomUUID().toString().replaceAll("-" , ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-" , ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-" , ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-" , ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-" , ""));
		System.out.println(UUID.randomUUID().toString().replaceAll("-" , ""));
	}

}
