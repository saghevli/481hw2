package com.example.pong;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import android.view.KeyEvent;

public class PongState extends java.lang.Object {

	//screen width and height
	int screenWidth = 0;
	int screenHeight = 0;

	//The ball
	final int ballSize = 10;
	int ballX = 100;
	int ballY = 100;
	int ballV_x = 10;
	int ballV_y = 10;

	//The bats
	final int batLength = 150;
	final int batHeight = 10;
	final int topBatY = 0;
	final int bottomBatY = 960;
	final int batSpeed = 5;
	
	//set in draw
	int bottomBatX = 0;
	int topBatX = 0;
	boolean setup = false;
	int hitCount = 0;
	int maxHitCount = 0;
	
	private Context _context;
	
	public PongState(Context context) {
		_context = context;
		hitCount = 0;
	}

	//The update method
	public void update() {
		ballX += ballV_x;
		ballY += ballV_y;
		
		//Collisions with the bats
		if(ballX > topBatX && ballX < topBatX + batLength && ballY <= topBatY) {
			ballV_y *= -1;
			hitCount++;
			return;
		}
	
		if(ballX > bottomBatX && ballX < bottomBatX+batLength && ballY >= bottomBatY) {
			ballV_y *= -1;
			hitCount++;
			return;
		}
		
		//DEATH!
		if(ballY > screenHeight || ballY < 0) {
			ballX = screenWidth/2;
			ballY = screenHeight/2;
			if(hitCount > maxHitCount) {
				maxHitCount = hitCount;
			}
			hitCount = 0;
		}
	
		//Collisions with the sides
		if(ballX > screenWidth || ballX < 0) {
		     		ballV_x *= -1;     	
		}
		
		
		
	}
	
	public boolean keyPressed(int keyCode, KeyEvent msg)
	{
		if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT && topBatX <= screenWidth) { //left
			topBatX += batSpeed;
			bottomBatX -= batSpeed;
			adjustBats();
		}
	
		if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && topBatX >= 0) { //right
			topBatX -= batSpeed;
			bottomBatX += batSpeed;
			adjustBats();
		}
		
		return true;
	}
	
	public void adjustBats() {
		if (topBatX < 0) {
			topBatX = 0;
		}
		if(bottomBatX > screenWidth) {
			bottomBatX = screenWidth;
		}
	}

	//the draw method
	public void draw(Canvas canvas, Paint paint) {	
		if(!setup) {
			screenWidth = canvas.getWidth();
			screenHeight = canvas.getHeight();
			topBatX = (screenWidth/2) - (batLength / 2);
			bottomBatX = (screenWidth/2) - (batLength / 2);
			setup = true;
		}
		//Clear the screen
		canvas.drawRGB(25, 25, 112);
	
		//set the color
		paint.setARGB(200, 255, 255, 0);
		paint.setTextSize(25);
	
		//draw the ball
		//canvas.drawRect(new Rect(ballX,ballY,ballX + ballSize,ballY + ballSize), paint);
		Bitmap b=BitmapFactory.decodeResource(_context.getResources(), R.drawable.m30);
        canvas.drawBitmap(b, ballX, ballY, paint);
	
		//draw the bats
		canvas.drawRect(new Rect(topBatX, topBatY, topBatX + batLength, topBatY + batHeight), paint); //top bat
		canvas.drawRect(new Rect(bottomBatX, bottomBatY, bottomBatX + batLength, bottomBatY + batHeight), paint); //bottom bat
		
		//draw the score
		canvas.drawText("Hits: " + hitCount, 0, 20, paint);
		canvas.drawText("High Score: " + maxHitCount, 0, (screenHeight/15), paint);

	}
}