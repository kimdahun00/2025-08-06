package com.carshop.model;

import java.time.LocalDate;

public class Car {
//	역할: 자동차 한 대의 데이터 저장
//	해야 할 일:
//	자동차 정보 멤버변수 선언 (이름, 코드번호, 가격, 입고일자, 재고 수량, 이미지 경로)
//	Getter/Setter 메서드 작성
//	생성자 작성 (모든 필드를 초기화)
//	toString() 재정의 (GUI/콘솔 출력용)
//	저장/로드용 문자열 변환 메서드 (toDataLine(), fromDataLine()) 작성

		private String name;
		private int codeNumber;
		private long price;
		private LocalDate arrivalDate;	
		private int stockQuantity;
		private String imagePath;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public int getCodeNumber() {
			return codeNumber;
		}
		
		public void setCodeNumber(int codeNumber) {
			this.codeNumber = codeNumber;
		}
		
		public long getPrice() {
			return price;
		}
		
		public void setPrice(long price) {
			this.price = price;
		}
		
		public LocalDate getArrivalDate() {
			return arrivalDate;
		}
		
		public void setArrivalDate(LocalDate arrivalDate) {
			this.arrivalDate = arrivalDate;
		}
		
		public int getStockQuantity() {
			return stockQuantity;
		}
		
		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
		
		public String getImagePath() {
			return imagePath;
		}
		
		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}
				
		public Car(String name, int codeNumber, long price, LocalDate arrivalDate, int stockQuantity, String imagePath){
			this.name = name;
			this.codeNumber = codeNumber;
			this.price = price;
			this.arrivalDate = arrivalDate;
			this.stockQuantity = stockQuantity;
			this.imagePath = imagePath;	
			
			
		}	

		public String toString() {
			return String.format("차종 : %s\n 코드번호 : %d\n 가격 : %d\n 입고일자 :%s\n 재고 : %d\n "
					+ "이미지 경로 :%s\n", this.name, this.codeNumber, this.price, this.arrivalDate, this.stockQuantity, this.imagePath);
		}
		
		public String toDataLine() {
		    return String.format("%s|%d|%d|%s|%d|%s", 
		        name, codeNumber, price, arrivalDate.toString(), stockQuantity, imagePath);
		}
		
		public static Car fromDataLine(String line) {
		    String[] parts = line.split("\\|");
		    if (parts.length != 6) {
		        throw new IllegalArgumentException("잘못된 데이터 형식");
		    }

		    String name = parts[0];
		    int codeNumber = Integer.parseInt(parts[1]);
		    long price = Long.parseLong(parts[2]);
		    LocalDate arrivalDate = LocalDate.parse(parts[3]);  // yyyy-MM-dd
		    int stockQuantity = Integer.parseInt(parts[4]);
		    String imagePath = parts[5];

		    return new Car(name, codeNumber, price, arrivalDate, stockQuantity, imagePath);
		}
//		🔹 생성자와 private 필드, 그리고 getter/setter 관계
//		1. private 필드로 캡슐화(정보 은닉)
//		클래스 내부 필드를 private으로 선언하면
//
//		외부에서 직접 접근 못 하게 막아서 데이터 보호 가능
//
//		2. 생성자로 초기화
//		생성자에서만 필드를 초기화해서,
//
//		객체가 올바른 상태로 태어나도록 보장
//
//		3. getter/setter로 간접 접근 제공
//		외부에서는 직접 필드를 건드리지 않고,
//
//		getter로 값을 읽고, setter로 값을 바꾸는 방법으로만 접근
//
//		이를 통해 필드 값 검증이나 변경 제한 등 로직 삽입 가능
}