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
	public static final int NOC = 20;
	
	private JTextField C_Field; 
	private JTextField R_Field;
	private String mid = "";
	private String rmid = "";
	private double result = 0.0;
	private double a = 0.0;
	private double b = 0.0;
	private String g = null;
	private boolean tf = false; //true면 연산기호 다음이라 숫자 다시 받음, false면 이어서 받음
	private boolean dot = false; // 소숫점이 없다면 false, 있다면 true로 변환

	private String[] B_Text = {"±", "√", "X^2", "1/X", "CE", "C", "←", "÷", "7", "8", "9"
			, "×", "4", "5", "6", "-", "1", "2", "3", "+", "00", "0", ".", "="}; // 버튼에 들어갈 값
	
	private JButton[] Button = new JButton[B_Text.length]; // 버튼 배열
	
	public static void main(String[] args) {
		Calculator gui = new Calculator();
		gui.setVisible(true);
	} // 프로그램 실행
	
	public Calculator()
	{
		super("Calculator");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		C_Field = new JTextField(NOC);
		C_Field.setEditable(false);
		add(C_Field);
		// 계산 과정 보여주는 텍스트 필드
		
		R_Field = new JTextField(NOC);
		R_Field.setEditable(false);
		add(R_Field);
		// 결과값을 보여주는 텍스트 필드
		
		JPanel ButtonPanel = new JPanel(new GridLayout(6, 4, 10, 10)); // 버튼 패널을 GridLayout을 적용해 6 x 5 로 간격은 가로 세로 5씩
		for(int i =  0; i<B_Text.length; i++)
		{
			Button[i] = new JButton(B_Text[i]);
			Button[i].addActionListener(this);
			ButtonPanel.add(Button[i]);
			
		}
		// 각 버튼을 만들어 버튼 패널에 넣음
		add(ButtonPanel);
	}

	
	public void actionPerformed(ActionEvent e) // 버튼을 눌렀을 때 
	{
		String p = e.getActionCommand();
		
		if(p.equals("1")||p.equals("2")||p.equals("3")||p.equals("4")||p.equals("5")
	            ||p.equals("6")||p.equals("7")||p.equals("8")||p.equals("9")) // 숫자를 입력받을 때
		{
			if(tf)
			{
				R_Field.setText(p);
				tf = false;
			}
			else
			{
				R_Field.setText(R_Field.getText()+p);
			}
		}
		else if(p.equals("0") || p.equals("00"))
		{
			if(R_Field.getText()!="" && !tf && dot)
			{
				R_Field.setText(R_Field.getText()+p);
			}
			else if(R_Field.getText()!=""&& ! tf && !dot)
			{
				double temp = Double.parseDouble(R_Field.getText());
				if(p.equals("0"))
				{
					R_Field.setText(temp*10+"");
				}
				else
				{
					R_Field.setText(temp*100+"");
				}
			}
			else if(tf)
			{
				R_Field.setText("0");
				tf = true;
			}
			else
			{
				R_Field.setText("0");
				tf = true;
			}
		}
		else if (p.equals("="))
		{
			b = Double.parseDouble(R_Field.getText());
			switch(g)
			{
				case ("+"):
					result = a + b ;
	            	a = result;
	            	R_Field.setText(result+"");
	            	C_Field.setText("");
	            	g = null;
	            	dot = false;
	            	tf = true;
	            	break;
	            case ("-"):
	               result = a - b ;
	               a = result;
	               R_Field.setText(result+"");
	               C_Field.setText("");
	               g = null;
	               dot = false;
	               tf = true;
	               break;
	            case ("×"):
	               result = a * b ;
	               a = result;
	               R_Field.setText(result+"");
	               C_Field.setText("");
	               g = null;
	               dot = false;
	               tf = true;
	               break;
	            case ("÷"):
	               result = a / b ;
	               a = result;
	               R_Field.setText(result+"");
	               C_Field.setText("");
	               g = null;
	               dot = false;
	               tf = true;
	               break;
	        }   
		}
	    else if (p.equals("+"))
	    {
	    	C_Field.setText(C_Field.getText()+R_Field.getText()+p);
	        if(g == null)
	        {
	           a = Double.parseDouble(R_Field.getText());
	           //R_Field.setText("");
	           g ="+";
               dot = false;
	           tf = true;
	        }
	        else
	        {
	           b = Double.parseDouble(R_Field.getText());
	           switch(g)
	           {
	              case ("+"):
	                 result = a + b ;
	                 a = result;
	                 R_Field.setText(result+"");
	                 g ="+";
		               dot = false;
	                 tf = true;
	                 break;
	              case ("-"):
	                 result = a - b ;
	                 a = result;
	                 R_Field.setText(result+"");
	                 g ="+";
		               dot = false;
	                 tf = true;
	                 break;
	              case ("×"):
	                 result = a * b ;
	              	 a = result;
	                 R_Field.setText(result+"");
	                 g ="+";
		               dot = false;
	                 tf = true;
	                 break;
	              case ("÷"):
	                 result = a / b ;
	                 a = result;
	                 R_Field.setText(result+"");
	                 g ="+";
		               dot = false;
	                 tf = true;
	                 break;
	            }   
	        }
	      }
	      else if (p.equals("-"))
	      {
	        	C_Field.setText(C_Field.getText()+R_Field.getText()+p);
	           if(g == null)
	         {
	            a = Double.parseDouble(R_Field.getText());
	            g ="-";
	               dot = false;
	            tf = true;
	         }
	         else
	         {
	            b = Double.parseDouble(R_Field.getText());
	            switch(g)
	            {
	               case ("+"):
	                  result = a + b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="-";
		               dot = false;
	                  tf = true;
	                  break;
	               case ("-"):
	                  result = a - b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="-";
		               dot = false;
	                  tf = true;
	                  break;
	               case ("×"):
	                  result = a * b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="-";
		               dot = false;
	                  tf = true;
	                  break;
	               case ("÷"):
	                  result = a / b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="-";
		               dot = false;
	                  tf = true;
	                  break;
	            }   
	         }
	        }
	        else if (p.equals("×"))
	        {
	        	C_Field.setText(C_Field.getText()+R_Field.getText()+p);
	           if(g == null)
	         {
	            a = Double.parseDouble(R_Field.getText());
	            g ="×";
	               dot = false;
	            tf = true;
	         }
	         else
	         {
	            b = Double.parseDouble(R_Field.getText());
	            switch(g)
	            {
	               case ("+"):
	                  result = a + b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="×";
		               dot = false;
	                  tf = true;
	                  break;
	               case ("-"):
	                  result = a - b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="×";
		               dot = false;
	                  tf = true;
	                  break;
	               case ("×"):
	                  result = a * b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="×";
		               dot = false;
	                  tf = true;
	                  break;
	               case ("÷"):
	                  result = a / b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="×";
		               dot = false;
	                  tf = true;
	                  break;
	            }   
	         }
	        }
	        else if (p.equals("÷"))
	        {
	        	C_Field.setText(C_Field.getText()+R_Field.getText()+p);
	           if(g == null)
	         {
	            a = Double.parseDouble(R_Field.getText());
	            g ="÷";
	               dot = false;
	            tf = true;
	         }
	         else
	         {
	            b = Double.parseDouble(R_Field.getText());
	            switch(g)
	            {
	               case ("+"):
	                  result = a + b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="÷";
		               dot = false;
	                  tf = true;
	                  break;
	               case ("-"):
	                  result = a - b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="÷";
		               dot = false;
	                  tf = true;
	                  break;
	               case ("×"):
	                  result = a * b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="÷";
		               dot = false;
	                  tf = true;
	                  break;
	               case ("÷"):
	                  result = a / b ;
	                  a = result;
	                  R_Field.setText(result+"");
	                  g ="÷";
		               dot = false;
	                  tf = true;
	                  break;
	            }   
	         }
	        }
	        else if (p.equals("C"))
	        {
	           result = 0.0;
	           a = 0.0;
	           b = 0.0;
	           g = null;
	           tf = false;
	           C_Field.setText("");
	           R_Field.setText("");
	        }
	        else if (p.equals("CE"))
	        {
	 
	           R_Field.setText("");
	        }
	        else if (p.equals("←"))
	        {
	        	int len = R_Field.getText().length();
	        	String update = R_Field.getText();
	        	if(len==1)
	        	{
	        		R_Field.setText("");
	        	}
	        	else
	        	{
	        		if(!"".equals(update))
	        		{
	        			update = update.substring(0, len-1);
	        			R_Field.setText(update);
	        		}
	        		else
	        		{
	        			R_Field.setText("");
	        		}
	        	}
	        }
	        else if (p.equals("X^2"))
			{
	        	String temp = R_Field.getText();
	        	String t = Double.parseDouble(temp)*Double.parseDouble(temp)+"";
	        	R_Field.setText(t);
			}
	        else if (p.equals("1/X"))
	        {
	        	String temp = R_Field.getText();
	        	String t = 1/Double.parseDouble(temp)+"";
	        	R_Field.setText(t);
	        }
	        else if (p.equals("√"))
	        {
	        	String temp = R_Field.getText();
	        	String t = Math.sqrt(Double.parseDouble(temp))+"";
	        	R_Field.setText(t);
	        }
	        else if (p.equals("±"))
	        {
	        	String temp = R_Field.getText();
	        	double num = Double.parseDouble(temp);
	        	if(num > 0)
	        	{
	        		String t = num * -1 +"";
	        		R_Field.setText(t);
	        	}
	        	else if (num < 0)
	        	{
	        		String t = num * -1 +"";
	        		R_Field.setText(t);
	        	}
	        	else
	        	{
	        		R_Field.setText("0");
	        		tf = true;
	        	}
	        }
	        else // .눌렀을 때
	        {
	        	String temp = R_Field.getText();
	        	if(dot)
	        	{
	        		R_Field.setText(temp);
	        	}
	        	else
	        	{
	        		if("".equals(temp))
	        		{
	        			R_Field.setText("0.");
	        			dot = true;
	        			tf = false;
	        		}
	        		else
	        		{
	        			R_Field.setText(temp+".");
	        			dot = true;
	        			tf = false;
	        		}
	        	}
	        }
		
		}
}
	
