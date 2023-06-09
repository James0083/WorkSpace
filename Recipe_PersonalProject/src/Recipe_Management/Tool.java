package Recipe_Management;

import java.util.Scanner;
/**
 * @author Jaewon You
 * <br>
 * 도구 클래스<br>
 * 기본 도구(오븐, 스토브, 냄비, 프라이팬)에 대한 토글<br>
 *  */
public class Tool{
	private boolean Oven;
	private boolean Stove;
	private boolean Pan;
	private boolean Pot;
	
	/** 기본 도구 생성자 - 모두 사용 안함 */
	public Tool() {
		this(false, false, false, false);
	}
	/** 기본 도구 생성자 - 입력값을 받아서 생성 및 초기화 */
	public Tool(boolean oven, boolean stove, boolean pot, boolean pan) {
		this.Oven=oven;
		this.Stove=stove;
		this.Pot=pot;
		this.Pan=pan;
	}
	

	public void setOven(boolean b) {
		this.Oven = b;
	}
	public void setStove(boolean b) {
		this.Stove = b;
	}
	public void setPan(boolean b) {
		this.Pan = b;
	}
	public void setPot(boolean b) {
		this.Pot = b;
	}
	public boolean getOven() {
		return this.Oven;
	}
	public boolean getStove() {
		return this.Stove;
	}
	public boolean getPan() {
		return this.Pan;
	}
	public boolean getPot() {
		return this.Pot;
	}
	
	public String getTrues() {
		String str = "";
		if(Oven) str+="<오븐> ";
		if(Stove) str+="<가스레인지/인덕션> ";
		if(Pan) str+="<프라이팬> ";
		if(Pot) str+="<냄비> ";
		
		return str;
	}
	
	public void getOSP() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("오븐 사용 여부(1.사용 / 0.미사용) : ");
		boolean bO = (sc.nextInt()==1)? true:false;
		System.out.print("가스레인지/인덕션 사용 여부(1.사용 / 0.미사용) : ");
		boolean bS = (sc.nextInt()==1)? true:false;
		System.out.print("냄비 사용 여부(1.사용 / 0.미사용) : ");
		boolean bPot = (sc.nextInt()==1)? true:false;
		System.out.print("프라이팬 사용 여부(1.사용 / 0.미사용) : ");
		boolean bPan = (sc.nextInt()==1)? true:false;
		this.setOven(bO);
		this.setStove(bS);
		this.setPot(bPot);
		this.setPan(bPan);
	}
}
