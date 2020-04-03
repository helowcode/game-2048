package cn.cpf.app.game2048;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author CPF
 *
 */
public class MainFrame extends JFrame {
	private boolean errorFlag = false;
	//最大值
	private int max = 2048;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the cn.cpf.app.gobang.frame.
	 */
	public MainFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("max:" + max);
		setBounds(300, 300, 400, 350);
		final MainPanel gamePanel = new MainPanel();
		gamePanel.setBounds(0, 0, 400, 350);
		// 添加到中央位置
		getContentPane().add(gamePanel);
		// 实例化按钮

		final JButton button = new JButton();
		button.setBounds(180, 239, 80, 23);
		gamePanel.add(button);
		// 注册事件
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				// 开始游戏
				gamePanel.updata();
			}
		});
		button.setText("开始");
		final JButton btnUp = new JButton();
		btnUp.setBounds(180, 210, 80, 23);
		gamePanel.add(btnUp);
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gamePanel.Up();
			}
		});
		btnUp.setText("Up");
		final JButton btnDown = new JButton();
		btnDown.setBounds(180, 271, 80, 23);
		gamePanel.add(btnDown);
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gamePanel.Down();
			}
		});
		btnDown.setText("Down");
		final JButton btnLeft = new JButton();
		btnLeft.setBounds(80, 239, 80, 23);
		gamePanel.add(btnLeft);
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gamePanel.Left();
			}
		});
		btnLeft.setText("Left");
		final JButton btnRight = new JButton();
		btnRight.setBounds(280, 239, 80, 23);
		gamePanel.add(btnRight);
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gamePanel.Right();
			}
		});
		btnRight.setText("Right");

		button.addKeyListener(new KeyListener() { // 添加窗体按键事件监听器
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) { // 抬起按键的处理方法
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
					case KeyEvent.VK_W:
						gamePanel.Up();
						button.setText("");
						break;
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_S:
						gamePanel.Down();
						button.setText("");
						break;
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_A:
						gamePanel.Left();
						button.setText("");
						break;
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:
						gamePanel.Right();
						button.setText("");
						break;
					default:
						errorFlag = true;
				}
				if (errorFlag)
					button.setText("error!");
				else if (gamePanel.sum == max) {
					int i = JOptionPane.showConfirmDialog(null, "成功，再来一局？", "Congratulations",
							JOptionPane.YES_NO_OPTION);
					// 开始新一局
					if (i == JOptionPane.YES_OPTION) {
						gamePanel.init();
					}
				} else if (gamePanel.falseFlag) {
					int i = JOptionPane.showConfirmDialog(null, "很遗憾，再来一局？", "You lose the game!",
							JOptionPane.YES_NO_OPTION);
					// 开始新一局
					if (i == JOptionPane.YES_OPTION) {
						gamePanel.init();
					}
				} else
					button.setText(String.valueOf(gamePanel.sum));
				errorFlag = false;
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});

	}

}
