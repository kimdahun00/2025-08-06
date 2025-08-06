package com.carshop.service;
import java.util.List;
import com.carshop.model.Car;

public interface BasicFunction {
//	역할: 프로그램이 꼭 구현해야 할 기능 목록 선언
//	해야 할 일:
//	입고(addCar(Car car))
//	판매(sellCar(String code))
//	재고확인(viewStock())
//	저장(saveData(String path))
//	불러오기(loadData(String path))
//	주의: 메서드 선언만, 구현은 하지 않음

	void addCar(Car car);
	void sellCar(String code);
	List<Car> viewStock();
	void saveData(String path);
	void loadData(String path);
}
