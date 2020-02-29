package com.zx.beatbox;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zx.beatbox.databinding.FragmentBeatBoxBinding;
import com.zx.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

public class BeatBoxFragment extends Fragment {
    private BeatBox mBeatBox;
    private static final String TAG = "BeatBoxFragment";

    public  static  BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       //实例化FragmentBeatBoxBinding
        FragmentBeatBoxBinding binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_beat_box, container, false);
        Log.i(TAG, "onCreateView: 35");
        //配置recyclerView
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));//mBeatBox.getSounds()返回list
        //数据绑定类返回整个布局视图，不用findViwById
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        mBeatBox = new BeatBox(getActivity());//相当于CrimeLab
    }

    private class SoundHolder extends RecyclerView.ViewHolder {
        private ListItemSoundBinding mBinding;

        private SoundHolder(ListItemSoundBinding binding) {//一般都是传入view 使用数据绑定类创建Item视图ListItemSoundBinding
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new SoundViewModel(mBeatBox));//实例化视图
        }

        public void bind(Sound sound) {
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();//刷新
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSounds;

        public SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

//由adapter创建 viewholder和要显示的视图
        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_sound, parent, false);
            return new SoundHolder(binding);
        }
//传入viewholder和位置，绑定数据
        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
