/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.swing.JPanel;
import java.util.*;
import classes.Coordinate;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 *
 * @author geo
 */
public class MyCanvas extends JPanel {

    ArrayList<Coordinate> locations = new ArrayList<Coordinate>();
    
    int displayEntity = 0;//1 for neighbours 2 for routes from s to d
    Coordinate neighbour = null;
    ArrayList<Coordinate> shortestroute=null;
    
    
    public MyCanvas() {

    }

    public void GenerateCoordinates(int count) {
        int max = 1300;
        int min = 100;

        for (Integer i = 1; i <= count; i++) {
            //rand.setSeed(5);
            max = 1300;
            int x = (int) (Math.random() * ((max - min) + 1)) + min;
            max = 600;
            int y = (int) (Math.random() * ((600 - min) + 1)) + min;
            locations.add(new Coordinate(x, y));
            locations.get(i - 1).setName(i.toString());
        }
        CalculateNearestCoordinates();

        repaint();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        g.drawRect(100, 100, 1300, 600);
        // Draw Text
        for (int i = 0; i < this.locations.size(); i++) {

            g.setColor(Color.GRAY);
            g.drawOval(locations.get(i).getX() - 15, locations.get(i).getY() - 25, 50, 50);
            g.setColor(Color.RED);
            g.drawString(locations.get(i).getName(), locations.get(i).getX(), locations.get(i).getY());
            g.setColor(Color.BLACK);
            g.fillOval(locations.get(i).getX(), locations.get(i).getY(), 5, 5);
        }

        if (displayEntity == 1) {//display neighbours
            g.setColor(Color.RED);
            g.fillOval(neighbour.getX() - 15, neighbour.getY() - 25, 50, 50);
            g.setColor(Color.yellow);
            g.drawString(neighbour.getName(), neighbour.getX(), neighbour.getY());
            g.setColor(Color.ORANGE);
            g.fillOval(neighbour.getX(), neighbour.getY(), 5, 5);

            //now showing neighbours
            if (neighbour.getNe() != null) {
                g.setColor(Color.orange);
                g.fillOval(neighbour.getNe().getX() - 15, neighbour.getNe().getY() - 25, 50, 50);
                g.setColor(Color.black);
                g.drawString(neighbour.getNe().getName(), neighbour.getNe().getX(), neighbour.getNe().getY());
                g.setColor(Color.cyan);
                g.fillOval(neighbour.getNe().getX(), neighbour.getNe().getY(), 5, 5);
            }
            if (neighbour.getNw() != null) {
                g.setColor(Color.orange);
                g.fillOval(neighbour.getNw().getX() - 15, neighbour.getNw().getY() - 25, 50, 50);
                g.setColor(Color.black);
                g.drawString(neighbour.getNw().getName(), neighbour.getNw().getX(), neighbour.getNw().getY());
                g.setColor(Color.cyan);
                g.fillOval(neighbour.getNw().getX(), neighbour.getNw().getY(), 5, 5);
            }
            if (neighbour.getSe() != null) {
                g.setColor(Color.orange);
                g.fillOval(neighbour.getSe().getX() - 15, neighbour.getSe().getY() - 25, 50, 50);
                g.setColor(Color.black);
                g.drawString(neighbour.getSe().getName(), neighbour.getSe().getX(), neighbour.getSe().getY());
                g.setColor(Color.cyan);
                g.fillOval(neighbour.getSe().getX(), neighbour.getSe().getY(), 5, 5);
            }
            if (neighbour.getSw() != null) {
                g.setColor(Color.orange);
                g.fillOval(neighbour.getSw().getX() - 15, neighbour.getSw().getY() - 25, 50, 50);
                g.setColor(Color.black);
                g.drawString(neighbour.getSw().getName(), neighbour.getSw().getX(), neighbour.getSw().getY());
                g.setColor(Color.cyan);
                g.fillOval(neighbour.getSw().getX(), neighbour.getSw().getY(), 5, 5);
            }

        }else if(displayEntity==2){
            for(int i=1;i!=shortestroute.size();i++){
                g.setColor(Color.GREEN);
                Coordinate a=shortestroute.get(i-1);
                Coordinate b=shortestroute.get(i);
                g.drawLine(a.getX(),a.getY(),b.getX(),b.getY());
            }
        }
    }

