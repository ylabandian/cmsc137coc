import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.Enumeration;
import java.*;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;

public class Clash extends JFrame implements ActionListener, Runnable{
	public static void main(String args[]){		 
		Clash c = new Clash();
	}

	Clash(){
		try{
			this.setComponents();
		}catch(IOException ex){}
		
		t.start();
		
	   	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   	this.setTitle("Clash of Clans");
	   	this.setSize(1200,600);
	   	this.setResizable(false);
	   	this.setVisible(true);
	}
	
	Image wall;
	Image cannon;
	Image archertower;
	Image mortar;
	Image airdef;
	Image wizardtower;
	Image airsweeper;
	Image hiddentesla;
	Image xbow;
	Image infernotower;
	Image buildershut;
	Image goldmine;
	Image elixcollector;
	Image dedrill;
	Image goldstorage;
	Image elixstorage;
	Image destorage;
	Image armycamp;
	Image barracks;
	Image darkbarracks;
	Image lab;
	Image spellfactory;
	Image darkspellfactory;
	Image townhall;
	Image clancastle;

	int[][] empty = new int[13][13];

	int barbCount = 100;
	int archCount = 75;
	int giantCount = 20;
	int wbCount = 20;
	int wizCount = 5;
	int dragCount = 5;
	int hogCount = 10;

	int barbPlaced = 0;
	int archPlaced = 0;
	int giantPlaced = 0;
	int wbPlaced = 0;
	int wizPlaced = 0;
	int dragPlaced = 0;
	int hogPlaced = 0;

	int currentBarb = 0;
	int currentArch = 0;
	int currentGiant = 0;
	int currentWb = 0;
	int currentWiz = 0;
	int currentDrag = 0;
	int currentHog = 0;

	int globalI = 0;
	int globalJ = 0;

	JButton[][] box = new JButton[13][13];
	JButton atk = new JButton("ATTACK");
	JButton barb = new JButton("Barbarian");
	JButton arch = new JButton("Archer");
	JButton giant = new JButton("Giant");
	JButton wb = new JButton("Wall Breaker");
	JButton drag = new JButton("Dragon");
	JButton wiz = new JButton("Wizard");
	JButton hog = new JButton("Hog Rider");

	JPanel mainPanel;
	JPanel panel1;
	JPanel game;
	JPanel troops;
	JPanel attack;

	Thread t = new Thread(this);
	
	public void setComponents() throws IOException{
		//-----------------PANEL 1------------------------//

			//-------------troops ------------------------//
			troops = new JPanel();
			troops.setLayout(new GridLayout(7,1));
			troops.setVisible(true);
			troops.setBackground(Color.GRAY);

			barb.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/barb.png")).getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));
			arch.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/archer.png")).getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));
			giant.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/giant.png")).getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));
			wb.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/wallbreaker.png")).getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));
			drag.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/dragon.png")).getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));
			wiz.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/wizard.png")).getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));
			hog.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/hog.png")).getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH)));


			barb.addActionListener(this);
			arch.addActionListener(this);
			giant.addActionListener(this);
			wb.addActionListener(this);
			drag.addActionListener(this);
			wiz.addActionListener(this);
			hog.addActionListener(this);

			troops.add(barb);
			troops.add(arch);
			troops.add(giant);
			troops.add(wb);
			troops.add(drag);
			troops.add(wiz);
			troops.add(hog);

			//-------------attacks ------------------------//
			attack = new JPanel();
			attack.setLayout(new BorderLayout());
			attack.setVisible(true);

			attack.add(atk, BorderLayout.SOUTH);


		//-----------------MAIN PANEL---------------------//
		mainPanel = new JPanel();
   		mainPanel.setLayout(new GridLayout(1,2));
   		mainPanel.setVisible(true);
   		
   		panel1 = new JPanel();
   		panel1.setLayout(new BorderLayout());
   		panel1.setVisible(true);

   		panel1.add(troops, BorderLayout.CENTER);
		//panel1.add(attack, BorderLayout.SOUTH);

   		
   		game = new JPanel();
   		game.setLayout(new GridLayout(13,13));
   		game.setVisible(true);
		
		
   		for(int i=0; i<13; i++){
			for(int j=0; j<13; j++){
				box[i][j] = new JButton();
				box[i][j].setBackground(Color.GREEN);
                //box[i][j].addActionListener(this);
				
				this.createIcons();
				
				game.add(box[i][j]);
			} 
		}
   		
   		this.changeIcon("inputFile.txt");

		mainPanel.add(panel1);
		mainPanel.add(game);
	   	this.add(mainPanel);
	}

