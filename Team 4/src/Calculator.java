import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;

@SuppressWarnings("serial") // warning 무시
public class Calculator extends JFrame implements ActionListener{
	public static final int WIDTH = 300;
	public static final int HEIGHT = 350;
	// 계산기 창 기본 설정
	public static final int NOC = 20; // 텍스트 필드 크기
	
	private JTextField C_Field; 
	private JTextField R_Field;
	// 계산 과정을 보여주는 C_Field와, 결과값과 입력값을 보여주는 R_Field
	
	private double result = 0.0; // 결과값을 저장
	private double a = 0.0; // 여태까지 계산한 중간값을 저장
	private double b = 0.0; // 연산될 피연산자
	private String g = null; // 연산자를 저장할 변수
	// 계산을 위한 변수들
	private boolean tf = false; //true면 연산기호 다음이라 숫자 다시 받음, false면 이어서 받음
	private boolean dot = false; // 소숫점이 없다면 false, 있다면 true로 변환

	private String[] B_Text = {"±", "√", "X^2", "1/X", "CE", "C", "←", "÷", "7", "8", "9"
			, "×", "4", "5", "6", "-", "1", "2", "3", "+", "00", "0", ".", "="}; // 버튼에 들어갈 값
	
	private JButton[] Button = new JButton[B_Text.length]; // 버튼 배열
	
	public static void main(String[] args)
	{
		Calculator gui = new Calculator();
		gui.setVisible(true);
	} // 프로그램 실행
	
	public Calculator()
	{
		super("계산기 by Team 4"); // 프로그램 이름
		setSize(WIDTH, HEIGHT); // 가로 300, 세로 350의 프로그램 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x를 눌렀을 때 바로 꺼짐
		setLayout(new FlowLayout()); // 프로그램을 FlowLayout으로 설정
		
		C_Field = new JTextField(NOC); // 크기 20을 가지는 C_Field를 생성
		C_Field.setEditable(false); // C_Field 안의 값들은 수정불가로 설정
		add(C_Field);
		// 계산 과정 보여주는 텍스트 필드
		
		R_Field = new JTextField(NOC); // 크기 20을 가지는 R_Field를 생성
		R_Field.setEditable(false); // R_Field 안의 값들은 수정불가로 설정
		add(R_Field);
		// 결과값을 보여주는 텍스트 필드
		
		JPanel ButtonPanel = new JPanel(new GridLayout(6, 4, 10, 10)); // 버튼 패널을 GridLayout을 적용해 6 x 5 로 간격은 가로 세로 5씩
		for(int i =  0; i<B_Text.length; i++)
		{
			Button[i] = new JButton(B_Text[i]); // 버튼에 B_Text에 해당하는 값을 입력하고 만듦
			Button[i].addActionListener(this); // 버튼을 눌렀을 때 동작하게
			ButtonPanel.add(Button[i]);
		}
		// 각 버튼을 만들어 버튼 패널에 넣음
		add(ButtonPanel);
	}

