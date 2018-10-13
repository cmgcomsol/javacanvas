/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author geo
 */
public class Coordinate extends Object{
    private Integer x;
    private Integer y;
    
    private Coordinate ne;
    private Coordinate nw;
    private Coordinate se;
    private Coordinate sw;

    public Coordinate getNe() {
        return ne;
    }

    public Coordinate getNw() {
        return nw;
    }

    public Coordinate getSe() {
        return se;
    }

    public Coordinate getSw() {
        return sw;
    }

    public void setNe(Coordinate ne) {
        this.ne = ne;
    }

    public void setNw(Coordinate nw) {
        this.nw = nw;
    }

    public void setSe(Coordinate se) {
        this.se = se;
    }

    public void setSw(Coordinate sw) {
        this.sw = sw;
    }
    
    private String name;
    
    public Coordinate(Integer x,Integer y){
        this.x=x;
        this.y=y;
        
        this.sw=this.se=this.nw=this.ne=null;
        
    }

    public String getName() {
        return name;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setName(String name) {
        this.name = name;
    }
}
