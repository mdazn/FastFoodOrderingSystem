import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.time.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class GUI extends JFrame implements ActionListener {
    private final int numFood = 10, numDrinks = 10, numDessert = 8;
    private JButton btnConfirm, btnBack;
    private JLabel lblFoodOrdered, lblTotal;
    private JTextField txtTotal;

    private DefaultTableModel dtm = new DefaultTableModel(0, 0);
    private JTable table;
    private JPanel pnlFood, pnlDrinks, pnlDessert;
    private Dish[] dish =new Dish[28];
    private JSpinner[] spinFood = new JSpinner[numFood];
    private JSpinner[] spinDrinks = new JSpinner[numDrinks];
    private JSpinner[] spinDessert = new JSpinner[numDessert];

    private JCheckBox cultOpt;
    private JLabel paymentOpt;
    private JRadioButton rbCash;
    private JRadioButton rbEwallet;
    private JRadioButton rbCredit;

   
    JLabel[] foodLabel = new JLabel[numFood];
    String[] foodName = new String[numFood];
	Double[] foodprice = new Double[numFood];
    Double[] foodtotalprice = new Double[numFood];
    JLabel[] drinkLabel = new JLabel[numDrinks];
    String[] drinkName = new String[numDrinks];
	Double[] drinkprice = new Double[numDrinks];
    JLabel[] dessertLabel = new JLabel[numDessert];
    String[] dessertName = new String[numDessert];
	Double[] dessertprice = new Double[numDessert];
    JLabel[] foodPricelbl = new JLabel[numFood];
    JLabel[] drinkPricelbl = new JLabel[numDrinks];
    JLabel[] dessertPricelbl = new JLabel[numDessert];
    double totalPrice = 0;
    double total = 0;
    double totalForFoods;
	double totalForDrinks;
	double totalForDesserts;
    double de0, de1, de2, de3, de4, de5, de6, de7;
    double dr0, dr1, dr2, dr3, dr4, dr5, dr6, dr7, dr8, dr9;
    double f0, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13;
    
    public GUI( ) {
        final String header[] = new String[] { "Item", "Qty", "Price", "Spinner" };
        //set the frame default properties.
        super.setTitle ( "MENU" );
        super.setSize ( 750, 500);
        super.setLocation ( 150, 250);

        //register 'Exit upon closing' as a default close operation
        super.setDefaultCloseOperation( EXIT_ON_CLOSE );

        try{
            Scanner scn = new Scanner(new File("menu.txt"));
            int i=0;
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                String tokens[] = line.split(",");      
                
                dish[i] = new Dish(tokens[0], Double.parseDouble(tokens[1]), tokens[2]);
                i++;
            }
            scn.close();
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int b=0, c=0, d=0;
        for(int a=0; a<dish.length; a++){
            if(dish[a].getDishType().equals("Food")){
                foodprice[b] = dish[a].getPrice();
                foodName[b] = dish[a].getDishName();
                b++;
            }
            else if(dish[a].getDishType().equals("Drinks")){
                drinkprice[c] = dish[a].getPrice();
                drinkName[c] = dish[a].getDishName();
                c++;
            }
            else if(dish[a].getDishType().equals("Dessert")){
                dessertprice[d] = dish[a].getPrice();
                dessertName[d] = dish[a].getDishName();
                d++;
            }
        }


        // create button object
        btnConfirm = new JButton("Confirm");
        btnBack = new JButton("Back");
        // register button to event listener
        btnConfirm.addActionListener(this);
        btnBack.addActionListener( this );
        // add to content pane
        Container cp = super.getContentPane();
        // set layout
        cp.setLayout( null);
        lblFoodOrdered = new JLabel("Food Ordered");
        lblFoodOrdered.setBounds(529, 11, 81, 14);
        cp.add(lblFoodOrdered);
        dtm.setColumnIdentifiers(header);
		dtm.addRow(header);
        table = new JTable();
		table.setModel(dtm);
		table.setBounds(475, 31, 1, 1);
		table.setSize(245, 300); 
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);
		table.setShowGrid(false); 
        cp.add(table);

        lblTotal = new JLabel("Total :");
        lblTotal.setBounds(519, 340, 46, 14);
		cp.add(lblTotal);

        txtTotal = new JTextField();
		txtTotal.setBounds(585, 340, 86, 20);
		cp.add(txtTotal);

        btnConfirm.setBounds(500, 385, 89, 23);
		cp.add(btnConfirm);
		btnBack.setBounds(610, 385, 89, 23);
		cp.add(btnBack);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

        pnlFood= new JPanel(new GridLayout(16,3));
        pnlDrinks= new JPanel(new GridLayout(12,3));
        pnlDessert= new JPanel(new GridLayout(11,3));

        JLabel header1=new JLabel("Item");
        JLabel header2=new JLabel("Price/Unit (RM)");
        JLabel header3=new JLabel("Qty");
        JLabel header4=new JLabel("Item");
        JLabel header5=new JLabel("Price/Unit (RM)");
        JLabel header6=new JLabel("Qty");
        JLabel header7=new JLabel("Item");
        JLabel header8=new JLabel("Price/Unit (RM)");
        JLabel header9=new JLabel("Qty");

        
        pnlFood.add(header1);
        pnlFood.add(header2);
        pnlFood.add(header3);
        for(int i=0; i<numFood;i++){
            SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1);
            spinFood[i] = new JSpinner(spnummodel);
            spinFood[i].addChangeListener(listenerFood);
            foodLabel[i]=new JLabel(foodName[i]);
            foodPricelbl[i] = new JLabel(Double.toString(foodprice[i]));
            pnlFood.add(foodLabel[i]);
            pnlFood.add(foodPricelbl[i]);
            pnlFood.add(spinFood[i]);
        }

        pnlDrinks.add(header4);
        pnlDrinks.add(header5);
        pnlDrinks.add(header6);
        for(int i=0; i<numDrinks;i++){
            SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1);
            spinDrinks[i] = new JSpinner(spnummodel);
            spinDrinks[i].addChangeListener(listenerDrinks);
            drinkLabel[i]=new JLabel(drinkName[i]);
            drinkPricelbl[i] = new JLabel(Double.toString(drinkprice[i]));
            pnlDrinks.add(drinkLabel[i]);
            pnlDrinks.add(drinkPricelbl[i]);
            pnlDrinks.add(spinDrinks[i]);
        }

        pnlDessert.add(header7);
        pnlDessert.add(header8);
        pnlDessert.add(header9);
        for(int i=0; i<numDessert;i++){
            SpinnerNumberModel spnummodel = new SpinnerNumberModel(0, 0, 10, 1);
            spinDessert[i] = new JSpinner(spnummodel);
            spinDessert[i].addChangeListener(listenerDessert);
            dessertLabel[i]=new JLabel(dessertName[i]);
            dessertPricelbl[i] = new JLabel(Double.toString(dessertprice[i]));
            pnlDessert.add(dessertLabel[i]);
            pnlDessert.add(dessertPricelbl[i]);
            pnlDessert.add(spinDessert[i]);
        }


        

        tabbedPane.addTab("Food", pnlFood);
        tabbedPane.addTab("Drinks", pnlDrinks);
        tabbedPane.addTab("Dessert", pnlDessert);
		tabbedPane.setBounds(18, 11, 450, 400);
		cp.add(tabbedPane);
        
    }

    
    ChangeListener listenerDessert = new ChangeListener() {
        public void stateChanged(ChangeEvent e) {

        final int quantity = (int) ((JSpinner) e.getSource()).getValue();
		final int rows = table.getRowCount();

			for (int row = 0; row < rows; row++) {
                if (dtm.getValueAt(row, 3) == e.getSource()) {
					if (dtm.getValueAt(row, 0).equals(dessertName[0])) {
						dtm.setValueAt(quantity, row, 1); // obj, row, column
						dtm.setValueAt(dessertprice[0] * quantity, row, 2);
						de0 = dessertprice[0] * quantity;

					} else if (dtm.getValueAt(row, 0).equals(dessertName[1])) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(dessertprice[1] * quantity, row, 2);
						de1 = dessertprice[1] * quantity;
					} else if (dtm.getValueAt(row, 0).equals(dessertName[2])) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(dessertprice[2] * quantity, row, 2);
						de2 = dessertprice[2] * quantity;
					} else if (dtm.getValueAt(row, 0).equals(dessertName[3])) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(dessertprice[3] * quantity, row, 2);
						de3 = dessertprice[3] * quantity;
					} else if (dtm.getValueAt(row, 0).equals(dessertName[4])) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(dessertprice[4] * quantity, row, 2);
						de4 = dessertprice[4] * quantity;
					} else if (dtm.getValueAt(row, 0).equals(dessertName[5])) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(dessertprice[5] * quantity, row, 2);
						de5 = dessertprice[5] * quantity;
					} else if (dtm.getValueAt(row, 0).equals(dessertName[6])) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(dessertprice[6] * quantity, row, 2);
						de6 = dessertprice[6] * quantity;
					} else if (dtm.getValueAt(row, 0).equals(dessertName[7])) {

						dtm.setValueAt(quantity, row, 1);
						dtm.setValueAt(dessertprice[7] * quantity, row, 2);
						de7 = dessertprice[7] * quantity;
					}

					if (quantity == 0) {
						dtm.removeRow(row);
					}

					totalForDesserts = de0 + de1 + + de2 + de3 + de4 + de5 + de6 + de7;
					total = totalForFoods + totalForDrinks + totalForDesserts;
					txtTotal.setText(total + "");
					return;
			}}

			// there was no row with this JSpinner, so we have to add it
			for (int i = 0; i < numDessert; i++) {
				// looking for the "clicked" JSpinner
				if (spinDessert[i] == e.getSource()) {
					totalPrice = dessertprice[i];
					if(dessertLabel[i].getText().equals(dessertName[0])) {
                        de0= dessertprice[i];
					}
                    else if(dessertLabel[i].getText().equals(dessertName[1])) {
                        de1= dessertprice[i];
					}
                    else if(dessertLabel[i].getText().equals(dessertName[2])) {
                        de2= dessertprice[i];
					}
                    else if(dessertLabel[i].getText().equals(dessertName[3])) {
                        de3= dessertprice[i];
					}
                    else if(dessertLabel[i].getText().equals(dessertName[4])) {
                        de4= dessertprice[i];
					}
                    else if(dessertLabel[i].getText().equals(dessertName[5])) {
                        de5= dessertprice[i];
					}
                    else if(dessertLabel[i].getText().equals(dessertName[6])) {
                        de6= dessertprice[i];
					}
                    else if(dessertLabel[i].getText().equals(dessertName[7])) {
                        de7= dessertprice[i];
					}
					totalForDesserts = de0 + de1 + + de2 + de3 + de4 + de5 + de6 + de7;
					total = totalForFoods + totalForDrinks + totalForDesserts;
					txtTotal.setText(total + "");
					dtm.addRow(new Object[] { dessertLabel[i].getText(), quantity, totalPrice, spinDessert[i] });
					return;
				}

			}
    
}};

