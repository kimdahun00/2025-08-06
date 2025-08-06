package com.carshop.service;
import com.carshop.model.Car;
import com.carshop.util.FileHandler;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.time.LocalDate;

public class CarManager implements BasicFunction{
//	역할: 자동차 리스트 관리 및 기능 구현
//	해야 할 일:
//	LinkedList<Car> 선언 및 초기화
//	addCar()에서 입고일 현재 날짜 자동 지정 후 리스트에 추가
//	sellCar()에서 코드번호로 찾아 재고 수량 1 감소, 재고 0시 삭제 혹은 알림 처리
//	viewStock()에서 전체 리스트 반환
//	saveData()에서 파일에 자동차 정보를 저장 (텍스트 포맷)
//	loadData()에서 파일 읽고 자동차 리스트 복원, 형식 에러 예외 처리
//	권장: 저장/로드 시 Car 클래스의 toDataLine() 및 fromDataLine() 활용
//	List<Car> carList = new LinkedList<>();
	 private List<Car> carList = new LinkedList<>();

	    @Override
	    public void addCar(Car car) {
	        car.setArrivalDate(LocalDate.now());  // 입고일 자동 설정
	        carList.add(car);
	    }

	    @Override
	    public void sellCar(String code) {
	        ListIterator<Car> iterator = carList.listIterator();
	        while (iterator.hasNext()) {
	            Car car = iterator.next();
	            if (String.valueOf(car.getCodeNumber()).equals(code)) {
	                if (car.getStockQuantity() > 0) {
	                    car.setStockQuantity(car.getStockQuantity() - 1);
	                    if (car.getStockQuantity() == 0) {
	                        iterator.remove();  // 재고 없으면 리스트에서 제거
	                        System.out.println("재고 0으로 삭제됨: " + car.getName());
	                    }
	                } else {
	                    System.out.println("재고가 없습니다.");
	                }
	                break;
	            }
	        }
	    }

	    @Override
	    public List<Car> viewStock() {
	        return carList;
	    }

	    @Override
	    public void saveData(String path) {
	        FileHandler.saveToTextFile(carList, path);  // 🔄 FileHandler로 위임
	    }

	    @Override
	    public void loadData(String path) {
	        carList = FileHandler.loadFromTextFile(path);  // 🔄 불러온 List로 교체
	    }
	}