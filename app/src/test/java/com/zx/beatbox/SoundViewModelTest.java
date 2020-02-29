package com.zx.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import static org.mockito.Mockito.mock;

public class SoundViewModelTest {
private BeatBox mBeatBox;
private Sound mSound;
private SoundViewModel mSubject;//c测试对象
    @Before
    public void setUp() throws Exception {
        mBeatBox=mock(BeatBox.class);//创建虚拟beatbox
        mSound=new Sound("assetPath");
        mSubject=new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }
    @Test
    public  void exposesSoundNameAsTitle(){
        assertThat(mSubject.getTitle(),is(mSound.getName()));
    }
}