ChangeListener listenerDrinks = new ChangeListener() {
    public void stateChanged(ChangeEvent e) {

    final int quantity = (int) ((JSpinner) e.getSource()).getValue();
    final int rows = table.getRowCount();

        for (int row = 0; row < rows; row++) {
            if (dtm.getValueAt(row, 3) == e.getSource()) {
                if (dtm.getValueAt(row, 0).equals(drinkName[0])) {
                    dtm.setValueAt(quantity, row, 1); // obj, row, column
                    dtm.setValueAt(drinkprice[0] * quantity, row, 2);
                    dr0 = drinkprice[0] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(drinkName[1])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(drinkprice[1] * quantity, row, 2);
                    dr1 = drinkprice[1] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(drinkName[2])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(drinkprice[2] * quantity, row, 2);
                    dr2 = drinkprice[2] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(drinkName[3])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(drinkprice[3] * quantity, row, 2);
                    dr3 = drinkprice[3] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(drinkName[4])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(drinkprice[4] * quantity, row, 2);
                    dr4 = drinkprice[4] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(drinkName[5])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(drinkprice[5] * quantity, row, 2);
                    dr5 = drinkprice[5] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(drinkName[6])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(drinkprice[6] * quantity, row, 2);
                    dr6 = drinkprice[6] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(drinkName[7])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(drinkprice[7] * quantity, row, 2);
                    dr7 = drinkprice[7] * quantity;
                }else if (dtm.getValueAt(row, 0).equals(drinkName[8])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(drinkprice[8] * quantity, row, 2);
                    dr8 = drinkprice[8] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(drinkName[9])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(drinkprice[9] * quantity, row, 2);
                    dr9 = drinkprice[9] * quantity;
                }

                if (quantity == 0) {
                    dtm.removeRow(row);
                }

                totalForDrinks = dr0+ dr1+ dr2+ dr3+ dr4+ dr5+ dr6+ dr7+ dr8+ dr9;
                total = totalForFoods + totalForDrinks + totalForDesserts;
                txtTotal.setText(total + "");
                return;
        }}

        // there was no row with this JSpinner, so we have to add it
        for (int i = 0; i < numDrinks; i++) {
            // looking for the "clicked" JSpinner
            if (spinDrinks[i] == e.getSource()) {
                totalPrice = drinkprice[i];
                if(drinkLabel[i].getText().equals(drinkName[0])) {
                    dr0= drinkprice[i];
                }
                else if(drinkLabel[i].getText().equals(drinkName[1])) {
                    dr1= drinkprice[i];
                }
                else if(drinkLabel[i].getText().equals(drinkName[2])) {
                    dr2= drinkprice[i];
                }
                else if(drinkLabel[i].getText().equals(drinkName[3])) {
                    dr3= drinkprice[i];
                }
                else if(drinkLabel[i].getText().equals(drinkName[4])) {
                    dr4= drinkprice[i];
                }
                else if(drinkLabel[i].getText().equals(drinkName[5])) {
                    dr5= drinkprice[i];
                }
                else if(drinkLabel[i].getText().equals(drinkName[6])) {
                    dr6= drinkprice[i];
                }
                else if(drinkLabel[i].getText().equals(drinkName[7])) {
                    dr7= drinkprice[i];
                }
                else if(drinkLabel[i].getText().equals(drinkName[6])) {
                    dr8= drinkprice[i];
                }
                else if(drinkLabel[i].getText().equals(drinkName[7])) {
                    dr9= drinkprice[i];
                }

                totalForDrinks = dr0+ dr1+ dr2+ dr3+ dr4+ dr5+ dr6+ dr7+ dr8+ dr9;
                total = totalForFoods + totalForDrinks + totalForDesserts;
                txtTotal.setText(total + "");
                dtm.addRow(new Object[] { drinkLabel[i].getText(), quantity, totalPrice, spinDrinks[i] });
                return;
            }

        }

}};


