package com.example.alan2.gato;

import android.widget.Button;

public class GameUI
{
	public static void toUI(Grid gameGrid, Button buttons[][])
	{
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				buttons[i][j].setText(gameGrid.getStatus(i, j) == CellStatus.Circle ? "O" : "X");
			}
		}
	}
	
	public static void setButton(Button button, CellStatus state)
	{
		button.setText(state == CellStatus.Circle ? "O" : "X");
	}
}
