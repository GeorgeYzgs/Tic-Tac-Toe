/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2tictactoe;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author George.Giazitzis
 */
public class Week2TicTacToe {

    static void printBoard(char[] board) {
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
    }

    static void fillBoard(char[] board) {
        for (int i = 0; i < board.length; i++) {
            board[i] = '-';
        }
    }

    static boolean checkWinner(char[] board) {
        boolean winCondition = false;
        if ((board[0] == board[1]) && (board[1] == board[2]) && (board[0] != '-')) {
            winCondition = true;
        }
        if ((board[3] == board[4]) && (board[4] == board[5]) && (board[3] != '-')) {
            winCondition = true;
        }
        if ((board[6] == board[7]) && (board[7] == board[8]) && (board[6] != '-')) {
            winCondition = true;
        }
        if ((board[0] == board[3]) && (board[3] == board[6]) && (board[0] != '-')) {
            winCondition = true;
        }
        if ((board[1] == board[4]) && (board[4] == board[7]) && (board[1] != '-')) {
            winCondition = true;
        }
        if ((board[2] == board[5]) && (board[5] == board[8]) && (board[2] != '-')) {
            winCondition = true;
        }
        if ((board[0] == board[4]) && (board[4] == board[8]) && (board[0] != '-')) {
            winCondition = true;
        }
        if ((board[2] == board[4]) && (board[4] == board[6]) && (board[2] != '-')) {
            winCondition = true;
        }
        return winCondition;
    }

    static boolean boardIsFull(char[] board) {

        boolean fullboard = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '-') {
                fullboard = false;
            }
        }
        return fullboard;
    }

    static char symbolSwap(char symbol) {
        if (symbol == 'X') {
            symbol = 'O';
        } else if (symbol == 'O') {
            symbol = 'X';
        } else if (symbol == 'x') {
            symbol = 'o';
        } else if (symbol == 'o') {
            symbol = 'x';
        }
        return symbol;
    }

    static boolean registration(char[] board, int i) {
        boolean spotFree = true;
        if ((board[i] == 'X') || (board[i] == 'O') || (board[i] == 'x') || (board[i] == 'o')) {
            spotFree = false;
        }
        return spotFree;
    }

    static boolean slotValidity(int slot) {
        boolean slotnumber = true;
        if (slot < 0 || slot > 8) {
            slotnumber = false;
        }
        return slotnumber;
    }

    static int nextPlayer(int player) {
        if (player == 2) {
            player = 1;
        } else if (player == 1) {
            player = 2;
        }
        return player;
    }

    static int whoGoesFirst() {
        Random r = new Random();
        int player = 0;
        int coin = r.nextInt(100);
        if (coin % 2 == 0) {
            player = 1;
        } else if (coin % 2 != 0) {
            player = 2;
        }
        return player;
    }

    static boolean symbolValidity(char symbol) {
        boolean validity = false;
        if (symbol == 'x' || symbol == 'X' || symbol == 'o' || symbol == 'O') {
            validity = true;
        }
        return validity;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char board[] = new char[9];
        fillBoard(board);
        ArrayList<String> players = new ArrayList();
        System.out.println("Welcome to Tic Tac Toe! Insert a name for player 1:");
        String player1Name = sc.nextLine();
        players.add(player1Name);
        System.out.println("Insert a name for player 2:");
        String player2Name = sc.nextLine();
        players.add(player2Name);
        System.out.println("Coin toss for who plays first!");
        int player = whoGoesFirst();
        System.out.println("Its Player " + player + "! " + players.get(player - 1) + "! Choose your symbol: \"X\" or \"O\" ?");
        char symbol = sc.next().charAt(0);
        while (!symbolValidity(symbol)) {
            System.out.println("Invalid symbol input " + players.get(player - 1) + "! Choose \"X\" or \"O\" !");
            symbol = sc.next().charAt(0);
        }
        printBoard(board);
        while (!boardIsFull(board) && !checkWinner(board)) {
            System.out.println("Player " + player + ": Select a position on the board from \"0\"(top left) to \"8\"(bottom right) to insert \"" + symbol + "\"");
            int slot = sc.nextInt();
            if (slotValidity(slot) && registration(board, slot)) {
                board[slot] = symbol;
                System.out.println("Your move has been registered " + players.get(player - 1) + "!");
                symbol = symbolSwap(symbol);
                player = nextPlayer(player);
            } else {
                System.out.println("\"" + symbol + "\" Cannot be placed there " + players.get(player - 1) + ", Try again!");
            }
            printBoard(board);
        }
        if (checkWinner(board)) {
            player = nextPlayer(player);
            System.out.println("We have a Winner! Player " + player + " wins! Congratulations " + players.get(player - 1) + "!");
        } else if (boardIsFull(board)) {
            System.out.println("The game ends in a draw, thank you for playing!");
        }
    }
}
