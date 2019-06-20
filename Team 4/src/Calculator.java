import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;

@SuppressWarnings("serial") // warning ����
public class Calculator extends JFrame implements ActionListener{
	public static final int WIDTH = 300;
	public static final int HEIGHT = 350;
	// ���� â �⺻ ����
	public static final int NOC = 20; // �ؽ�Ʈ �ʵ� ũ��
	
	private JTextField C_Field; 
	private JTextField R_Field;
	// ��� ������ �����ִ� C_Field��, ������� �Է°��� �����ִ� R_Field
	
	private double result = 0.0; // ������� ����
	private double a = 0.0; // ���±��� ����� �߰����� ����
	private double b = 0.0; // ����� �ǿ�����
	private String g = null; // �����ڸ� ������ ����
	// ����� ���� ������
	private boolean tf = false; //true�� �����ȣ �����̶� ���� �ٽ� ����, false�� �̾ ����
	private boolean dot = false; // �Ҽ����� ���ٸ� false, �ִٸ� true�� ��ȯ

	private String[] B_Text = {"��", "��", "X^2", "1/X", "CE", "C", "��", "��", "7", "8", "9"
			, "��", "4", "5", "6", "-", "1", "2", "3", "+", "00", "0", ".", "="}; // ��ư�� �� ��
	
	private JButton[] Button = new JButton[B_Text.length]; // ��ư �迭
	
	public static void main(String[] args)
	{
		Calculator gui = new Calculator();
		gui.setVisible(true);
	} // ���α׷� ����
	
	public Calculator()
	{
		super("���� by Team 4"); // ���α׷� �̸�
		setSize(WIDTH, HEIGHT); // ���� 300, ���� 350�� ���α׷� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x�� ������ �� �ٷ� ����
		setLayout(new FlowLayout()); // ���α׷��� FlowLayout���� ����
		
		C_Field = new JTextField(NOC); // ũ�� 20�� ������ C_Field�� ����
		C_Field.setEditable(false); // C_Field ���� ������ �����Ұ��� ����
		add(C_Field);
		// ��� ���� �����ִ� �ؽ�Ʈ �ʵ�
		
		R_Field = new JTextField(NOC); // ũ�� 20�� ������ R_Field�� ����
		R_Field.setEditable(false); // R_Field ���� ������ �����Ұ��� ����
		add(R_Field);
		// ������� �����ִ� �ؽ�Ʈ �ʵ�
		
		JPanel ButtonPanel = new JPanel(new GridLayout(6, 4, 10, 10)); // ��ư �г��� GridLayout�� ������ 6 x 5 �� ������ ���� ���� 5��
		for(int i =  0; i<B_Text.length; i++)
		{
			Button[i] = new JButton(B_Text[i]); // ��ư�� B_Text�� �ش��ϴ� ���� �Է��ϰ� ����
			Button[i].addActionListener(this); // ��ư�� ������ �� �����ϰ�
			ButtonPanel.add(Button[i]);
		}
		// �� ��ư�� ����� ��ư �гο� ����
		add(ButtonPanel);
	}

