package com.zx.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    //资源管理类
//    日志
    private static final String TAG = "BeatBox";
//储存资源管理目录
    private static final String SOUNDS_FOLDER = "sample_sound";
    private static final int MAX_SOUND=5;
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        mSoundPool=new SoundPool(MAX_SOUND, AudioManager.STREAM_MUSIC,0);
        //参数分别表示最多同时播放音频个数，音频流类型，音量控制常量，采样率装换品质
        loadSounds();
    }
    //方便通过AssetManager获取assets，这里通过构造方法见下面
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);//AssetManager.list列出文件名
            Log.i(TAG, "Found " + soundNames.length + " sounds"+soundNames.toString());
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        for (String filename : soundNames) {

            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;//路径
                Sound sound = new Sound(assetPath);//
                load(sound);
                mSounds.add(sound);
            } catch (IOException e) {
                Log.e(TAG, "loadSounds: counld not load sound "+filename,e );
            }
        }
    }
public  void play(Sound sound){
        Integer soundId=sound.getSoundId();
        if(soundId==null){
            return;
        }
        mSoundPool.play(soundId,1.0f,1.0f,1,0,1.0f);
}
    private void load(Sound sound)throws IOException{
        AssetFileDescriptor afd=mAssets.openFd(sound.getAssetPath());
        int soundId=mSoundPool.load(afd,1);
        sound.setSoundId(soundId);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