    private double DistanceBetween(Coordinate a, Coordinate b) {

        double length = (double) (a.getX() - b.getX());
        length = Math.abs(length);
        double height = (double) (a.getY() - b.getY());
        height = Math.abs(height);
        return Math.sqrt(length * length + height * height);
    }

    private void CalculateNearestCoordinates() {
        for (int i = 0; i < locations.size(); i++) {
            Coordinate l = locations.get(i);

            Coordinate ne = null;
            double dne = 9999.00;

            Coordinate nw = null;
            double dnw = 9999.00;

            Coordinate se = null;
            double dse = 9999.00;

            Coordinate sw = null;
            double dsw = 9999.00;

            for (int j = 0; j < locations.size(); j++) {
                Coordinate tl = locations.get(j);

                if (l == tl) {  //same location
                    continue;
                }

                //if ne y is n/s x is e/w
                if (l.getY() >= tl.getY() && l.getX() <= tl.getX()) {
                    //System.out.println(tl.getName()+" is north east of "+l.getName());
                    //System.out.println("Distance :"+DistanceBetween(l, tl));
                    if (dne > DistanceBetween(l, tl)) {
                        dne = DistanceBetween(l, tl);
                        ne = tl;
                    }
                }

                //if nw
                if (l.getY() >= tl.getY() && l.getX() >= tl.getX()) {
                    //System.out.println(tl.getName()+" is north west of "+l.getName());
                    //System.out.println("Distance :"+DistanceBetween(l, tl));
                    if (dnw > DistanceBetween(l, tl)) {
                        dnw = DistanceBetween(l, tl);
                        nw = tl;
                    }
                }

                //if se
                if (l.getY() <= tl.getY() && l.getX() <= tl.getX()) {
                    //System.out.println(tl.getName()+" is south east of "+l.getName());
                    //System.out.println("Distance :"+DistanceBetween(l, tl));
                    if (dse > DistanceBetween(l, tl)) {
                        dse = DistanceBetween(l, tl);
                        se = tl;
                    }
                }

                //if sw
                if (l.getY() <= tl.getY() && l.getX() >= tl.getX()) {
                    //System.out.println(tl.getName()+" is south west of "+l.getName());
                    //System.out.println("Distance :"+DistanceBetween(l, tl));
                    if (dsw > DistanceBetween(l, tl)) {
                        dsw = DistanceBetween(l, tl);
                        sw = tl;
                    }
                }
            }
            l.setNe(ne);
            l.setNw(nw);
            l.setSe(se);
            l.setSw(sw);

        }
    }

    public ArrayList<Coordinate> getLocations() {
        return locations;
    }

    public Coordinate getLocationFromName(String name) {
        for (Coordinate x : this.locations) {
            if (x.getName() == name) {
                return x;
            }
        }
        return null;
    }

    public void SetNeighbourFromName(String Name) {
        this.neighbour = getLocationFromName(Name);
        this.displayEntity = 1;
        repaint();
    }
    
    public void SetShortestDistanceBetween(String a, String b){
        ArrayList<Coordinate> shortestrouteA=GetShortestDistanceBetween(a,b);
        double adistance=this.GetRouteDistanceBetween(shortestrouteA);
        
        ArrayList<Coordinate> shortestrouteB=GetShortestDistanceBetween(b,a);
        double bdistance=this.GetRouteDistanceBetween(shortestrouteB);
        
        if(adistance>bdistance){
            this.shortestroute=shortestrouteB;
        }else{
            this.shortestroute=shortestrouteA;
        }
        
        this.displayEntity=2;
        repaint();
    }
    
    /**
     * Calculates double route distance between a & b
     * @param a
     * @param b
     * @return double value of distance between a & b
     */
    public double GetRouteDistanceBetween(ArrayList<Coordinate> routelist){
        double distance=0.0;
        for(int i=0;i<=routelist.size()-2;i++){
            distance+=this.DistanceBetween(routelist.get(i),routelist.get(i+1));
        }
        return distance;
    }
    
