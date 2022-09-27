package bubbleeffect;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;           // For Graphics, etc.
import java.awt.geom.*; 	 // For Ellipse2D, etc.
import static java.awt.Color.*;

public class Bubble extends Ellipse2D.Float{
	private int radiusSize = 5 ;
	private int mouseX = 0;
	private int mouseY = 0;
	private static Color colors[] = {BLUE, CYAN, GREEN, MAGENTA, ORANGE, PINK, RED,YELLOW, LIGHT_GRAY, WHITE };
	private int colorIndex = 0;
	private static int nextIndex=0;
	public Bubble(){
		if(colorIndex==9){
			colorIndex=0;
		}else{
			colorIndex=colorIndex+1;
		}

	}
	public Bubble(int mouseX,int mouseY){
		this.mouseX = mouseX-radiusSize;
		this.mouseY = mouseY-radiusSize;
		super.setFrame(mouseX-radiusSize,mouseY-radiusSize,radiusSize*2,radiusSize*2); 
		
		if(nextIndex==9){
			nextIndex=0;
		}else{
			nextIndex=nextIndex+1;
		}
		colorIndex=nextIndex;
	}
	
	public Color getColor(){
		return colors[colorIndex];
	}

	public int getSize(){
		return radiusSize;
	}
	
	public void setSize(int size){
		this.radiusSize = size;
	}
	public int incrementSize(){
		radiusSize = radiusSize+1;
		setFrame(mouseX-radiusSize,mouseY-radiusSize,radiusSize*2);
		return radiusSize;
	}
	public void setFrame(int mouseX,int mouseY,int size){
		super.setFrame(mouseX,mouseY,size,size);
	}

	public static void main(String[] arg){
		
		Bubble b = new Bubble();
		Bubble b1 = new Bubble(200,200);		
	}

}

