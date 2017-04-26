package com.clv.parttimejobs.entity.my.dailyattendance;

import java.io.IOException;

import com.clv.homework.R;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class Music {

	private MediaPlayer mMediaPlayer;
	
	public void playMusic(Context  context ){
		mMediaPlayer = MediaPlayer.create(context, R.raw.pull_event);
		try {
			mMediaPlayer.prepare();
			} catch (IllegalStateException e) {

			} catch (IOException e) {

			}

			mMediaPlayer.start();

			mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer arg0) {
					// TODO Auto-generated method stub
					mMediaPlayer.release();
				}
			});
	}
}
