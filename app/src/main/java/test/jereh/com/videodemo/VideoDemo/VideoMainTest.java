package test.jereh.com.videodemo.VideoDemo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;

import java.util.ArrayList;
import java.util.List;

import test.jereh.com.videodemo.R;

public class VideoMainTest extends AppCompatActivity implements
        MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener {
    private VideoViewDemo videoView;
    private MediaController mMediaController;
    private int intPositionWhenPause=-1;//标记当视频暂停时播放位置
    private List<Uri> uriList=new ArrayList<>();
    private int current=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_main);
        videoView= (VideoViewDemo) findViewById(R.id.vip);

        uriList.add(Uri.parse("android.resource://test.jereh.com.videodemo/"+R.raw.aaa));
        uriList.add(Uri.parse("android.resource://test.jereh.com.videodemo/"+R.raw.bbb));
        uriList.add(Uri.parse("android.resource://test.jereh.com.videodemo/"+R.raw.ddd));

        videoView.setUriList(uriList);

        mMediaController= new MediaController(this);

        videoView.setMediaController(mMediaController);
        mMediaController.show(0);
        videoView.setOnCompletionListener(this);//设置播放完成以后的监听
        videoView.setOnPreparedListener(this);//设置在视频文件加载完毕以后的回调函数

        initVideoView();

    }

    private void initVideoView() {
        Uri uri=uriList.get(current);
        videoView.setVideoURI(uri);
        videoView.start();//开始

    }

    @Override
    protected void onStart() {
        super.onStart();
        //启动视频播放
        videoView.start();
        videoView.setFocusable(true);//获取焦点
    }

    @Override
    protected void onPause() {
        super.onPause();
        intPositionWhenPause=videoView.getCurrentPosition();//如果页面暂停则保存当前播放的位置，并暂停
        videoView.stopPlayback();//停止播放视频文件
    }

    @Override
    protected void onResume() { //从暂停中恢复播放
        super.onResume();
        if(intPositionWhenPause>=0){ //跳转到暂停时保存的位置
            videoView.seekTo(intPositionWhenPause);
            intPositionWhenPause=-1;//初始播放的位置
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {  //播放完成以后回调

    }

    @Override
    public void onPrepared(MediaPlayer mp) {  //视频文件加载完毕以后的回调函数

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null!=videoView){
            videoView=null;
        }
    }

}