    public  ArrayList<Coordinate> GetShortestDistanceBetween(String a,String b){
        ArrayList<ArrayList<Coordinate>> mainpool=new ArrayList<>();
        
        Coordinate Source=getLocationFromName(a);
        Coordinate Destination=getLocationFromName(b);
        
        ArrayList<Coordinate> tmplist=new ArrayList<>();
        tmplist.add(Source);
        
        mainpool.add(tmplist);
        
        while(true){//super loop
            //check each tmplist if having dest as last element and if true exit
            for(ArrayList<Coordinate> tl:mainpool){
                if(tl.get(tl.size()-1)==Destination){
                    return tl;
                }
            }
            
            //so not found then identify the nearest last item to destination
            ArrayList<Coordinate> identifiedNearestLocationSequence=null;
            double nearestDistance=10000000.00;
            
            for(ArrayList<Coordinate> tl:mainpool){
                double currentDistance=DistanceBetween(tl.get(tl.size()-1),Destination);
                
                System.out.println("Checking distance between"+tl.get(tl.size()-1).getName()+" and "+Destination.getName());
                System.out.println("Distance found="+currentDistance);
                if(currentDistance<nearestDistance){
                    identifiedNearestLocationSequence=tl;
                    nearestDistance=currentDistance;
                }
            }
            
            //now add the 4 coordinates of the nearest node as new 4 way path to mainpool for the nearest Distance pool
            //first of all build list of last nodes
            ArrayList<Coordinate> lastNodes=new ArrayList<Coordinate>();
            
            for(ArrayList<Coordinate> tl:mainpool){
                lastNodes.add(tl.get(tl.size()-1));                
            }
            
            
            //testprint of identifiedNearestLocationSequence
            System.out.println("\n\n\n\nCurrent list");
            if(identifiedNearestLocationSequence==null){
                System.out.println("identifiedNearestLocationSequence is null");
            }
            
            
            Coordinate lastNearestNodeYet=identifiedNearestLocationSequence.get(identifiedNearestLocationSequence.size()-1);
            for(Coordinate tl:identifiedNearestLocationSequence){
                System.out.println(tl.getName());
            }

            //check and add the 4 corners of shortest route if not in last nodes      
            boolean anotherRoute=false;
            
            if(lastNearestNodeYet.getNe()!=null){
                Coordinate ne=lastNearestNodeYet.getNe();
                ArrayList<Coordinate> newlist= (ArrayList<Coordinate>)identifiedNearestLocationSequence.clone();
                newlist.add(ne);
                mainpool.add(newlist);
                anotherRoute=true;
            }
            
            if(lastNearestNodeYet.getNw()!=null){
                Coordinate nw=lastNearestNodeYet.getNw();
                ArrayList<Coordinate> newlist= (ArrayList<Coordinate>)identifiedNearestLocationSequence.clone();
                newlist.add(nw);
                mainpool.add(newlist);
                anotherRoute=true;
            }
            
            if(lastNearestNodeYet.getSe()!=null){
                Coordinate se=lastNearestNodeYet.getSe();
                ArrayList<Coordinate> newlist= (ArrayList<Coordinate>)identifiedNearestLocationSequence.clone();
                newlist.add(se);
                mainpool.add(newlist);
                anotherRoute=true;
            }
            
            if(lastNearestNodeYet.getSw()!=null){
                Coordinate sw=lastNearestNodeYet.getSw();
                ArrayList<Coordinate> newlist= (ArrayList<Coordinate>)identifiedNearestLocationSequence.clone();
                newlist.add(sw);
                mainpool.add(newlist);
                anotherRoute=true;
            }
            
            
            if(anotherRoute){
                //remove the fucking old route...
                
                ArrayList<Integer> itemnos=new ArrayList<>();
                for(int i=mainpool.size()-1;i>=0;i--){
                    if(mainpool.get(i).get(mainpool.get(i).size()-1)==lastNearestNodeYet){
                        itemnos.add(i);
                    }
                }
                for(Integer delindex:itemnos){
                    mainpool.remove(delindex);                    
                }
                
            }
        }
    }

}
