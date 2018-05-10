package com.example.alan2.gato;

enum CellState
{
	Cross,
	Circle,
	None
}

public final class Grid
{
	private CellState[][] m_grid;
	
	/**
	 * Create an empty grid
	 */
	Grid()
	{
		m_grid = new CellState[3][3];
		for (CellState[] arreglo : m_grid)
		{
			for (int i = 0; i < 3; i++)
			{
				arreglo[i] = CellState.None;
			}
		}
	}
	
	/**
	 * Get the status of a cell
	 *
	 * @param row    which row?
	 * @param column which column?
	 * @return who the cell belongs to
	 * @throws IllegalArgumentException if row or column are less than 0 or more than 2
	 */
	public CellState getStatus(int row, int column) throws IllegalArgumentException
	{
		if (row >= 3 || row < 0 || column >= 3 || column < 0)
		{
			throw new IllegalArgumentException("x o y deben estar entre el rango [0, 3)");
		}
		return m_grid[row][column];
	}
	
	/**
	 * Set a Cell status which can represent if the cell was played by P1 or P2
	 *
	 * @param row      which row?
	 * @param column   which column?
	 * @param newState new state, it can be Cross, Circle or None, however, you shouldn't use None
	 * @throws IllegalArgumentException if row or column are less than 0 or more than 2 or if the cell already have owner
	 */
	public void setGridStatus(int row, int column, CellState newState) throws IllegalArgumentException
	{
		if (row >= 3 || row < 0 || column >= 3 || column < 0)
		{
			throw new IllegalArgumentException("x o y deben estar entre el rango [0, 3)");
		}
		if (m_grid[row][column] != CellState.None)
		{
			throw new IllegalArgumentException("el argumento" + row + "," + column + " ya tenía estado");
		}
		m_grid[row][column] = newState;
	}
}
