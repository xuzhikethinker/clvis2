package main;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5515079934174057500L;
	
	/**
	 * Main menus
	 */
	public final JMenu menu1 = new JMenu("Настройки");
	public final JMenu menu2 = new JMenu("Алгоритмы");
	public final JMenu menu3 = new JMenu("Фильтры");
	public final JMenu menu4 = new JMenu("Данные");
	
	/**
	 * 
	 */
	public MenuBar() {
		super();
		
		add(menu1);
		add(menu2);
		add(menu3);	
		add(menu4);	
	}
}