	public void createIcons() throws IOException{
		try{
			wall = ImageIO.read(getClass().getResource("buildings/wall.png"));								//1
			cannon = ImageIO.read(getClass().getResource("buildings/cannon.png"));							//2
			archertower = ImageIO.read(getClass().getResource("buildings/archertower.png"));				//3
			mortar = ImageIO.read(getClass().getResource("buildings/mortar.png"));							//4
			airdef = ImageIO.read(getClass().getResource("buildings/airdef.png"));							//5
			wizardtower = ImageIO.read(getClass().getResource("buildings/wizardtower.png"));				//6
			airsweeper = ImageIO.read(getClass().getResource("buildings/airsweeper.png"));					//7
			hiddentesla = ImageIO.read(getClass().getResource("buildings/hiddentesla.png"));				//8
			xbow = ImageIO.read(getClass().getResource("buildings/xbow.png"));								//9
			infernotower = ImageIO.read(getClass().getResource("buildings/infernotower.png"));				//10
			//buildershut = ImageIO.read(getClass().getResource("buildings/buildershut.png"));				//11
			//goldmine = ImageIO.read(getClass().getResource("buildings/goldmine.png"));					//12
			//elixcollector = ImageIO.read(getClass().getResource("buildings/elixcollector.png"));			//13
			//dedrill = ImageIO.read(getClass().getResource("buildings/dedrill.png"));						//14
			//goldstorage = ImageIO.read(getClass().getResource("buildings/goldstorage.png"));				//15
			//elixstorage = ImageIO.read(getClass().getResource("buildings/elixstorage.png"));				//16
			//destorage = ImageIO.read(getClass().getResource("buildings/destorage.png"));					//17
			armycamp = ImageIO.read(getClass().getResource("buildings/armycamp.png"));						//18		
			barracks = ImageIO.read(getClass().getResource("buildings/barracks.png"));						//19
			darkbarracks = ImageIO.read(getClass().getResource("buildings/darkbarracks.png"));				//20
			lab = ImageIO.read(getClass().getResource("buildings/lab.png"));								//21
			spellfactory = ImageIO.read(getClass().getResource("buildings/spellfactory.png"));				//22
			darkspellfactory = ImageIO.read(getClass().getResource("buildings/darkspellfactory.png"));		//23
			townhall = ImageIO.read(getClass().getResource("buildings/townhall.png"));						//24
			clancastle = ImageIO.read(getClass().getResource("buildings/clancastle.png"));					//25
		}catch(IOException ex){}
	}

	public void changeIcon(String name) throws IOException{
		Scanner sc = new Scanner(new File(name));
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		int[][] grid = new int[13][13];
		int index = 0;

		try{
			while(sc.hasNextLine()) {	//gets all the numbers in the file, and put them in an arraylist (numbers)
				numbers.add(sc.nextInt());
		   	}
		}
		catch(Exception e){}
		finally{
			sc.close();
		}

		Image wall2 = wall.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);								
		Image cannon2 = cannon.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image archertower2 = archertower.getScaledInstance(39, 50, java.awt.Image.SCALE_SMOOTH);
		Image mortar2 = mortar.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image airdef2 = airdef.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image wizardtower2 = wizardtower.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image airsweeper2 = airsweeper.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image hiddentesla2 = hiddentesla.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image xbow2 = xbow.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image infernotower2 = infernotower.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		// Image buildershut2 = buildershut.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		// Image goldmin2 = goldmine.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		// Image elixcollector2 = elixcollector.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		// Image dedrill2 = dedrill.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		// Image goldstorage2 = goldstorage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		// Image elixstorage2 = elixstorage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		// Image destorage2 = destorage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image armycamp2 = armycamp.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
		Image barracks2 = barracks.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image darkbarracks2 = darkbarracks.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image lab2 = lab.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image spellfactory2 = spellfactory.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image darkspellfactory2 = darkspellfactory.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image townhall2 = townhall.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		Image clancastle2 = clancastle.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		
		for(int i=0; i<13; i++){
			for(int j=0; j<13; j++){
				grid[i][j] = numbers.get(index);
				index++;
			}
		}

