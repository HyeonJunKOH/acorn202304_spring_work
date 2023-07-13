package test;

public class MainClass {
	public static void main(String[] args) {
		printNames();
		printNames("aaa");
		printNames("aaa","bbb");
		printNames("aaa","bbb","ccc");
	}
	//가변 인자 String type을 전달 받는 메소드
	public static void printNames(String...names) {
		//names는 String[] type이다.
		for(String tmp:names) {
			System.out.println(tmp);
		}
		System.out.println(names.length+"개를 출력했습니다.");
	}
}
