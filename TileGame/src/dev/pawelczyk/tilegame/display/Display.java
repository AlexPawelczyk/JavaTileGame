package dev.pawelczyk.tilegame.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	private Canvas canvas;
	private JFrame frame;
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		
		createDisplay();
	}
	
	private void createDisplay() {
		frame = new JFrame(this.title);							// initializes window
		frame.setSize(this.width, this.height);					// sets width and height of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// terminates program when window is closed
		frame.setResizable(false);  							// prevents user from resizing window
		frame.setLocationRelativeTo(null);  					// window appears at center of screen instead of side
		frame.setVisible(true); 								// makes the window visible on the screen
		
		canvas = new Canvas();									// initializes canvas
		canvas.setPreferredSize(new Dimension(width, height));	// sets width and height of canvas
		canvas.setMaximumSize(new Dimension(width, height));	// sets maximum canvas size to width and height
		canvas.setMinimumSize(new Dimension(width, height));	// sets minimum canvas size to width and height
		
		frame.add(canvas);										// add canvas to jframe
		frame.pack();											// resize our window so we can fully see canvas
			
	}
	
	public Canvas getCanvas() {
		return canvas;
	}

}
