package Programmers;
import java.util.Arrays;

public class Level2_SkillTree {

	public static void main(String[] args) {
		int answer = 0;
		// input example
		String skill = "CBD";
		// 1~26 이하
		String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
		// 1~20 배열, 2~26이하 문자열
		char[] skillArr = skill.toCharArray();
		boolean[] isPass = new boolean[skillArr.length];
		
		for(int i = 0; i < skill_trees.length; i++) {
			String checkedSkill = skill_trees[i];
			System.out.println(i + "번째 스킬 트리 체크 시작!==============");
			Arrays.fill(isPass, false);
			int flag = 0;
			for(int j = 0; j < checkedSkill.length(); j++) {
				String box = String.valueOf(checkedSkill.charAt(j));	
				if(skill.contains(box)) {
					System.out.println(skill + "은 " + box + "를 포함합니다.");
					System.out.println(skill.charAt(flag) + "와 " + checkedSkill.charAt(j) + "를 비교합니다.");
					if(skill.charAt(flag) == checkedSkill.charAt(j)) {
						System.out.println("스킬 순서가 맞으니 괜찮습니다.");
						flag++;
					}
					else {
						System.out.println("순서가 맞지 않습니다.");
						break;
					}
				}
				if(j == checkedSkill.length() - 1) {
					System.out.println("마지막까지 문제 없었습니다.");
					answer++;
				}
				
			}
		}
	
		/* 
		 * 			for(int j = 0; j < checkedSkill.length(); j++) {
				boolean isCont = true;
				char checked = checkedSkill.charAt(j);
				int flag = 0;
				for(int k = 0; k < skillArr.length; k++) {
					if(isPass[k] == false) {
						System.out.println(k + "번째 체크 배열이 False이므로 " + k + "보다 이후 스킬이 먼저 나오면 안됩니다.");
						flag = k;
						break;
					}
				}
				for(int k = 0; k < skillArr.length; k++) {
					if(skillArr[k] == checked) {
						System.out.println("동일한 값 발견!");
						System.out.println(i + "번째 String " + checkedSkill + "의 " + j + "번쨰 원소 " + checked + "가 skillArr의 " + k + "번째 원소 " + skillArr[k] + "와 동일합니다.");
						if(flag < k) {
							System.out.println("선행 스킬을 익히지 않았습니다.");
							isCont = false;
							break;
						}
						else {
							System.out.println("선행 스킬을 익혔습니다.");
							isPass[k] = true;
							flag++;
						}
					}
				}
				if(j == checkedSkill.length() - 1) {
					System.out.println(i + "번째 스킬 트리는 마지막까지 문제 없었습니다.@@@@@@@@@");
					answer++;
				}
				if(isCont == false)
					break;
			}

		 * */
		System.out.println("최종 값 : " + answer);
	}

}
