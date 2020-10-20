package day10;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayList01 {
	public static void main(String[] args) {
		ArrayList<String> arr = new ArrayList<>();
		arr.add("사과");
		arr.add("귤");
		arr.add("배");
		arr.add("감귤");
		arr.add("수박");
		
		arr.add(0, "포도");
		String asdf = "포도, 사과, 귤, 배, 감귤, 수박";
		String[] sdf =asdf.split(",");
		
		System.out.println(Arrays.toString(sdf));
		
		System.out.println(arr.toString());
	}
}
