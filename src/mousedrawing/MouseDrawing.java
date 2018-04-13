
package mousedrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class MouseDrawing extends JFrame 
{
    
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JPanel drawPanel = new JPanel();
    JLabel leftColorLabel = new JLabel();
    JLabel rightColorLabel = new JLabel();
    JPanel colorPanel = new JPanel();
    JLabel[] colorLabel = new JLabel[8];
    Graphics2D g2D;
    double xPrevious, yPrevious;
    Color drawColor, leftColor, rightColor;

    public static void main(String args[]) 
    {
        // construct frame
        new MouseDrawing().setVisible(true);
    }
    
    public MouseDrawing()
    {
        // frame constructor
        setTitle("Artistic Creations by Tanner Helms! March 2018");
        setResizable(false);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        Color backgroundColor = new Color(122,220,153);
        getContentPane().setBackground(backgroundColor);
        
        // build menu
        setJMenuBar(mainMenuBar);
        fileMenu.setMnemonic('F');
        Color menubarColor = new Color(102,200,233);
        mainMenuBar.setBackground(menubarColor);
        colorPanel.setBackground(menubarColor);
        mainMenuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        //fileMenu.addSeparater();
        fileMenu.add(exitMenuItem);
       // newMenuItem.addActionListener(new ActionListener()
       // {
        //placeholder
        //});
        drawPanel.setPreferredSize(new Dimension(800, 600));
        drawPanel.setBackground(Color.BLACK);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 2;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(drawPanel, gridConstraints);
        //drawPanel.addMouseListener(new MouseAdapter());
        
        leftColorLabel.setPreferredSize(new Dimension(40,40));
        leftColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(leftColorLabel, gridConstraints);
        rightColorLabel.setPreferredSize(new Dimension(40,40));
        rightColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(rightColorLabel, gridConstraints);
        
        colorPanel.setPreferredSize(new Dimension(80,160));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(colorPanel, gridConstraints);
        
        colorPanel.setLayout(new GridBagLayout());
        int j = 0;
        for (int i = 0; i < 8; i++)
        {
            colorLabel[i] = new JLabel();
            colorLabel[i].setPreferredSize(new Dimension(30,30));
            colorLabel[i].setOpaque(true);
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = j;
            gridConstraints.gridy = i - j * 4;
            colorPanel.add(colorLabel[i], gridConstraints);
            if(i==3)
            {
                j++;
            }
            colorLabel[i].addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    colorMousePressed(e);
                }
            });
        }
        colorLabel[0].setBackground(Color.GRAY);
        colorLabel[1].setBackground(Color.BLUE);
        colorLabel[2].setBackground(Color.GREEN);
        colorLabel[3].setBackground(Color.CYAN);
        colorLabel[4].setBackground(Color.RED);
        colorLabel[5].setBackground(Color.MAGENTA);
        colorLabel[6].setBackground(Color.YELLOW);
        colorLabel[7].setBackground(Color.WHITE);
        leftColor = colorLabel[0].getBackground();
        leftColorLabel.setBackground(leftColor);
        rightColor = colorLabel[7].getBackground();
        rightColorLabel.setBackground(rightColor);
        
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())),getWidth(),getHeight());
        g2D = (Graphics2D) drawPanel.getGraphics();
    }
    
    private void colorMousePressed(MouseEvent e)
    {
        Component clickedColor = e.getComponent();
        Toolkit.getDefaultToolkit().beep();
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            leftColor = clickedColor.getBackground();
            leftColorLabel.setBackground(leftColor);
        }else if (e.getButton() == MouseEvent.BUTTON3)
        {
            rightColor = clickedColor.getBackground();
            rightColorLabel.setBackground(rightColor);
        }
    }
    
    private void exitForm(WindowEvent e)
    {
        g2D.dispose();
        System.exit(0);
    }
}
