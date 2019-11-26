package threads;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame {
    private static Image balloonImage;
    private static Image explosionImage;
    private static JButton startStopButton;
    private static ArrayList<JLabel> balloonLabels;
    private static ArrayList<Integer> velocities;

    private static int positionY = 350;

    private static boolean stopped = true;

    public Main() {
        setTitle("ИПР 2");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            balloonImage = ImageIO.read(new File("src/threads/balloon.png"));
            explosionImage = ImageIO.read(new File("src/threads/explosion.png"));
        } catch (IOException ignored) {
            return;
        }


        balloonLabels = new ArrayList<>();
        velocities = new ArrayList<>();

        for (int i = 0; i <5 ; i++) {
            var label = new JLabel(new ImageIcon(balloonImage));
            label.setText(String.valueOf(i));
            label.setBounds(i * 100 + 60, 350, 50, 50);
            int finalI = i;
            label.addMouseListener(new MouseAdapter() {
                public int index = finalI;

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    getContentPane().remove(balloonLabels.get(index));
                    getContentPane().repaint();
                }
            });
            balloonLabels.add(label);
            velocities.add(i + 1);
        }

        Container content = new JPanel();
        content.setLayout(null);

        startStopButton = new JButton("Start/Stop");
        startStopButton.setBounds(0, 0, 100, 30);
        startStopButton.setBackground(Color.WHITE);
        startStopButton.setForeground(Color.BLACK);
        startStopButton.addActionListener(event -> {
            if(stopped) {
                Thread paint = new Thread(new PaintTask());
                stopped = !stopped;
                paint.start();
            }
            // TODO: print score
        });

        content.add(startStopButton);
        content.add(new DrawPanel());
        for (int i = 0; i < 5; i++) {
            content.add(balloonLabels.get(i));
        }

        setContentPane(content);
    }


    private static class DrawPanel extends JPanel {
        DrawPanel() {
            setOpaque(false);
            setPreferredSize(new Dimension(640, 400));
        }
    }

    public static class PaintTask implements Runnable {
        @Override
        public void run() {
            while (!stopped) {
                for (int i = 0; i < 5; i++) {
                    var label = balloonLabels.get(i);
                    if (label != null) {
                        label.setBounds(label.getX(), label.getY() - velocities.get(i), 40, 40);
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
