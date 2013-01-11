/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ravi
 */
import java.net.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

public class adminserver 
{
    static Vector ClientSockets;
    static Vector Clientinfo ;
    
    guihandler gh;
    adminserver( ) throws Exception
    {
       gh = new guihandler(); 
       System.out.println("admin server started");
        ServerSocket soc = new ServerSocket(6000);
        ClientSockets = new Vector();
        Clientinfo = new Vector();
        while(true)
        {
            Socket CSoc = soc.accept();
            AcceptClient obClient = new AcceptClient(CSoc);
            System.out.println("connection accepted");
        }
    }
    
   /* public static void main(String args[]) throws Exception
    {
        adminserver as = new adminserver();
    }*/
    
    void addClientPanel(info f)
    {
        
    }
    
    
    class AcceptClient extends Thread
    {
        Socket ClientSocket ;
        DataInputStream din ;
        DataOutputStream dout;
        info s;
        AcceptClient(Socket CSoc) throws Exception
        {
            ClientSocket = CSoc ;
            din=new DataInputStream(ClientSocket.getInputStream());
            dout=new DataOutputStream(ClientSocket.getOutputStream()); 
             s = new info(); //BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             s.name = din.readUTF();
             System.out.println(" my name is "+s.name);
            s.d = din.read();
            
            s.m = din.read();
            
            s.y = din.read();
            System.out.println(" m d y  is "+s.d +" " + s.m + " " + s.y);
            s.hour = din.readUTF();
            s.min = din.readUTF();
            s.mobile = din.readUTF();
            System.out.println("hour min  is "+s.hour+ " " +s.min );
            System.out.println(" my mobile is  is "+s.mobile);
            s.fname = din.readUTF();
            System.out.println(" my fname is "+s.fname);
            s.tname = din.readUTF();
            System.out.println(" my tname is "+s.tname);
            s.passen_no = din.readUTF();
            s.tox = toint(din.readUTF());
            System.out.println("tox is "+s.tox);
            s.toy = toint(din.readUTF());
            System.out.println("toy is "+s.toy);
            s.fromx = toint(din.readUTF());
            System.out.println("fromx is "+s.fromx);
            s.fromy = toint(din.readUTF());
            System.out.println("fromy is "+s.fromy);
            s.type = din.readUTF();
            System.out.println(" my type is "+s.type);
            s.date = din.readUTF( );
           
            
            gh.addClient(s);
            ClientSockets.add(ClientSocket);
            start();
        }
        int toint(String s)
        {
            int l = s.length();
            int sum = 0;
            for(int i = 0 ; i< l; i++)
            {
                sum = sum*10 + (s.charAt(i)-'0');
            }
            return sum;
        }
        public void run()
        {
            while(true)
            {
              try
              {
                stat q= new stat();
                if( !q.getstat().equals("-99"))
                {
                    dout.writeUTF(q.getstat());
                    remove(s);        
                break ;
                }
              }catch(Exception e){}
            }
        }
    }
    void remove(info p)
    {
        System.out.println("inside remove current capacity is "+Clientinfo.capacity());
        int i = Clientinfo.indexOf(p);
        Clientinfo.remove(i);
        ClientSockets.remove(i);
        System.out.println("after remove capacity is "+Clientinfo.capacity());
    }
}
class stat
{
    static String status = "-99";
    void putstat(String s)
    {
        status = s;
    }
    String getstat()
    {
        return status ;
    }
}