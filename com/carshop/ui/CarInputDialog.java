package com.carshop.ui;

import com.carshop.model.Car;
import com.carshop.service.CarManager;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDate;

public class CarInputDialog extends JDialog {
//	역할: 자동차 정보 입력 창
//	해야 할 일:
//	자동차 정보 입력 필드(이름, 코드번호, 가격, 재고 수, 이미지 선택 등)
//	이미지 파일 선택 버튼과 JFileChooser 연결
//	등록 버튼 클릭 시 Car 객체 생성 후 CarManager의 addCar() 호출
//	입력값 검증 (예: 빈칸, 숫자 형식 등)
	 private JTextField nameField;
	    private JFormattedTextField codeField, priceField, stockField;
	    private JTextField imageField;
	    private JButton imageButton, registerButton;
	    private CarManager manager;

	    public CarInputDialog(JFrame parent, CarManager manager) {
	        super(parent, "자동차 입고", true);
	        this.manager = manager;

	        setLayout(new GridLayout(6, 2, 10, 10));
	        setSize(400, 300);
	        setLocationRelativeTo(parent);

	        // 숫자 포맷터 설정 (음수 입력 불가)
	        NumberFormat intFormat = NumberFormat.getIntegerInstance();
	        intFormat.setGroupingUsed(false);
	        NumberFormatter numberFormatter = new NumberFormatter(intFormat);
	        numberFormatter.setValueClass(Integer.class);
	        numberFormatter.setAllowsInvalid(false);
	        numberFormatter.setMinimum(0);

	        NumberFormat longFormat = NumberFormat.getIntegerInstance();
	        longFormat.setGroupingUsed(false);
	        NumberFormatter longFormatter = new NumberFormatter(longFormat);
	        longFormatter.setValueClass(Long.class);
	        longFormatter.setAllowsInvalid(false);
	        longFormatter.setMinimum(0L);

	        // 입력 필드 초기화
	        nameField = new JTextField();
	        codeField = new JFormattedTextField(numberFormatter);
	        priceField = new JFormattedTextField(longFormatter);
	        stockField = new JFormattedTextField(numberFormatter);
	        imageField = new JTextField();
	        imageField.setEditable(false);

	        imageButton = new JButton("이미지 선택");
	        registerButton = new JButton("등록");

	        // 레이아웃
	        add(new JLabel("차 이름:"));
	        add(nameField);

	        add(new JLabel("코드번호:"));
	        add(codeField);

	        add(new JLabel("가격:"));
	        add(priceField);

	        add(new JLabel("재고 수량:"));
	        add(stockField);

	        add(new JLabel("이미지 경로:"));
	        add(imageField);

	        add(imageButton);
	        add(registerButton);

	        // 이미지 선택 버튼 액션 리스너
	        imageButton.addActionListener(this::chooseImage);

	        // 등록 버튼 액션 리스너
	        registerButton.addActionListener(this::registerCar);
	    }

	    private void chooseImage(ActionEvent e) {
	        JFileChooser chooser = new JFileChooser();
	        chooser.setDialogTitle("이미지 선택");
	        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

	        // 이미지 확장자 필터 추가
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                "이미지 파일 (JPG, PNG)", "jpg", "jpeg", "png");
	        chooser.setFileFilter(filter);

	        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
	            File selected = chooser.getSelectedFile();
	            imageField.setText(selected.getAbsolutePath());
	        }
	    }

	    private void registerCar(ActionEvent e) {
	        try {
	            String name = nameField.getText().trim();
	            int code = ((Number) codeField.getValue()).intValue();
	            long price = ((Number) priceField.getValue()).longValue();
	            int stock = ((Number) stockField.getValue()).intValue();
	            String imagePath = imageField.getText().trim();

	            if (name.isEmpty() || imagePath.isEmpty()) {
	                throw new IllegalArgumentException("빈 칸 없이 입력해주세요.");
	            }

	            Car car = new Car(name, code, price, LocalDate.now(), stock, imagePath);
	            manager.addCar(car);

	            JOptionPane.showMessageDialog(this, "자동차 입고 완료!");
	            dispose();
	        } catch (NullPointerException ex) {
	            JOptionPane.showMessageDialog(this, "모든 숫자 필드를 올바르게 입력해주세요.");
	        } catch (IllegalArgumentException ex) {
	            JOptionPane.showMessageDialog(this, "입력 오류: " + ex.getMessage());
	        }
	    }
	}