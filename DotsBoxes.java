package JAVAhomework;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class DotsBoxes {

    private static boolean humanplayfirst, backToMenu;
    private ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<Edge> temedges = new ArrayList<>();
    private ArrayList<Dot> dots = new ArrayList<>();
    private ArrayList<CharacterAB> characters = new ArrayList<>();
    private ArrayList<CharacterAB> temcharacters = new ArrayList<>();
    private Color currentColor = Color.RED;
    private static int dotnumber, mode;
    private boolean isend = false, trying = false, getout = false;
    private int pointa = 0, pointb = 0, tempointa = 0, tempointb = 0;
    private Color temcolor;
    private Edge temedge;

    private DotsBoxes(int canvasWidth, int canvasHeight) {
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, canvasWidth);
        StdDraw.setYscale(0, canvasHeight);
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < dotnumber; i++) {
            for (int j = 0; j < dotnumber - 1; j++) {
                edges.add(new Edge(75 + 150 * j, 70 + 150 * i, 150, 10));
                edges.add(new Edge(70 + 150 * i, 75 + 150 * j, 10, 150));
            }
        }
        for (int i = 0; i < dotnumber; i++) {
            for (int j = 0; j < dotnumber; j++) {
                dots.add(new Dot(75 + 150 * i, 75 + 150 * j, 15));
            }
        }
        for (int i = 0; i < dotnumber - 1; i++) {
            for (int j = 0; j < dotnumber - 1; j++) {
                characters.add(new CharacterAB(150 + 150 * i, 130 + 150 * j));
            }
        }
    }

    private void checkpoint(Edge edge) {
        if (edge.getBounds().height == 10) {
            int checkupside = 0, checkdownside = 0;
            for (Edge edge1 : edges) {
                if ((edge1.getBounds().x == (edge.getBounds().x - 5) && edge1.getBounds().y == (edge.getBounds().y + 5) && !edge1.isFree()
                ) || (edge1.getBounds().x == edge.getBounds().x && edge1.getBounds().y == (edge.getBounds().y + 150) && !edge1.isFree()
                ) || (edge1.getBounds().x == (edge.getBounds().x + 145) && edge1.getBounds().y == (edge.getBounds().y + 5) && !edge1.isFree())) {
                    checkupside++;
                }
                if ((edge1.getBounds().x == (edge.getBounds().x - 5) && edge1.getBounds().y == (edge.getBounds().y - 145) && !edge1.isFree()
                ) || (edge1.getBounds().x == edge.getBounds().x && edge1.getBounds().y == (edge.getBounds().y - 150) && !edge1.isFree()
                ) || (edge1.getBounds().x == (edge.getBounds().x + 145) && edge1.getBounds().y == (edge.getBounds().y - 145) && !edge1.isFree())) {
                    checkdownside++;
                }
            }
            if (checkupside == 3 || checkdownside == 3) {
                if (!trying) currentColor = currentColor == Color.RED ? Color.CYAN : Color.RED;
                for (CharacterAB character : characters) {
                    if (character.getX() == (edge.getBounds().x + 75) && character.getY() == (edge.getBounds().y + 60) && checkupside == 3) {
                        character.setEyeable(true);
                        character.setCurrentcolor(currentColor);
                        CountPoint();
                    }
                    if (character.getX() == (edge.getBounds().x + 75) && character.getY() == (edge.getBounds().y - 90) && checkdownside == 3) {
                        character.setEyeable(true);
                        character.setCurrentcolor(currentColor);
                        CountPoint();
                    }
                }
            }
        } else {
            int checkleft = 0, checkright = 0;
            for (Edge edge1 : edges) {
                if ((edge1.getBounds().x == (edge.getBounds().x + 5) && edge1.getBounds().y == (edge.getBounds().y - 5) && !edge1.isFree()
                ) || (edge1.getBounds().x == (edge.getBounds().x + 150) && edge1.getBounds().y == edge.getBounds().y && !edge1.isFree()
                ) || (edge1.getBounds().x == (edge.getBounds().x + 5) && edge1.getBounds().y == (edge.getBounds().y + 145) && !edge1.isFree())) {
                    checkright++;
                }
                if ((edge1.getBounds().x == (edge.getBounds().x - 145) && edge1.getBounds().y == (edge.getBounds().y - 5) && !edge1.isFree()
                ) || (edge1.getBounds().x == (edge.getBounds().x - 150) && edge1.getBounds().y == edge.getBounds().y && !edge1.isFree()
                ) || (edge1.getBounds().x == (edge.getBounds().x - 145) && edge1.getBounds().y == (edge.getBounds().y + 145) && !edge1.isFree())) {
                    checkleft++;
                }
            }
            if (checkright == 3 || checkleft == 3) {
                if (!trying) currentColor = currentColor == Color.RED ? Color.CYAN : Color.RED;
                for (CharacterAB character : characters) {
                    if (character.getX() == (edge.getBounds().x + 80) && character.getY() == (edge.getBounds().y + 55) && checkright == 3) {
                        character.setEyeable(true);
                        character.setCurrentcolor(currentColor);
                        CountPoint();
                    }
                    if (character.getX() == (edge.getBounds().x - 70) && character.getY() == (edge.getBounds().y + 55) && checkleft == 3) {
                        character.setEyeable(true);
                        character.setCurrentcolor(currentColor);
                        CountPoint();
                    }
                }
            }
        }
        isend = true;
        for (CharacterAB character : characters) {
            if (!character.isEyeable()) {
                isend = false;
                break;
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void FakeSleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateMM() {
        for (Edge edge : edges) {
            if (edge.isFree()) {
                trying = true;
                getout = false;
                int Mtempa = pointa, Mtempb = pointb;
                checkpoint(edge);
                if (Mtempb != pointb || Mtempa != pointa) {
                    edge.setColor(currentColor);
                    edge.setVisible(true);
                    edge.setFree(false);
                    getout = true;
                    sleep();
                    break;
                }
            }
        }
        if (!getout) {
            trying = false;
            Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
            Random r = new Random();
            mousePoint.x = r.nextInt(150 * dotnumber);
            mousePoint.y = r.nextInt(150 * dotnumber);
            for (Edge edge : edges) {
                if (edge.isFree()) {
                    if (edge.getBounds().contains(mousePoint)) {
                        edge.setColor(currentColor);
                        edge.setVisible(true);
                        currentColor = currentColor == Color.RED ? Color.CYAN : Color.RED;
                        edge.setFree(false);
                        sleep();
                        break;
                    }
                }
            }
        }
    }

    private void updateHH() {
        trying = false;
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        boolean isMousePressed = StdDraw.isMousePressed();
        if (isMousePressed && mousePoint.x >= 0 && mousePoint.x <= 70 && mousePoint.y >= 3 && mousePoint.y <= 33 && !temedge.isFree()) {
            temedge.setFree(true);
            edges.clear();
            for (Edge x : temedges) {
                edges.add(x.clone());
            }
            characters.clear();
            for (CharacterAB x : temcharacters) {
                characters.add(x.clone());
            }
            pointa = tempointa;
            pointb = tempointb;
            if (currentColor == Color.RED) {
                pointa--;
            } else pointb--;
            CountPoint();
            currentColor = temcolor;
        }
        for (Edge edge : edges) {
            if (edge.isFree()) {
                if (edge.getBounds().contains(mousePoint)) {
                    edge.setColor(currentColor);
                    edge.setVisible(true);
                    if (isMousePressed) {
                        edge.setColor(currentColor);
                        temedges.clear();
                        for (Edge x : edges) {
                            temedges.add(x.clone());
                        }
                        temcharacters.clear();
                        for (CharacterAB x : characters) {
                            temcharacters.add(x.clone());
                        }
                        tempointa = pointa;
                        tempointb = pointb;
                        temcolor = currentColor;
                        temedge = edge;
                        currentColor = currentColor == Color.RED ? Color.CYAN : Color.RED;
                        edge.setFree(false);
                        checkpoint(edge);
                    }
                } else {
                    edge.setVisible(false);
                }
            }
        }
    }

    private void updateHM() {
        if (humanplayfirst) {
            if (currentColor == Color.RED) {
                updateHH();
            } else {
                updateMM();
            }
        } else {
            if (currentColor == Color.RED) {
                updateMM();
            } else {
                updateHH();
            }
        }
    }

    private void CountPoint() {
        if (currentColor == Color.RED) {
            pointa++;
        }
        if (currentColor == Color.CYAN) {
            pointb++;
        }
    }

    private void result() {
        StdDraw.clear();
        paintPoint();
        menu();
        Font f = new Font("Arial", Font.PLAIN, dotnumber * 25);
        StdDraw.setFont(f);
        String s1, s2;
        if (pointa > pointb) {
            StdDraw.setPenColor(Color.red);
            s1 = "Winner is";
            s2 = "A !";
        } else if (pointa == pointb) {
            StdDraw.setPenColor(Color.black);
            s1 = "Game over,";
            s2 = " no winner";
        } else {
            StdDraw.setPenColor(Color.cyan);
            s1 = "Winner is";
            s2 = "B !";
        }
        StdDraw.text(75 * dotnumber, 75 * dotnumber + 70, s1);
        StdDraw.text(75 * dotnumber, 75 * dotnumber - 70, s2);
    }

    private void paintPoint() {
        Font f = new Font("Arial", Font.PLAIN, 25);
        StdDraw.setFont(f);
        StdDraw.setPenColor(Color.black);
        StdDraw.textRight(150 * dotnumber - 30, 150 * dotnumber - 30, "B: " + pointb);
        StdDraw.textRight(150 * dotnumber - 110, 150 * dotnumber - 30, "Point  A: " + pointa);
    }

    private void paint() {
        if (isend) {
            result();
        } else {
            StdDraw.clear();
            for (Edge edge : edges) {
                edge.paint();
            }
            for (Dot dot : dots) {
                dot.paint();
            }
            for (CharacterAB character : characters) {
                character.paint();
            }
            if (temcolor == null) {
                StdDraw.setPenColor(Color.black);
            } else StdDraw.setPenColor(temcolor);
            StdDraw.rectangle(35, 18, 35, 15);
            StdDraw.rectangle(125, 18, 45, 15);
            Font f = new Font("Arial", Font.PLAIN, 25);
            StdDraw.setFont(f);
            StdDraw.textLeft(3, 15, "Undo");
            StdDraw.textLeft(85, 15, "Restart");

            StdDraw.setPenColor(currentColor);
            if (currentColor == Color.RED) {
                StdDraw.text(60, 150 * dotnumber - 20, "A's turn");
            } else {
                StdDraw.text(60, 150 * dotnumber - 20, "B's turn");
            }
            paintPoint();
            menu();
        }
        StdDraw.show();
    }

    private void menu() {
        StdDraw.setPenColor(Color.black);
        StdDraw.rectangle(150 * dotnumber - 50, 18, 35, 15);
        Font f = new Font("Arial", Font.PLAIN, 25);
        StdDraw.setFont(f);
        StdDraw.textRight(150 * dotnumber - 16, 15, "Menu");
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        boolean isMousePressed = StdDraw.mouseClicked();
        if (isMousePressed && mousePoint.x >= 150 * dotnumber - 85 && mousePoint.x <= 150 * dotnumber - 15 && mousePoint.y >= 3 && mousePoint.y <= 33) {
            backToMenu = true;
            StdDraw.mouseClicked = false;
        }
    }

    private void Restart(DotsBoxes game) {
        Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
        boolean isMousePressed = StdDraw.isMousePressed();
        if (isMousePressed && mousePoint.x >= 80 && mousePoint.x <= 170 && mousePoint.y >= 3 && mousePoint.y <= 33) {
            edges.clear();
            characters.clear();
            pointa = pointb = 0;
            game.initialize();
            currentColor = Color.red;
        }
    }

    private void MachineMachine(DotsBoxes game) {
        do {
            game.updateMM();
            game.paint();
            FakeSleep();
        } while (!backToMenu);
    }

    private void HumanHuman(DotsBoxes game) {
        do {
            game.updateHH();
            game.paint();
            game.Restart(game);
            FakeSleep();
        } while (!backToMenu);
    }

    private void HumanMachine(DotsBoxes game) {
        do {
            game.updateHM();
            game.paint();
            game.Restart(game);
            FakeSleep();
        } while (!backToMenu);
    }

    private static void ModeChoice() {
        while (true) {
            Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
            boolean isMousePressed = StdDraw.mouseClicked();
            Font f = new Font("Arial", Font.PLAIN, 50);
            StdDraw.setFont(f);
            StdDraw.setPenColor(Color.blue);
            StdDraw.text(225, 380, "Mode");
            StdDraw.rectangle(225, 300, 170, 30);
            StdDraw.rectangle(225, 200, 170, 30);
            StdDraw.rectangle(225, 100, 170, 30);
            Font f2 = new Font("Arial", Font.PLAIN, 35);
            StdDraw.setFont(f2);
            StdDraw.text(225, 300, "Machine vs. Machine");
            StdDraw.text(225, 200, "Human vs. Human");
            StdDraw.text(225, 100, "Human vs. Machine");
            StdDraw.show();
            if (mousePoint.x >= 55 && mousePoint.x <= 395 && mousePoint.y >= 270 && mousePoint.y <= 330) {
                StdDraw.setPenColor(Color.getHSBColor(252, 53, 77));
                StdDraw.filledRectangle(225, 300, 170, 30);
                if (isMousePressed) {
                    mode = 1;
                    StdDraw.mouseClicked = false;
                    break;
                }
            } else {
                StdDraw.setPenColor(Color.getHSBColor(225, 225, 225));
                StdDraw.filledRectangle(225, 300, 170, 30);
            }
            if (mousePoint.x >= 55 && mousePoint.x <= 395 && mousePoint.y >= 170 && mousePoint.y <= 230) {
                StdDraw.setPenColor(Color.getHSBColor(82, 70, 65));
                StdDraw.filledRectangle(225, 200, 170, 30);
                if (isMousePressed) {
                    mode = 2;
                    StdDraw.mouseClicked = false;
                    break;
                }
            } else {
                StdDraw.setPenColor(Color.getHSBColor(225, 225, 225));
                StdDraw.filledRectangle(225, 200, 170, 30);
            }

            if (mousePoint.x >= 55 && mousePoint.x <= 395 && mousePoint.y >= 70 && mousePoint.y <= 130) {
                StdDraw.setPenColor(Color.GREEN);
                StdDraw.filledRectangle(225, 100, 170, 30);
                if (isMousePressed) {
                    mode = 3;
                    StdDraw.mouseClicked = false;
                    break;
                }
            } else {
                StdDraw.setPenColor(Color.getHSBColor(225, 225, 225));
                StdDraw.filledRectangle(225, 100, 170, 30);
            }
        }
    }

    private static void BoardChoice() {
        while (true) {
            StdDraw.clear();
            Font f = new Font("Arial", Font.PLAIN, 50);
            StdDraw.setFont(f);
            StdDraw.setPenColor(Color.blue);
            StdDraw.text(225, 380, "Board");
            StdDraw.rectangle(120, 270, 70, 50);
            StdDraw.rectangle(320, 270, 70, 50);
            StdDraw.rectangle(120, 120, 70, 50);
            StdDraw.rectangle(320, 120, 70, 50);
            Font f2 = new Font("Arial", Font.PLAIN, 45);
            StdDraw.setFont(f2);
            StdDraw.text(120, 270, "3×3");
            StdDraw.text(320, 270, "4×4");
            StdDraw.text(120, 120, "5×5");
            StdDraw.text(320, 120, "6×6");
            StdDraw.show();
            Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
            boolean isMousePressed = StdDraw.mouseClicked();
            if (mousePoint.x >= 50 && mousePoint.x <= 190 && mousePoint.y >= 220 && mousePoint.y <= 320 && isMousePressed) {
                dotnumber = 3;
                StdDraw.mouseClicked = false;
                break;
            }
            if (mousePoint.x >= 250 && mousePoint.x <= 390 && mousePoint.y >= 220 && mousePoint.y <= 320 && isMousePressed) {
                dotnumber = 4;
                StdDraw.mouseClicked = false;
                break;
            }
            if (mousePoint.x >= 50 && mousePoint.x <= 190 && mousePoint.y >= 70 && mousePoint.y <= 170 && isMousePressed) {
                dotnumber = 5;
                StdDraw.mouseClicked = false;
                break;
            }
            if (mousePoint.x >= 250 && mousePoint.x <= 390 && mousePoint.y >= 70 && mousePoint.y <= 170 && isMousePressed) {
                dotnumber = 6;
                StdDraw.mouseClicked = false;
                break;
            }
        }
    }

    private static void WhoFirst() {
        while (true) {
            if (backToMenu) { break; }
            StdDraw.clear();
            Font f = new Font("Arial", Font.PLAIN, 30);
            StdDraw.setFont(f);
            StdDraw.setPenColor(Color.blue);
            StdDraw.text(225, 380, "Do you want to draft.play first?");
            StdDraw.rectangle(120, 270, 55, 45);
            StdDraw.rectangle(320, 270, 55, 45);
            Font f2 = new Font("Arial", Font.PLAIN, 45);
            StdDraw.setFont(f2);
            StdDraw.text(120, 270, "YES");
            StdDraw.text(320, 270, "NO");
            StdDraw.show();
            Point mousePoint = new Point((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
            boolean isMousePressed = StdDraw.mouseClicked();
            if (mousePoint.x >= 50 && mousePoint.x <= 190 && mousePoint.y >= 220 && mousePoint.y <= 320 && isMousePressed) {
                humanplayfirst = true;
                StdDraw.mouseClicked = false;
                break;
            }
            if (mousePoint.x >= 250 && mousePoint.x <= 390 && mousePoint.y >= 220 && mousePoint.y <= 320 && isMousePressed) {
                humanplayfirst = false;
                StdDraw.mouseClicked = false;
                break;
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            backToMenu = false;
            DotsBoxes game = new DotsBoxes(450, 450);
            ModeChoice();
            BoardChoice();
            switch (mode) {
                case 1:
                    DotsBoxes game1 = new DotsBoxes(150 * dotnumber, 150 * dotnumber);
                    game1.MachineMachine(game1);
                case 2:
                    DotsBoxes game2 = new DotsBoxes(150 * dotnumber, 150 * dotnumber);
                    game2.HumanHuman(game2);
                case 3:
                    WhoFirst();
                    DotsBoxes game3 = new DotsBoxes(150 * dotnumber, 150 * dotnumber);
                    game3.HumanMachine(game3);
            }
        }
    }
}