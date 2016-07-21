package test.jereh.com.videodemo.VideoDemo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

import test.jereh.com.videodemo.R;

public class PlayVideo extends Activity implements OnGestureListener,
		OnClickListener,// ����
		OnBufferingUpdateListener,// �����绺��������仯��ʱ����Ĳ����¼�
		OnCompletionListener,// ��ý����Դ�ڲ��ŵ�ʱ�򵽴��յ�ʱ����Ĳ����¼�
		MediaPlayer.OnPreparedListener, SurfaceHolder.Callback// �ص�����
{
	// ��Ƶ�ߺͿ�
	int videoWidth;
	int videoHeight;
	// ��ť
	ImageButton play;
	// ������ť
	ImageButton fastPlay;
	// ������˰�ť
	ImageButton fastBack;
	// ������
	LinearLayout layout_control;
	LinearLayout layout_prograss;
	LinearLayout videoBack;
	// ��������ý��
	MediaPlayer mediaPlayer;
	// ��ʾý��
	SurfaceView surView;
	// ��������SurfaceView
	SurfaceHolder surHolder;
	// ·��
	String path;
	// �Ƿ��ǲ���״̬
	boolean boTing = true;
	// ��ȡ���ŵ�λ��
	int num;
	// �����Ļ����
	int count;
	// ��һ�ε��
	int firClick;
	// �ڶ��ε��
	int secClick;
	// ͨ��flag�ж��Ƿ�ȫ��
	boolean flag;
	// ���Ž����
	SeekBar seekbar;
	// ��ʾʱ���齨
	TextView showTime;
	// �����ļ���ʱ��
	int minute;
	int second;
	// ��������
	int progress;
	// �߳̿���
	MyThread mt;
	// ��������
	//SeekBar sound;
	// �������
	int soundId;
	// ��ʾ����
	//TextView showSound;
	// ��ȡ�϶������
	int videoLength;
	boolean f = true;
	// ���ųߴ�
	// ��ť����ʱ��
	int hint = 5000;
	// ���ڽ�ȡ���ListViewλ��
	int position;
	// ��������
	Random random;
	// ��buttonFlag�жϰ�ť����
	boolean buttonFlag = true;
	// ��ʾ��Ƶ��ʱ��
	TextView allTime;
	//TextView distant;
	PopupWindow popuWindow;
	View view;
	boolean popFlag;
	
	final String strVideoPath = "/sdcard/Driving/video/�������ʶ����Ƶ��Ļ��.3gp";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.video);
		// �������
		view = this.getLayoutInflater().inflate(R.layout.popuwindow, null);
		// �õ����ֵ�ID
		videoBack = (LinearLayout) view.findViewById(R.id.videoback);
		// �������
		play = (ImageButton) view.findViewById(R.id.video_bu_bofang);
		layout_control = (LinearLayout) findViewById(R.id.layout_control);
		layout_prograss = (LinearLayout) findViewById(R.id.layout_prograss);
		seekbar = (SeekBar) view.findViewById(R.id.seekbar);
		showTime = (TextView) view.findViewById(R.id.showtime);
		fastPlay = (ImageButton) view.findViewById(R.id.fastplay);
		fastBack = (ImageButton) view.findViewById(R.id.fastback);
		//sound = (SeekBar) view.findViewById(R.id.sound);
		//showSound = (TextView) view.findViewById(R.id.showsound);
		surView = (SurfaceView) findViewById(R.id.surfaceview_1);
		allTime = (TextView) view.findViewById(R.id.alltime);
		//distant = (TextView) findViewById(R.id.distant);
		surHolder = surView.getHolder();
		popuWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		// ���ûص�����
		surHolder.addCallback(this);
		// ���÷��
		surHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		// bu_boFang.setVisibility(View.INVISIBLE);
		// ���ð������
		play.setOnClickListener(this);
		// ������
		fastPlay.setOnClickListener(this);
		// ���˰�ť����
		fastBack.setOnClickListener(this);

		// new��������
		random = new Random();
		// ��ȡ�������б��е����λ��
		// position = VideoList.position;

		/*
		 * try { // ��ȡShareActivity������ VideoList.context =
		 * createPackageContext("cn.com.iotek",
		 * Context.CONTEXT_IGNORE_SECURITY); VideoList.share =
		 * VideoList.context.getSharedPreferences( "setupadapter",
		 * VideoList.context.MODE_WORLD_READABLE); VideoList.editor =
		 * VideoList.share.edit(); } catch (NameNotFoundException e) {
		 * e.printStackTrace(); }
		 */
		// �Զ�ȡ����Ϣ�����ж�
		/*
		 * if (VideoList.share.getString("hinttime", "5��").equals("5��")) { hint
		 * = 5000; } if (VideoList.share.getString("hinttime",
		 * "5��").equals("10��")) { hint = 10000; } if
		 * (VideoList.share.getString("hinttime", "5��").equals("15��")) { hint =
		 * 15000; }
		 */
		// ����ȫ������
		surView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				// handler.sendEmptyMessageAtTime(0x11, 3000);

				if (MotionEvent.ACTION_DOWN == event.getAction()) {
					count++;
					if (count == 1) {
						new countClear().start();
						firClick = (int) System.currentTimeMillis();
						if (!popFlag) {
							// // // popuWindow = new PopupWindow(view,
							// LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
							popuWindow.showAtLocation(view, Gravity.BOTTOM, 0,
									0);
							popFlag = true;
							handler.removeMessages(0x11);
							handler.sendEmptyMessageDelayed(0x11, hint);
						}
						// ����layout��ʾ
						// if (!flag) {
						/*
						 * layout_control.setVisibility(View.VISIBLE);
						 * layout_prograss.setVisibility(View.VISIBLE);
						 */

						// ���surfaceView��ʱ3S������Ϣ
						/*
						 * handler.removeMessages(0x11);
						 * handler.sendEmptyMessageDelayed(0x11, hint);
						 */
						// }

					} else if (count == 2) {
						secClick = (int) System.currentTimeMillis();
						if (secClick - firClick < 1000) {
							flag = !flag;
							count = 0;
						}
						if (flag) {
							//distant.setHeight(0);
							surView.setLayoutParams(new LayoutParams(
									LayoutParams.MATCH_PARENT,
									LayoutParams.MATCH_PARENT));
							surHolder.setFixedSize(480, 760);
							if (!popFlag) {
								// popuWindow = new PopupWindow(view,
								// LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
								popuWindow.showAtLocation(view, Gravity.BOTTOM,
										0, 0);
								popFlag = true;
								handler.removeMessages(0x11);
								handler.sendEmptyMessageDelayed(0x11, hint);
							}
							// ��ʼ����
							// mePlayer.start();
						} else {
							/*
							 * if (VideoList.share.getString("size", "4:3")
							 * .equals("4:3")) {
							 */
							surView.setLayoutParams(new LayoutParams(
									LayoutParams.WRAP_CONTENT, 360));
							surHolder.setFixedSize(480, 760);
							//distant.setHeight(200);
							if (!popFlag) {
								// popuWindow = new PopupWindow(view,
								// LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
								popuWindow.showAtLocation(view, Gravity.BOTTOM,
										0, 0);
								popFlag = true;
								handler.removeMessages(0x11);
								handler.sendEmptyMessageDelayed(0x11, hint);
								// }
							}
							/*
							 * if (VideoList.share.getString("size", "4:3")
							 * .equals("16:9")) { surView.setLayoutParams(new
							 * LinearLayout.LayoutParams(
							 * LayoutParams.MATCH_PARENT, 270));
							 * surHolder.setFixedSize(480, 270);
							 * distant.setHeight(250); if (!popFlag) { //
							 * popuWindow = new PopupWindow(view, //
							 * LayoutParams
							 * .FILL_PARENT,LayoutParams.WRAP_CONTENT);
							 * popuWindow.showAtLocation(view, Gravity.BOTTOM,
							 * 0, 0); popFlag = true;
							 * handler.removeMessages(0x11);
							 * handler.sendEmptyMessageDelayed(0x11, hint); } }
							 */
						}
						count = 0;
						firClick = 0;
						secClick = 0;

					}
				}
				return true;
			}
		});
		// �����������
		seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
				videoLength = seekBar.getProgress();
				// int q = mediaPlayer.getCurrentPosition();
				mediaPlayer.seekTo(videoLength);
				// ��ȡ�������ǰ��λ��
				// int dest = seekBar.getProgress();
				// ���ò��������ڵĲ���λ��
				// mediaPlayer.seekTo(num);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// �õ�seekbar�Ľ��
				/*
				 * seekbar.setProgress(progress); videoLength =
				 * seekBar.getProgress();
				 */
			}
		});
		/*sound.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int i = seekBar.getProgress();
				mediaPlayer.setVolume(i / 100f, i / 100f);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				int i = sound.getProgress();
				showSound.setText("������" + i);
				handler.removeMessages(0x11);
				handler.sendEmptyMessageDelayed(0x11, hint);
			}
		});*/
	}

	private void playVideo() {

		//String strVideoPath = "/sdcard/Driving/video/�������ʶ����Ƶ��Ļ��.3gp";

		// ����MediaPlayer����
		mediaPlayer = new MediaPlayer();
		try {
			// ����ý���ļ�·��
			// mediaPlayer.setDataSource(VideoList.path);
			mediaPlayer.setDataSource(strVideoPath);
			// ����ͨ��SurfaceView����ʾ����
			mediaPlayer.setDisplay(surHolder);
			// ׼��
			mediaPlayer.prepare();
			// distant.setHeight(200);
			// �����¼�����
			mediaPlayer.setOnBufferingUpdateListener(this);
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			seekbar.setMax(mediaPlayer.getDuration());
			// ���õ�ǰ�����������
			//soundId = sound.getProgress();
			//mediaPlayer.setVolume(soundId, soundId);
			//showSound.setText("������" + soundId);
			mt = new MyThread();
			mt.start();
			handler.sendEmptyMessage(0x13);
			int n = mediaPlayer.getDuration();// ��ó���ʱ��
			seekbar.setMax(n);
			n = n / 1000;
			int m = n / 60;
			int h = m / 60;
			int s = n % 60;
			m = m % 60;
			allTime.setText(String.format("时间" + "%02d:%02d:%02d", h, m, s));

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// MediaPlayer.OnPreparedListener
	public void onCompletion(MediaPlayer mp) {
		handler.sendEmptyMessage(0x14);
	}

	// MediaPlayer.OnPreparedListener
	// ��ʱ��ȷ��player����Prepared״̬������start������ʵ�
	public void onPrepared(MediaPlayer mp) {
		// if (VideoList.share.getString("size", "4:3").equals("4:3")) {
		surView.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, 360));
		// ���ò�����Ƶ�Ŀ�Ⱥ͸߶�
		surHolder.setFixedSize(480, 360);
		
		// ��ʼ����
		mediaPlayer.start();
		// }
		/*
		 * if (VideoList.share.getString("size", "4:3").equals("16:9")) { //
		 * ���ò�����Ƶ�Ŀ�Ⱥ͸߶� surView.setLayoutParams(new LinearLayout.LayoutParams(
		 * LayoutParams.MATCH_PARENT, 270)); surHolder.setFixedSize(480, 270);
		 * // ��ʼ���� mediaPlayer.start(); }
		 */

	}

	public void surfaceCreated(SurfaceHolder holder) {
		playVideo();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null) {
			mediaPlayer.release();
			mediaPlayer = null;
			// �ر�cursor
			// VideoList.cursor.close();
			// �ر���ݿ�
			// VideoList.db.close();
			// �ı��߳�ѭ������
			f = false;
			handler.removeMessages(0x11);
			popuWindow.dismiss();
		}

	}

	public void onClick(View v) {
		if (v == play) {
			// ������ڲ���
			if (buttonFlag) {
				play.setImageResource(R.drawable.pause);
				mediaPlayer.pause();
				buttonFlag = false;
			} else {
				play.setImageResource(R.drawable.play);
				mediaPlayer.start();
				buttonFlag = true;
			}
			handler.removeMessages(0x11);
			handler.sendEmptyMessageDelayed(0x11, hint);
		}

		if (v == fastPlay) {
			int i = mediaPlayer.getCurrentPosition() + 5000;
			mediaPlayer.seekTo(i);
			seekbar.setProgress(i);
			handler.removeMessages(0x11);
			handler.sendEmptyMessageDelayed(0x11, hint);
		}
		if (v == fastBack) {
			int i = mediaPlayer.getCurrentPosition() - 5000;
			mediaPlayer.seekTo(i);
			seekbar.setProgress(i);
			handler.removeMessages(0x11);
			handler.sendEmptyMessageDelayed(0x11, hint);
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 0x11) {

				if (popuWindow.isShowing() && f) {
					popuWindow.dismiss();
					popFlag = false;
				}

				// //����������layout����
				/*
				 * layout_control.setVisibility(View.INVISIBLE);
				 * layout_prograss.setVisibility(View.INVISIBLE);
				 */
			}
			if (msg.what == 0x12) {
				if (mediaPlayer != null) {
					num = mediaPlayer.getCurrentPosition();
				}
				seekbar.setProgress(num);
				// Ϊʱ �� �븳ֵ
				num = num / 1000;
				int minute = num / 60;
				int hour = minute / 60;
				int second = num % 60;
				minute = minute % 60;
				/*
				 * if (mediaPlayer!=null) {
				 * seekbar.setProgress(mediaPlayer.getCurrentPosition()); }
				 */

				showTime.setText(String.format("���Ž�ȣ�" + "%02d:%02d:%02d",
						hour, minute, second));
				// handler.sendEmptyMessage(0x12);
			}
			if (msg.what == 0x14) {
				// if (VideoList.share.getString("playstyle", "˳�򲥷�").equals(
				// "����ѭ��")) {
				// �ͷŲ��ŵ���Ƶ
				mediaPlayer.release();
				// �α��ƶ������ListViewλ��
				// VideoList.cursor.moveToPosition(VideoList.position);
				// ����ݿ���ȡ��ListView�����·��
				// VideoList.path = VideoList.cursor.getString(1);
				// ������Ƶ
				playVideo();
				// }
				/*
				 * if (VideoList.share.getString("playstyle", "˳�򲥷�").equals(
				 * "˳�򲥷�")) { // �ͷŵ�ǰ��MediaPlayer mediaPlayer.release(); // �Լ�
				 * position++; if (position == VideoList.videoList.getCount()) {
				 * position = 0; } // �α������ƶ�
				 * VideoList.cursor.moveToPosition(position); // ȡ���ƶ������Ƶ·��
				 * VideoList.path = VideoList.cursor.getString(1); // ������Ƶ
				 * playVideo(); } if (VideoList.share.getString("playstyle",
				 * "˳�򲥷�").equals( "����")) { // �ͷŲ��ŵ���Ƶ mediaPlayer.release();
				 * int r = random.nextInt(VideoList.videoList.getCount() - 1);
				 * VideoList.cursor.moveToPosition(r);
				 * 
				 * // ����ݿ���ȡ��ListView�����·�� VideoList.path =
				 * VideoList.cursor.getString(1); // ������Ƶ playVideo(); }
				 */
			}

		};
	};

	class MyThread extends Thread {

		@Override
		public void run() {
			handler.sendEmptyMessageDelayed(0x11, hint);
			// if (mePlayer.getDuration()+"".equals("0") ) {

			while (f) {
				try {

					sleep(1000);
					handler.sendEmptyMessage(0x12);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// }

		}

	}

	class countClear extends Thread {
		@Override
		public void run() {
			try {
				sleep(1000);
				// ˯һ���count��0
				count = 0;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		// TODO Auto-generated method stub

	}

	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
}
