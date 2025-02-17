/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package motorph.payroll_system;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


public class menu extends JComponent{

    /**
     * @return the event
     */
    public MenuEvent getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(MenuEvent event) {
        this.event = event;
    }
    
    private MenuEvent event;
    private MigLayout layout;
    private String[][] menuItems = new String[][]{
        {"Dashboard"},
        {"Menu","Profile", "Net Details" },
        {"Attendance","Attendance sheet", "Calculation of working hours" },
        {"Employee Management","Add New Hiring", "Edit Employee profile", "Delete Existing Employee profile" },
        {"Logout" },
        //ADD MENU HERE{"menu name","submenu name"},
    };
    
    public menu(){
        init();
    }
    
    
    private void init() {
        layout=new MigLayout("wrap 1, fillx, gapy 0, inset 2", "fill");
        setLayout(layout);
        setOpaque(true);
        //init MenuItem
        for(int i = 0; i < menuItems.length; i++){
            addMenu(menuItems[i][0], i);
         }
    }
    
   private Icon getIcon(int index){
    URL url = getClass().getResource("/icon/" + index  + ".png");
    //System.out.println("Icon URL: " + url);
    if (url != null){
        return new ImageIcon(url);
    } else {
        System.out.println("Icon resource not found for index: " + index);
        return null;
    }
}
    
    private void addMenu(String menuName, int index){
        int length=menuItems[index].length;
        MenuItem item=new MenuItem(menuName, index, length>1);
        //icon
        Icon icon = getIcon(index);
        if (icon != null){
            item.setIcon(icon);
        }
            item.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
             // throw new UnsupportedOperationException("Not supported yet.");
            if (length>1){
           //add submenu here
             if (!item.isSelected()){
            item.setSelected(true);
             addSubMenu(item, index, length, getComponentZOrder(item));
            }else {
             //hide menu
            hideMenu(item, index);
            item.setSelected(false);
                }     
            }else{
            if (event != null){
            event.selected(index, 0);
            }
            }
        }
    });
    
    add(item);
    revalidate();
    repaint();
    
    }
    private void addSubMenu(MenuItem item, int index, int length, int indexZorder){
        JPanel panel = new JPanel(new MigLayout("wrap 1, fillx, inset 0, gapy 0", "fill"));
        panel.setName(index + "");
        panel.setOpaque(false);
        for(int i = 1; i < length; i++) {
        MenuItem subItem = new MenuItem(menuItems[index] [i], i, false);
        subItem.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae){
            if(event != null){
            event.selected(index, subItem.getIndex());
            }
        }
        });
        subItem.initSubMenu(i, length);
        panel.add(subItem);
        }
        add(panel,"h 0", indexZorder+1);
        revalidate();
        repaint();
        MenuAnimation.showMenu(panel, item, layout, true);
    
    }
    private void hideMenu(MenuItem item, int index){
        for(Component com:getComponents()){
        if(com instanceof JPanel&&com.getName()!=null&&com.getName().equals(index+"")){
        com.setName(null);
        MenuAnimation.showMenu(com, item, layout, false);
        break;
             }
        }
               
    }
    
   @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
    
        Graphics2D g2 = (Graphics2D) grphcs.create();
        Color startColor = Color.decode("#ED213A"); // Dark red
        Color endColor = Color.decode("#93291E");   // Darker red
        GradientPaint gradient = new GradientPaint(0, 0, startColor, getWidth(), getHeight(), endColor);
        g2.setPaint(gradient);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
}
    
    
    
}