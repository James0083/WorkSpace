package Recipe_Management;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class ListPage extends JPanel{

	HomeGUI app;
//	ArrayList<Recipe> myRecipes;
	ArrayList<JPanel> ListPanel;
	CardLayout card;
	JPanel pMain = new JPanel();
	JPanel p1 = new JPanel(), p2 = new JPanel();
	JTextArea ta;
	String str = "";
	
	public ListPage(HomeGUI mav) {
		this.app = mav;
		
		setBackground(Color.white);
		setLayout(card =new CardLayout());
		add(p1, "btList");
		add(p2, "Detail");
		
		p1.setLayout(new BorderLayout());
		
		JScrollPane sp = new JScrollPane(ta = new JTextArea(str));
		p1.add(sp, "Center");

	}
}
