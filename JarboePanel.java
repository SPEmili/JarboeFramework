package jarboe;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class JarboePanel extends JPanel {
	private static final long serialVersionUID = 1L; //default serial ID, added due to some compilers having trouble without it
	int X1, X2, Y1, Y2;
	JarboeLine J=null;
	JarboeLine[] JJ=null;
	static Timer timer;
	public JarboePanel()
	{
		super();
	}
	
	public JarboePanel(int x1, int y1, int x2, int y2) {
		super();
		X1=x1;
		Y1=y1;
		X2=x2;
		Y2=y2;
	}
	    
	public JarboePanel(JarboeLine j) {
		super();
		J = j;
	}
	
	public JarboePanel(JarboeLine[] jarboeLines) {
		super();
		JJ = jarboeLines;
	}

	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    if (J != null)
	    {
	    	g.drawLine(J.getX1(), J.getY1(), J.getX2(), J.getY2());
	    } else if (JJ != null) {
	    	for (int i = 0; i < JJ.length; i++)
	    	{
	    		g.drawLine(JJ[i].getX1(), JJ[i].getY1(), JJ[i].getX2(), JJ[i].getY2());
	    	}
	    }
	    
	}
	
	private boolean within(int P1,int P2,int range) //check if points are within a range of eachother but only one dimension at a time
	{
		if(P1+range > P2 && P1-range < P2)
			return true;
		else
			return false;
	}
	
	private boolean within(int X1,int Y1,int X2,int Y2,int range) //checks if points are within a range of eachother, both dimensions at a time
	{
		if(within(X1,X2,range) && within(Y1,Y2,range))
			return true;
		else
			return false;
	}
	
	//animated stuff
	private int tempX=0, tempY=0; public boolean isAnimating=false; 
	private boolean backwardsX=false, backwardsY=false; //used to determine the direction of movement
	public void transitionJolt(final int newX, final int newY, int slowness, final int jumps) //slowness is for the timer, jumps are the steps in each move
	{
		isAnimating = true; tempX=getX(); tempY = getY(); if(slowness <= 0) {slowness = 100;}; 
		
		//determine direction
		if(newX < getX()) {backwardsX=true;}
		if(newY < getY()) {backwardsY=true;}
		
		ActionListener transition = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.out.println("clicJ");
                if(tempX != newX)
                {
                	if(backwardsX)
                		tempX-=jumps;
                	else
                		tempX+=jumps;
                	if(within(tempX,newX,jumps))
                		tempX=newX;
                }
                
                if(tempY != newY)
                {
                	if(backwardsY)
                		tempY-=jumps;
                	else
                		tempY+=jumps;
                	if(within(tempY,newY,jumps))
                		tempY=newY;
                }
                
                setLocation(tempX, tempY);
                System.out.println(getX()+","+getY());
                if(tempX==newX && tempY==newY) {isAnimating = false; timer.stop();}
            }
        };
		timer = new Timer(slowness, transition);
		
        timer.setRepeats(true);
        timer.start();
	}
	
}