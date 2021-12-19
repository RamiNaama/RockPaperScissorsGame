import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RPS extends JFrame {
    private JPanel panel1; //panel 1 components
    private JLabel winLB;
    private JLabel winCountLB;
    private JLabel lossLB;
    private JLabel lossCountLB;
    private JLabel tieLB;
    private JLabel tieCountLB;
    //panel 2 component (3 buttons array)
    private JPanel panel2;
    private JButton[] threeBtn = new JButton[3];//three buttons array


    private JPanel panel3;//panel 3 has one button component
    private JButton quitBtn;//Quit Button

    //newFrame components
    private JFrame newFrame;
    private JPanel resultPanel;
    private JLabel resultLB0;
    private JLabel resultLB2;
    private JLabel resultLB3;

    private int rand; //to reach random number and use it to comparing

    //frame size
    private final int WINDOW_WIDTH = 350;
    private final int WINDOW_HEIGHT = 250;

    //create objects of Images
    ImageIcon rockImage = new ImageIcon("C:\\Users\\rami_\\IdeaProjects\\GUIGAME\\src\\rock.png");
    ImageIcon paperImage = new ImageIcon("C:\\Users\\rami_\\IdeaProjects\\GUIGAME\\src\\paper.png");
    ImageIcon scissorsImage = new ImageIcon("C:\\Users\\rami_\\IdeaProjects\\GUIGAME\\src\\scissors.png");

    //I used Integer class to swap between string and int
    Integer tie = 0;
    Integer win= 0;
    Integer loss= 0;

    public RPS() { //constructor
        JFrame frame = new JFrame();//create new frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Exit by X
        setTitle("Rock, Paper, Scissors");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLayout(null);
        buildPanel();//calling
        add(panel1);//set panel1 to north
        add(panel2);//set panel2 in center
        add(panel3);//set panel1 to south
        setResizable(false);
    }

    public void buildPanel() {
        //panel 1 component win, loss, tie
        panel1 = new JPanel();
        panel1.setBounds(0, 0, 330, 20);
        panel1.setLayout(new FlowLayout(FlowLayout.LEADING));//to start from left
        add(panel1);
        winLB = new JLabel("Result: Win = ");
        panel1.add(winLB);
        //this text will be change if human win
        winCountLB = new JLabel("0");
        panel1.add(winCountLB);
        lossLB = new JLabel(", Loss = ");
        panel1.add(lossLB);
        //this text will be change if human loss
        lossCountLB = new JLabel("0");
        panel1.add(lossCountLB);
        tieLB = new JLabel(", Tie = ");
        panel1.add(tieLB);
        //this text will be change if tie
        tieCountLB = new JLabel("0");
        panel1.add(tieCountLB);

        //panel 2 components 3 buttons array
        panel2 = new JPanel();
        panel2.setBounds(0, 20, 337, 160);
        panel2.setLayout(new GridLayout(1, 3));
        add(panel2);

        for (int i = 0; i < 3; i++) {//loop to full the array
            threeBtn[0] = new JButton(rockImage);//add rock image
            threeBtn[1] = new JButton(paperImage);//add paper image
            threeBtn[2] = new JButton(scissorsImage);//add scissor image


            panel2.add(threeBtn[i]);//add 3 buttons array to panel 2

            threeBtn[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    random();//calling random function to create random number
                    //set rock Image in label resultLB2 for the new Computer window
                    resultLB2.setIcon(rockImage);
                    //increments depend on which condition is executed
                    if(rand==0){
                        resultLB0.setText("Human wins!");
                        win++;
                        winCountLB.setText(win.toString());//update label winCountLB
                    }
                    else if(rand==1){
                        resultLB0.setText("It is tie!");
                        tie++;
                        tieCountLB.setText(tie.toString());//update label tieCountLB
                    }
                    else{
                        resultLB0.setText("Computer wins!");
                        loss++;
                        lossCountLB.setText(loss.toString());//update label lossCountLB
                    }
                }
            });

            threeBtn[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    random();
                    resultLB2.setIcon(paperImage);
                    if(rand==0){
                        resultLB0.setText("Computer wins!");
                        loss++;
                        lossCountLB.setText(loss.toString());
                    }
                    else if(rand==1){
                        resultLB0.setText("Human wins!");
                        win++;
                        winCountLB.setText(win.toString());
                    }
                    else{
                        resultLB0.setText("It is tie!");
                        tie++;
                        tieCountLB.setText(tie.toString());
                    }
                }
            });

            threeBtn[2].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    random();
                    resultLB2.setIcon(scissorsImage);
                    if(rand==0){
                        resultLB0.setText("It is tie!");
                        tie++;
                        tieCountLB.setText(tie.toString());
                    }
                    else if(rand==1){
                        resultLB0.setText("Computer wins!");
                        loss++;
                        lossCountLB.setText(loss.toString());
                    }
                    else{
                        resultLB0.setText("Human wins!");
                        win++;
                        winCountLB.setText(win.toString());
                    }
                }
            });
        }
        //panel 3 for Quit button
        panel3 = new JPanel();
        add(panel3);
        panel3.setBounds(0, 180, 337, 33);
        panel3.setLayout(new GridLayout());
        quitBtn = new JButton("Quit");
        panel3.add(quitBtn);
        setVisible(true);
        //action listener for Quit Button
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == quitBtn) {
                    int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?",
                            "Quit", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            }
        });
    }

    public void newBuildPanel() {//for Computer window(new frame)
        resultPanel = new JPanel();
        newFrame.add(resultPanel,BorderLayout.CENTER);
        resultPanel.setLayout(new BorderLayout());
        resultLB0 = new JLabel("");
        resultLB2 = new JLabel("Human's Choice ");
        resultLB3 = new JLabel("Compute Choice ");
        resultPanel.add(resultLB0,BorderLayout.NORTH);
        resultPanel.add(resultLB2,BorderLayout.WEST);
        resultPanel.add(resultLB3,BorderLayout.EAST);
    }

    public void resultWindow() {//for Computer window(new frame)
        newFrame = new JFrame("Computer");
        newFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);//Hide by X
        newFrame.setSize(450, 250);
        newFrame.setLayout(new BorderLayout());
        newBuildPanel();
        newFrame.setVisible(false);//set on false and will update when press any button(3 button)
    }
    //random class to create from (0 or 1 or 2)
    public void random() {
        int min = 0;
        int max = 2;
        int range = max - min + 1;
         rand = (int) (Math.random() * range);
        if (rand == 1) {
            resultWindow();
            newBuildPanel();
            resultLB3.setIcon(rockImage);
            newFrame.setVisible(true);//update visibility
        } else if (rand == 2) {
            resultWindow();
            newBuildPanel();
            resultLB3.setIcon(paperImage);
            newFrame.setVisible(true);//update visibility
        } else {
            resultWindow();
            newBuildPanel();
            resultLB3.setIcon(scissorsImage);
            newFrame.setVisible(true);//update visibility
        }
    }
    public static void main(String[] args) {
        new RPS();
    }
}
