package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminMain extends JFrame {
	ArrayList<Words> wArr;
	ArrayList<Member> mArr;
	MemberDAO mdao = new MemberDAO();
	WordsDAO wdao = new WordsDAO();

	private JPanel content;
	private JTextField tSearch;
	private JTable table;
	private JTextField tNum;
	private JTextField tName;
	private JTextField tId;
	private JTextField tPass;
	private JTextField tScore;
	private JTextField tWordNum;
	private JTextField tEngilsh;
	private JTextField tKorea;
	private JTextField tWordSearch;
	private JTable tableWord;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMain adminMain = new AdminMain();
					adminMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminMain() {
		setTitle("회원관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 460);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		content.setLayout(new BorderLayout(0, 0));
		String[] cols = { "번호", "이름", "아이디", "패스워드", "이메일", "점수" };
		DefaultTableModel dt = new DefaultTableModel(cols, mArr.size()); // 멤버에 관한
		for (int i = 0; i < mArr.size(); i++) {
			// 원하는 위치를 가져오는 가져오는 메소드
			dt.setValueAt(mArr.get(i).getNum(), i, 0); // 번호
			dt.setValueAt(mArr.get(i).getName(), i, 1); // 이름
			dt.setValueAt(mArr.get(i).getId(), i, 2); // 아이디
			dt.setValueAt(mArr.get(i).getPw(), i, 3); // 비밀번호
			dt.setValueAt(mArr.get(i).getEmail(), i, 4); // 이메일

		String[] wordcols = { "번호", "영어", "한글" }; // 단어에 대한
		DefaultTableModel wrdt = new DefaultTableModel(wordcols, wArr.size());
		for (int i1 = 0; i1 < wArr.size(); i1++) {
			wrdt.setValueAt(wArr.get(i1).getNum(), i1, 0);
			wrdt.setValueAt(wArr.get(i1).getEnglish(), i1, 1);
			wrdt.setValueAt(wArr.get(i1).getKorea(), i1, 2);
		}
		// 테이블 판
		JTabbedPane tp = new JTabbedPane(JTabbedPane.TOP);
		content.add(tp, BorderLayout.NORTH);
		// 단어 패널
		JPanel paWord = new JPanel();
		tp.addTab("단어관리창", null, paWord, null);
		paWord.setLayout(null);

		// 분활창
		JSplitPane sp = new JSplitPane();
		sp.setBounds(0, 0, 705, 456);
		paWord.add(sp);

		JPanel pa_2 = new JPanel();
		pa_2.setLayout(null);
		sp.setLeftComponent(pa_2);

		JLabel laWordNum = new JLabel("번호");
		laWordNum.setHorizontalAlignment(SwingConstants.RIGHT);
		laWordNum.setBounds(17, 38, 60, 21);
		pa_2.add(laWordNum);

		JLabel laEnglish = new JLabel("영어");
		laEnglish.setHorizontalAlignment(SwingConstants.RIGHT);
		laEnglish.setBounds(17, 38, 60, 21);
		pa_2.add(laEnglish);

		JLabel laKorea = new JLabel("한글");
		laKorea.setHorizontalAlignment(SwingConstants.RIGHT);
		laKorea.setBounds(17, 228, 60, 21);
		pa_2.add(laKorea);

		tWordNum = new JTextField();
		tWordNum.setHorizontalAlignment(SwingConstants.CENTER);
		tWordNum.setColumns(10);
		tWordNum.setBounds(98, 35, 173, 27);
		pa_2.add(laWordNum);

		tEngilsh = new JTextField();
		tEngilsh.setHorizontalAlignment(SwingConstants.CENTER);
		tEngilsh.setColumns(10);
		tEngilsh.setBounds(98, 225, 173, 27);
		pa_2.add(tEngilsh);

		tKorea = new JTextField();
		tKorea.setHorizontalAlignment(SwingConstants.CENTER);
		tKorea.setColumns(10);
		tKorea.setBounds(98, 225, 173, 27);
		pa_2.add(tKorea);

		// 전체 단어 목록 버튼
		JButton btWordList = new JButton("단어 전체 목록");
		btWordList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tWordNum.setText("");
				tEngilsh.setText("");
				tKorea.setText("");
				// wArr = wdao.WordView();
				String[] wordcols = { "번호", "영어", "한글" };
				DefaultTableModel wrdt = new DefaultTableModel(wordcols, wArr.size());
				tableWord.setModel(wrdt);
				for (int i = 0; i < wArr.size(); i++) {
					wrdt.setValueAt(wArr.get(i).getNum(), i, 0);
					wrdt.setValueAt(wArr.get(i).getEnglish(), i, 1);
					wrdt.setValueAt(wArr.get(i).getKorea(), i, 2);
				}
				tableWord.getColumn("번호").setPreferredWidth(10);
				tableWord.getColumn("영어").setPreferredWidth(150);
				tableWord.getColumn("한글").setPreferredWidth(150);

			}
		});

		// 단어 수정하기
		JButton btWordUpdate = new JButton("수정");
		btWordUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Words words = new Words();
				words.setNum(Integer.parseInt(tWordNum.getText()));
				words.setEnglish(tEngilsh.getText());
				words.setKorea(tKorea.getText());
				//wdao.btWordUpdate();
				//btWordList.doClick(words);
				tWordNum.setText("");
				tEngilsh.setText("");
				tKorea.setText("");
				tWordSearch.setText("");
			}
		});
		btWordUpdate.setBounds(12, 311, 129, 29);
		pa_2.add(btWordUpdate);

		// 전체 회원 목록 버튼
		JButton btList = new JButton("회원 전체 목록");
		btList.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tNum.setText("");
				tName.setText("");
				tId.setText("");
				tPass.setText("");
				tScore.setText("");
				String[] cols = { "번호", "이름", "아이디", "비밀번호", "이메일", "점수" };
				DefaultTableModel dt = new DefaultTableModel(cols, mArr.size());
				table.setModel(dt);
				for (int i = 0; i < mArr.size(); i++) {
					dt.setValueAt(mArr.get(i).getNum(), i, 0);
					dt.setValueAt(mArr.get(i).getName(), i, 1);
					dt.setValueAt(mArr.get(i).getId(), i, 2);
					dt.setValueAt(mArr.get(i).getPw(), i, 3);
					dt.setValueAt(mArr.get(i).getEmail(), i, 4);
					
				}
				table.getColumn("번호").setPreferredWidth(40);
				table.getColumn("이름").setPreferredWidth(100);
				table.getColumn("아이디").setPreferredWidth(100);
				table.getColumn("비밀번호").setPreferredWidth(100);
				table.getColumn("이메일").setPreferredWidth(100);
			
			}
		});
		btList.setBounds(118, 5, 168, 29);
		pa_2.add(btList);

		// 회원 수정하기
		JButton btUpdate = new JButton("회원 수정");
		btUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Member member = new Member();
				member.setNum(Integer.parseInt(tNum.getText()));
				member.setName(tName.getText());
				member.setId(tId.getText());
				member.setPw(tPass.getText());
				// mdao.MemberUPdate(member);
				btWordList.doClick();
				tNum.setText("");
				tName.setText("");
				tId.setText("");
				tPass.setText("");
				tScore.setText("");
				tSearch.setText("");
			}
		});
		btUpdate.setBounds(12, 311, 129, 29);
		pa_2.add(btUpdate);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedColumn();
				tNum.setText(table.getValueAt(row, 0) + ""); // 번호
				tName.setText(table.getValueAt(row, 1) + ""); // 이름
				tId.setText(table.getValueAt(row, 2) + ""); // 아이디
				tPass.setText(table.getValueAt(row, 3) + ""); // 비밀번호
				tScore.setText(table.getValueAt(row, 4) + ""); // 스코어

			}
		});
		table.setModel(dt);
		tableWord.setModel(wrdt);
		btWordList.doClick();
		btList.doClick();
	}

	}
}
