/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ravi
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.lang.String;
import java.util.ArrayList;
public class mapper {
   static ArrayList<line> list ;
    int x, y;
    BufferedWriter br ;
    JFrame f;
    JButton b;
    JTextField field , fx , fy;
    mypanel m;
    public static void main(String args[]) throws Exception
    {
        
        mapper m = new mapper();list = new ArrayList<line>();
        m.go();
    }
    void go()throws Exception
    {
        br = new BufferedWriter(new FileWriter("myoop.txt"));
        b = new JButton("do");
        b.addActionListener(new blis());
        f = new JFrame();
        f.setSize(600,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        field = new JTextField(10);
        fx = new JTextField(10);
        fy = new JTextField(10);
        JPanel p = new JPanel();
        p.add(fx);
        p.add(fy);p.add(field);p.add(b);
        f.getContentPane().add(p , BorderLayout.NORTH);
         m = new mypanel();
        m.addMouseListener(new mlis());
        f.getContentPane().add(m , BorderLayout.CENTER);
    }
    
class blis implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
         String s = field.getText();  
        String d = fx.getText()+","+fy.getText()+"/"+s ;f.repaint();
        System.out.println(d);
        
        try
        {
            br.write(d);
            br.newLine();
        }catch(Exception f){}
        field.setText("");fx.setText("");fy.setText("");
    }
}
String from,to;
class mlis implements MouseListener
{
    public void mouseEntered(MouseEvent e)
    {
        
    }
    public void mouseExited(MouseEvent e)
    {
        
    }
    int x1,y1,x2,y2;int yes = 0;
    public void mouseClicked(MouseEvent e)
    {
        line l = new line();
        if( yes == 0)
        {
         x = e.getX(); y = e.getY(); x1 = x;y1 = y;  l.x1 = x; l.y1 = y;   System.out.println("l.x1 and l.y1 is"+l.x1 +" " +l.y1) ;
       // System.out.println("mouse Clicked");       
         from = fo(x1,y1);
        fx.setText(x+"-"+y); m.repaint();  yes = 1;} 
        else
        {
            yes = 0;
         x = e.getX(); y = e.getY();x2 = x; y2 = y;   l.x2 = x; l.y2 = y;   System.out.println("l.x2 and l.y2 is"+l.x2 +" " +l.y2) ;
        //System.out.println("mouse Clicked");         
         to = fo(x2,y2);   
        fy.setText(x+"-"+y); m.repaint();        double dis= (x1-x2)*(x1-x2) + (y1-y1)*(y1-y2); dis = Math.sqrt(dis);field.setText(dis+"");
        }
        f.repaint();
    }
    int toint(String s)
       {
        int sum = 0;
        for(int i = 0 ; i < s.length() ; i++)
        {
            int e = s.charAt(i) - '0';
            sum = sum*10 + e ;
        }
        return sum ;
       }
    String fo(int x , int y)
        {
        	//System.out.println("inside getname");
            String name = null;
            try
            {
            	//System.out.println("opening jmap.txt");
                BufferedReader br = new BufferedReader(new FileReader("jmap.txt"));
                        String line = "" ;  double min = 999999999;
                        while((line = br.readLine())!= null)
                        {
                        	//System.out.println("line is now "+line);
                            String[] s = new String[3];s = line.split("/");
                            int xx = toint(s[1]);int yy = toint(s[2]);double dis = (xx-x)*(xx-x) + (yy-y)*(yy-y) ; dis = Math.sqrt(dis);
                            //System.out.println("xx is "+xx);System.out.println("yy is "+yy);System.out.println("dis is "+dis);
                           // if(dis <= 7)
                            //{
                            	//System.out.println("yo we got a hit ");
                                if(min > dis){  min = dis ; name = s[0];}
                            //}
                        }
                        if(name == null)
                            name = "------";
                     //   System.out.println("i got name as "+name);
            }catch(Exception ee){}
           // System.out.println(" outside catch and i got name as "+name);
            return name;
        }
    public void mousePressed(MouseEvent e)
    {
        
    }
    public void mouseReleased(MouseEvent e)
    {
        
    }
}
int s = 1;
class mypanel extends JPanel 
{
    
    public void paintComponent(Graphics g)
    {
       // System.out.println("inside mypanel"); 
        g.setColor(Color.white); g.drawRect(0, 0, this.getHeight(), this.getWidth());
        
        
            Image i = new ImageIcon("jmap.jpg").getImage();
        g.drawImage(i,9 , 9, this);
        g.setColor(Color.red);
        g.drawOval(x, y, 2, 2); 
        
        
       // System.out.println("size is "+list.size());
        for(int ii = 0 ; ii< list.size(); ii++)
        {
           // System.out.println("inside for loop");
            g.setColor(Color.RED);
            g.drawLine(list.get(ii).x1, list.get(ii).y1 , list.get(ii).x2 , list.get(ii).y2);
        }
    }
}

}

class line
{
    int x1, y1, x2 , y2;
}