
import javax.swing.*;
import java.sql.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.ImageIcon;


class royalenfi implements ActionListener
{
	JFrame f;
    JTable j;
	JPanel panel;  
	JLabel l1,l2,l3,engLabel,brlLabel,modelLabel,yLabel,filterLabel,imagelabel1;
	JButton  insert, del, disp, done ,delconfirmButton,filter,filterdone;
	JTextField engno,delengno,brand,model,year,filtertext;
	String eng, br, mod, yom, filterprice;
	
	
	
	royalenfi() throws ClassNotFoundException
	{
		
		f = new JFrame("RE SHOWROOM");
		
		panel = new JPanel(null, false);

		panel.setBounds(0,0,650,450);    
        panel.setBackground(Color.gray);  

		engno = new JTextField();
		brand = new JTextField();
		model = new JTextField();
		year = new JTextField();
        filtertext = new JTextField();

		engLabel = new JLabel("Engine no:");
		brlLabel = new JLabel("Model:");
		modelLabel = new JLabel("Displacement:");
		yLabel = new JLabel("Price:");
        filterLabel = new JLabel("Price:");
		imagelabel1=new JLabel();
		

		l1 = new JLabel("RE BIKE INVENTORY");
		l2 = new JLabel("Enter the data");
		l3 = new JLabel("Enter the engine number of the vehicle to be deleted");

		insert = new JButton("Insert");
		del = new JButton("Delete");
		disp = new JButton("Display");
		done = new JButton("Done");
		delconfirmButton = new JButton("Confirm Deletion");
        filter = new JButton("Filter by Price");
        filterdone = new JButton("Done");
		
		insert.setBounds(40,100,100,40);
		del.setBounds(150,100,100,40);
		disp.setBounds(260,100,100,40);
        filter.setBounds(370,100,150,40);

	
		done.setBounds(120,360,80,40);
		delconfirmButton.setBounds(120,240,130,40);
        filterdone.setBounds(120,250,100,30);

		l1.setBounds(40, 50, 800, 40);
		l2.setBounds(120,160,800,40);
		l3.setBounds(120,160,800,40);
		engLabel.setBounds(10,200,100,30);
		brlLabel.setBounds(10,240,100,30);
		modelLabel.setBounds(10,280,100,30);
		yLabel.setBounds(10,320,100,30);
        filterLabel.setBounds(60, 200, 100, 30);


		engno.setBounds(120,200,300,30);
        filtertext.setBounds(120,200,300,30);
		brand.setBounds(120,240,300,30);
		model.setBounds(120,280,300,30);
		year.setBounds(120,320,300,30);
		imagelabel1.setBounds(200, 60, 500, 450);
		
		
		panel.add(insert);
		panel.add(del);
		panel.add(disp);
		panel.add(l1);
        panel.add(filter);
		panel.add(imagelabel1);
		

		f.add(panel);
		f.setLayout(null);
		f.setVisible(true);
		f.setSize(650,450);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon imageIcon = new ImageIcon("images.jpeg");
        imagelabel1.setIcon(imageIcon);

		insert.addActionListener(this);
		del.addActionListener(this);
		disp.addActionListener(this);
		done.addActionListener(this);
		delconfirmButton.addActionListener(this);
        filter.addActionListener(this);
        filterdone.addActionListener(this );
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == insert)
		{
			System.out.print("insert");
			panel.removeAll();

			panel.add(insert);
			panel.add(del);
			panel.add(disp);
			panel.add(done);
            panel.add(filter);
			

			panel.add(l1);
			panel.add(engLabel);
			panel.add(brlLabel);
			panel.add(modelLabel);
			panel.add(yLabel);
			panel.add(l2);

			panel.add(engno);
			panel.add(brand);
			panel.add(model);
			panel.add(year);
			
			
			panel.repaint();		
		}

		if(e.getSource() == done)
		{
				
				System.out.println("done");
				eng = engno.getText();
				System.out.println(eng);
				br = brand.getText();
				System.out.println(br);
				mod = model.getText();
				System.out.println(mod);
				yom = year.getText();
			
				try 
				{
				// Register JDBCdriver
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Open a connection
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bikes","root","aromal2003");
				//Create a statement object
				Statement st  = con.createStatement();
				st.executeUpdate("insert into royalenfibikes values('"+eng+"','"+br+"','"+mod+"','"+yom+"')");
				con.close();
				engno.setText("");
				brand.setText("");
				model.setText("");
				year.setText("");
				}
	 
				catch (Exception eere) 
				{
					System.out.println(eere);
				}		
		}

