package com.carshop.main;	// main() 메서드를 포함한 메인 실행 패키지

// 차량 관리 기능(입고, 판매, 검색, 저장 등)을 담당하는 클래스 import
import com.carshop.service.CarManager;

// 애플리케이션의 메인 GUI 창 (로고, 로그인, 메뉴 등 포함) 클래스 import
import com.carshop.ui.MainFrame;

public class Main { // 애플리케이션 실행 진입점 클래스

	public static void main(String[] args) { // 프로그램의 시작점 메인 메서드

		// SwingUtilities.invokeLater():
		// GUI 관련 코드를 이벤트 디스패치 스레드(Event Dispatch Thread, EDT)에서 안전하게 실행하기 위해 사용.
		// Swing은 단일 스레드 모델이기 때문에 UI 작업은 반드시 EDT에서 실행되어야 함.
		javax.swing.SwingUtilities.invokeLater(() -> {

			// 차량 정보를 관리하는 핵심 로직 객체 생성
			// 이후 UI 컴포넌트들이 버튼 클릭 등을 통해 이 객체를 이용해 차량 정보를 처리함
			CarManager manager = new CarManager();

			// 메인 프레임 생성 (JFrame을 상속한 GUI 창)
			// 상점 이름, 로고, 메뉴 등의 GUI 요소를 포함하며
			// 내부적으로 로그인 프레임(LoginFrame)을 먼저 실행해 사용자 인증부터 수행함
			// 생성자에 CarManager 객체를 넘겨 UI에서 공통으로 차량 관리 기능을 활용할 수 있게 함
			new MainFrame(manager);

		});
	}
}