	public void actionPerformed(ActionEvent e) // 버튼을 눌렀을 때 
	{
		String p = e.getActionCommand(); // 버튼을 눌렀을 때 버튼에 해당하는 값을 p에 저장
		
		if(p.equals("1")||p.equals("2")||p.equals("3")||p.equals("4")||p.equals("5")
	            ||p.equals("6")||p.equals("7")||p.equals("8")||p.equals("9")) // 숫자를 입력받을 때
		{
			if(tf) // 만약 처음 적거나 연산 부호 뒤에 쓴다면
			{
				R_Field.setText(p); // R_Field에 있는 값과 상관없이 해당 숫자를 입력
				tf = false; // 이어적을 수 있게 변경
			}
			else // 아니라면
			{
				R_Field.setText(R_Field.getText()+p); // R_Field에 있는 값 뒤에 이어적기
			}
		}
		
		else if(p.equals("0") || p.equals("00")) // 만약 '0', '00' 버튼을 눌렀다면
		{
			if(R_Field.getText()!="" && !tf && dot) // 만약 R_Field에 값이 있고, 처음 적거나 연산 부호 뒤도 아니고, 소숫점을 추가했다면
			{
				R_Field.setText(R_Field.getText()+p); // 여태껏 썼던 값 뒤에 추가
			}
			else if(R_Field.getText()!=""&& ! tf && !dot) // 위와 동일한 상태에서 소숫점을 추가하지 않은 경우
			{
				double temp = Double.parseDouble(R_Field.getText()); // 해당 값을 받아서
				if(p.equals("0")) // 만약 0을 눌렀다면
				{
					R_Field.setText(temp*10+""); // 10을 곱한 값을 출력
				}
				else // 00을 눌렀다면
				{
					R_Field.setText(temp*100+""); // 100을 곱한 값을 출력
				}
			}
			else if(tf) // 만약 처음 적거나 연산 부호 뒤라면
			{
				R_Field.setText("0"); // 0으로 출력시킴
				tf = true;
			}
			else // R_Field 내에 값이 없다면
			{
				R_Field.setText("0"); // 0으로 출력시킴
				tf = true;
			}
		}
		
		else if (p.equals("=")) // 만약 '=' 버튼을 눌렀다면
		{
			b = Double.parseDouble(R_Field.getText()); // R_Field 내에 있는 값을 b에 저장
			switch(g)
			{
				case ("+"): // g에 +가 저장되어있으면
					result = a + b; // 연산하고
					a = result; // a에 result 값 저장
					R_Field.setText(result+""); // R_Field에 결과값을 출력
					C_Field.setText(""); // C_Field의 값들을 없애고 비워둠
					g = null;
					dot = false;
	            	tf = true;
	            	// 부호 여부, 기타 조건들 초기화
	            	break;
	            case ("-"): // g에 -가 저장되어있으면
	               result = a - b; // 연산하고
	               a = result; // a에 result 값 저장
	               R_Field.setText(result+""); // R_Field에 결과값을 출력
	               C_Field.setText(""); // C_Field의 값들을 없애고 비워둠
	               g = null;
	               dot = false;
	               tf = true;
	               // 부호 여부, 기타 조건들 초기화
	               break;
	            case ("×"): // g에 ×가 저장되어있으면
	               result = a * b; // 연산하고
	               a = result; // a에 result 값 저장
	               R_Field.setText(result+""); // R_Field에 결과값을 출력
	               C_Field.setText(""); // C_Field의 값들을 없애고 비워둠
	               g = null;
	               dot = false;
	               tf = true;
	               // 부호 여부, 기타 조건들 초기화
	               break;
	            case ("÷"): // g에 ÷가 저장되어있으면
	               result = a / b; // 연산하고
	               a = result; // a에 result 값 저장
	               R_Field.setText(result+""); // R_Field에 결과값을 출력
	               C_Field.setText(""); // C_Field의 값들을 없애고 비워둠
	               g = null;
	               dot = false;
	               tf = true;
	               // 부호 여부, 기타 조건들 초기화
	               break;
			}   
		}
		
	    else if (p.equals("+")) // 만약 '+' 버튼을 눌렀다면
	    {
	    	
	    	C_Field.setText(C_Field.getText()+R_Field.getText()+p); // C_Field에 여태껏 저장되어있던 C_Field의 값에 R_Field의 값과 해당 부호를 추가
	        if(g == null) // 만약 처음으로 부호를 받았다면 
	        {
	        	a = Double.parseDouble(R_Field.getText()); // a에 R_Field의 값을 저장
	        	g ="+"; // g에 + 부호 저장
	        	dot = false; // 소숫점 쓸 수 있게 초기화
	        	tf = true; // 연산 부호 뒤임을 알려줌
	        }
	        else
	        {
	        	b = Double.parseDouble(R_Field.getText()); // R_Field 내에 있는 값을 b에 저장
	        	switch(g)
	        	{
	        		case ("+"): // g에 +가 저장되어있으면
	        			result = a + b; // 연산하고
	                 	a = result; // a에 result 값 저장
	                 	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                 	g ="+"; // g에 + 부호 저장
	                 	dot = false; // 소숫점 쓸 수 있게 초기화
	                 	tf = true; // 연산 부호 뒤임을 알려줌
	                 	break;
	        		case ("-"): // g에 -가 저장되어있으면
	        			result = a - b; // 연산하고
	        			a = result; // a에 result 값 저장
	        			R_Field.setText(result+""); // R_Field에 결과값을 출력
	        			g ="+"; // g에 + 부호 저장
	        			dot = false; // 소숫점 쓸 수 있게 초기화
	        			tf = true; // 연산 부호 뒤임을 알려줌
	        			break;
	        		case ("×"): // g에 ×가 저장되어있으면
	        			result = a * b; // 연산하고
	              	 	a = result; // a에 result 값 저장
	              	 	R_Field.setText(result+""); // R_Field에 결과값을 출력
	              	 	g ="+"; // g에 + 부호 저장
	              	 	dot = false; // 소숫점 쓸 수 있게 초기화
	              	 	tf = true; // 연산 부호 뒤임을 알려줌
	              	 	break;
	        		case ("÷"): // g에 ÷가 저장되어있으면
	        			result = a / b; // 연산하고
	                 	a = result; // a에 result 값 저장
	                 	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                 	g ="+"; // g에 + 부호 저장
	                 	dot = false; // 소숫점 쓸 수 있게 초기화
	                 	tf = true; // 연산 부호 뒤임을 알려줌
	                 	break;
	        	}   
	        }
	    }
			
	    else if (p.equals("-")) // 만약 '-' 버튼을 눌렀다면
	    {
	    	C_Field.setText(C_Field.getText()+R_Field.getText()+p); // C_Field에 여태껏 저장되어있던 C_Field의 값에 R_Field의 값과 해당 부호를 추가
	    	if(g == null) // 만약 처음으로 부호를 받았다면 
	    	{
	    		a = Double.parseDouble(R_Field.getText()); // a에 R_Field의 값을 저장
	            g ="-"; //  // g에 - 부호 저장
	            dot = false; // 소숫점 쓸 수 있게 초기화
	            tf = true; // 연산 부호 뒤임을 알려줌
	    	}
	    	else
	    	{
	    		b = Double.parseDouble(R_Field.getText()); // R_Field 내에 있는 값을 b에 저장
	            switch(g)
	            {
	            	case ("+"): // g에 +가 저장되어있으면
	            		result = a + b; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                  	g ="-"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            	case ("-"): // g에 -가 저장되어있으면
	            		result = a - b; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                  	g ="-"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            	case ("×"): // g에 ×가 저장되어있으면
	            		result = a * b; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                  	g ="-"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            	case ("÷"): // g에 ÷가 저장되어있으면
	            		result = a / b; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                  	g ="-"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            }   
	    	}
	    }
		
	    else if (p.equals("×")) // 만약 '×' 버튼을 눌렀다면
	    {
	    	C_Field.setText(C_Field.getText()+R_Field.getText()+p); // C_Field에 여태껏 저장되어있던 C_Field의 값에 R_Field의 값과 해당 부호를 추가
	    	if(g == null) // 만약 처음으로 부호를 받았다면 
	    	{
	    		a = Double.parseDouble(R_Field.getText()); // a에 R_Field의 값을 저장
	            g ="×"; // g에 × 부호 저장
	            dot = false; // 소숫점 쓸 수 있게 초기화
	            tf = true; // 연산 부호 뒤임을 알려줌
	    	}
	    	else
	    	{
	    		b = Double.parseDouble(R_Field.getText()); // R_Field 내에 있는 값을 b에 저장
	            switch(g)
	            {
	            	case ("+"): // g에 +가 저장되어있으면
	            		result = a + b ; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                  	g ="×"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            	case ("-"): // g에 -가 저장되어있으면
	            		result = a - b ; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                  	g ="×"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            	case ("×"): // g에 ×가 저장되어있으면
	            		result = a * b ; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                  	g ="×"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            	case ("÷"): // g에 ÷가 저장되어있으면
	            		result = a / b ; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                  	g ="×"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            }   
	    	}
	    }
			
	    else if (p.equals("÷")) // 만약 '÷' 버튼을 눌렀다면
	    {
	    	C_Field.setText(C_Field.getText()+R_Field.getText()+p); // C_Field에 여태껏 저장되어있던 C_Field의 값에 R_Field의 값과 해당 부호를 추가
	    	if(g == null) // 만약 처음으로 부호를 받았다면 
	    	{
	    		a = Double.parseDouble(R_Field.getText()); // a에 R_Field의 값을 저장
	            g ="÷"; // g에 ÷ 부호 저장
	            dot = false; // 소숫점 쓸 수 있게 초기화
	            tf = true; // 연산 부호 뒤임을 알려줌
	    	}
	    	else
	    	{
	    		b = Double.parseDouble(R_Field.getText()); // R_Field 내에 있는 값을 b에 저장
	            switch(g)
	            {
	            	case ("+"): // g에 +가 저장되어있으면
	            		result = a + b ; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                  	g ="÷"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            	case ("-"): // g에 -가 저장되어있으면
	            		result = a - b ; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+""); // R_Field에 결과값을 출력
	                  	g ="÷"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            	case ("×"): // g에 ×가 저장되어있으면
	            		result = a * b ; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+"");
	                  	g ="÷"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            	case ("÷"): // g에 ÷가 저장되어있으면
	            		result = a / b ; // 연산하고
	                  	a = result; // a에 result 값 저장
	                  	R_Field.setText(result+"");
	                  	g ="÷"; // g에 - 부호 저장
	                  	dot = false; // 소숫점 쓸 수 있게 초기화
	                  	tf = true; // 연산 부호 뒤임을 알려줌
	                  	break;
	            }   
	    	}	
	    }
		
	    else if (p.equals("C")) // 만약 'C' 버튼을 눌렀다면
	    {
	    	result = 0.0;
	    	a = 0.0;
	    	b = 0.0;
	    	g = null;
	    	tf = false;
	    	C_Field.setText("");
	    	R_Field.setText("");
	    	// 모든 값을 초기화
	    }
		
	    else if (p.equals("CE")) // 만약 'CE' 버튼을 눌렀다면
	    {
	    	R_Field.setText(""); // R_Field에 있는 값을 지움
	    }
		
	    else if (p.equals("←")) // 만약 '←' 버튼을 눌렀다면	
	    {
	    	int len = R_Field.getText().length(); // R_Field에 있는 문자열의 길이를 찾아서
	    	String update = R_Field.getText(); // R_Field에 있는 값을 저장한 후
	    	if(len==1) // 만약 문자열의 길이가 1이라면
	    	{
	    		R_Field.setText(""); // 값을 지워 빈 공간으로 만듦
	    	}
	    	else // 아니라면
	    	{
	    		if(!"".equals(update)) // R_Field의 내용이 공백이 아니면
	    		{
	    			update = update.substring(0, len-1); // R_Field의 내용 중 제일 최근에 추가한 문자를 삭제
	    			R_Field.setText(update); // R_Field에 추가
	    		}
	    		else // 만약 공백이라면
	    		{
	    			R_Field.setText(""); // 그냥 그대로 공백인 상태로
	    		}
	        	}
	        }
		
	    else if (p.equals("X^2")) // 만약 'X^2'(제곱) 버튼을 눌렀다면
	    {
	    	String temp = R_Field.getText(); // R_Field의 값을 받아서
	    	String t = Double.parseDouble(temp)*Double.parseDouble(temp)+""; // 제곱한 값을 저장하고
	    	R_Field.setText(t); // R_Field에 해당 값을 출력
	    }
		
	    else if (p.equals("1/X")) // 만약 '1/X'(역수) 버튼을 눌렀다면
	    {
	    	String temp = R_Field.getText(); // R_Field의 값을 받아서
	    	String t = 1/Double.parseDouble(temp)+""; // 1을 R_Field의 값으로 나눈 후  
	    	R_Field.setText(t); // R_Field에 해당 값을 출력
	    }
		
	    else if (p.equals("√")) // 만약 '√' (제곱근) 버튼을 눌렀다면
	    {
	    	String temp = R_Field.getText(); // R_Field의 값을 받아서
	    	String t = Math.sqrt(Double.parseDouble(temp))+""; // 제곱근의 값을 구하고
	    	R_Field.setText(t); // R_Field에 해당 값을 출력
	    }
		
	    else if (p.equals("±")) // 만약 '±' (부호) 버튼을 눌렀다면
	    {
	    	String temp = R_Field.getText(); // R_Field의 값을 받아서
	    	double num = Double.parseDouble(temp); // 해당 값을 double형으로 변환한 후
	    	if(num == 0) // 그 값이 0이면
	    	{
	    		R_Field.setText("0"); // 그대로 0을 출력
	    		tf = true;
	    	}
	    	else // 0이 아니라면
	    	{
	    		String t = num * -1 +""; // -1을 곱하여 부호를 반대로 만든 후
	    		R_Field.setText(t); // 출력
	    	}
	    }
			
	    else // 만약 '.' (소숫점) 버튼을 눌렀을 때
	    {
	    	String temp = R_Field.getText(); // R_Field의 값을 받은 후
	    	if(dot) // 소숫점을 처음 누른게 아니라면
	    	{
	    		R_Field.setText(temp); // 변화없이 그대로 출력
	    	}
	    	else // 소숫점을 처음 누른거라면
	    	{
	    		if("".equals(temp)) // R_Field의 내용이 공백이면
	    		{
	    			R_Field.setText("0."); // 앞에 0을 붙여서 소숫점 추가
	    			dot = true; // 소숫점을 붙였음을 보여줌
	    			tf = false; // 이어적게 만듦
	    		}
	    		else // R_Field의 내용이 공백이 아니라면
	    		{
	    			R_Field.setText(temp+"."); // R_Field의 내용 뒤에 소숫점 추가
	    			dot = true; // 소숫점을 붙였음을 확인
	    			tf = false; // 이어적게 만듦
	    		}
	    	}
	    }		
	}
}
	