ChangeListener listenerFood = new ChangeListener() {
    public void stateChanged(ChangeEvent e) {

    final int quantity = (int) ((JSpinner) e.getSource()).getValue();
    final int rows = table.getRowCount();

        for (int row = 0; row < rows; row++) {
            if (dtm.getValueAt(row, 3) == e.getSource()) {
                if (dtm.getValueAt(row, 0).equals(foodName[0])) {
                    dtm.setValueAt(quantity, row, 1); 
                    dtm.setValueAt(foodprice[0] * quantity, row, 2);
                    f0 = foodprice[0] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(foodName[1])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(foodprice[1] * quantity, row, 2);
                    f1 = foodprice[1] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(foodName[2])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(foodprice[2] * quantity, row, 2);
                    f2 = foodprice[2] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(foodName[3])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(foodprice[3] * quantity, row, 2);
                    f3 = foodprice[3] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(foodName[4])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(foodprice[4] * quantity, row, 2);
                    f4 = drinkprice[4] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(foodName[5])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(drinkprice[5] * quantity, row, 2);
                    f5 = foodprice[5] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(foodName[6])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(foodprice[6] * quantity, row, 2);
                    f6 = foodprice[6] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(foodName[7])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(foodprice[7] * quantity, row, 2);
                    f7 = foodprice[7] * quantity;
                }else if (dtm.getValueAt(row, 0).equals(foodName[8])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(foodprice[8] * quantity, row, 2);
                    f8 = foodprice[8] * quantity;
                } else if (dtm.getValueAt(row, 0).equals(foodName[9])) {
                    dtm.setValueAt(quantity, row, 1);
                    dtm.setValueAt(foodprice[9] * quantity, row, 2);
                    f9 = foodprice[9] * quantity;
                }

                if (quantity == 0) {
                    dtm.removeRow(row);
                }

                totalForFoods =f0+ f1+ f2+ f3+ f4+f5+ f6+ f7+ f8+ f9;
                total = totalForFoods + totalForDrinks + totalForDesserts;
                txtTotal.setText(total + "");
                return;
        }}

        // there was no row with this JSpinner, so we have to add it
        for (int i = 0; i < numFood; i++) {
            // looking for the "clicked" JSpinner
            if (spinFood[i] == e.getSource()) {
                totalPrice = foodprice[i];
                if(foodLabel[i].getText().equals(foodName[0])) {
                    f0= foodprice[i];
                }
                else if(foodLabel[i].getText().equals(foodName[1])) {
                    f1= foodprice[i];
                }
                else if(foodLabel[i].getText().equals(foodName[2])) {
                    f2= foodprice[i];
                }
                else if(foodLabel[i].getText().equals(foodName[3])) {
                    f3= foodprice[i];
                }
                else if(foodLabel[i].getText().equals(foodName[4])) {
                    f4= foodprice[i];
                }
                else if(foodLabel[i].getText().equals(foodName[5])) {
                    f5= foodprice[i];
                }
                else if(foodLabel[i].getText().equals(foodName[6])) {
                    f6= foodprice[i];
                }
                else if(foodLabel[i].getText().equals(foodName[7])) {
                    f7= foodprice[i];
                }
                else if(foodLabel[i].getText().equals(foodName[8])) {
                    f8= foodprice[i];
                }
                else if(foodLabel[i].getText().equals(foodName[9])) {
                    f9= foodprice[i];
                }

                totalForFoods = f0+ f1+ f2+ f3+ f4+f5+ f6+ f7+ f8+ f9;
                total = totalForFoods + totalForDrinks + totalForDesserts;
                txtTotal.setText(total + "");
                dtm.addRow(new Object[] { foodLabel[i].getText(), quantity, totalPrice, spinFood[i] });
                return;
            }

        }

}};

    

