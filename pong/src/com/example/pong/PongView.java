package com.example.pong;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongView extends SurfaceView implements SurfaceHolder.Callback {
	private PongThread _thread;

	public PongView(Context context, AttributeSet attrs) {
		super(context, attrs);

		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		setFocusable(true);

		// instantiate the thread
		_thread = new PongThread(holder, context, new Handler());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		return _thread.getPongState().keyPressed(keyCode, msg);
	}

	
	//surfaceholder interface methods
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override	
	public void surfaceCreated(SurfaceHolder holder) {
		_thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		_thread.stop();
	}
}