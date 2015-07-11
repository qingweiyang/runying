package com.runying.util;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 这是将汉字转为拼音的工具类
 * 
 * @author yqw
 *
 */
public class Pinyin {

	public static void main(String[] args) {
		List<String> res = toPinyin("");
		if(res == null)
			return;
		for(String s : res) {
			System.out.print(s + ",");
		}
	}
	
	/**
	 * 将中文字符串转为拼音首字母，有多音字存在；如 “重庆” 将返回 "zq cq"
	 * 
	 * @param word
	 * @return
	 */
	public static String toPinyinString(String word) {
		if(word == null || word.equals("")) {
			return "";
		}
		
		List<String> pinyins = toPinyin(word);
		String str = "";
		for(String s : pinyins) {
			str += s + " ";
		}
		return str;
	}
	
	/**
	 * 将中文字符串转为拼音首字母，有多音字存在；如 “重庆” 将返回 {"zq", "cq"}
	 * 
	 * @param word
	 * @return
	 */
	public static List<String> toPinyin(String word) {
		if(word == null || word.length() == 0) {
			return null;
		}
		
		String first[] = firstChar(PinyinHelper.toHanyuPinyinStringArray(word.charAt(0)));
		if(first == null) {
			first = new String[1];
			first[0] = word.charAt(0) + "";
		}
		for(int i = 1 ; i < word.length() ; i++) {
			String tmp[] = PinyinHelper.toHanyuPinyinStringArray(word.charAt(i));
			if(tmp == null) {
				tmp = new String[1];
				tmp[0] = word.charAt(i) + "";
			}
			first = combineDouble(first, tmp);
		}
		
		//这里有大量重复（pinyin4j自身问题，嗯，为啥 “啊” 转换拼音有5种呢...a5,a1,a2,a3,a4, ），去重 
		List<String> res = new ArrayList<String>();
		for(String s : first) {
			if(!res.contains(s)) {
				res.add(s);
			}
		}
		return res;
	}

	/**
	 * 取str1与str2.charAt(0) 组合
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	private static String[] combineDouble(String str1[], String str2[]) {
		String res[] = new String[str1.length * str2.length];
		
		int index = 0;
		for(String s1 : str1) {
			for(String s2 : str2) {
				res[index++] = "" + s1 + s2.charAt(0);
			}
		}
		return res;
	}
	
	private static String[] firstChar(String str[]) {
		if(str == null || str.length == 0) {
			return null;
		}
		
		for(int i = 0 ; i < str.length ; i++) {
			str[i] = str[i].charAt(0) + "";
		}
		return str;
	}
}
