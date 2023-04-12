package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {
	boolean idFlag; // 아이디 참 거짓
	int idCheck; // 아이디 확인
	String idStr = ""; // 아이디 문자 확인
	MemberDAO mdao=new MemberDAO();

	private JPanel content; // 라벨 콘텐츠
	private JLabel laName; // 이름
	private JLabel laId; // 아이디
	private JLabel laPass; // 비밀번호
	private JLabel laEmail; // 이메일
	private JLabel laPwCon; // 컨펌
	private JTextField teName; // 텍스트 필드 이름
	private JTextField teId; // 필드 아이디
	private JTextField tEmail; // 필드 이메
	private JButton btLogin; // 로그인 버튼
	private JButton btCancel; // 취소 버튼
	private JPasswordField password; // 비밀번호
	private JPasswordField passwordCon; // 비밀번호 컨펌
	private JButton btIdCon; // 아이디 컨
	private JLabel lanabad; // 이름이 안맞는 경
	private JLabel laPwNull; // 비밀번호가 빈 경우
	private JLabel laPwBad; // 비밀번호가 안맞은경우
	private JLabel laPwGood; // 비밀번호가 맞는경우

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login login = new Login();
					login.setVisible(true);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 390);
		content = new JPanel();
		content.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(content);

		content.setLayout(null);
		content.add(getLaName());
		content.add(getLaId());
		content.add(getLaPass());
		content.add(getLaPwCon());
		content.add(getLaEmail());
		content.add(getTeName());
		content.add(getTeId());
		content.add(getPassword());
		content.add(getPasswordCon());
		content.add(getTEmail());
		content.add(getBtLogin());
		content.add(getBtCancel());
		content.add(getBtIdCon());
		content.add(getLaNaBad());
		content.add(getLaPwNull());
		content.add(getLaPwBad());
		content.add(getLaPwGood());

	}

	private JLabel getLaName() {// 이름
		if (laName == null) {
			laName = new JLabel("이름");
			laName.setBounds(18, 23, 57, 15);
		}
		return laName;
	}

	private JLabel getLaNaBad() { // 이름을 안한 경우
		if (lanabad == null) {
			lanabad = new JLabel("이름을 입력하세요");
			lanabad.setForeground(new Color(255,204,000)); // 글자색 변경코드
			lanabad.setHorizontalAlignment(SwingConstants.RIGHT);
			lanabad.setBounds(130, 46, 133, 15);

		}
		return lanabad;
	}

	private JLabel getLaId() { // 아이디
		if (laId == null) {
			laId = new JLabel("아이디");
			laId.setBounds(18, 80, 57, 15);
		}
		return laId;
	}

	private JLabel getLaPass() {
		if (laPass == null) {
			laPass = new JLabel("비밀번호");
			laPass.setBounds(18, 154, 57, 15);
		}
		return laPass;
	}

	private JLabel getLaPwNull() {
		if (laPwNull == null) {
			laPwNull = new JLabel("비밀번호를 입력하세요");
			laPwNull.setHorizontalAlignment(SwingConstants.RIGHT);
			laPwNull.setForeground(new Color(255,102,255));
			laPwNull.setBounds(130, 177, 133, 15);
			laPwNull.setVisible(false);

		}

		return laPwNull;
	}

	private JLabel getLaPwCon() {
		if (laPwCon == null) {
			laPwCon = new JLabel("비밀번호확인");
			laPwCon.setBounds(18, 205, 100, 15);
		}
		return laPwCon;
	}

	private JLabel getLaPwGood() {
		if (laPwGood == null) { // 암호일치
			laPwGood = new JLabel("암호가 일치합니다");
			laPwGood.setForeground(Color.BLUE);
			laPwGood.setHorizontalAlignment(SwingConstants.RIGHT);
			laPwGood.setBounds(206, 229, 57, 15);
			laPwGood.setVisible(false);
		}
		return laPwGood;
	}

	private JLabel getLaPwBad() {
		if (laPwBad == null) { // 암호불일치
			laPwBad = new JLabel("암호가 불일치합니다");
			laPwBad.setForeground(Color.YELLOW);
			laPwBad.setHorizontalAlignment(SwingConstants.RIGHT);
			laPwBad.setBounds(156, 229, 107, 15);
			laPwBad.setVisible(false);
		}
		return laPwBad;
	}

	private JLabel getLaEmail() {
		if (laEmail == null) {
			laEmail = new JLabel("이메일");
			laEmail.setBounds(19, 255, 57, 15);
		}
		return laEmail;
	}

	private JTextField getTeName() { // 이름 텍스트 필드
		if (teName == null) {
			teName = new JTextField();
			teName.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (teName.getText().equals("")) {
						lanabad.setVisible(true); // 이름을입력하세요 보이는 경우
					} else {
						lanabad.setVisible(false); // 아닌 경우
					}
				}
			});
			teName.setBounds(109, 20, 155, 21);
			teName.setColumns(10);
		}
		return teName;
	}

	private JTextField getTeId() { // 아이디 텍스트 필드
		if (teId == null) {
			teId = new JTextField();
			teId.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (teId.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "아이디가 공백입니다");
					}
				}
			});
			teId.setBounds(109, 77, 155, 21);
			teId.setColumns(10);
		}
		return teId;
	}

	private JButton getBtIdCon() { // 아이디 중복확인 버튼이다 그리고 가입버튼을 누르면 중복확인 버튼 누르고 메세지 띄우기이다.
		if (btIdCon == null) {
			btIdCon = new JButton("아이디 중복확인");
			btIdCon.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					idFlag=mdao.IdCheck(teId.getText());
					idCheck = 0; // 아이디를 체크 안했을때이다
					if (idFlag == false) {
						JOptionPane.showMessageDialog(null, "중복된 아이디 입니다");
						idCheck = 1; // 아이디를 체크 했는데 중복일 때이다
					} else if (idFlag == true) {
						JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다");
						idCheck = 2; // 아이디 체크 했고 중복이 아닐때 이다 그럼 성공
					}
				}

			});

			btIdCon.setActionCommand("아이디 중복확인");
			btIdCon.setBounds(109, 107, 154, 23);
		}
		return btIdCon;
	}
	
	//이메일 텍스트 필드
	private JTextField getTEmail() {
		if(tEmail==null) {
			tEmail=new JTextField();
			tEmail.setBounds(108,252,155,21);
			tEmail.setColumns(10);
		}
		return tEmail;
	}

	private JPasswordField getPassword() { // 비밀번호
		if (password == null) {
			password = new JPasswordField();
			password.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					char[] secret_ps = password.getPassword();
					String ps = "";
					for (char cha : secret_ps) { // 비밀번호 가려진 경우
						Character.toString(cha);
						ps += cha;
					}
					if (ps.equals("")) {
						laPwNull.setVisible(true);
						laPwBad.setVisible(false);
						laPwGood.setVisible(false);
					} else {
						laPwNull.setVisible(false);
						laPwBad.setVisible(false);
						laPwGood.setVisible(false);
					}
				}
			});
			password.setBounds(108, 151, 155, 21);
		}

		return password;
	}

	private JPasswordField getPasswordCon() {
		if (passwordCon == null) {
			passwordCon = new JPasswordField();
			passwordCon.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					char[] secret_ps = password.getPassword(); // 패스워드 받아오기
					String ps = "";
					for (char cha : secret_ps) {
						Character.toString(cha);
						ps += cha;
					}
					char[] secret_ps_confirm = passwordCon.getPassword(); // 패스워드 확인 받아오기
					String ps_confirm = "";
					for (char cha : secret_ps_confirm) {
						Character.toString(cha);
						ps_confirm += cha;
					}
					if (ps_confirm.equals("")) { // 암호가 빈칸일때이다
						laPwNull.setVisible(true);
						laPwBad.setVisible(false);
						laPwGood.setVisible(false);
					} else if (ps.equals(ps_confirm)) { // 암호가 일치 할때이다
						laPwGood.setVisible(true);
						laPwBad.setVisible(false);
						laPwNull.setVisible(false);
					} else { // 암호가 일치하지 않을때이다
						laPwBad.setVisible(true);
						laPwGood.setVisible(false);
						laPwNull.setVisible(false);
					}
				}
			});
			passwordCon.setBounds(108, 202, 155, 21);
		}

		return passwordCon;
	}
	//가입버튼 DB회원 저장이다.
	private JButton getBtLogin() {
		if(btLogin==null) {
			btLogin=new JButton("가입");
			btLogin.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					int a=0;
					if(teName.getText().equals("")) {					
					}else {
						a++; //이름을 써야한다 a==1
					}
					if(idCheck==0) { //아이디 체크를 하지 않을때이다
						JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요");
					}else if(idCheck==2) {
						idStr=teId.getText();
						a++;
					}
					//패스워드 저장이다
					char[]secret_ps=password.getPassword();
					String ps="";
					for(char cha:secret_ps) {
						Character.toString(cha);
						ps+=cha;
					}
					if(ps.equals("")) {//패스워드가 공백일때이다.
						laPwNull.setVisible(true);
						laPwBad.setVisible(false);
						laPwGood.setVisible(false);
					}
					//패스워드 확인 저장
					char[]secret_ps_confirm=passwordCon.getPassword();
					String ps_confirm="";
					for(char cha:secret_ps_confirm) {
						Character.toString(cha);
						ps_confirm+=cha;
					}
					if(ps_confirm.equals("")) { //비밀번로 확인이 공백일때이다
						laPwNull.setVisible(true);
						laPwBad.setVisible(false);
						laPwGood.setVisible(false);
					}
					if(ps.equals(ps_confirm)) { //비밀번호가 일치할때이다
						laPwGood.setVisible(true);
						laPwNull.setVisible(false);
						laPwBad.setVisible(false);
					}else {
						laPwBad.setVisible(true);
						laPwGood.setVisible(false);
						laPwNull.setVisible(false);
					}
					if(a==3) {
						Member member=new Member();
						member.setName(idStr);
						member.setId(teId.getText());
						member.setPw(ps_confirm);
						member.setEmail(tEmail.getText());
						
						mdao.memberInsert(member);
						JOptionPane.showMessageDialog(null, "가입이되었습니다");
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "가입양식을 확인하세요");
					}
				}
			});
			btLogin.setBounds(29,313,97,23);
		}
		return btLogin;
	}
	//취소버튼
	private JButton getBtCancel() {
		if(btCancel==null) {
			btCancel=new JButton("취소");
			btCancel.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btCancel.setBounds(156,313,97,23);
		}
		return btCancel;
	}

}
