package shoot.duckhunt.GUI;

import shoot.duckhunt.controller.DuckController;
import shoot.duckhunt.model.Duck;
import shoot.duckhunt.utility.Resources.Resources;
import shoot.duckhunt.utility.sound.Sound;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import shoot.duckhunt.controller.GameListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class GamePanel extends JPanel implements MouseMotionListener {

    private static final Cursor CURSOR = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(), "null");
    private static final int BULLET_NUMBER = 3;

    private BufferedImage backgroundImg;
    private BufferedImage cursorImg;
    private BufferedImage dogCurrentImage;
    private BufferedImage duckCurrentImage;
    private BufferedImage flyAwayImage;
    private BufferedImage[] ammo;
    private BufferedImage gameResultImage;
    private BufferedImage pressEnterImage;

    private Sound shootSound;
    private Sound endOfGameSound;

    private Rectangle cursorRectangle;

    private DuckController duckController;

    private GameListener gameListener;

    private Duck duck;

    private GameThread gameThread;

    private boolean isGameFinished;
    private boolean showImage;

    private int currentAmmoNumber;
    private int killedDucks;

    public GamePanel() {
        initPanel();
        gameThread.start();
    }

    private void initPanel() {
        this.setLayout(null);
        this.setCursor(CURSOR);
        this.addMouseMotionListener(this);

        Resources.getSound("/shoot/resources/sounds/gameIntro.wav").play();
        backgroundImg = Resources.getImage("/shoot/resources/images/gameBackground.png");
        duckCurrentImage = Resources.getImage("/shoot/resources/images/duckUpRight0.png");
        dogCurrentImage = Resources.getImage("/shoot/resources/images/dogRight0.png");
        cursorImg = Resources.getImage("/shoot/resources/images/gunsight.png");
        flyAwayImage = Resources.getImage("/shoot/resources/images/flyAway.png");
        pressEnterImage = Resources.getImage("/shoot/resources/images/pressEnter.png");
        shootSound = Resources.getSound("/shoot/resources/sounds/shoot.wav");
        endOfGameSound = Resources.getSound("/shoot/resources/sounds/gameClear.wav");
        ammo = new BufferedImage[3];
        for (int i = 0; i < BULLET_NUMBER; i++) {
            ammo[i] = Resources.getImage("/shoot/resources/images/bullet.png");
        }
        showImage = true;
        isGameFinished = false;
        cursorRectangle = new Rectangle();
        cursorRectangle.setSize(cursorImg.getWidth(null), cursorImg.getHeight(null));
        cursorRectangle.setLocation(-cursorImg.getWidth(null), -cursorImg.getHeight(null));
        gameThread = new GameThread();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                shootSound.play();
                Point hitPoint = e.getPoint();
                if (duck != null) {
                    duckController.decreaseAmmunition();
                    currentAmmoNumber--;
                    hitPoint.x -= duck.getX();
                    hitPoint.y -= duck.getY();
                    if (contains(duckController.getCurrentImage(), hitPoint.x, hitPoint.y)) {
                        duckController.theDuckWasHit(true);
                        killedDucks++;
                    }
                }
            }
        });
    }

    public boolean contains(BufferedImage image, int x, int y) {
        if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()) {
            return false;
        }
        Color pixel = new Color(image.getRGB(x, y), true);
        return pixel.getAlpha() > 128;
    }

    public void setDogCurrentImage(BufferedImage pImage) {
        this.dogCurrentImage = pImage;
    }

    public void setDuckCurrentImage(BufferedImage pImage) {
        this.duckCurrentImage = pImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2D.drawImage(backgroundImg, 0, 0, this);
        g2D.drawImage(cursorImg, this.cursorRectangle.x, this.cursorRectangle.y, this);


        for (int i = 0; i < currentAmmoNumber; i++) {
            g2D.drawImage(ammo[i], i * 30, 520, this);
        }

        if (duckController.isDuckVisible()) {
            g2D.drawImage(duckCurrentImage, duck.getX(), duck.getY(), this);
        }

        if (duckController.isFlownAway()) {
            g2D.drawImage(flyAwayImage, 300, 100, this);
        }

        if (isGameFinished) {
            g2D.drawImage(gameResultImage, 300, 100, this);
            if (showImage && pressEnterImage != null) {
                g2D.drawImage(pressEnterImage, 300, 150, this);
            }
        }

    }

    private void imageBlinker() {
        ActionListener listener = (ActionEvent e) -> {
            if (pressEnterImage != null) {
                showImage = !showImage;
                repaint();
            }
        };
        Timer timer = new Timer(600, listener);
        timer.start();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        cursorRectangle.x = e.getPoint().x - cursorRectangle.width / 2;
        cursorRectangle.y = e.getPoint().y - cursorRectangle.height / 2;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // 
    }

    public void addListener(GameListener pListener) {
        gameListener = pListener;
    }

    public void notifyGameStatus() {
        if (isGameFinished) {
            gameListener.gameIsFinished();
        }
    }

    public class GameThread implements Runnable {

        static final int DUCK_NUMBER = 10;
        private Thread thread;
        private int i;

        public GameThread() {

            duckController = DuckController.getIstance();

            duckController.setPanel(GamePanel.this);
        }

        public void start() {
            reset();
            thread = new Thread(this);
            thread.start();
        }

        public void stop() {
            duckController.theDuckIsFlownAway(false);
            currentAmmoNumber = 0;
            endOfGameSound.play();
            imageBlinker();
            notifyGameStatus();
            if (thread != null && thread.isAlive()) {
                thread.interrupt();
            }
        }

        private void reset() {
            isGameFinished = false;
            duckController.theDuckIsFlownAway(false);
            isGameFinished = false;
            killedDucks = 0;
            i = 0;
        }

        @Override
        public void run() {
            while (!isGameFinished) {
                try {
      
                    while (i < DUCK_NUMBER) {
                        currentAmmoNumber = 3;
                        duck = new Duck();
                        duckController.setDuck(duck);
                        duckController.getDuckAnimation().start();
  
                        i++;
                    }
                    if (killedDucks > 5) {
                        System.out.println("YOU WIN");
                        gameResultImage = Resources.getImage("/shoot/resources/images/youWin.png");
                        repaint();
                    } else {
                        System.out.println("YOU LOSE");
                        gameResultImage = Resources.getImage("/shoot/resources/images/gameover.png");
                        repaint();
                    }
                    isGameFinished = true;
                } catch (InterruptedException ex) {
                    System.out.println("an error occured during game thread execution");
                }
            }
            stop();
        }
    }
}
