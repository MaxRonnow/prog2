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
        clearBoard();

    }

    public void updateBoard() {
        if (game.getPlayer1Position() == Kubb.Position.POSITION_1) {
            board.get(4).set(6, "⋅");
            board.get(4).set(12, "●");
        }
        else {
            board.get(4).set(6, "●");
            board.get(4).set(12, "⋅");
        }
        if (game.getPlayer2Position() == Kubb.Position.POSITION_1) {
            board.get(44).set(6, "⋅");
            board.get(44).set(12, "●");
        }
        else {
            board.get(44).set(6, "●");
            board.get(44).set(12, "⋅");
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
            for (double x = 5; x < 48; x++) {
                double y = Math.tan(Math.toRadians(angle)) * (x - 5) + 24;
                double ySquare = y / 2;
                board.get( (int) x ).set((int) Math.round(ySquare), getLineChar(ySquare));
                if (game.checkHit(x, y)) {
                    return;
                }
            }
        }
        else if (game.getTurn() == Kubb.Player.PLAYER_1 && game.getPlayer1Position() == Kubb.Position.POSITION_2) {
            for (double x = 5; x < 48; x++) {
                double y = Math.tan(Math.toRadians(angle)) * (x - 5) + 12;
                double ySquare = y / 2;
                board.get( (int) x ).set((int) Math.round(ySquare), getLineChar(ySquare));
                if (game.checkHit(x, y)) {
                    return;
                }
            }
        }
        else if (game.getTurn() == Kubb.Player.PLAYER_2 && game.getPlayer2Position() == Kubb.Position.POSITION_1) {
            for (double x = 43; x > 0; x--) {
                double y = Math.tan(Math.toRadians(-angle)) * (x - 43) + 24;
                double ySquare = y / 2;
                board.get( (int) x ).set((int) Math.round(ySquare), getLineChar(ySquare));
                if (game.checkHit(x, y)) {
                    return;
                }
            }
        }
        else if (game.getTurn() == Kubb.Player.PLAYER_2 && game.getPlayer2Position() == Kubb.Position.POSITION_2) {
            for (double x = 43; x > 1; x--) {
                double y = Math.tan(Math.toRadians(-angle)) * (x - 43) + 12;
                double ySquare = y / 2;
                board.get( (int) x ).set((int) Math.round(ySquare), getLineChar(ySquare));
                if (game.checkHit(x, y)) {
                    return;
                }
            }
        }
    }

    private String getLineChar(double d) {
        double x = d - Math.floor(d);
        if ( 0.5 > x && x > 0.2) {
            return "˙";
        }
        else if (x < 0.2 || x > 0.8) {
            return "⋅";
        }
        else {
            return ".";
        }
    }

    public void clearBoard() {
        for (int i = 0; i < 49; i++) {
            for (int j = 0; j < 19; j++) {
                board.get(i).set(j, " ");
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

        for (int y = 1; y < 6; y++) {
            board.get(8).set(y * 3, game.getPlayer1Pieces().get(y).statusToString());
        }
        for (int y = 1; y < 6; y++) {
            board.get(40).set(y * 3, game.getPlayer2Pieces().get(y).statusToString());
        }

        board.get(24).set(9, game.getPlayer1Pieces().get(5).statusToString());

        board.get(4).set(6, "⋅");
        board.get(4).set(12, "⋅");

        board.get(44).set(6, "⋅");
        board.get(44).set(12, "⋅");
    }
}
