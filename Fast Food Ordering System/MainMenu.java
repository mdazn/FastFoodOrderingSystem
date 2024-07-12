import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel implements ActionListener {
	JLabel picLabel, title;
	JButton button1, button2;
	private Panel panel_1;
	static JFrame frame;

	public void createAndShowGUI() throws IOException {
		JPanel panel = new JPanel(new BorderLayout());
		Image image = ImageIO.read(this.getClass().getResource("logo-fast-food-signboard.jpg"));
		Image imageScaled = image.getScaledInstance(350, 300, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(imageScaled);
		picLabel = new JLabel(imageIcon);
		Box right = Box.createVerticalBox();
		panel_1 = new Panel();
		title = new JLabel("FAST FOOD RESTAURANT");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setAlignmentY(0.0f);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Bauhaus 93",Font.PLAIN, 18));
		title.setForeground(Color.BLACK);

		// Button, with filler
		button1 = new JButton("Start Ordering");
		button1.setPreferredSize(new Dimension (150, 30));
		button1.addActionListener(this);
		button2 = new JButton("Delivery");
		panel_1.add(button1);
		button1.setAlignmentX(Component.CENTER_ALIGNMENT);


		panel.add(picLabel, BorderLayout.CENTER);
		panel.add(right, BorderLayout.SOUTH);
		right.add(title);
		right.add(panel_1);
		add(panel);

		
	}

	public static void main(String args[]) throws IOException {
		MainMenu main = new MainMenu();
		main.createAndShowGUI();
		frame = new JFrame();
		frame.setTitle("Fast Food Ordering System");
		frame.getContentPane().add(main);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button1){
			CustDetailFrame p;
			try {
				p = new CustDetailFrame();
				p.main(null);
				frame.dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
			
		}
		
	}
}
