/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ravi
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
public class cab {

    int pmax , luggagemax;
    String loc;
    String type;
    ArrayList<info> cabinfolist ;
    ArrayList<route> routeinfolist ;
    
    cab(int m , int n)
    {
        pmax = m ;
        luggagemax = n ;
        cabinfolist = new ArrayList<info>();
        routeinfolist = new ArrayList<route>();
    }
    cab()
    {
        pmax = 5 ;
        luggagemax = 5 ;
        cabinfolist = new ArrayList<info>();
        routeinfolist = new ArrayList<route>();
        
    }
    int canadd( int m , int n)
    {
        
        if(pmax >= m && luggagemax >= n)
        {
            return 1;
        }
        return 0;
    }
    
    
    
}

class optimum_cab
{
    static int rem_cabs = 20;
    void set_cabs(int m)
    {
        rem_cabs = m;
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
    
    String find(info f , ArrayList<point>list)
    {
        System.out.println("inside find my size is "+list.size());
        String ss = null;
        double min = 999999999; int index = -99;
        for(int i = 0 ; i< list.size() ; i++)
        {
            double dis = (f.fromx-list.get(i).x)*(f.fromx-list.get(i).x) + (f.fromy-list.get(i).y)*(f.fromy-list.get(i).y) ;
            dis = Math.sqrt(dis);
            if(min > dis)
            {
                min = dis; index = i;ss = list.get(index).name;
            }
        }
        list.remove(index);
        System.out.println("exiting find my size is now "+list.size());
        return ss;
    }
    cab getcab(ArrayList<point> startloc ,String s[] , info f , ArrayList<cab> cabs)
    {
        
        System.out.println("inside get cab");
        cab c = null;
        route r = new route();
        for(int i = 5 ; i < s.length ; )
        {
           // System.out.println("adding point");
            point np = new point();
            np.x = toint(s[i]); np.y = toint(s[i+1]); np.name = fo(np.x , np.y);
            r.pointlist.add(0, np); 
            i = i+2;
        }
        int done = 0;
       // System.out.println("done adding points");
        for(int i = 0 ; i< cabs.size() ; i++)
        {
            if(!cabs.get(i).type.equals(f.type))
            {
                System.out.println("type was different") ;
                continue; 
            }
            if(has_no_fare(cabs.get(i),f ))
            {
                System.out.println("has no fare on that day");
                 cabs.get(i).cabinfolist.add(f);cabs.get(i).routeinfolist.add(r);f.cab_no = i;done = 1;
                break;
            }
            else
            {
                if(has_no_time(cabs.get(i),f) == -99)
                {
                    System.out.println("has no time");
                    cabs.get(i).cabinfolist.add(f);cabs.get(i).routeinfolist.add(r);f.cab_no = i;done = 1;
                break; 
                }
                else
                {
                    if(has_route(cabs.get(hit) , f , r))
                    {
                        System.out.println("has same route");        
                    }
                }
            }
        }
        if(done ==0)
        {
            String ss = find(f , startloc);
            System.out.println("i have to add a new cab");
            if(rem_cabs-cabs.size() > 0)
            {
                    c = new cab();c.cabinfolist.add(f);c.routeinfolist.add(r); cabs.add(c);f.cab_no = cabs.indexOf(c);done = 1;
                    c.type = f.type; c.loc = ss;
            }
        }
        draw d = new draw(); d.go(r);
        System.out.println("start loc of cab is "+c.loc);
        
        return c;
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
    
    
    int hit = 0;
    boolean has_route(cab c , info f , route r)
    {
        
        boolean var = true;
        if(toint(c.cabinfolist.get(hit).min) > toint(f.min))
        {
            for(int i = 0 ; i< c.routeinfolist.get(hit).pointlist.size() ; i++)
            {    
                if(c.routeinfolist.get(hit).pointlist.get(i).x == f.fromx && c.routeinfolist.get(hit).pointlist.get(i).y == f.fromy)
                {
                    var = false ; return var;
                }
            }
            for(int i = 0 ; i< r.pointlist.size() ; i++)
            {   
                int y = 0;
                if(c.routeinfolist.get(hit).pointlist.get(i).x == r.pointlist.get(i).x && c.routeinfolist.get(hit).pointlist.get(i).y == r.pointlist.get(i).x)
                {
                     y = 1;
                }
                if(y== 0)
                {
                    var = false;return var;
                }
            }
        }
        else
        {
            for(int i = 0 ; i< r.pointlist.size() ; i++)
            {
                if(c.routeinfolist.get(hit).pointlist.get(i).x == f.fromx && c.routeinfolist.get(hit).pointlist.get(i).y == f.fromy)
                {
                    var = false; return var;
                }
            }
            for(int i = 0 ; i< c.routeinfolist.get(hit).pointlist.size() ; i++)
            {
                int y = 0;
                if(c.routeinfolist.get(hit).pointlist.get(i).x == r.pointlist.get(i).x && c.routeinfolist.get(hit).pointlist.get(i).y == r.pointlist.get(i).x)
                {
                     y = 1;
                }
                if(y== 0)
                {
                    var = false;return var;
                }
            }
        }
        return var;
    }
    int has_no_time(cab c , info f)
    {
       int diff = 0;
        for(int i = 0 ; i< c.cabinfolist.size() ; i++)
        {
            int hr = (c.cabinfolist.get(i).date.charAt(0) - '0' ) *10 + (c.cabinfolist.get(i).date.charAt(1) - '0' );
            hr = hr - (f.hour.charAt(0) - '0' )*10 - (f.hour.charAt(1) - '0')  ;
            if( hr >= 1  || hr <= -1 )
            {
                diff = -99;
            }
            if(hr == 0)
            {
                diff = 0; hit = i;
                return diff;
            }
            
        }
        return diff;
       
    }
    boolean has_no_fare(cab c , info f)
    {
        boolean var = true;
        for(int i = 0 ; i< c.cabinfolist.size() ; i++)
        {
            if(c.cabinfolist.get(i).date.equals(f.date))
            {
                var = false;
            }
        }
        return var;
    }
}

class route
{
    ArrayList<point> pointlist ;
    public route()
    {
        pointlist = new ArrayList<point>();
    }
}

class point
{
    int x;
    int y;
    String name;
}

