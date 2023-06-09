package Recipe_Management;

import java.util.*;
/**
 * @author Jaewon You
 * <br>
 * 레시피 클래스<br>
 * -포함목<br>
 * 요리이<br>
 * 기본도구(오븐, 스토브, 냄비, 프라이팬)에 대한 토글과 도구 배열<br>
 * 재료 배열<br>
 * 조리순서 배열<br>
 *  */
public class Recipe {
	private String fName;
	Tool OSP = new Tool(false, false, false, false);
	ArrayList<String> toolsArr = new ArrayList<>();
	ArrayList<String> recipeStep = new ArrayList<>();
	HashSet<Ingredient> ingreSet=new HashSet<>();

	/** 레시피 이름 입력하는 메서드 */
	public void setFName(String name) {
		this.fName=name;
	}

	/** 레시피 이름 반환하는 메서드 */
	public String getFName() {
		return this.fName;
	}

	/** 단계 배열에 추가하는 메서드 */
	public boolean addSteps(String explain) {
		return recipeStep.add(explain);
	}

	/** 재료 배열에 추가하는 메서드 */
	public void addIngredients(Ingredient ingre) {
		ingreSet.add(ingre);
	}

	/** 도구 배열에 추가하는 메서드 */
	public void addTool(String tool) {
		toolsArr.add(tool);
	}

	/** 레시피를 문자열로 변환해주는 메서드 */
	public String RecipeToString() {
		String rts = "========"+fName+"========\n";
		rts += "[사용도구]\n"+OSP.getTrues();

		Iterator<String> it = toolsArr.iterator();
		if(it.hasNext()) rts += it.next();
		
		while(it.hasNext()) {
			rts += ", "+it.next();
		}
		rts += "\n\n[재료]\n";
		for(Ingredient ing:ingreSet) {
			rts += ing.getIngre()+": "+ing.getQuantity()+" "
					+ing.getUnit()+"\n";
		}
		rts += "\n[조리 단계]\n";
		int i = 1;
		for(String str:recipeStep) {
			rts += (i++) +". "+str+"\n";
		}
		return rts;
	}
	
	/** 레시피 이름이 같은지 비교하는 메서드 */
	public boolean compareRecipe(String str) {
		return str.equals(fName);
	}
}
