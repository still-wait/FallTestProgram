package old.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.IBinder;
import android.util.Log;

import com.example.ygh.falltestprogram.R;

import java.util.HashMap;

public class JbSoundService extends Service{

	private SoundPool sp;
	private HashMap<Integer, Integer> spMap;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("---播放警报----", "---1111oncreate----");
	}

	@SuppressLint("UseSparseArrays")
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.e("---播放警报----", "---2222onStartCommand----");
		sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		spMap = new HashMap<Integer, Integer>();
		spMap.put(1, sp.load(this, R.raw.sound_sos, 1));
		Log.e("---播放警报----", "---#####----");
		playSounds(1, -1);

		return super.onStartCommand(intent, flags, startId);
	}

	public void playSounds(int sound, int number) {
		AudioManager am = (AudioManager) this
				.getSystemService(TsSoundService.AUDIO_SERVICE);
		float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float audioCurrentVolumn = am
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float volumnRatio = audioCurrentVolumn / audioMaxVolumn;
		sp.play(spMap.get(sound), volumnRatio, volumnRatio, 1, number, 1);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sp.stop(spMap.get(1));
		Log.e("---关闭警报---", "---已关闭-----");
	}

}
