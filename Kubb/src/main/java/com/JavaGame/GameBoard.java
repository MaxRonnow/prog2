package com.JavaGame;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    List<List<String>> board;
    // 49x19
    Kubb game;

    public GameBoard(Kubb game) {
        this.game = game;
        board = new ArrayList<>(49);
        for (int i = 0; i < 49; i++) {
            List<String> x = new ArrayList<>(19);
            board.add(x);
            for (int j = 0; j < 19; j++) {
                x.add(" ");
            }
        }

        for (int x = 0; x < 49; x++) {
            for (int y = 0; y < 19; y++) {

                if (x == 0 || x == 48) {
                    board.get(x).set(y, "│");
                }
                if (y == 0 || y == 18) {
                    board.get(x).set(y, "─");
                }
            }
        }
        board.get(0).set(0, "└");
        board.get(48).set(0, "┘");
        board.get(0).set(18, "┌");
        board.get(48).set(18, "┐");

        for (int y = 3; y < 16; y = y + 3) {
            board.get(8).set(y, "■");
        }
        for (int y = 3; y < 16; y = y + 3) {
            board.get(40).set(y, "■");
        }

        board.get(24).set(9, "╬");

        board.get(4).set(6, "·");
        board.get(4).set(12, "·");

        board.get(44).set(6, "·");
        board.get(44).set(12, "·");
    }

    public void updateBoard() {
        if (game.getPlayer1Position() == Kubb.Position.POSITION_1) {
            board.get(4).set(6, "·");
            board.get(4).set(12, "●");
        }
        else {
            board.get(4).set(6, "●");
            board.get(4).set(12, "·");
        }
        if (game.getPlayer2Position() == Kubb.Position.POSITION_1) {
            board.get(44).set(6, "·");
            board.get(44).set(12, "●");
        }
        else {
            board.get(44).set(6, "●");
            board.get(44).set(12, "·");
        }

        int position = 3;
        for (int i = 0; i < 5; i++) {
            board.get(8).set(position, game.getPlayer1Pieces().get(i).statusToString());
            board.get(40).set(position, game.getPlayer2Pieces().get(i).statusToString());
            position += 3;
        }

    }

    public void drawHitLine(double angle) {
        if (game.getTurn() == Kubb.Player.PLAYER_1 && game.getPlayer1Position() == Kubb.Position.POSITION_1) {
            for (double i = 5; i < 48; i++) {
                double y = Math.tan(Math.toRadians(angle)) * (i - 5.5) + 25;
                double ySquare = y / 2;
                board.get( (int) i ).set((int) ySquare, getLineChar(ySquare));
            }
        }
        else if (game.getTurn() == Kubb.Player.PLAYER_1 && game.getPlayer1Position() == Kubb.Position.POSITION_2) {
            for (double i = 5; i < 48; i++) {
                double y = Math.tan(Math.toRadians(angle)) * (i - 5.5) + 13;
                double ySquare = y / 2;
                board.get( (int) i ).set((int) ySquare, getLineChar(ySquare));
            }
        }
        else if (game.getTurn() == Kubb.Player.PLAYER_2 && game.getPlayer2Position() == Kubb.Position.POSITION_1) {
            for (double i = 45; i > 1; i--) {
                double y = Math.tan(Math.toRadians(angle)) * (i - 5.5) + 25;
                double ySquare = y / 2;
                board.get( (int) i ).set((int) ySquare, getLineChar(ySquare));
            }
        }
        else if (game.getTurn() == Kubb.Player.PLAYER_2 && game.getPlayer2Position() == Kubb.Position.POSITION_2) {
            for (double i = 45; i > 1; i--) {
                double y = Math.tan(Math.toRadians(angle)) * (i - 5.5) + 13;
                double ySquare = y / 2;
                board.get( (int) i ).set((int) ySquare, getLineChar(ySquare));
            }
        }
    }

    private String getLineChar(double d) {
        if ( (d - Math.floor(d)) < 0.333) {
            return ".";
        }
        else if ((d - Math.floor(d)) < 0.666) {
            return "·";
        }
        else {
            return "˙";
        }
    }
}
