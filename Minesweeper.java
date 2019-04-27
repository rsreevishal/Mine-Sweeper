import java.util.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*<applet code="Minesweeper.class" width=600 height=600></applet>*/
public class Minesweeper extends JApplet implements ActionListener{
    class Node extends JButton implements ActionListener{
    int x,y,neigbours;
    boolean bomb,revealed;
    Node(int x,int y){
        this.x = x;
        this.y = y;
        this.neigbours =0;
        this.bomb = false;
        this.revealed = false;
        this.setForeground(Color.red);
    }
    public void neigbourCount(Node[][] arr){
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(i==0&&j==0||this.bomb){ continue;}
                else{
                    try{
                        if(arr[i+this.x][j+this.y].bomb){this.neigbours++;}
                        }
                    catch(Exception e){continue;}
                }
            }
        }
    }
    public void explore(Node obj){
        for(int i=-1;i<=1;i++){
                for(int j=-1;j<=1;j++){
                    if(i==0&&j==0){continue;}
                    else{
                        try{table[obj.x+i][obj.y+j].setText(String.valueOf(table[obj.x+i][obj.y+j].neigbours));
                            table[obj.x+i][obj.y+j].setBackground(Color.blue);
                            if(table[obj.x+i][obj.y+j].neigbours==0 &&table[obj.x+i][obj.y+j].revealed==false){
                               table[obj.x+i][obj.y+j].revealed = true;
                               table[obj.x+i][obj.y+j].setBackground(Color.blue);
                               explore(table[obj.x+i][obj.y+j]);
                            }
                            table[obj.x+i][obj.y+j].revealed=true;
                            table[obj.x+i][obj.y+j].setBackground(Color.blue);
                        }
                        catch(Exception e){continue;}
                    }
                }
            }
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(GV == 0){
        this.setBackground(Color.blue);
        this.revealed = true;
        if(this.bomb){this.setIcon(new ImageIcon("bomb.gif"));
            for(int i=0;i<SIZE;i++){
                for(int j=0;j<SIZE;j++){
                    if(table[i][j].bomb){table[i][j].setIcon(new ImageIcon("bomb.gif"));this.revealed=true;
                        table[i][j].setBackground(Color.blue);
                    }
                }
            }
            sta.setFont(new Font("Helevitica",Font.BOLD,15));
            sta.setText("---GAMEOVER---");
            GV = 1;
        }
        else{this.setText(String.valueOf(this.neigbours));}
        if(this.neigbours==0&&this.bomb==false){
            this.explore(this);
        }
        if(victory(table)){
            GV = 1;
            sta.setFont(new Font("Helevitica",Font.BOLD,15));
            sta.setText("---VICTORY---");
            for(int i=0;i<SIZE;i++){
                for(int j=0;j<SIZE;j++){
                    if(table[i][j].bomb){
                        table[i][j].setIcon(new ImageIcon("flag.gif"));
                        table[i][j].setBackground(Color.blue);
                    }
                }
            }
        }
    }
  }
}
    public boolean victory(Node[][] arr){
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                if(arr[i][j].revealed==false){
                    if(arr[i][j].bomb){
                    continue;}
                    else{
                        return false;
                    }
                }
                else{continue;}
            }
        }
        return true;
    }
    JFrame main;
    int GV = 0;
    CardLayout clay;
    Node[][] table;
    Random r ;
    Label sta,helplbl=new Label("Help"),title = new Label("MINE SWEEPER"),abtLabel = new Label("About") ;
    JButton rs,play=new JButton("PLAY"),helpbtn = new JButton("Help"),exit,hexit,about= new JButton("About"),aexit = new JButton("back") ;
    JPanel head,GameArea,status,emh,mainArea,mainJPanel,emhJPanel,PHA,helpJPanel,abtJPanel;
    CheckboxGroup cbg = new CheckboxGroup();
    Checkbox easy = new Checkbox("Easy",cbg,true),medium=new Checkbox("Medium",cbg,false),hard=new Checkbox("Hard",cbg,false);
    JTextArea helptext = new JTextArea( "Mine Sweeper is a puzzle based game and\n "
            + "it is quite tricky and also sometimes luck favours.\n"
            + "It is a good puzzle game and popular in early "
            + "\nMS Windows versions\n"
            + "-----RULES-----\n"
            + "1)Clear all the squares which has no bomb\n"
            + "2)The bombs are identified by the number in the box\n"
            + "3)Eg.if the the box has number 3 it means it is \n"
            + "surrounded by 3 bombs(i.e 3 bombs in 8 squares)\n"
            + "4)Note:not all squares are surrounded by 8 squares\n"
            + "(i.e corner square is surrounded only by 3 squares) \n"
            +"-----Learning the Game-----\n"
            +"1)While starting the game start with easy mode\n"
            +"2)Identify the patterns while playing for eg.\n"
            + "if the corner square shows number 3 it means\n"
            + "surely it surrounded fully by bombs because\n"
            + "corner square has only 3 squares surrounding it\n"
            + "3)NEVER GIVE UP...!.Keep going\n");
    JTextArea abtText = new JTextArea("            Created by R.SREE VISHAL             \n"
            +"Mine Sweeper v.1.0\n"
            +"This game is created using Java 7.0\n"
            +"Special Thanks to Nandini Madam\n"
            +"All rights received.Copy right 2018-19\n"
            +"Contact me on facebook and instagram\n"
            +"fb id : Sree Vishal\n"
            +"instagram id : sree.vishal\n"
            +"other social media:\n"
            +"Sololearn : sree vishal\n");
    int SIZE=7;
     void createGame(int size){
        GameArea.setLayout(new GridLayout(size,size));
        table = new Node[size][size];
        //Initializing the objects
        for(int i=0;i<size;i++){
            for(int j =0;j<size;j++){
                table[i][j] = new Node(i,j);
                switch(size){
                    case 7:table[i][j].setFont(new Font(Font.SANS_SERIF,Font.BOLD,25));break;
                    case 10:table[i][j].setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));break;
                    case 15:table[i][j].setFont(new Font(Font.SANS_SERIF,Font.BOLD,11));break;
                }
                table[i][j].addActionListener(table[i][j]);
                GameArea.add(table[i][j]);
            }
       }
       //Initializing the bombs
        for(int i=0;i<size+(size/2);i++){
            table[r.nextInt(size)][r.nextInt(size)].bomb = true;
        }
        //Counting the niegbours
        for(int i=0;i<size;i++){for(int j=0;j<size;j++){table[i][j].neigbourCount(table);
        }}
        status.add(new Label("SIZE="+String.valueOf(size)+"X"+String.valueOf(size)));
    }
    //@Override
    Minesweeper(){
        main = new JFrame("main");
        easy.setFont(new Font(Font.SERIF,Font.BOLD,40));
        medium.setFont(new Font(Font.SERIF,Font.BOLD,40));
        hard.setFont(new Font(Font.SERIF,Font.BOLD,40));
        abtJPanel = new JPanel();
        abtJPanel.setLayout(new BorderLayout());
        abtLabel.setAlignment(Label.CENTER);
        abtLabel.setFont(new Font(Font.SERIF,Font.BOLD,30));
        abtJPanel.add(abtLabel,BorderLayout.NORTH);
        abtText.setColumns(45);
        abtText.setRows(15);
        abtText.setEditable(false);
        abtText.setFont(new Font(Font.SERIF,Font.BOLD,20));
        abtJPanel.add(abtText,BorderLayout.CENTER);
        abtJPanel.add(aexit,BorderLayout.SOUTH);
        hexit = new JButton("Back");
        exit = new JButton("Exit");
        helpJPanel  = new JPanel();
        helpJPanel.setLayout(new BorderLayout());
        helptext.setEditable(false);
        helptext.setFont(new Font(Font.SERIF,Font.BOLD,20));
        helptext.setColumns(45);
        helptext.setRows(15);
        helplbl.setAlignment(Label.CENTER);
        helplbl.setFont(new Font(Font.SERIF,Font.BOLD,30));
        helpJPanel.add(helplbl,BorderLayout.NORTH);
        helpJPanel.add(helptext,BorderLayout.CENTER);
        helpJPanel.add(hexit,BorderLayout.SOUTH);
        GameArea = new JPanel();
        mainArea = new JPanel();
        PHA = new JPanel();
        PHA.setLayout(new FlowLayout());
        play.setFont(new Font(Font.SERIF,Font.BOLD,40));
        helpbtn.setFont(new Font(Font.SERIF,Font.BOLD,40));
        about.setFont(new Font(Font.SERIF,Font.BOLD,40));
        PHA.add(play);
        PHA.add(helpbtn);
        PHA.add(about);
        clay = new CardLayout();
        emh = new JPanel();
        emh.setLayout(new FlowLayout());
        emh.add(easy);
        emh.add(medium);
        emh.add(hard);
        emhJPanel = new JPanel();
        emhJPanel.setLayout(new BorderLayout());
        title.setAlignment(Label.CENTER);
        title.setFont(new Font("Helevitica",Font.BOLD+Font.ITALIC,60));
        emhJPanel.add(title,BorderLayout.NORTH);
        emhJPanel.setFont(new Font("Helevitica",Font.BOLD,25));
        emhJPanel.add(emh,BorderLayout.CENTER);
        play.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                String res = cbg.getSelectedCheckbox().getLabel();
                if(res == "Easy"){
                    SIZE = 7;
                    createGame(7);
                }
                else if(res == "Medium"){
                    SIZE = 10;
                    createGame(10);
                }
                else{
                    SIZE = 15;
                    createGame(15);
                }
            clay.show(mainJPanel,"mainArea");
            }
        }
        );
        emhJPanel.add(PHA,BorderLayout.SOUTH);
        mainJPanel = new JPanel();
        mainJPanel.setLayout(clay);
        r = new Random();
        sta = new Label("                                 ");
        rs = new JButton("Restart");
        rs.addActionListener(this);
        setLayout(new BorderLayout());
        head = new JPanel();
        head.setLayout(new FlowLayout());
        head.add(new Label("MINE SWEEPER"));
        head.setFont(new Font("Helevitica",Font.BOLD,25));
        status = new JPanel();
        status.add(sta);
        status.add(rs);
        status.add(exit);
        mainArea.setLayout(new BorderLayout());
        mainArea.add(head,BorderLayout.NORTH);
        mainArea.add(GameArea,BorderLayout.CENTER);
        mainArea.add(status,BorderLayout.SOUTH);
        mainJPanel.add("mainArea",mainArea);
        mainJPanel.add("emhJPanel",emhJPanel);
        mainJPanel.add("helpJPanel",helpJPanel);
        mainJPanel.add("abtJPanel",abtJPanel);
        main.add(mainJPanel,BorderLayout.CENTER);
        clay.show(mainJPanel,"emhJPanel");
        helpbtn.addActionListener(new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent ae){
            clay.show(mainJPanel,"helpJPanel");
            createGame(SIZE);
        }
        });
        exit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                clay.show(mainJPanel,"emhJPanel");
                GameArea.removeAll();
                status.remove(3);
                GV = 0;
            }
        });
        hexit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                clay.show(mainJPanel,"emhJPanel");
                GameArea.removeAll();
                status.remove(3);
            }
        });
        about.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                clay.show(mainJPanel,"abtJPanel");
            }
        });
        aexit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                clay.show(mainJPanel,"emhJPanel");
                GameArea.removeAll();
            }
        });
        main.setVisible(true);
        main.setSize(800,800);
        main.setResizable(false);
}
public void actionPerformed(ActionEvent e) {
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                table[i][j].setIcon(null);
                table[i][j].setText(null);
                table[i][j].setBackground(null);
                table[i][j].revealed=false;
                table[i][j].bomb = false;
                table[i][j].neigbours = 0;
            }
        }
        GV = 0;
         //Initializing the bombs
        for(int i=0;i<SIZE+(SIZE/2);i++){
            table[r.nextInt(SIZE)][r.nextInt(SIZE)].bomb = true;
        }
        //Counting the niegbours
        for(int i=0;i<SIZE;i++){for(int j=0;j<SIZE;j++){table[i][j].neigbourCount(table);
        }}
        sta.setText("");
    }
    public static void main(String args[]){
        new Minesweeper();
    }
}
