package com.example.boot06;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //setter, getter 만둘기
@Builder // setter 메소드 대신에 chain 형태로 값을 넣어줄 수 있게 만들어 준다.)
@NoArgsConstructor //디폴트 생성자
@AllArgsConstructor //인자로 모든 갑싱 전달되는 식자재
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
