package com.JavaGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
    p11 = (4, 12) = (4, 24)
    p12 = (4, 6) = (4, 12)

    p21 = (44, 12) = (44, 24)
    p22 = (44, 6) = (44, 12)

    p1k:
    k1 = (8, 3) = (8, 6)
    k2 = (8, 6) = (8, 12)
    k3 = (8, 9) = (8, 18)
    k4 = (8, 12) = (8, 24)
    k5 = (8, 15) = (8, 30)

    p1k:
    k1 = (40, 3) = (40, 6)
    k2 = (40, 6) = (40, 12)
    k3 = (40, 9) = (40, 18)
    k4 = (40, 12) = (40, 24)
    k5 = (40, 15) = (40, 30)

    kung = (24, 9) = (24, 18)

*/

public class Kubb {
    private Player turn;
    private Position player1Position;
    private Position player2Position;

    private List<GamePiece> player1Pieces;
    private List<GamePiece> player2Pieces;

    private TerminalUI ui;

    public Kubb() {
        player1Pieces = new ArrayList<>();
        player2Pieces = new ArrayList<>();

        for (int y = 1; y < 6; y++) {
            player1Pieces.add(new KubbPiece(8, y * 3 * 2));
            player2Pieces.add(new KubbPiece(40, y * 3 * 2));
        }
        King king = new King(24, 18);

        player1Pieces.add(king);
        player2Pieces.add(king);

        player1Position = Position.POSITION_1;
        player2Position = Position.POSITION_1;

        turn = Player.PLAYER_1;

        ui = new TerminalUI(this);
    }

    public void startGame() throws IOException {
        while (true) {
            ui.draw();
            IO.println("choose position:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            updatePositionState(input);
            ui.draw();
            IO.println("Choose angle of throw:");
            String input2 = br.readLine();
            ui.gameBoard.clearBoard();
            updateGameState(input2);
            nextTurn();
        }
    }

    private void nextTurn() {
        if (turn == Player.PLAYER_1) {
            turn = Player.PLAYER_2;
        }
        else {
            turn = Player.PLAYER_1;
        }
    }

    private void updateGameState(String input2) {
        ui.gameBoard.drawHitLine(Integer.parseInt(input2));
    }

    public boolean checkHit(double x, double y) {
        if (turn == Player.PLAYER_1) {
            for (GamePiece p : player2Pieces) {
                if (Math.round(x) < p.x + 1 && Math.round(x) > p.x - 1 && Math.round(y) < p.y + 1 && Math.round(y) > p.y - 1) {
                    if (p instanceof King && isPlayer2Fallen()){
                        IO.println("Player 1 Wins!");
                        return true;
                    }
                    else if (p instanceof King && !isPlayer2Fallen()){
                        IO.println("Player 1 loses, knocked King down too early!");
                    }
                    else {
                        p.fall();
                        return true;
                    }

                }
            }
            return false;
        }
        else if (turn == Player.PLAYER_2) {
            for (GamePiece p : player1Pieces) {
                if (Math.round(x) < p.x + 1 && Math.round(x) > p.x - 1 && Math.round(y) < p.y + 1 && Math.round(y) > p.y - 1) {
                    if (p instanceof King && isPlayer1Fallen()){
                        IO.println("Player 2 Wins!");
                        return true;
                    }
                    else if (p instanceof King && !isPlayer1Fallen()){
                        IO.println("Player 2 loses, knocked King down too early!");
                    }
                    else {
                        p.fall();
                        return true;
                    }
                }
            }
            return false;
        }

        return false;
    }

    private void updatePositionState(String input) {
        switch (input) {
            case "1":
                if (turn == Player.PLAYER_1) {
                    player1Position = Position.POSITION_1;
                    break;
                }
                else {
                    player2Position = Position.POSITION_1;
                    break;
                }
            case "2":
                if (turn == Player.PLAYER_1) {
                    player1Position = Position.POSITION_2;
                    break;
                }
                else {
                    player2Position = Position.POSITION_2;
                    break;
                }
        }
    }

    private boolean isPlayer1Fallen() {
        boolean fallen = true;
        for (GamePiece p : player1Pieces) {
            if (!(p instanceof King)) {
                if (!p.isFallen()) {
                    fallen = false;
                }
            }
        }
        return fallen;
    }

    private boolean isPlayer2Fallen() {
        boolean fallen = true;
        for (GamePiece p : player2Pieces) {
            if (!(p instanceof King)) {
                if (!p.isFallen()) {
                    fallen = false;
                }
            }
        }
        return fallen;
    }

    public Player getTurn() {
        return turn;
    }

    public List<GamePiece> getPlayer1Pieces() {
        return player1Pieces;
    }

    public List<GamePiece> getPlayer2Pieces() {
        return player2Pieces;
    }

    public Position getPlayer1Position() {
        return player1Position;
    }

    public Position getPlayer2Position() {
        return player2Position;
    }


    enum Player {
        PLAYER_1,
        PLAYER_2,
    }

    enum Position {
        POSITION_1,
        POSITION_2,
    }
}
