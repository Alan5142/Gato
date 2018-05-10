package com.example.alan2.gato;

enum Turn
{
	Circle,
	Cross
}

enum Player
{
	Player1,
	Player2,
	None
}

public class Game
{
	private Turn m_turn;
	private Grid m_grid;
	
	public Game()
	{
		m_turn = Turn.Circle;
		m_grid = new Grid();
	}
	
	/**
	 * Set a cell in the game
	 *
	 * @param column which column?
	 * @param row    which row?
	 * @return true if the cell was modified, else false
	 */
	public boolean move(int column, int row)
	{
		try
		{
			m_grid.setGridStatus(column, row, m_turn == Turn.Circle ? CellState.Circle : CellState.Cross);
			m_turn = m_turn == Turn.Circle ? Turn.Cross : Turn.Circle;
			return true;
		}
		catch (IllegalArgumentException argument)
		{
			return false;
		}
	}
	
	
	/**
	 * Checks if a player won
	 *
	 * @return Player1 if player one won, Player2 if player 2 won, None if no one won
	 */
	public Player checkWinner()
	{
		short countHorizontalP1 = 0, countHorizontalP2 = 0;
		short countVerticalP1 = 0, countVerticalP2 = 0;
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (m_grid.getStatus(i, j) == CellState.Circle)
				{
					countHorizontalP1++;
				}
				else
				{
					countHorizontalP1 = 0;
				}
				
				if (m_grid.getStatus(j, i) == CellState.Circle)
				{
					countVerticalP1++;
				}
				else
				{
					countVerticalP1 = 0;
				}
				
				if (m_grid.getStatus(i, j) == CellState.Cross)
				{
					countHorizontalP2++;
				}
				else
				{
					countHorizontalP2 = 0;
				}
				
				if (m_grid.getStatus(j, i) == CellState.Cross)
				{
					countVerticalP2++;
				}
				else
				{
					countVerticalP2 = 0;
				}
				
				if (countHorizontalP1 == 3 || countVerticalP1 == 3)
				{
					return Player.Player1;
				}
				
				if (countHorizontalP2 == 3 || countVerticalP2 == 3)
				{
					return Player.Player2;
				}
			}
			
			boolean diagonalLeftToRightP1 =
					m_grid.getStatus(0, 0) == CellState.Circle &&
							m_grid.getStatus(1, 1) == CellState.Circle &&
							m_grid.getStatus(2, 2) == CellState.Circle;
			
			boolean diagonalRightToLeftP1 =
					m_grid.getStatus(0, 0) == CellState.Circle &&
							m_grid.getStatus(1, 1) == CellState.Circle &&
							m_grid.getStatus(2, 2) == CellState.Circle;
			
			if (diagonalLeftToRightP1 || diagonalRightToLeftP1)
			{
				return Player.Player1;
			}
			
			boolean diagonalLeftToRightP2 =
					m_grid.getStatus(0, 0) == CellState.Cross &&
							m_grid.getStatus(1, 1) == CellState.Cross &&
							m_grid.getStatus(2, 2) == CellState.Cross;
			
			boolean diagonalRightToLeftP2 =
					m_grid.getStatus(0, 0) == CellState.Cross &&
							m_grid.getStatus(1, 1) == CellState.Cross &&
							m_grid.getStatus(2, 2) == CellState.Cross;
			
			if (diagonalLeftToRightP2 || diagonalRightToLeftP2)
			{
				return Player.Player2;
			}
			
		}
		
		return Player.None;
	}
}
