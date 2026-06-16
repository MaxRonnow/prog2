package com.JavaGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

        for (int i = 0; i < 5; i++) {
            player1Pieces.add(new KubbPiece());
            player2Pieces.add(new KubbPiece());
        }

        player1Pieces.add(new King());
        player2Pieces.add(new King());

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

        calculateHit(Integer.parseInt(input2));
    }

    private boolean calculateHit(int angle) {
        if (turn == Player.PLAYER_1 && player1Position == Position.POSITION_1) {
            // (4.5, 24)
            // tan()*adj=opp

            double hit = Math.tan((double) angle) * 36;



        }
        else if (turn == Player.PLAYER_1 && player1Position == Position.POSITION_2) {

        }
        else if (turn == Player.PLAYER_2 && player1Position == Position.POSITION_1) {

        }
        else if (turn == Player.PLAYER_2 && player1Position == Position.POSITION_2) {

        }


//        y:
//        pos1 = 12;
//        pos2 = 24;
//        kubb1 = 6;
//        kubb2 = 12;
//        kubb3 = 18;
//        kubb4 = 24;
//        kubb5 = 30;
//        king = 18;
//
//        x:
//        p1 = 4.5;
//        p1pieces = 8.5;
//        p2pieces = 40.5;
//        p2 = 44.5;
//        king = 25;

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

    public Player getTurn() {
        return turn;
    }

    public void setTurn(Player turn) {
        this.turn = turn;
    }

    public List<GamePiece> getPlayer1Pieces() {
        return player1Pieces;
    }

    public void setPlayer1Pieces(List<GamePiece> player1Pieces) {
        this.player1Pieces = player1Pieces;
    }

    public List<GamePiece> getPlayer2Pieces() {
        return player2Pieces;
    }

    public void setPlayer2Pieces(List<GamePiece> player2Pieces) {
        this.player2Pieces = player2Pieces;
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