	public void actionPerformed(ActionEvent e) // ��ư�� ������ �� 
	{
		String p = e.getActionCommand(); // ��ư�� ������ �� ��ư�� �ش��ϴ� ���� p�� ����
		
		if(p.equals("1")||p.equals("2")||p.equals("3")||p.equals("4")||p.equals("5")
	            ||p.equals("6")||p.equals("7")||p.equals("8")||p.equals("9")) // ���ڸ� �Է¹��� ��
		{
			if(tf) // ���� ó�� ���ų� ���� ��ȣ �ڿ� ���ٸ�
			{
				R_Field.setText(p); // R_Field�� �ִ� ���� ������� �ش� ���ڸ� �Է�
				tf = false; // �̾����� �� �ְ� ����
			}
			else // �ƴ϶��
			{
				R_Field.setText(R_Field.getText()+p); // R_Field�� �ִ� �� �ڿ� �̾�����
			}
		}
		
		else if(p.equals("0") || p.equals("00")) // ���� '0', '00' ��ư�� �����ٸ�
		{
			if(R_Field.getText()!="" && !tf && dot) // ���� R_Field�� ���� �ְ�, ó�� ���ų� ���� ��ȣ �ڵ� �ƴϰ�, �Ҽ����� �߰��ߴٸ�
			{
				R_Field.setText(R_Field.getText()+p); // ���²� ��� �� �ڿ� �߰�
			}
			else if(R_Field.getText()!=""&& ! tf && !dot) // ���� ������ ���¿��� �Ҽ����� �߰����� ���� ���
			{
				double temp = Double.parseDouble(R_Field.getText()); // �ش� ���� �޾Ƽ�
				if(p.equals("0")) // ���� 0�� �����ٸ�
				{
					R_Field.setText(temp*10+""); // 10�� ���� ���� ���
				}
				else // 00�� �����ٸ�
				{
					R_Field.setText(temp*100+""); // 100�� ���� ���� ���
				}
			}
			else if(tf) // ���� ó�� ���ų� ���� ��ȣ �ڶ��
			{
				R_Field.setText("0"); // 0���� ��½�Ŵ
				tf = true;
			}
			else // R_Field ���� ���� ���ٸ�
			{
				R_Field.setText("0"); // 0���� ��½�Ŵ
				tf = true;
			}
		}
		
		else if (p.equals("=")) // ���� '=' ��ư�� �����ٸ�
		{
			b = Double.parseDouble(R_Field.getText()); // R_Field ���� �ִ� ���� b�� ����
			switch(g)
			{
				case ("+"): // g�� +�� ����Ǿ�������
					result = a + b; // �����ϰ�
					a = result; // a�� result �� ����
					R_Field.setText(result+""); // R_Field�� ������� ���
					C_Field.setText(""); // C_Field�� ������ ���ְ� �����
					g = null;
					dot = false;
	            	tf = true;
	            	// ��ȣ ����, ��Ÿ ���ǵ� �ʱ�ȭ
	            	break;
	            case ("-"): // g�� -�� ����Ǿ�������
	               result = a - b; // �����ϰ�
	               a = result; // a�� result �� ����
	               R_Field.setText(result+""); // R_Field�� ������� ���
	               C_Field.setText(""); // C_Field�� ������ ���ְ� �����
	               g = null;
	               dot = false;
	               tf = true;
	               // ��ȣ ����, ��Ÿ ���ǵ� �ʱ�ȭ
	               break;
	            case ("��"): // g�� ���� ����Ǿ�������
	               result = a * b; // �����ϰ�
	               a = result; // a�� result �� ����
	               R_Field.setText(result+""); // R_Field�� ������� ���
	               C_Field.setText(""); // C_Field�� ������ ���ְ� �����
	               g = null;
	               dot = false;
	               tf = true;
	               // ��ȣ ����, ��Ÿ ���ǵ� �ʱ�ȭ
	               break;
	            case ("��"): // g�� ���� ����Ǿ�������
	               result = a / b; // �����ϰ�
	               a = result; // a�� result �� ����
	               R_Field.setText(result+""); // R_Field�� ������� ���
	               C_Field.setText(""); // C_Field�� ������ ���ְ� �����
	               g = null;
	               dot = false;
	               tf = true;
	               // ��ȣ ����, ��Ÿ ���ǵ� �ʱ�ȭ
	               break;
			}   
		}
		
	    else if (p.equals("+")) // ���� '+' ��ư�� �����ٸ�
	    {
	    	
	    	C_Field.setText(C_Field.getText()+R_Field.getText()+p); // C_Field�� ���²� ����Ǿ��ִ� C_Field�� ���� R_Field�� ���� �ش� ��ȣ�� �߰�
	        if(g == null) // ���� ó������ ��ȣ�� �޾Ҵٸ� 
	        {
	        	a = Double.parseDouble(R_Field.getText()); // a�� R_Field�� ���� ����
	        	g ="+"; // g�� + ��ȣ ����
	        	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	        	tf = true; // ���� ��ȣ ������ �˷���
	        }
	        else
	        {
	        	b = Double.parseDouble(R_Field.getText()); // R_Field ���� �ִ� ���� b�� ����
	        	switch(g)
	        	{
	        		case ("+"): // g�� +�� ����Ǿ�������
	        			result = a + b; // �����ϰ�
	                 	a = result; // a�� result �� ����
	                 	R_Field.setText(result+""); // R_Field�� ������� ���
	                 	g ="+"; // g�� + ��ȣ ����
	                 	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                 	tf = true; // ���� ��ȣ ������ �˷���
	                 	break;
	        		case ("-"): // g�� -�� ����Ǿ�������
	        			result = a - b; // �����ϰ�
	        			a = result; // a�� result �� ����
	        			R_Field.setText(result+""); // R_Field�� ������� ���
	        			g ="+"; // g�� + ��ȣ ����
	        			dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	        			tf = true; // ���� ��ȣ ������ �˷���
	        			break;
	        		case ("��"): // g�� ���� ����Ǿ�������
	        			result = a * b; // �����ϰ�
	              	 	a = result; // a�� result �� ����
	              	 	R_Field.setText(result+""); // R_Field�� ������� ���
	              	 	g ="+"; // g�� + ��ȣ ����
	              	 	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	              	 	tf = true; // ���� ��ȣ ������ �˷���
	              	 	break;
	        		case ("��"): // g�� ���� ����Ǿ�������
	        			result = a / b; // �����ϰ�
	                 	a = result; // a�� result �� ����
	                 	R_Field.setText(result+""); // R_Field�� ������� ���
	                 	g ="+"; // g�� + ��ȣ ����
	                 	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                 	tf = true; // ���� ��ȣ ������ �˷���
	                 	break;
	        	}   
	        }
	    }
			
	    else if (p.equals("-")) // ���� '-' ��ư�� �����ٸ�
	    {
	    	C_Field.setText(C_Field.getText()+R_Field.getText()+p); // C_Field�� ���²� ����Ǿ��ִ� C_Field�� ���� R_Field�� ���� �ش� ��ȣ�� �߰�
	    	if(g == null) // ���� ó������ ��ȣ�� �޾Ҵٸ� 
	    	{
	    		a = Double.parseDouble(R_Field.getText()); // a�� R_Field�� ���� ����
	            g ="-"; //  // g�� - ��ȣ ����
	            dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	            tf = true; // ���� ��ȣ ������ �˷���
	    	}
	    	else
	    	{
	    		b = Double.parseDouble(R_Field.getText()); // R_Field ���� �ִ� ���� b�� ����
	            switch(g)
	            {
	            	case ("+"): // g�� +�� ����Ǿ�������
	            		result = a + b; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+""); // R_Field�� ������� ���
	                  	g ="-"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            	case ("-"): // g�� -�� ����Ǿ�������
	            		result = a - b; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+""); // R_Field�� ������� ���
	                  	g ="-"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            	case ("��"): // g�� ���� ����Ǿ�������
	            		result = a * b; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+""); // R_Field�� ������� ���
	                  	g ="-"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            	case ("��"): // g�� ���� ����Ǿ�������
	            		result = a / b; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+""); // R_Field�� ������� ���
	                  	g ="-"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            }   
	    	}
	    }
		
	    else if (p.equals("��")) // ���� '��' ��ư�� �����ٸ�
	    {
	    	C_Field.setText(C_Field.getText()+R_Field.getText()+p); // C_Field�� ���²� ����Ǿ��ִ� C_Field�� ���� R_Field�� ���� �ش� ��ȣ�� �߰�
	    	if(g == null) // ���� ó������ ��ȣ�� �޾Ҵٸ� 
	    	{
	    		a = Double.parseDouble(R_Field.getText()); // a�� R_Field�� ���� ����
	            g ="��"; // g�� �� ��ȣ ����
	            dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	            tf = true; // ���� ��ȣ ������ �˷���
	    	}
	    	else
	    	{
	    		b = Double.parseDouble(R_Field.getText()); // R_Field ���� �ִ� ���� b�� ����
	            switch(g)
	            {
	            	case ("+"): // g�� +�� ����Ǿ�������
	            		result = a + b ; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+""); // R_Field�� ������� ���
	                  	g ="��"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            	case ("-"): // g�� -�� ����Ǿ�������
	            		result = a - b ; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+""); // R_Field�� ������� ���
	                  	g ="��"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            	case ("��"): // g�� ���� ����Ǿ�������
	            		result = a * b ; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+""); // R_Field�� ������� ���
	                  	g ="��"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            	case ("��"): // g�� ���� ����Ǿ�������
	            		result = a / b ; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+""); // R_Field�� ������� ���
	                  	g ="��"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            }   
	    	}
	    }
			
	    else if (p.equals("��")) // ���� '��' ��ư�� �����ٸ�
	    {
	    	C_Field.setText(C_Field.getText()+R_Field.getText()+p); // C_Field�� ���²� ����Ǿ��ִ� C_Field�� ���� R_Field�� ���� �ش� ��ȣ�� �߰�
	    	if(g == null) // ���� ó������ ��ȣ�� �޾Ҵٸ� 
	    	{
	    		a = Double.parseDouble(R_Field.getText()); // a�� R_Field�� ���� ����
	            g ="��"; // g�� �� ��ȣ ����
	            dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	            tf = true; // ���� ��ȣ ������ �˷���
	    	}
	    	else
	    	{
	    		b = Double.parseDouble(R_Field.getText()); // R_Field ���� �ִ� ���� b�� ����
	            switch(g)
	            {
	            	case ("+"): // g�� +�� ����Ǿ�������
	            		result = a + b ; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+""); // R_Field�� ������� ���
	                  	g ="��"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            	case ("-"): // g�� -�� ����Ǿ�������
	            		result = a - b ; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+""); // R_Field�� ������� ���
	                  	g ="��"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            	case ("��"): // g�� ���� ����Ǿ�������
	            		result = a * b ; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+"");
	                  	g ="��"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            	case ("��"): // g�� ���� ����Ǿ�������
	            		result = a / b ; // �����ϰ�
	                  	a = result; // a�� result �� ����
	                  	R_Field.setText(result+"");
	                  	g ="��"; // g�� - ��ȣ ����
	                  	dot = false; // �Ҽ��� �� �� �ְ� �ʱ�ȭ
	                  	tf = true; // ���� ��ȣ ������ �˷���
	                  	break;
	            }   
	    	}	
	    }
		
	    else if (p.equals("C")) // ���� 'C' ��ư�� �����ٸ�
	    {
	    	result = 0.0;
	    	a = 0.0;
	    	b = 0.0;
	    	g = null;
	    	tf = false;
	    	C_Field.setText("");
	    	R_Field.setText("");
	    	// ��� ���� �ʱ�ȭ
	    }
		
	    else if (p.equals("CE")) // ���� 'CE' ��ư�� �����ٸ�
	    {
	    	R_Field.setText(""); // R_Field�� �ִ� ���� ����
	    }
		
	    else if (p.equals("��")) // ���� '��' ��ư�� �����ٸ�	
	    {
	    	int len = R_Field.getText().length(); // R_Field�� �ִ� ���ڿ��� ���̸� ã�Ƽ�
	    	String update = R_Field.getText(); // R_Field�� �ִ� ���� ������ ��
	    	if(len==1) // ���� ���ڿ��� ���̰� 1�̶��
	    	{
	    		R_Field.setText(""); // ���� ���� �� �������� ����
	    	}
	    	else // �ƴ϶��
	    	{
	    		if(!"".equals(update)) // R_Field�� ������ ������ �ƴϸ�
	    		{
	    			update = update.substring(0, len-1); // R_Field�� ���� �� ���� �ֱٿ� �߰��� ���ڸ� ����
	    			R_Field.setText(update); // R_Field�� �߰�
	    		}
	    		else // ���� �����̶��
	    		{
	    			R_Field.setText(""); // �׳� �״�� ������ ���·�
	    		}
	        	}
	        }
		
	    else if (p.equals("X^2")) // ���� 'X^2'(����) ��ư�� �����ٸ�
	    {
	    	String temp = R_Field.getText(); // R_Field�� ���� �޾Ƽ�
	    	String t = Double.parseDouble(temp)*Double.parseDouble(temp)+""; // ������ ���� �����ϰ�
	    	R_Field.setText(t); // R_Field�� �ش� ���� ���
	    }
		
	    else if (p.equals("1/X")) // ���� '1/X'(����) ��ư�� �����ٸ�
	    {
	    	String temp = R_Field.getText(); // R_Field�� ���� �޾Ƽ�
	    	String t = 1/Double.parseDouble(temp)+""; // 1�� R_Field�� ������ ���� ��  
	    	R_Field.setText(t); // R_Field�� �ش� ���� ���
	    }
		
	    else if (p.equals("��")) // ���� '��' (������) ��ư�� �����ٸ�
	    {
	    	String temp = R_Field.getText(); // R_Field�� ���� �޾Ƽ�
	    	String t = Math.sqrt(Double.parseDouble(temp))+""; // �������� ���� ���ϰ�
	    	R_Field.setText(t); // R_Field�� �ش� ���� ���
	    }
		
	    else if (p.equals("��")) // ���� '��' (��ȣ) ��ư�� �����ٸ�
	    {
	    	String temp = R_Field.getText(); // R_Field�� ���� �޾Ƽ�
	    	double num = Double.parseDouble(temp); // �ش� ���� double������ ��ȯ�� ��
	    	if(num == 0) // �� ���� 0�̸�
	    	{
	    		R_Field.setText("0"); // �״�� 0�� ���
	    		tf = true;
	    	}
	    	else // 0�� �ƴ϶��
	    	{
	    		String t = num * -1 +""; // -1�� ���Ͽ� ��ȣ�� �ݴ�� ���� ��
	    		R_Field.setText(t); // ���
	    	}
	    }
			
	    else // ���� '.' (�Ҽ���) ��ư�� ������ ��
	    {
	    	String temp = R_Field.getText(); // R_Field�� ���� ���� ��
	    	if(dot) // �Ҽ����� ó�� ������ �ƴ϶��
	    	{
	    		R_Field.setText(temp); // ��ȭ���� �״�� ���
	    	}
	    	else // �Ҽ����� ó�� �����Ŷ��
	    	{
	    		if("".equals(temp)) // R_Field�� ������ �����̸�
	    		{
	    			R_Field.setText("0."); // �տ� 0�� �ٿ��� �Ҽ��� �߰�
	    			dot = true; // �Ҽ����� �ٿ����� ������
	    			tf = false; // �̾����� ����
	    		}
	    		else // R_Field�� ������ ������ �ƴ϶��
	    		{
	    			R_Field.setText(temp+"."); // R_Field�� ���� �ڿ� �Ҽ��� �߰�
	    			dot = true; // �Ҽ����� �ٿ����� Ȯ��
	    			tf = false; // �̾����� ����
	    		}
	    	}
	    }		
	}
}
	
