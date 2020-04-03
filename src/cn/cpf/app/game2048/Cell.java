package cn.cpf.app.game2048;

import javax.swing.JTextField;

public class Cell extends JTextField{

	public Cell(){
		this.setSize(100,50);
		this.setEditable(false);
		this.setText("0");
		this.setHorizontalAlignment(JTextField.CENTER);
	}

	
	public void change(boolean flag,String number){
		this.setText(number);
		this.setVisible(flag);
	}
}