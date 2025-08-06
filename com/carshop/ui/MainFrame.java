package com.carshop.ui;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import com.carshop.service.CarManager;

public class MainFrame extends JFrame {
//	역할: 프로그램 최초 시작 화면 (로고, 상점명, 개발자명)
//	해야 할 일:
//	JFrame 생성 및 기본 설정 (크기, 종료 동작 등)
//	상단에 로고 이미지 표시 (이미지 크기 조절 포함)
//	중앙에 상점 이름 JLabel
//	하단에 개발자명과 [시작하기] 버튼 배치
//	버튼 클릭 시 CarGUI 또는 로그인 창으로 화면 전환 연결
	private CarManager manager;  // 🔹 필드 추가

    public MainFrame(CarManager manager) {
        this.manager = manager;  // 🔹 전달받은 매니저 저장
        setTitle("CarShop 시스템 시작화면");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 상단 로고
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String imagePath = "C:\\Java_Programming\\workspace\\Inventory_Management_System\\images\\logo.png";
        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(300, 120, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            logoLabel.setText("로고 이미지 없음");
            logoLabel.setFont(new Font("맑은 고딕", Font.ITALIC, 16));
        }

        // 🔹 중앙 상호명
        JLabel shopNameLabel = new JLabel("🚗 CarShop 자동차 상점 🚗", SwingConstants.CENTER);
        shopNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));

        // 🔹 하단 버튼
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JLabel devLabel = new JLabel("개발자: 김다훈", SwingConstants.CENTER);
        devLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));

        JButton startButton = new JButton("시작하기");
        startButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        startButton.addActionListener(e -> {
            dispose();                    // 현재 창 닫고
            new LoginFrame(manager);     // 🔹 CarManager 전달
        });

        bottomPanel.add(devLabel, BorderLayout.NORTH);
        bottomPanel.add(startButton, BorderLayout.SOUTH);

        add(logoLabel, BorderLayout.NORTH);
        add(shopNameLabel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
