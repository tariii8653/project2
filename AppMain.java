package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AppMain extends JFrame {
	ArrayList<Words> wArr; // 전역변수로 선언
	ArrayList<Member> mArr;
	WordsDAO wdao = new WordsDAO();
	MemberDAO mdao = new MemberDAO();

	private JPanel content; //메일패널
	private JPanel pStudy; //공부
	private JPanel pList; //리스트
	private JPanel pEdit; //추가
	private JPanel pLogin; //로그인
	private JSplitPane sp; //정리
	static JTabbedPane tP; //탭

	private JLabel laId, laId2; //라벨 아이디
	private JTextField tId; //필드 아이디
	private JButton btLogin; //버튼 로그인
	private JButton btSign; //버튼 가입
	private JButton btLogout; //버튼 로그아웃
	private JButton btManager; //버튼 관리
	private JLabel laEngWord; //영어단어
	private JLabel laKorWord; //한글
	private JTextField tEng1, tEng2, tEng3, tEng4, tEng5, tEng6, tEng7, tEng8, tEng9, tEng10; //영어 필드들
	private JTextField tKor1, tKor2, tKor3, tKor4, tKor5, tKor6, tKor7, tKor8, tKor9, tKor10; //한글 필드들
	private JScrollPane scroll; //스크롤바
	private JTable table; //테이블
	//private JPanel pSearch;
	//private JComboBox combo;
	// private JTextField tSearch;
	// private JButton btSearch;
	private JPasswordField passWord;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				AppMain appMain = new AppMain();
				try {
					tP.setEnabledAt(1, false);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
	}

	public AppMain() {
		setTitle("영어단어암기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 45, 570);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);
		content.setLayout(null);
		content.add(getSp());
	}

	public JSplitPane getSp() {
		if (sp == null) {
			sp = new JSplitPane();
			sp.setBounds(5, 5, 418, 504);
			sp.setOrientation(JSplitPane.VERTICAL_SPLIT);
		}
		return sp;
	}

	public JPanel getPStudy() {
		if (pStudy == null) {
			pStudy = new JPanel();
			pStudy.setLayout(new BorderLayout(0, 0));
			pStudy.add(getTP(), BorderLayout.CENTER);
		}
		return pStudy;
	}

	public JTabbedPane getTP() {
		if (tP == null) {
			tP = new JTabbedPane(JTabbedPane.TOP);
			tP.addTab("단어목록", null, getPList(), null);
			tP.addTab("단어추가", null, getPEdit(), null);

		}
		return tP;
	}

	public JScrollPane getScroll() {
		if (scroll == null) {
			scroll = new JScrollPane();
			scroll.setViewportView(getTable());
		}
		return scroll;
	}

	public JPanel getPList() {
		if (pList == null) {
			pList = new JPanel();
			pList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
				}
			});
			pList.setBackground(SystemColor.window);
			pList.setLayout(new BorderLayout(0, 0));
			pList.add(getScroll(), BorderLayout.CENTER);
			// pList.add(getPSearch(), BorderLayout.NORTH);
		}
		return pList;
	}

	public JPanel getPEdit() {
		if (pEdit == null) {
			pEdit = new JPanel();
			pEdit.setLayout(null);
			pEdit.add(getTEng1());
			pEdit.add(getTEng2());
			pEdit.add(getTEng3());
			pEdit.add(getTEng4());
			pEdit.add(getTEng5());
			pEdit.add(getTEng6());
			pEdit.add(getTEng7());
			pEdit.add(getTEng8());
			pEdit.add(getTEng9());
			pEdit.add(getTEng10());

			pEdit.add(getTKor1());
			pEdit.add(getTKor2());
			pEdit.add(getTKor3());
			pEdit.add(getTKor4());
			pEdit.add(getTKor5());
			pEdit.add(getTKor6());
			pEdit.add(getTKor7());
			pEdit.add(getTKor8());
			pEdit.add(getTKor9());
			pEdit.add(getTKor10());

		}
		return pEdit;
	}

	public JPanel getPLogin() {
		if (pLogin == null) {
			pLogin = new JPanel();
			pLogin.setLayout(null);
			pLogin.add(getLaId());
			pLogin.add(getLaId2());

		}
		return pLogin;
	}

	public JLabel getLaId() {
		if (laId == null) {
			laId = new JLabel("아이디");
			laId.setFont(new Font("Default", Font.BOLD, 14));
			laId.setBounds(8, 12, 79, 23);
			laId.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return laId;
	}

	public JLabel getLaId2() {
		if (laId2 == null) {
			laId2 = new JLabel("비밀번호");
			laId2.setFont(new Font("Default", Font.BOLD, 14));
			laId2.setBounds(8, 45, 79, 23);
			laId2.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return laId2;
	}

	JTextField[] engArr = new JTextField[10]; // 영어단어 텍스트 필드로간다
	JTextField[] korArr = new JTextField[10]; // 한글 의미 텍스트 필드로 간다.

	// 단어 추가 패널(영어단어)
	private JLabel getLaEngWord() {
		if (laEngWord == null) {
			laEngWord = new JLabel("영어단어");
			laEngWord.setHorizontalAlignment(SwingConstants.CENTER);
			laEngWord.setBounds(91, 10, 57, 15);
		}
		return laEngWord;
	}

	// engArr[i]=tEng1
	private JTextField getTEng1() {
		if (tEng1 == null) {
			tEng1 = new JTextField();
			tEng1.setBounds(61, 33, 116, 21);
			engArr[0] = tEng1;
		}
		return tEng1;
	}

	private JTextField getTEng2() {
		if (tEng2 == null) {
			tEng2 = new JTextField();
			tEng2.setBounds(61, 64, 116, 21);
			engArr[1] = tEng2;
		}
		return tEng2;
	}

	private JTextField getTEng3() {
		if (tEng3 == null) {
			tEng3 = new JTextField();
			tEng3.setBounds(61, 95, 116, 21);
			engArr[2] = tEng3;
		}
		return tEng3;
	}

	private JTextField getTEng4() {
		if (tEng4 == null) {
			tEng4 = new JTextField();
			tEng4.setBounds(61, 126, 116, 21);
			engArr[3] = tEng4;
		}
		return tEng4;
	}

	private JTextField getTEng5() {
		if (tEng5 == null) {
			tEng5 = new JTextField();
			tEng5.setBounds(61, 157, 116, 21);
			engArr[4] = tEng5;
		}
		return tEng5;
	}

	private JTextField getTEng6() {
		if (tEng6 == null) {
			tEng6 = new JTextField();
			tEng6.setBounds(61, 188, 116, 21);
			engArr[5] = tEng6;
		}
		return tEng6;
	}

	private JTextField getTEng7() {
		if (tEng7 == null) {
			tEng7 = new JTextField();
			tEng7.setBounds(61, 219, 116, 21);
			engArr[6] = tEng7;
		}
		return tEng7;
	}

	private JTextField getTEng8() {
		if (tEng8 == null) {
			tEng8 = new JTextField();
			tEng8.setBounds(61, 255, 116, 21);
			engArr[7] = tEng8;
		}
		return tEng8;
	}

	private JTextField getTEng9() {
		if (tEng9 == null) {
			tEng9 = new JTextField();
			tEng9.setBounds(61, 286, 116, 21);
			engArr[8] = tEng9;
		}
		return tEng9;
	}

	private JTextField getTEng10() {
		if (tEng10 == null) {
			tEng10 = new JTextField();
			tEng10.setBounds(61, 317, 116, 21);
			engArr[9] = tEng10;
		}
		return tEng10;
	}

	// 단어 추가 (한글 의미)
	private JLabel getLaKorWord() {
		if (laKorWord == null) {
			laKorWord = new JLabel("한글의미");
			laKorWord.setHorizontalAlignment(SwingConstants.CENTER);
			laKorWord.setBounds(273, 10, 57, 15);
		}
		return laKorWord;
	}

	private JTextField getTKor1() {
		if (tKor1 == null) {
			tKor1 = new JTextField();
			tKor1.setColumns(10);
			tKor1.setBounds(238, 64, 116, 21);
			korArr[0] = tKor1;
		}
		return tKor1;
	}

	private JTextField getTKor2() {
		if (tKor2 == null) {
			tKor2 = new JTextField();
			tKor2.setColumns(10);
			tKor2.setBounds(237, 64, 116, 21);
			korArr[1] = tKor2;
		}
		return tKor2;
	}

	private JTextField getTKor3() {
		if (tKor3 == null) {
			tKor3 = new JTextField();
			tKor3.setColumns(10);
			tKor3.setBounds(237, 95, 116, 21);
			korArr[2] = tKor3;
		}
		return tKor3;
	}

	private JTextField getTKor4() {
		if (tKor4 == null) {
			tKor4 = new JTextField();
			tKor4.setColumns(10);
			tKor4.setBounds(237, 126, 116, 21);
			korArr[3] = tKor4;
		}
		return tKor4;
	}

	private JTextField getTKor5() {
		if (tKor5 == null) {
			tKor5 = new JTextField();
			tKor5.setColumns(10);
			tKor5.setBounds(237, 157, 116, 21);
			korArr[4] = tKor5;
		}
		return tKor5;
	}

	private JTextField getTKor6() {
		if (tKor6 == null) {
			tKor6 = new JTextField();
			tKor6.setColumns(10);
			tKor6.setBounds(237, 188, 116, 21);
			korArr[5] = tKor6;
		}
		return tKor6;
	}

	private JTextField getTKor7() {
		if (tKor7 == null) {
			tKor7 = new JTextField();
			tKor7.setColumns(10);
			tKor7.setBounds(237, 219, 116, 21);
			korArr[6] = tKor7;
		}
		return tKor7;
	}

	private JTextField getTKor8() {
		if (tKor8 == null) {
			tKor8 = new JTextField();
			tKor8.setColumns(10);
			tKor8.setBounds(237, 255, 116, 21);
			korArr[7] = tKor8;
		}
		return tKor1;
	}

	private JTextField getTKor9() {
		if (tKor9 == null) {
			tKor9 = new JTextField();
			tKor9.setColumns(10);
			tKor9.setBounds(237, 286, 116, 21);
			korArr[8] = tKor9;
		}
		return tKor9;
	}

	private JTextField getTKor10() {
		if (tKor10 == null) {
			tKor10 = new JTextField();
			tKor10.setColumns(10);
			tKor10.setBounds(237, 316, 116, 21);
			korArr[9] = tKor10;
		}
		return tKor10;
	}

	// 로그인 비밀번호 필드
	private JPasswordField getPassWord() {
		if (passWord == null) {
			passWord = new JPasswordField();
			passWord.setBounds(103, 47, 112, 21);
		}
		return passWord;
	}

	// 로그인 패널의 로그인 버튼 아이디 하고 비밀범호 여부하고 어떻게 로그인을 해아하나 고민이다...
	private JButton getBtLogin() {
		if (btLogin == null) {
			btLogin = new JButton("로그인");
			btLogin.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String id = tId.getText().trim(); // 아이디 저장
					char[] secret_ps = passWord.getPassword(); // 비밀번호 저장
					String ps = "";
					for (char cha : secret_ps) {
						Character.toString(cha);
						ps += cha;
					}
					int result = 3;
					result = mdao.LoginCheck(id, ps);
					if (result == 0) {
						JOptionPane.showMessageDialog(null, "로그인이 성공했습니다");
						btSign.setVisible(true); // 가입 활성화
						btLogin.setVisible(false); // 로그인 비활성화
						btLogout.setVisible(false); // 로그아웃 비활성화
						tId.setEnabled(false);
						passWord.setEnabled(false);
						tP.setEnabledAt(1, true);
					} else {
						JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 오류입니다.");
					}
					if (id.equals("ghkd4232") && ps.equals("1234")) {
						JOptionPane.showMessageDialog(null, "관리자 로그인을 하였습니다");
						btManager.setVisible(true); // 관리 버튼 활성화
						btLogin.setVisible(false); // 로그인 버튼 비활성화
						btSign.setVisible(false);
						btLogout.setVisible(false);
						tP.setEnabledAt(2, true); // 단어 추가 활성화
					}

				}
			});
			btLogin.setBounds(230, 14, 81, 54);
		}
		return btLogin;
	}

	// 로그인 가입 버튼
	private JButton getBtSign() {
		if (btSign == null) {
			btSign = new JButton("가입");
			btSign.setActionCommand("가입");
			btSign.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Login login = new Login(); // 버튼 연결하면 창이 뜬다.
					login.setVisible(true);
				}
			});
			btSign.setBounds(323, 14, 81, 55);
		}
		return btSign;
	}

	// 로그인 아웃 버튼
	private JButton getBtLogout() {
		if (btLogout == null) {
			btLogout = new JButton("로그아웃");
			btLogout.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					btLogin.setVisible(true);
					btSign.setVisible(true);
					btLogout.setVisible(false);
					btManager.setVisible(false);
					tId.setEnabled(true);
					passWord.setEnabled(true);
					tId.setText("");
					passWord.setText("");
					tP.setSelectedIndex(0);
					tP.setEnabledAt(1, false); // 단어 추가 비활성화
				}
			});
			btLogout.setBounds(23, 13, 81, 54);
			btLogout.setVisible(false);
		}
		return btLogout;
	}

	// 관리자 버튼
	private JButton getBtManager() {
		if (btManager == null) {
			btManager = new JButton("관리");
			btManager.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					AdminMain adminMain = new AdminMain();
					adminMain.setVisible(true); // adminMain창이 뜨도록 한다.
				}
			});
			btManager.setBounds(328, 13, 82, 54);
			btManager.setVisible(false);
		}
		return btManager;
	}

	// 단어 테이블
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			WordsDAO wdao = new WordsDAO();
			wArr = wdao.WordView(); // 단어 전체 보기 화면
			String[] cols = { "번호", "영어", "한글" };
			DefaultTableModel dt = new DefaultTableModel(cols, wArr.size());
			table.setModel(dt);
			for (int i = 0; i < wArr.size(); i++) {
				dt.setValueAt(wArr.get(i).getNum(), i, 0);
				dt.setValueAt(wArr.get(i).getEnglish(), i, 1);
				dt.setValueAt(wArr.get(i).getKorea(), i, 2);
			}
			table.getColumn("번호").setPreferredWidth(10);
			table.getColumn("영어").setPreferredWidth(150);
			table.getColumn("한글").setPreferredWidth(150);
		}
		return table;
	}

	// 단어 추가 패널

}
