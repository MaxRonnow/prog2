package com.JavaGame;

import java.util.List;
import java.util.Scanner;

public class TerminalUI {

    Kubb game;
    GameBoard gameBoard;
    int counter = 0;

    public TerminalUI(Kubb game) {
        this.game = game;
        this.gameBoard = new GameBoard(game);
    }


    public void draw() {
        IO.println("\033[H\033[2J");
        gameBoard.updateBoard();
        IO.println("     " + getPlayer1TurnLabel() + "                     " + getPlayer2TurnLabel());
        for (int x = 18; x >= 0; x--) {
            StringBuilder builder = new StringBuilder();
            for (List<String> col : gameBoard.board) {
                builder.append(col.get(x));
            }
            IO.println(builder.toString());
        }


    }

    private String getPlayer1TurnLabel() {
        if (game.getTurn() == Kubb.Player.PLAYER_1) {
            return "●PLAYER 1●";
        }
        else return " player 1";
    }

    private String getPlayer2TurnLabel() {
        if (game.getTurn() == Kubb.Player.PLAYER_2) {
            return "●PLAYER 2●";
        }
        else return "player 2";
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
