package com.carshop.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class DateUtil {
//	 역할: 날짜 관련 편의 메서드 제공
//	 해야 할 일:
//	 오늘 날짜를 "yyyy-MM-dd" 문자열로 리턴하는 메서드 (getTodayDate()) 작성
	/**
     * 오늘 날짜를 "yyyy-MM-dd" 형식의 문자열로 반환
     */
    public static String getTodayDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return today.format(formatter);

    }

    // 필요 시 다른 날짜 관련 유틸 메서드도 추가 가능
}
