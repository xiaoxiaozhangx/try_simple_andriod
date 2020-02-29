package com.zx.beatbox;

import android.util.Log;

public class Sound {
    //管理文件名，用户应该看到的文件名
    private String mAssetPath;
    private String mName;//文件名
    private static final String TAG = "Sound";
    private Integer mSoundId;

    public Integer getSoundId() {
        return mSoundId;
    }

    public void setSoundId(Integer soundId) {
        mSoundId = soundId;
    }

    public Sound(String assetPath) {
        mAssetPath = assetPath;
//        Log.i(TAG, "Sound: "+mAssetPath);//sample_sound/65_cjipie.wav
        String[] components = assetPath.split("/");//分离出文件名
//        Log.i(TAG, "Sound: "+components);
        String filename = components[components.length - 1];//65_cjipie.wav
//        Log.i(TAG, "Sound: "+filename);
        mName = filename.replace(".wav", "");//删除wave后缀,65_cjipie
//        Log.i(TAG, "Sound: "+mName);
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }
}
