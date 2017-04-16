package com.lxy.ideaEdit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import javax.swing.JTextPane;
import javax.swing.JTable;

public class IdeaEdit extends JFrame {

	private JTextArea textEditArea;
	private JPanel contentPane;
	private boolean flag = false;
	private JLabel lblWordCountMsg = null;
	private JLabel lblRowMsg = null;
	private JLabel lblFileMsg = null;
	private String filename;
	private String filepath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IdeaEdit frame = new IdeaEdit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IdeaEdit() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Quit();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 576);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JLabel lblNewLabel_3 = new JLabel("        ");
		menuBar.add(lblNewLabel_3);

		JMenu menu = new JMenu("\u6587\u4EF6");
		menuBar.add(menu);

		JMenuItem menuItem_3 = new JMenuItem("\u65B0\u5EFA\u6587\u4EF6");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//有些功能并没有做全。
				Jpanel.showMessageDialog("this function didn't implement");
			}
		});
		menu.add(menuItem_3);

		JMenuItem menuItem = new JMenuItem("\u6253\u5F00\u6587\u4EF6");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openfile();
			}
		});
		menu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("\u4FDD\u5B58\u6587\u4EF6");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		menu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("\u53E6\u5B58\u4E3A");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileAs();
			}
		});
		menu.add(menuItem_2);

		JMenuItem menuItem_4 = new JMenuItem("\u5173\u95ED");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
				dispose();
			}
		});
		menu.add(menuItem_4);

		JMenuItem menuItem_20 = new JMenuItem("\u9000\u51FA");
		menuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuitActionPerform(e);
			}
		});
		menu.add(menuItem_20);

		JMenu menu_1 = new JMenu("  \u7F16\u8F91");
		menuBar.add(menu_1);

		JMenuItem menuItem_5 = new JMenuItem("\u590D\u5236");
		menu_1.add(menuItem_5);

		JMenuItem menuItem_6 = new JMenuItem("\u526A\u5207");
		menu_1.add(menuItem_6);

		JMenuItem menuItem_7 = new JMenuItem("\u7C98\u8D34");
		menu_1.add(menuItem_7);

		JMenuItem menuItem_8 = new JMenuItem("\u5220\u9664");
		menu_1.add(menuItem_8);

		JMenuItem menuItem_9 = new JMenuItem("\u5168\u9009");
		menu_1.add(menuItem_9);

		JMenu menu_3 = new JMenu("  \u683C\u5F0F");
		menuBar.add(menu_3);

		JMenuItem menuItem_13 = new JMenuItem("\u989C\u8272");
		menuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTextColor();
			}

		});

		JMenu menu_5 = new JMenu("\u5B57\u4F53");
		menu_3.add(menu_5);

		JMenuItem mntmSourceCodePro = new JMenuItem("source code pro 16");
		mntmSourceCodePro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEditArea.setFont(new java.awt.Font("souce code pro", 1, 16));
			}
		});
		menu_5.add(mntmSourceCodePro);

		JMenuItem menuItem_12 = new JMenuItem("\u5B8B\u4F53 20");
		menuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEditArea.setFont(new java.awt.Font("宋体", 1, 20));
			}
		});
		menu_5.add(menuItem_12);
		menu_3.add(menuItem_13);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		btnNewButton.setIcon(new ImageIcon(IdeaEdit.class.getResource("/images/save.png")));
		toolBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEditArea.copy();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(IdeaEdit.class.getResource("/images/copy.png")));
		toolBar.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEditArea.cut();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(IdeaEdit.class.getResource("/images/cut.png")));
		toolBar.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEditArea.paste();
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(IdeaEdit.class.getResource("/images/paste.png")));
		toolBar.add(btnNewButton_3);

		JToolBar status = new JToolBar();
		status.setEnabled(false);
		contentPane.add(status, BorderLayout.SOUTH);

		lblFileMsg = new JLabel("Java Source File ");
		status.add(lblFileMsg);

		lblRowMsg = new JLabel("\u5F53\u524D\u884C\u6570\uFF1A0 ");
		status.add(lblRowMsg);

		lblWordCountMsg = new JLabel("\u5F53\u524D\u5B57\u6570\uFF1A0 ");
		status.add(lblWordCountMsg);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textEditArea = new JTextArea();
		textEditArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				editKeyPressPerform(arg0);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				linecount(e);
			}
		});
		textEditArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				flag = true;
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				flag = true;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				flag = true;
			}
		});
		scrollPane.setViewportView(textEditArea);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(textEditArea, popupMenu);

		JMenuItem menuItem_16 = new JMenuItem("\u590D\u5236 ");
		menuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEditArea.copy();
			}
		});
		menuItem_16.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		popupMenu.add(menuItem_16);

		JMenuItem menuItem_17 = new JMenuItem("\u526A\u5207");
		menuItem_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEditArea.cut();
			}
		});
		menuItem_17.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		popupMenu.add(menuItem_17);

		JMenuItem menuItem_18 = new JMenuItem("\u7C98\u8D34");
		menuItem_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEditArea.paste();
			}
		});
		menuItem_18.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		popupMenu.add(menuItem_18);

		JMenuItem menuItem_19 = new JMenuItem("\u5168\u9009");
		menuItem_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textEditArea.selectAll();
			}
		});
		menuItem_19.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		popupMenu.add(menuItem_19);
		// 设置JFrame居中显示
		this.setLocationRelativeTo(null);
	}
	// 设置文本颜色

	@SuppressWarnings("static-access")
	private void setTextColor() {
		// TODO Auto-generated method stub
		JColorChooser chooser = new JColorChooser();
		Color color = textEditArea.getForeground();
		textEditArea.setForeground(chooser.showDialog(textEditArea, "选择字体颜色", color));
	}

	// 保存文件操作
	private void saveFile() {
		// TODO Auto-generated method stub
		if (filepath == null || filename == "") {
			saveFileAs();
			return;
		} else {
			FileWriter fileWriter;
			try {
				fileWriter = new FileWriter(filepath);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				// 把文本框的内容全部写到文件
				fileWriter.write(textEditArea.getText());
				fileWriter.close();
				bufferedWriter.close();
				flag = false;
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	// 另存为操作
	private void saveFileAs() {
		// TODO Auto-generated method stub
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new Textfilefilter(".txt", "文本文件(*.txt)"));
		chooser.setFileFilter(new Textfilefilter(".xml", "xml(*.xml)"));
		chooser.setFileFilter(new Textfilefilter(".c", "c(*.c)"));
		int result = chooser.showSaveDialog(this); // 打开“另存为文件”对话框
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			this.setTitle(file.getName());
			this.filename = file.getName();
			this.filepath = file.getPath();
			// 把文本框的内容写入到文件中
			FileWriter fileWriter;
			try {
				fileWriter = new FileWriter(file);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				fileWriter.write(textEditArea.getText());
				bufferedWriter.close();
				fileWriter.close();
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}

	// 打开文件操作
	private void openfile() {
		// TODO Auto-generated method stub
		JFileChooser fileChooser = new JFileChooser();
		Textfilefilter txtfilefilter = new Textfilefilter(".txt", "文本文件(*.txt)");
		Textfilefilter xmlfilefilter = new Textfilefilter(".xml", "xml(*.xml)");
		Textfilefilter cfilefilter = new Textfilefilter(".c", "c(*.c)");
		Textfilefilter cplusfilefilter = new Textfilefilter(".cpp", "cpp(*.cpp)");
		fileChooser.addChoosableFileFilter(txtfilefilter);
		fileChooser.addChoosableFileFilter(xmlfilefilter);
		fileChooser.addChoosableFileFilter(cfilefilter);
		fileChooser.addChoosableFileFilter(cplusfilefilter);
		fileChooser.setFileFilter(txtfilefilter);
		int result = fileChooser.showOpenDialog(this);// 打开“打开文件”对话框
		// 将选择的File对象赋值给result
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			this.setTitle(file.getName());
			this.filepath = file.getPath();
			this.filename = file.getName();
			this.lblFileMsg.setText(this.filename.substring(filename.lastIndexOf(".") + 1) + " Source File ");
			// 读入文件的内容到文本框
			FileReader fileReader;
			try {
				fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				textEditArea.setText("");
				String string = "";
				while ((string = bufferedReader.readLine()) != null) {
					textEditArea.append(string + "\r\n");
				}
				bufferedReader.close();
				fileReader.close();
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}

	// 下方状态栏变化
	private void editKeyPressPerform(KeyEvent e) {
		// TODO Auto-generated method stub
		this.lblRowMsg.setText("当前字数：" + String.valueOf(textEditArea.getText().trim().length()));
		this.lblWordCountMsg.setText("当前行数：" + String.valueOf(textEditArea.getLineCount()));
	}
	//显示行号
	private void linecount(KeyEvent e) {
		// TODO Auto-generated method stub
			//this.textArea.setText((textEditArea.getLineCount()+"\r\n"));
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	// 退出事件处理
	private void QuitActionPerform(ActionEvent e) {
		// TODO Auto-generated method stub
		Quit();
	}

	// 退出处理
	private void Quit() {
		if (flag == true) {
			int resp = JOptionPane.showConfirmDialog(null, "是否要保存文件并退出？");
			if (resp == JOptionPane.YES_OPTION) {
				saveFile();
				dispose();
			} else if (resp == JOptionPane.NO_OPTION) {
				dispose();
			} else if (resp == 2) {
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		} else {
			dispose();
		}
	}
}

/**
 * 自定义文件过滤器
 * 
 * @author xiaoyang
 *
 */
final class Textfilefilter extends FileFilter {
	private String extension;
	private String description;

	public Textfilefilter(String extension, String description) {
		super();
		this.extension = extension;
		this.description = description.toLowerCase();
	}

	@Override
	public boolean accept(File file) {
		// TODO Auto-generated method stub
		return file.getName().toLowerCase().endsWith(this.extension);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

}
