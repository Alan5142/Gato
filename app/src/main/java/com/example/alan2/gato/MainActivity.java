package com.example.alan2.gato;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
	private Game m_game;
	private Button m_buttons[][];
	private TextView m_gameText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final MediaPlayer player = MediaPlayer.create(this, R.raw.miichannel);
		player.start();
		
		final MainActivity thisActivity = this;
		Button restartButton = findViewById(R.id.restart);
		restartButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				player.pause();
				thisActivity.recreate();
			}
		});
		
		m_game = new Game();
		m_buttons = new Button[3][3];
		
		m_buttons[0][0] = findViewById(R.id.col_1_fila_1);
		m_buttons[0][1] = findViewById(R.id.col_2_fila_1);
		m_buttons[0][2] = findViewById(R.id.col_3_fila_1);
		
		m_buttons[1][0] = findViewById(R.id.col_1_fila_2);
		m_buttons[1][1] = findViewById(R.id.col_2_fila_2);
		m_buttons[1][2] = findViewById(R.id.col_3_fila_2);
		
		m_buttons[2][0] = findViewById(R.id.col_1_fila_3);
		m_buttons[2][1] = findViewById(R.id.col_2_fila_3);
		m_buttons[2][2] = findViewById(R.id.col_3_fila_3);
		
		m_gameText = findViewById(R.id.state);
		
		m_gameText.setText("Turno: Circulo");
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				final int column = i;
				final int row = j;
				m_buttons[i][j].setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						if (!m_game.hasWinner())
						{
							onButtonClicked((Button) v, row, column);
						}
					}
				});
			}
		}
	}
	
	private void onButtonClicked(Button button, int row, int column)
	{
		if (m_game.move(row, column))
		{
			GameUI.setButton(button, m_game.getGrid().getStatus(row, column));
			m_gameText.setText(m_game.getTurn() == Turn.Circle ? "Turno: Circulo" : "Turno: Cruz");
			if (m_game.checkWinner() == Player.Player1)
			{
				m_gameText.setText("Ganador: Circulo");
			}
			else if (m_game.checkWinner() == Player.Player2)
			{
				m_gameText.setText("Ganador: Cruz");
			}
			if (m_game.isFilledWithNoWinner())
			{
				m_gameText.setText("¡Empate!");
			}
		}
		
	}
}
