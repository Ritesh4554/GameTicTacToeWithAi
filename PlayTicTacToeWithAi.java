package GameTic;

import java.util.Random;
import java.util.Scanner;

public class PlayTicTacToeWithAi {
	static char[][] board;

	public PlayTicTacToeWithAi() {
		board = new char[3][3];
		initBoard();
	}

	void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	static void placeMark(int row, int col, char mark) {
		if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
			board[row][col] = mark;
		} else {
			System.out.println("Invalid Position");
		}
	}

	static boolean checkColWin() {
		for (int j = 0; j <= 2; j++) {
			if (board[0][1] != ' ' && board[0][1] == board[1][j] && board[1][j] == board[2][j]) {
				return true;

			}
		}
		return false;

	}

	static boolean checkRowWin() {
		for (int i = 0; i <= 2; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkDiaWin() {
		if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
				|| board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}

	static void dispBoard() {
		System.out.println("------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("|");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " | ");

			}
			System.out.println();
			System.out.println("------------");
		}
	}

	abstract static class Player {
		String name;
		char mark;

		abstract void makeMove();

		boolean isValidMove(int row, int col) {
			if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
				if (PlayTicTacToeWithAi.board[row][col] == ' ') {
					return true;
				}
			}
			return false;
		}
	}

	static class HumanPlayer extends Player {

		HumanPlayer(String name, char mark) {
			this.name = name;
			this.mark = mark;
		}

		@Override

		void makeMove() {
			Scanner sc = new Scanner(System.in);
			int row;
			int col;
			do {
				System.out.println("Enter the row and col");
				row = sc.nextInt();
				col = sc.nextInt();

			} while (!isValidMove(row, col));

			PlayTicTacToeWithAi.placeMark(row, col, mark);
		}

		static class AIPlayer extends Player {
			String name;
			char mark;

			AIPlayer(String name, char mark) {
				this.name = name;
				this.mark = mark;
			}

			@Override

			void makeMove() {
				Scanner sc = new Scanner(System.in);
				int row;
				int col;
				do {
					Random r = new Random();
					row = r.nextInt(3);
					col = r.nextInt(3);

				} while (!isValidMove(row, col));

				PlayTicTacToeWithAi.placeMark(row, col, mark);
			}
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub

			PlayTicTacToeWithAi ob = new PlayTicTacToeWithAi();

			HumanPlayer p1 = new HumanPlayer("Bob ", 'X');
			AIPlayer p2 = new AIPlayer("AI", 'O');

			Player cp;
			cp = p1;

			while (true) {

				System.out.println(cp.name + " turn");
				cp.makeMove();
				PlayTicTacToeWithAi.dispBoard();
				if (PlayTicTacToeWithAi.checkColWin() || PlayTicTacToeWithAi.checkRowWin()
						|| PlayTicTacToeWithAi.checkDiaWin()) {
					System.out.println(cp.name + "Won");
					break;
				} else {
					if (cp == p1) {
						cp = p2;
					} else {
						cp = p1;
					}
				}
			}

		}
	}
}