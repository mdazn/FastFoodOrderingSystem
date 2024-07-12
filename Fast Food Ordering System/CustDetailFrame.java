import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;


public class CustDetailFrame implements ActionListener{
    private JLabel lbl;
    private JLabel lblName;
    private JLabel lblPhone;
    private JTextField txtName;
    private JTextField txtPhone;
    private JLabel lblAddress;
    private JTextArea txtAddress;
    private JLabel lblOpt;
    private JRadioButton rbSP;
    private JRadioButton rbD;
    private JLabel lblTime;
    private JTextField txtTime;
    private JComboBox cbTime;
    private JButton btnConfirm;
    private JButton btnCancel;
    static JFrame frame;
    String[] choice = {"AM", "PM"};

    public void createAndShowGUI() {
        //construct preComponents
        frame = new JFrame ("Customer Details");
        frame.setBounds(100, 100, 420, 350);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //construct components
        lbl = new JLabel ("Fill in the details.");
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
		lbl.setForeground(Color.BLUE);
        lblName = new JLabel ("Name:");
        lblPhone = new JLabel ("Phone No:");
        txtName = new JTextField (10);
        txtPhone = new JTextField (10);
        lblAddress = new JLabel ("Address:");
        txtAddress = new JTextArea (5,10);
        txtAddress.setLineWrap(true);
        txtAddress.setWrapStyleWord(true);
        lblOpt = new JLabel ("Option:");
        ButtonGroup bg = new ButtonGroup();
        rbSP = new JRadioButton ("Self-Pickup");
        rbD = new JRadioButton ("Delivery");
        bg.add(rbSP);
        bg.add(rbD);
        lblTime = new JLabel ("Time:");
        txtTime = new JTextField (10);
        cbTime = new JComboBox (choice);
        btnConfirm = new JButton ("Confirm");
        btnConfirm.addActionListener(this);
        btnCancel = new JButton ("Cancel");
        btnCancel.addActionListener(this);

        //adjust size and set layout
        frame.setPreferredSize (new Dimension (370, 340));

        //add components
        frame.getContentPane().add (lbl);
        frame.getContentPane().add (lblName);
        frame.getContentPane().add (lblPhone);
        frame.getContentPane().add (txtName);
        frame.getContentPane().add (txtPhone);
        frame.getContentPane().add (lblAddress);
        frame.getContentPane().add (txtAddress);
        frame.getContentPane().add (lblOpt);
        frame.getContentPane().add (rbSP);
        frame.getContentPane().add (rbD);
        frame.getContentPane().add (lblTime);
        frame.getContentPane().add (txtTime);
        frame.getContentPane().add (cbTime);
        frame.getContentPane().add (btnConfirm);
        frame.getContentPane().add (btnCancel);

        //set component bounds (only needed by Absolute Positioning)
        lbl.setBounds (120, 10, 110, 25);
        lblName.setBounds (30, 40, 100, 25);
        lblPhone.setBounds (30, 70, 100, 25);
        txtName.setBounds (105, 40, 200, 25);
        txtPhone.setBounds (105, 70, 200, 25);
        lblAddress.setBounds (30, 100, 100, 25);
        txtAddress.setBounds (105, 100, 200, 75);
        lblOpt.setBounds (30, 180, 100, 25);
        rbSP.setBounds (105, 180, 100, 25);
        rbD.setBounds (215, 180, 100, 25);
        lblTime.setBounds (30, 210, 100, 25);
        txtTime.setBounds (105, 210, 80, 25);
        cbTime.setBounds (190, 210, 50, 25);
        btnConfirm.setBounds (115, 255, 90, 25);
        btnCancel.setBounds (210, 255, 90, 25);
    }


    public static void main (String[] args) {
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustDetailFrame c = new CustDetailFrame();
                    c.createAndShowGUI();
                    
                    frame.pack();
                    c.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
        
    }

    public CustDetailFrame() throws IOException{
        createAndShowGUI();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnCancel){
			MainMenu food;
			try {
                food = new MainMenu();
				food.main(null);
				frame.dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    }

    if(e.getSource()==btnConfirm){
        if (txtName.getText().equals("") || txtPhone.getText().equals("")
                    || txtAddress.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Field cannot be empty");
            } else {
                String custName = txtName.getText();
                String custPhone = txtPhone.getText();
                String custAddress = txtAddress.getText();
                String opt ="";
                if(rbSP.isSelected()){
                    opt = "Self-Pickup";
                }
                else if(rbD.isSelected()){
                    opt = "Delivery";
                }
                
                String time = txtTime.getText();
                String time2 = choice[cbTime.getSelectedIndex()];

                File f = new File("custDetails.txt");
                try (PrintWriter pw = new PrintWriter(f)) {
                    pw.println(custName+","+custPhone+","+custAddress+","+ opt +"," +time+","+time2);
                    pw.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                

                StartOrder main = new StartOrder();
                main.main(null);
                frame.dispose();

                
            }}
}}
