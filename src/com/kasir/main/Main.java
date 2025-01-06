package com.kasir.main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

public class Main extends JLayeredPane {
    public Main() {
        init();
    }

    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
    }
    
    private class MainFormLayout implements LayoutManager{

        @Override
        public void addLayoutComponent(String name, Component comp) {
            
        }

        @Override
        public void removeLayoutComponent(Component comp) {
            
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized(parent.getTreeLock()){
                return new Dimension(5,5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized(parent.getTreeLock()){
                return new Dimension(0,0);
           }
        }

        @Override
        public void layoutContainer(Container parent) {
            
        }
    }
}
        


