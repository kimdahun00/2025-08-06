package com.carshop.util;
import com.carshop.model.Car;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileHandler {
//	역할: 파일 저장/불러오기 공통 기능 담당
//	해야 할 일:
//	saveToTextFile(List<Car> list, String path) : 파일 저장 구현
//	loadFromTextFile(String path) : 파일 읽기 후 List<Car> 반환
//	파일 입출력 예외 처리 및 메시지 출력
	 public static void saveToTextFile(List<Car> list, String path) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
	            for (Car car : list) {
	                writer.write(car.toDataLine());
	                writer.newLine();
	            }
	            System.out.println("✅ 파일 저장 성공: " + path);
	        } catch (IOException e) {
	            System.out.println("❌ 파일 저장 실패: " + e.getMessage());
	        }
	    }

	    /**
	     * 역할: 텍스트 파일에서 Car 객체 리스트로 로드
	     */
	    public static List<Car> loadFromTextFile(String path) {
	        List<Car> list = new ArrayList<>();

	        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                try {
	                    Car car = Car.fromDataLine(line);
	                    list.add(car);
	                } catch (Exception e) {
	                    System.out.println("⚠️ 잘못된 형식의 데이터 무시됨: " + line);
	                }
	            }
	            System.out.println("✅ 파일 로드 성공: " + path);
	        } catch (IOException e) {
	            System.out.println("❌ 파일 로드 실패: " + e.getMessage());
	        }

	        return list;
	    }
}
