package com.carshop.util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
public class ImageUtil {
//	역할: 이미지 크기 조절 및 처리
//	해야 할 일:
//	이미지 리사이징 메서드 (예: resizeImage(String path, int width, int height)) 작성
//	CarGUI, MainFrame에서 이미지 표시할 때 활용
	 /**
     * 경로로부터 이미지를 읽고 지정된 크기로 리사이징하여 ImageIcon으로 반환
     *
     * @param path 이미지 파일 경로
     * @param width 원하는 너비
     * @param height 원하는 높이
     * @return 리사이징된 ImageIcon 객체, 실패 시 null
     */
    public static ImageIcon resizeImage(String path, int width, int height) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("이미지 파일이 존재하지 않습니다: " + path);
            return null;
        }

        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}
