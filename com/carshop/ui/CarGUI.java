package com.carshop.ui;
import com.carshop.model.Car;
import com.carshop.service.CarManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.util.List;
public class CarGUI extends JFrame {
//	역할: 실제 자동차 재고 관리 UI
//	해야 할 일:
//	자동차 목록 표시 (JTable 또는 JList 추천)
//	자동차 선택 시 상세정보 + 이미지 미리보기 영역 구현	
//	입고, 판매, 저장, 불러오기 버튼과 이벤트 연결 (CarManager 호출)
//	이미지 파일 대화상자 통해 입고 시 이미지 선택 기능 구현
//	UI 업데이트 및 예외 처리
	 private CarManager manager;
	    private JTable table;
	    private DefaultTableModel tableModel;
	    private JLabel imageLabel;
	    private JTextArea detailArea;

	    public CarGUI(CarManager manager) {
	        this.manager = manager;
	        setTitle("자동차 재고 관리 시스템");
	        setSize(800, 600);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());

	        // 테이블 모델 설정
	        String[] columnNames = {"이름", "코드번호", "가격", "입고일자", "재고수량"};
	        tableModel = new DefaultTableModel(columnNames, 0);
	        table = new JTable(tableModel);
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        JScrollPane tableScroll = new JScrollPane(table);

	        // 오른쪽 상세보기
	        JPanel rightPanel = new JPanel(new BorderLayout());
	        imageLabel = new JLabel("이미지 미리보기", SwingConstants.CENTER);
	        imageLabel.setPreferredSize(new Dimension(200, 150));
	        detailArea = new JTextArea(6, 20);
	        detailArea.setEditable(false);
	        rightPanel.add(imageLabel, BorderLayout.NORTH);
	        rightPanel.add(new JScrollPane(detailArea), BorderLayout.CENTER);

	        // 하단 버튼 패널
	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        JButton addBtn = new JButton("입고");
	        JButton sellBtn = new JButton("판매");
	        JButton saveBtn = new JButton("저장");
	        JButton loadBtn = new JButton("불러오기");

	        buttonPanel.add(addBtn);
	        buttonPanel.add(sellBtn);
	        buttonPanel.add(saveBtn);
	        buttonPanel.add(loadBtn);

	        // 컴포넌트 배치
	        add(tableScroll, BorderLayout.CENTER);
	        add(rightPanel, BorderLayout.EAST);
	        add(buttonPanel, BorderLayout.SOUTH);

	        // 이벤트 연결
	        addBtn.addActionListener(e -> openCarInputDialog());
	        sellBtn.addActionListener(e -> sellSelectedCar());
	        saveBtn.addActionListener(e -> saveData());
	        loadBtn.addActionListener(e -> loadData());

	        table.getSelectionModel().addListSelectionListener(e -> showSelectedCarDetail());

	        refreshTable(); // 초기 데이터 표시
	        setVisible(true);
	    }

	    private void refreshTable() {
	        tableModel.setRowCount(0);
	        for (Car car : manager.viewStock()) {
	            tableModel.addRow(new Object[]{
	                    car.getName(), car.getCodeNumber(), car.getPrice(),
	                    car.getArrivalDate(), car.getStockQuantity()
	            });
	        }
	    }

	    private void showSelectedCarDetail() {
	        int row = table.getSelectedRow();
	        if (row == -1) return;
	        Car car = manager.viewStock().get(row);
	        detailArea.setText(car.toString());

	        // 이미지 표시
	        File imgFile = new File(car.getImagePath());
	        if (imgFile.exists()) {
	            ImageIcon icon = new ImageIcon(car.getImagePath());
	            Image scaled = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
	            imageLabel.setIcon(new ImageIcon(scaled));
	            imageLabel.setText("");
	        } else {
	            imageLabel.setIcon(null);
	            imageLabel.setText("이미지 없음");
	        }
	    }

	    private void openCarInputDialog() {
	        CarInputDialog dialog = new CarInputDialog(this, manager);
	        dialog.setVisible(true);
	        refreshTable();
	    }

	    private void sellSelectedCar() {
	        int row = table.getSelectedRow();
	        if (row == -1) {
	            JOptionPane.showMessageDialog(this, "판매할 차를 선택하세요.");
	            return;
	        }
	        String code = String.valueOf(table.getValueAt(row, 1));
	        manager.sellCar(code);
	        refreshTable();
	    }

	    private void saveData() {
	        JFileChooser chooser = new JFileChooser();
	        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
	            manager.saveData(chooser.getSelectedFile().getPath());
	        }
	    }

	    private void loadData() {
	        JFileChooser chooser = new JFileChooser();
	        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
	            manager.loadData(chooser.getSelectedFile().getPath());
	            refreshTable();
	        }
	    }
	}