		for(int i=0; i<13; i++){
			for(int j=0; j<13; j++){
				switch(grid[i][j]){
					case 1:	
						box[i][j].setIcon(new ImageIcon(wall2));
						empty[i][j] = 1;
						break;
					case 2:	
						box[i][j].setIcon(new ImageIcon(cannon2));
						empty[i][j] = 1;
						break;
					case 3:	
						box[i][j].setIcon(new ImageIcon(archertower2));
						empty[i][j] = 1;
						break;
					case 4:	
						box[i][j].setIcon(new ImageIcon(mortar2));
						empty[i][j] = 1;
						break;
					case 5:	
						box[i][j].setIcon(new ImageIcon(airdef2));
						empty[i][j] = 1;
						break;
					case 6:	
						box[i][j].setIcon(new ImageIcon(wizardtower2));
						empty[i][j] = 1;
						break;
					case 7:	
						box[i][j].setIcon(new ImageIcon(airsweeper2));
						empty[i][j] = 1;
						break;
					case 8:	
						box[i][j].setIcon(new ImageIcon(hiddentesla2));
						empty[i][j] = 1;
						break;
					case 9:	
						box[i][j].setIcon(new ImageIcon(xbow2));
						empty[i][j] = 1;
						break;
					case 10:	
						box[i][j].setIcon(new ImageIcon(infernotower2));
						empty[i][j] = 1;
						break;
					// case 11:	
					// 	box[i][j].setIcon(new ImageIcon(buildershut2));
					//  empty[i][j] = 1;
					// 	break;
					// case 12:	
					// 	box[i][j].setIcon(new ImageIcon(goldmin2));
					//  empty[i][j] = 1;
					// 	break;
					// case 13:	
					// 	box[i][j].setIcon(new ImageIcon(elixcollector2));
					//  empty[i][j] = 1;
					// 	break;
					// case 14:	
					// 	box[i][j].setIcon(new ImageIcon(dedrill2));
					//  empty[i][j] = 1;
					// 	break;
					// case 15:	
					// 	box[i][j].setIcon(new ImageIcon(goldstorage2));
					//  empty[i][j] = 1;
					// 	break;
					// case 16:	
					// 	box[i][j].setIcon(new ImageIcon(elixstorage2));
					//  empty[i][j] = 1;
					// 	break;
					// case 17:	
					// 	box[i][j].setIcon(new ImageIcon(destorage2));
					//  empty[i][j] = 1;
					// 	break;
					case 18:	
						box[i][j].setIcon(new ImageIcon(armycamp2));
						empty[i][j] = 1;
						break;
					case 19:	
						box[i][j].setIcon(new ImageIcon(barracks2));
						empty[i][j] = 1;
						break;
					case 20:	
						box[i][j].setIcon(new ImageIcon(darkbarracks2));
						empty[i][j] = 1;
						break;
					case 21:
						box[i][j].setIcon(new ImageIcon(lab2));
						empty[i][j] = 1;
						break;
					case 22:	
						box[i][j].setIcon(new ImageIcon(spellfactory2));
						empty[i][j] = 1;
						break;
					case 23:	
						box[i][j].setIcon(new ImageIcon(darkspellfactory2));
						empty[i][j] = 1;
						break;
					case 24:
						box[i][j].setIcon(new ImageIcon(townhall2));
						empty[i][j] = 1;
						break;
					case 25:	
						box[i][j].setIcon(new ImageIcon(clancastle2));
						empty[i][j] = 1;
						break;

				}
				index++;
			}
		}
	}

	public void actionPerformed(ActionEvent event){
		try{
			if(event.getSource() == barb){	
				placeRandomTroop(barb);
				
				barbCount--; //barbarians left
				barbPlaced++; //barbarians placed
				checkTroopCount(barb);
			}else if(event.getSource() == arch){			
				placeRandomTroop(arch);
				archCount--;
				checkTroopCount(arch);
			}else if(event.getSource() == giant){			
				placeRandomTroop(giant);
				giantCount--;
				checkTroopCount(giant);
			}else if(event.getSource() == wb){			
				placeRandomTroop(wb);
				wbCount--;
				checkTroopCount(wb);
			}else if(event.getSource() == wiz){			
				placeRandomTroop(wiz);
				wizCount--;
				checkTroopCount(wiz);
			}else if(event.getSource() == drag){			
				placeRandomTroop(drag);
				dragCount--;
				checkTroopCount(drag);
			}else if(event.getSource() == hog){			
				placeRandomTroop(hog);
				hogCount--;
				checkTroopCount(hog);
			}
		}catch(Exception e){}
	}

	public void placeRandomTroop(JButton troop) throws IOException{
		boolean redZone;
		int i;
		int j;
		int newI = 0;
		int newJ = 0;

		do{
			i = (int)(Math.random()*13); 
			j = (int)(Math.random()*13); 

			redZone = isEmpty(i,j);
		}while(!redZone);

		if(troop == barb){
			box[i][j].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/barb.png")).getScaledInstance(43, 43, java.awt.Image.SCALE_SMOOTH)));	
		}else if(troop == arch){
			box[i][j].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/archer.png")).getScaledInstance(43, 43, java.awt.Image.SCALE_SMOOTH)));
		}else if(troop == giant){
			box[i][j].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/giant.png")).getScaledInstance(43, 43, java.awt.Image.SCALE_SMOOTH)));
		}else if(troop == wb){
			box[i][j].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/wallbreaker.png")).getScaledInstance(43, 43, java.awt.Image.SCALE_SMOOTH)));
		}else if(troop == wiz){
			box[i][j].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/wizard.png")).getScaledInstance(43, 43, java.awt.Image.SCALE_SMOOTH)));
		}else if(troop == drag){
			box[i][j].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/dragon.png")).getScaledInstance(43, 43, java.awt.Image.SCALE_SMOOTH)));
		}else if(troop == hog){
			box[i][j].setIcon(new ImageIcon(ImageIO.read(getClass().getResource("troops/hog.png")).getScaledInstance(43, 43, java.awt.Image.SCALE_SMOOTH)));
		}

		empty[i][j] = 1;


		// System.out.println("CLASH -> Old I: "+barbT[barbIndex].getOldI());
		// System.out.println("CLASH -> New I: "+barbT[barbIndex].getNewI());
		// // newI = barbT[barbIndex].getI();

		globalI = i;
		globalJ = j;

		System.out.println("CLASH PLACERANDOMTROOP: globalI == "+globalI);
	}

	public boolean isEmpty(int i, int j){
		if(empty[i][j] == 0) return true;
		else return false;
	}

	public void checkTroopCount(JButton troop){

		if(troop == barb){
			if(barbCount == 0) barb.setEnabled(false);
		}else if(troop == arch){
			if(archCount == 0) arch.setEnabled(false);
		}else if(troop == giant){
			if(giantCount == 0) giant.setEnabled(false);
		}else if(troop == wb){
			if(wbCount == 0) wb.setEnabled(false);
		}else if(troop == wiz){
			if(wizCount == 0) wiz.setEnabled(false);
		}else if(troop == drag){
			if(dragCount == 0) drag.setEnabled(false);
		}else if(troop == hog){
			if(hogCount == 0) hog.setEnabled(false);
		}
	}

	// public void moveTroop(int i, int j, int newI, int newJ) throws IOException{
	// 	try{
			
	// 	}catch(InterruptedException ie){}
	// }

	public void run(){
		// try{
		// 	moveTroop(barbT[barbIndex].getOldI(), barbT[barbIndex].getOldJ(), barbT[barbIndex].getNewI(), barbT[barbIndex].getNewJ());
		// }catch(Exception e){}
		Thread t;

		while(true){
			// if(barbPlaced > currentBarb){
				System.out.println("CLASH RUN: globalI == "+globalI);
				Runnable r = new Troop(this, globalI, globalJ);
				t = new Thread(r);
				t.start();
				currentBarb++;
			// }
			
		}
	}

}
