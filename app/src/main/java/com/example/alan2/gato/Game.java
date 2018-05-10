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
	private boolean m_winner;
	
	Game()
	{
		m_winner = false;
		m_turn = Turn.Circle;
		m_grid = new Grid();
	}
	
	public Turn getTurn()
	{
		return m_turn;
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
			m_grid.setGridStatus(column, row, m_turn == Turn.Circle ? CellStatus.Circle : CellStatus.Cross);
			m_turn = m_turn == Turn.Circle ? Turn.Cross : Turn.Circle;
			
			return true;
		}
		catch (IllegalArgumentException argument)
		{
			return false;
		}
	}
	
	public boolean hasWinner()
	{
		return m_winner;
	}
	
	public boolean isFilledWithNoWinner()
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (m_grid.getStatus(i, j) == CellStatus.None)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks if a player won
	 *
	 * @return Player1 if player one won, Player2 if player 2 won, None if no one won
	 */
	public Player checkWinner()
	{
		
		short countHorizontalP1, countHorizontalP2;
		short countVerticalP1, countVerticalP2;
		for (int i = 0; i < 3; i++)
		{
			countHorizontalP1 = 0;
			countHorizontalP2 = 0;
			countVerticalP1 = 0;
			countVerticalP2 = 0;
			
			for (int j = 0; j < 3; j++)
			{
				if (m_grid.getStatus(i, j) == CellStatus.Circle)
				{
					countHorizontalP1++;
				}
				else
				{
					countHorizontalP1 = 0;
				}
				
				if (m_grid.getStatus(j, i) == CellStatus.Circle)
				{
					countVerticalP1++;
				}
				else
				{
					countVerticalP1 = 0;
				}
				
				if (m_grid.getStatus(i, j) == CellStatus.Cross)
				{
					countHorizontalP2++;
				}
				else
				{
					countHorizontalP2 = 0;
				}
				
				if (m_grid.getStatus(j, i) == CellStatus.Cross)
				{
					countVerticalP2++;
				}
				else
				{
					countVerticalP2 = 0;
				}
				
				if (countHorizontalP1 == 3 || countVerticalP1 == 3)
				{
					m_winner = true;
					return Player.Player1;
				}
				
				if (countHorizontalP2 == 3 || countVerticalP2 == 3)
				{
					m_winner = true;
					return Player.Player2;
				}
			}
		}
		
		boolean diagonalLeftToRightP1 =
				m_grid.getStatus(0, 0) == CellStatus.Circle &&
						m_grid.getStatus(1, 1) == CellStatus.Circle &&
						m_grid.getStatus(2, 2) == CellStatus.Circle;
		
		boolean diagonalRightToLeftP1 =
				m_grid.getStatus(0, 0) == CellStatus.Circle &&
						m_grid.getStatus(1, 1) == CellStatus.Circle &&
						m_grid.getStatus(2, 2) == CellStatus.Circle;
		
		if (diagonalLeftToRightP1 || diagonalRightToLeftP1)
		{
			m_winner = true;
			return Player.Player1;
		}
		
		boolean diagonalLeftToRightP2 =
				m_grid.getStatus(0, 0) == CellStatus.Cross &&
						m_grid.getStatus(1, 1) == CellStatus.Cross &&
						m_grid.getStatus(2, 2) == CellStatus.Cross;
		
		boolean diagonalRightToLeftP2 =
				m_grid.getStatus(0, 0) == CellStatus.Cross &&
						m_grid.getStatus(1, 1) == CellStatus.Cross &&
						m_grid.getStatus(2, 2) == CellStatus.Cross;
		
		if (diagonalLeftToRightP2 || diagonalRightToLeftP2)
		{
			m_winner = true;
			return Player.Player2;
		}
		
		return Player.None;
	}
	
	Grid getGrid()
	{
		return m_grid;
	}
}
