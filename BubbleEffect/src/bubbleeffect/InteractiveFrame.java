package bubbleeffect;

/*******************************************************

 ********************************************************/
//	PACKAGES NEEDED
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import interactivex.*;

//INTERACTIVE FRAME CLASS
public class InteractiveFrame extends EffectManager implements CameraListener {

    private int nextBubble = 4;
    RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private ArrayList<Bubble> allBubbles = new ArrayList<>();
    //private CameraShapesEffect cse;

    public InteractiveFrame() {
        this.setSize(700, 600);
        this.setVisible(true);
        setConfigurePanel(new JPanelInteractiveConfigure());
        
        this.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseMoved(MouseEvent me) {
                if (nextBubble >= 4) {
                    allBubbles.add(new Bubble(me.getX(), me.getY() - 20));
                    nextBubble = 0;
                } else {
                    nextBubble++;
                }
            }
        });

        animate();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.black);
        for (int i = 0; i < allBubbles.size(); i++) {
            g2d.setPaint(allBubbles.get(i).getColor());
            g2d.setStroke(new BasicStroke(3));
            hints.add(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.setRenderingHints(hints);
            g2d.draw(allBubbles.get(i));
        }

    }

    public void animate() {

        class RunBack extends Thread {

            RunBack() {
                setDaemon(true);
                start();
            }

            public void run() {
                while (true) {


                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e) {
                    }
                    for (int j = 0; j < allBubbles.size(); j++) {
                        allBubbles.get(j).incrementSize();
                        if (allBubbles.get(j).getSize() * 2 > 150) {
                            allBubbles.remove(j);
                        }
                    }
                    repaint();
                }
            }
        }

        new RunBack();

    }

    /****************************************************************************************/
    @Override
    public void shapeOnCameraDetected(CameraEvent ce) {
        int x = ce.getX();
        int y = ce.getY();
        //System.out.println(x+" "+y);
        double width = this.getBounds().getWidth();
        double height = this.getBounds().getHeight();
        double ratioWidth = width / 320;
        double ratioHeight = height / 240;

        allBubbles.add(new Bubble((int) (x * ratioWidth), (int) (y * ratioHeight)));
    }

    /****************************************************************************************/
    public static void main(String[] arg) {

        InteractiveFrame iFrame = new InteractiveFrame();
        JFrame jf = new JFrame("Interactive Frame");
        jf.setContentPane(iFrame);
        jf.setSize(700, 600);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
