package com.carshop.ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.carshop.service.CarManager;

public class LoginFrame extends JFrame {
//	역할: 사용자 로그인 처리
//	해야 할 일:
//	ID, 비밀번호 입력 필드 UI 구성
//	로그인 버튼과 이벤트 리스너 구현
//	로그인 성공 시 CarGUI 화면 호출
//	로그인 실패 시 오류 메시지 출력
	 private JTextField idField;
	    private JPasswordField passwordField;
	    private CarManager manager;  // 🔹 필드 추가

	    public LoginFrame(CarManager manager) {
	        this.manager = manager;  // 🔹 전달받기

	        setTitle("로그인");
	        setSize(400, 250);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout(10, 10));

	        JLabel titleLabel = new JLabel("CarShop 로그인", SwingConstants.CENTER);
	        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
	        add(titleLabel, BorderLayout.NORTH);

	        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
	        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
	        inputPanel.add(new JLabel("아이디:"));
	        idField = new JTextField();
	        inputPanel.add(idField);
	        inputPanel.add(new JLabel("비밀번호:"));
	        passwordField = new JPasswordField();
	        inputPanel.add(passwordField);
	        add(inputPanel, BorderLayout.CENTER);

	        JButton loginButton = new JButton("로그인");
	        loginButton.addActionListener(new LoginActionListener());

	        JPanel buttonPanel = new JPanel();
	        buttonPanel.add(loginButton);
	        add(buttonPanel, BorderLayout.SOUTH);

	        setVisible(true);
	    }

	    private class LoginActionListener implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String inputId = idField.getText();
	            String inputPw = new String(passwordField.getPassword());

	            if (inputId.equals("admin") && inputPw.equals("1234")) {
	                JOptionPane.showMessageDialog(LoginFrame.this, "로그인 성공!");
	                dispose();
	                new CarGUI(manager);  // 🔹 로그인 성공 시 CarManager 넘김
	            } else {
	                JOptionPane.showMessageDialog(LoginFrame.this, "로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.", "오류", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }
	}