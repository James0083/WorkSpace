package memo.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.util.List;
/**
 * --------------------------------------
 * @author 유재원
 * 작엉일: 2023-04-05
 * 버전 : 1.1
 * 한줄 메모장 애플리케이션의 화면을 담당하는 클래스
 * --------------------------------------
 * MFC(c++),JFC(java swing)
 * MVC 패턴===> 웹 mvc패턴 도입
 * 
 * 모델1 방식 : mvc패턴을 적용하지 않을 때
 * 모델2 방식 : mvc패턴 적용
 * 
 * Model  :  DB접근 로직(DB처리 로직을 갖는다. CRUD) [Persistence Layer-영속성 계층]
 * 			VO, DAO
 * View   :  화면단 (Presentatioin Layer) - Swing, HTML(JSP)
 * 
 * Controller: Model View사이에서 제어하는 역할을 담당하는 계층. Event Handler,  Servlet/SpringFramework Controller
 * 
 * MemoAppView:=> GUI /View 담당 [Presentatioin Layer]
 * XXXDAO: DB접근 로직(DB처리 로직을 갖는다. CRUD)
 * 		   Data Access Object  [Persistence Layer-영속성 계층]
 * XXXVO/XXXDTO [Domain Layer]
 *  Value Object/ Data Transfer Object
 * 	: 사용자가 입력한 값을 담거나 DB에서 가져온 값을 갖고 있는 객체
 * --------------------------------------
 * javadoc vm옵션
 * -locale ko_KR -encoding UTF-8 -charset UTF-8 -docencoding UTF-8
 */
public class MemoAppView extends JFrame {

	Container cp;
	JPanel pN = new JPanel(new GridLayout(2,1)); //2행 1열 //북쪽
	JPanel pS = new JPanel();//FlowLayout //남쪽
	
	JPanel pN_sub = new JPanel(new GridLayout(2,1));
	
	JPanel pN_sub_1=new JPanel();
	JPanel pN_sub_2=new JPanel();
	
	JTextArea ta; //중앙
	JButton btAdd, btList, btDel, btEdit, btEditEnd, btFind;
	JLabel lbTitle, lbName, lbDate, lbNo, lbMsg;
	JTextField tfName, tfDate, tfNo, tfMsg;
	
	MemoHandler handler;//Controller

	public MemoAppView() {
		super("::MemoAppView::");
		cp = this.getContentPane();
		
		ta=new JTextArea("::한줄 메모장::\n");
		JScrollPane sp=new JScrollPane(ta);
		
		cp.add(sp, "Center");
		cp.add(pN, "North");
		cp.add(pS, "South");
		ta.setEditable(false);
		
		lbTitle=new JLabel("♥♥ 한줄 메모장 ♥♥", JLabel.CENTER);
		pN.add(lbTitle);
		pN.add(pN_sub);
		
		pN_sub.add(pN_sub_1);
		pN_sub.add(pN_sub_2);

		pN_sub_1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pN_sub_1.add(lbName=new JLabel("작성자: "));
		pN_sub_1.add(tfName=new JTextField(15));

		pN_sub_1.add(lbDate=new JLabel("작성일: "));
		pN_sub_1.add(tfDate=new JTextField(15));
		
		pN_sub_1.add(lbNo=new JLabel("글번호: "));
		pN_sub_1.add(tfNo=new JTextField(15));
		
		pN_sub_2.setLayout(new FlowLayout(FlowLayout.LEFT));
		pN_sub_2.add(lbMsg=new JLabel("메모내용: "));
		pN_sub_2.add(tfMsg=new JTextField(40));
		pN_sub_2.add(btAdd=new JButton("글등록"));
		pN_sub_2.add(btList=new JButton("글목록"));
		
		tfDate.setEditable(false);
		tfNo.setEditable(false);
		tfDate.setForeground(Color.blue);
		String date =this.getSysDate(); //오늘 날짜 얻어오기
		tfDate.setText(date);
		tfDate.setFont(new Font("Serif", Font.BOLD, 14));
		tfDate.setHorizontalAlignment(JTextField.CENTER);
		
		lbTitle.setFont(new Font("Serif", Font.BOLD, 28));
		
		pS.add(btDel=new JButton("글삭제︎︎"));
		pS.add(btEdit=new JButton("글수정︎︎"));
		pS.add(btEditEnd=new JButton("글수정 처리︎︎"));
		pS.add(btFind=new JButton("글검색"));
		//리스너 부착-----
		//***** MemoHandler와 MemoAppView가 연동하기 위해서는 생성자에서 this를 넘겨준다 *****
		handler = new MemoHandler(this);
		btAdd.addActionListener(handler);
		btList.addActionListener(handler);
		btDel.addActionListener(handler);
		btEdit.addActionListener(handler);
		btEditEnd.addActionListener(handler);
		btFind.addActionListener(handler);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}// 생성자--------------------------
	
	/** 메시지를 대화창에 보여주는 메서
	 * 	 */
	public void showMessage(String str) {
		JOptionPane.showMessageDialog(this, str);
	}//---------------------------
	
	/** 입력 필드를 모두 빈 문자열로 초기화하는 메서드
	 * 	 */
	public void clearInput() {
		tfNo.setText("");
		tfName.setText("");
		tfMsg.setText("");
		tfName.requestFocus();//입력포커스
	}//--------------------------
	
	/** 현재 날짜를 yyyy-MM-dd 포맷의 문자열로 변환하여 반환하는 메서드 (mm은 분)*/
	public String getSysDate() {
		Date today = new Date(); //java.util.Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //java.text.SimpleDateFormat
		String str = sdf.format(today);
		return str;
		/*
		 * Java		: yy:년도		MM: 월	dd: 일	hh: 시간		mm: 분	ss: 초
		 * Oracle	: yy:년도		mm: 월	dd: 일	hh: 시간		mi: 분	ss: 초
		 */
	}//---------------------------
	/** 전체 메모 글을 텍스트 에리어에 출력해주는 메서드*/
	public void showTextArea(List<MemoVO> arr) {
		if(arr==null||arr.size()==0) {
			ta.setText("데이터가 없습니다");
		}else {
			for(MemoVO vo:arr) {
				ta.append("\t"+vo.getNo()+"\t"+vo.getName()+"\t"+vo.getMsg()+"\t"+vo.getWdate()+"\n");
			}
		}
		
	}//-----------------------------

	public static void main(String[] args) {
		MemoAppView my = new MemoAppView();
		my.setSize(900, 600);
		my.setResizable(false); //프레임 사이즈 조절 불가
		my.setLocation(500,300);
		my.setVisible(true);

	}

}
