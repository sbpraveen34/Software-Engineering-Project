
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import sun.awt.geom.AreaOp.AddOp;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ravi
 */
public class adminmap {
    JFrame f; JButton done; ArrayList<point> li ; int ma ;
  
    void go(ArrayList<point> list ,int maxx)
    {
        li = list;
        ma = maxx;
        System.out.println("inside admin map");
        System.out.println("max is "+maxx);
         f = new JFrame("map");
         f.setSize(600,600);
         done = new JButton("DONE");
         done.addActionListener(new lis());
         mypanel2 m = new mypanel2();m.addMouseListener(new mlis());
         f.setVisible(true);
         f.getContentPane().add(m , BorderLayout.CENTER);
         f.getContentPane().add(done , BorderLayout.SOUTH);
    }
    
    class lis implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int rem = ma - li.size();
            if(ma != 0)
            {
                optimum_cab c = new optimum_cab();
                String s = c.fo(400, 400);
                for(int i  = 0 ; i< rem ; i++)
                {
                    point p = new point();
                    p.x = 400; p.y = 400 ;p.name = s;
                    li.add(p);
                }
            }
            f.setVisible(false);
        }
    }
    class mlis implements MouseListener
    {
       int r = 0;
        @Override
        public void mouseClicked(MouseEvent e) {
            if(r <= ma)
            {
            int x = e.getX(); int y = e.getY();
           // System.out.println("clicked at "+x +"  "+y);
            point p = new point();
            p.x = x; p.y = y ;
            optimum_cab c = new optimum_cab();
            p.name = c.fo(x, y);
           // System.out.println("name si "+p.name);
            li.add(p);
           // System.out.println("point added");
            System.out.println("list size is "+li.size());
           f.repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
          
        }

        @Override
        public void mouseReleased(MouseEvent e) {
          
        }

        @Override
        public void mouseEntered(MouseEvent e) {
          
        }

        @Override
        public void mouseExited(MouseEvent e) {
          
        }
        
    }
   class mypanel2 extends JPanel
   {
       public void paintComponent(Graphics g)
       {
           Image i = new ImageIcon("jmap.jpg").getImage();
           g.drawImage(i, 0, 0, this);
           for(int ii = 0 ; ii< li.size() ; ii++)
           {
              // System.out.println("painting");
               Image gg = new ImageIcon("icon.jpg").getImage();
               g.drawImage(gg ,li.get(ii).x , li.get(ii).y , this);
           }
       }
   }
   
}


class draw
{
    JFrame f; int x1,y1,x2,y2; route r;
    void go(route rr)
    {
        r = rr;
        f = new JFrame("shortest path");
        f.setVisible(true);
        f.setSize(700,700);
        mypanel m = new mypanel();
                f.getContentPane().add(m);
                
    }
    class mypanel extends JPanel
    {
        public void paintComponent(Graphics g)
        {
            Image i = new ImageIcon("jmap.jpg").getImage();
            g.drawImage(i,0,0,this);
            for(int j = 0 ; j<r.pointlist.size()-1 ; j++)
                {
                    x1 = r.pointlist.get(j).x; y1 = r.pointlist.get(j).y;
                    x2 = r.pointlist.get(j+1).x; y2 = r.pointlist.get(j+1).y;
                    System.out.println("x1 is " +x1);
                    System.out.println("x2 is " +x2);
                    System.out.println("y1 is " +y1);
                    System.out.println("y2 is " +y2);
                    g.setColor(Color.red);
            g.drawLine(x1,y1,x2,y2);        
                }
            
                  }
    }
}