@Override
    public void actionPerformed(ActionEvent e) {

        // check the event source
        if (e.getSource() == btnConfirm) {
            MainMenu p;
            String message = "TOTAL PRICE :   RM " + Double.toString(total);
            cultOpt = new JCheckBox ("No cutlery needed.");
            paymentOpt = new JLabel ("Payment Method:");
            rbCash = new JRadioButton ("Cash");
            rbEwallet = new JRadioButton ("E-wallet");
            rbCredit = new JRadioButton ("Credit/Debit card");
            JPanel pane = new JPanel();
            ButtonGroup bg = new ButtonGroup();
            bg.add(rbCash);
            bg.add(rbEwallet);
            bg.add(rbCredit);
            rbCash.addActionListener(this);
            rbEwallet.addActionListener(this);
            rbCredit.addActionListener(this);
            cultOpt.addActionListener(this);
            pane.add(paymentOpt);
            pane.add(rbCash);
            pane.add(rbEwallet);
            pane.add(rbCredit);
            paymentOpt.setBounds (30, 90, 100, 25);
            rbCash.setBounds (130, 90, 100, 25);
            rbEwallet.setBounds (130, 120, 100, 25);
            rbCredit.setBounds (130, 150, 125, 25);
            Object[] params = {message, cultOpt,pane};
            Object[] options = {"Confirm","Cancel"};
            int result =JOptionPane.showOptionDialog(null, params, "Order Confirmation",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,null);

            if(result == JOptionPane.YES_OPTION){
                try{
                    File f = new File("custDetails.txt");
                    Scanner scn = new Scanner(f);
                    PrintWriter pw = new PrintWriter("receipt.txt");
                    String line = scn.nextLine();
                    String tokens[] = line.split(",");
                    String custName = tokens[0];
                    String custPhone = tokens[1];
                    String custAddress = tokens[2];
                    String Opt =tokens[3];
                    String time = tokens[4];
			        String time2 = tokens[5];
                    LocalDate today = LocalDate.now();
                    LocalTime now = LocalTime.now();
                    final int rows = table.getRowCount();
                    int min =0, max=9999;
                    int orderID = (int)Math.floor(Math.random()*(max-min+1)+min);
                    String cultNeed = "";
                    if(cultOpt.isSelected())
                        cultNeed = "No cutlery needed";
                    else
                        cultNeed = "Cutlery needed";

                    String payMethod ="";
                    if(rbCash.isSelected())
                        payMethod = "Cash";
                    else if(rbEwallet.isSelected())
                        payMethod = "E-Wallet";
                    else if(rbCredit.isSelected())
                        payMethod = "Credit/Debit card";
            
                    pw.println("\t\tFAST FOOD RESTAURANT");
                    pw.println("--------------------------------------");
                    pw.println("Order ID: " + orderID);
                    pw.println("Date: "+today+", "+now);
                    pw.println("--------------------------------------");
                    pw.println("Customer Details:");
                    pw.println("Name: " + custName);
                    pw.println("Phone No: " + custPhone);
                    if(custAddress!=" "){
                         pw.println("Address: " + custAddress);
                    }
                    pw.println("Option: "+Opt);
                    pw.println("Pickup/Delivery Time: " + time + time2);
                    pw.println("Payment Method: "+payMethod);
                    pw.println("--------------------------------------");
                    for(int row=0;row<rows;row++){;
                        pw.printf("%-23s %3s %7s\n",dtm.getValueAt(row, 0),dtm.getValueAt(row, 1),dtm.getValueAt(row, 2));
                    }
                    pw.println("--------------------------------------");
                    pw.printf("SUBTOTAL(RM) %23.2f\n",total);
                    pw.println("--------------------------------------");
                    pw.println("Note: "+cultNeed);
                    scn.close();
                    pw.close();
                    JOptionPane.showMessageDialog(null, "Your foods will be ready soon. Thank you for ordering.");
				    this.dispose();
                    
				    
				    try {
                        p = new MainMenu();
                        p.main(null);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }


                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                }
                else if(result==JOptionPane.CANCEL_OPTION){
                    JOptionPane.getRootFrame().dispose(); 
                }
            }
    

        if(e.getSource()==btnBack){
            CustDetailFrame c;
            try {
				c = new CustDetailFrame();
				c.main(null);
				this.dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }

    }
}