		if(e.getSource() == del)
		{
			panel.removeAll();

			panel.add(insert);
			panel.add(del);
			panel.add(disp);
            panel.add(filter);

			panel.add(l1);
			panel.add(engLabel);

			engno.setText("");
			brand.setText("");
			model.setText("");
			year.setText("");

			panel.add(l3);
			panel.add(engno);
			engno.repaint(0, 0, 0, 0, 0);
			panel.add(delconfirmButton);
			panel.repaint();
				
						
		}
		if(e.getSource() == delconfirmButton)
		{
			System.out.println("delconfirmed");
			eng = engno.getText();
			System.out.println("eng num: "+eng);
			try 
				{
				// Register JDBCdriver
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Open a connection
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bikes","root","aromal2003");
				//Create a statement object
				Statement st  = con.createStatement();
				st.executeUpdate("delete from royalenfibikes where enginenumber ='"+eng+"'; ");
				con.close();
				engno.setText("");
				}
	 
				catch (Exception eere) 
				{
					System.out.println(eere);
				}		

		}
		if(e.getSource() == disp)
		{
			JFrame frame = new JFrame("Bikes Database");
			frame.dispose();
		
			JPanel panel = new JPanel();

			try 
				{
				// Register JDBCdriver
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Open a connection
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bikes","root","aromal2003");
				//Create a statement object
				Statement st  = con.createStatement();
				ResultSet rs = st.executeQuery("Select * from royalenfibikes");
				rs.next();
				int count = 1;
				while(rs.next())
				{
					count+=1;
				}				
				System.out.println(count);
				String[][] rec = new String[count][4];

				ResultSet r = st.executeQuery("Select * from royalenfibikes");
				r.next();
				for(int i = 0; i < count; i++)
				{
					rec[i][0] = r.getString(1);
					rec[i][1] = r.getString(2);
					rec[i][2] = r.getString(3);
					rec[i][3] = r.getString(4);
					r.next();
				}
		
		
				String[] header = { "Engine number ","Model","Displacement" ,"Price" };
				JTable table = new JTable(rec, header);
				panel.add(new JScrollPane(table));
				frame.add(panel);
				frame.setSize(550, 400);
				frame.setVisible(true);
				con.close();
				
			}
			catch (Exception eere) 
			{
				System.out.println(eere);
			}
		}

        if(e.getSource() == filter)
		{
            panel.removeAll();
            
			panel.add(insert);
			panel.add(del);
			panel.add(disp);
            panel.add(filter);
            panel.add(filterdone);

			panel.add(l1);
            panel.add(filterLabel);
            panel.add(filtertext);
            panel.repaint();
		}
        if(e.getSource() == filterdone)
		{
            System.out.println("filter done");
            JFrame frame = new JFrame("Bikes Database");
			frame.dispose();
			JPanel panel = new JPanel();

			System.out.println("filter done");
			filterprice = filtertext.getText();
			System.out.println("eng num: "+filterprice);
			try 
				{
				// Register JDBCdriver
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Open a connection
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bikes","root","aromal2003");
				//Create a statement object
				Statement st  = con.createStatement();
                ResultSet rs = st.executeQuery("select * from royalenfibikes where price <='"+filterprice+"'; ");
                rs.next();
				int count = 1;
				while(rs.next())
				{
					count+=1;
				}				
				System.out.println(count);
				String[][] rec = new String[count][4];

				ResultSet r = st.executeQuery("select * from royalenfibikes where price <='"+filterprice+"'; ");
				r.next();
				for(int i = 0; i < count; i++)
				{
					rec[i][0] = r.getString(1);
					rec[i][1] = r.getString(2);
					rec[i][2] = r.getString(3);
					rec[i][3] = r.getString(4);
					r.next();
				}
		
		
				String[] header = { "Engine number ","Model","Displacement" ,"Price" };
				JTable table = new JTable(rec, header);
				panel.add(new JScrollPane(table));
				frame.add(panel);
				frame.setSize(550, 400);
				frame.setVisible(true);
				con.close();

				filtertext.setText("");
				}
	 
				catch (Exception eere) 
				{
					System.out.println(eere);
				}		

		}		
			
	}
	public static void main(String args[])throws ClassNotFoundException
	{
		try
		{
			new royalenfi();
		}
		catch(Exception e)
		{

		}
	}
}
