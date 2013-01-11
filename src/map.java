
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ravi
 */
public class map 
{
    String map_name ;
    static ArrayList<node> map_nodes ;
    public map()
    {
        
    }
    void makemap(String text) throws Exception
    {
        try {
            map_nodes = new ArrayList<node>();
            System.out.println("map is making nodes");
            BufferedReader br = new BufferedReader(new FileReader("jmap.txt"));
            String line = "";
            while((line = br.readLine()) != null)
            {   //System.out.println("line is "+line);
                String[] str = new String[3];
                str = line.split("/");
                addnode(str[0] , toint(str[1]) , toint(str[2]));
            }
        } catch (FileNotFoundException ex) {}
            
        System.out.println("nodes are made "+map_nodes.size());
        
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
    void addnode(String name , int x , int y)
    {
      //  System.out.println("inside add node");
        node newnode = new node();
        newnode.name = name;
        newnode.location_x = x;
        newnode.location_y = y;
      //  System.out.println("helloagain");
        map_nodes.add(newnode);
      //  System.out.println("added node");
    }
    
    
    
    node removesmall(ArrayList<node> dupe)
    {
        double min = 999999999;node min_node  = new node() ;
        for(int i = 0 ; i< dupe.size() ; i++)
        {
            if(min < dupe.get(i).distance )
            {
                min = dupe.get(i).distance;
                min_node = dupe.get(i) ;
            }
        }
        
        dupe.remove(min_node);
        return min_node;
    }
    node findroot(int x , int y)
    {
        double mindis = 999999999;
        node minnode = new node();
        for(int i = 0 ; i< map_nodes.size() ; i++)
        {
            if(mindis > dist(map_nodes.get(i) , x , y))
            {
              //  System.out.println("i got a hit for min node");
                mindis = dist(map_nodes.get(i) , x , y) ;
                minnode = map_nodes.get(i);
            }
        }
       // System.out.println("iam in find root i got node as "+ minnode.name);
        return minnode ;
    }
    double dis(node n1 , node n2)
    {
        double i = (n1.location_x - n2.location_x)*(n1.location_x - n2.location_x) + (n1.location_y - n2.location_y)*(n1.location_y - n2.location_y)   ;
        return Math.sqrt(i);
    }
    double dist(node n1 , int x , int y)
    {
        double i = (n1.location_x - x)*(n1.location_x - x) + (n1.location_y - y)*(n1.location_y - y)   ;
       // System.out.println("dist is now "+i);
        return Math.sqrt(i);
    }
}

class node
{
    double distance;
    node parent;
    String name;
    int location_x;
    int location_y;
   
    
}